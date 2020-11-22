package com.sst.service.impl;

import com.sst.bean.GoodsCategoryDO;
import com.sst.error.BusinessException;
import com.sst.error.EmBusinessError;
import com.sst.mapper.GoodsCategoryMapper;
import com.sst.service.IGoodsCategoryService;
import com.sst.service.model.GoodsCategoryModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;
    //添加商品类目
    @Override
    public boolean creatGoodsCategory(GoodsCategoryModel goodsCategoryModel) throws BusinessException {
        GoodsCategoryDO goodsCategoryDO = this.convertDOFromModel(goodsCategoryModel);
        if (goodsCategoryDO == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        int affectedRow = goodsCategoryMapper.insertSelective(goodsCategoryDO);
        if (affectedRow>0){
            return true;
        }
        return false;
    }

    //查询一级分类
    @Override
    public List<GoodsCategoryModel> getOnePrimary(){
        List<GoodsCategoryDO> goodsCategoryDOList = goodsCategoryMapper.selectByParent(0);
        return convertModelListFromDOList(goodsCategoryDOList);
    }

    //查询下级分类
    @Override
    public List<GoodsCategoryModel> getSubdirectories(Integer id){
        List<GoodsCategoryDO> goodsCategoryDOList = goodsCategoryMapper.selectByParent(id);
        return convertModelListFromDOList(goodsCategoryDOList);
    }


    //用户端获取整个分类
    @Override
    @Cacheable(value = "goodsCategory")
    public List<GoodsCategoryModel> getGoodsCategoryAll(){
        List<GoodsCategoryDO> goodsCategoryDOList = goodsCategoryMapper.selectByParent(0);
        List<GoodsCategoryModel> goodsCategoryModelList = goodsCategoryDOList.stream().map(goodsCategoryDO -> {
            GoodsCategoryModel goodsCategoryModel = this.convertModelFromDO(goodsCategoryDO);
            List<GoodsCategoryDO> goodsCategoryDOList2 = goodsCategoryMapper.selectByParent(goodsCategoryDO.getId());
            goodsCategoryModel.setGoodsCategoryModelList(
                    goodsCategoryDOList2.stream().map(goodsCategoryDO2 -> {
                        GoodsCategoryModel goodsCategoryModel2 = this.convertModelFromDO(goodsCategoryDO2);
                        List<GoodsCategoryDO> goodsCategoryDOList3 = goodsCategoryMapper.selectByParent(goodsCategoryDO2.getId());
                        goodsCategoryModel2.setGoodsCategoryModelList(
                                goodsCategoryDOList3.stream().map(goodsCategoryDO3 -> {
                                    GoodsCategoryModel goodsCategoryModel3 = this.convertModelFromDO(goodsCategoryDO3);
                                    return goodsCategoryModel3;
                                }).collect(Collectors.toList())
                        );
                        return goodsCategoryModel2;
                    }).collect(Collectors.toList()));
            return goodsCategoryModel;
        }).collect(Collectors.toList());
        return goodsCategoryModelList;
    }

    //修改商品类目
    @Override
    @CacheEvict(value = "goodsCategory")
    public boolean updateGoodsCategoryById(GoodsCategoryModel goodsCategoryModel) throws BusinessException {
        GoodsCategoryDO goodsCategoryDO = this.convertDOFromModel(goodsCategoryModel);
        if (goodsCategoryDO == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        int affectedRow = goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategoryDO);
        if (affectedRow>0){
            return true;
        }
        return false;
    }

    //删除商品类目
    @Override
    @CacheEvict(value = "goodsCategory")
    public boolean deleteGoodsCategoryById(Integer id) throws BusinessException {
        if (id == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        int affectedRow = goodsCategoryMapper.deleteByPrimaryKey(id);
        if (affectedRow>0){
            return true;
        }
        return false;
    }

    private GoodsCategoryDO convertDOFromModel(GoodsCategoryModel goodsCategoryModel){
        if(goodsCategoryModel == null){
            return null;
        }
        GoodsCategoryDO goodsCategoryDO = new GoodsCategoryDO();
        BeanUtils.copyProperties(goodsCategoryModel,goodsCategoryDO);
        return goodsCategoryDO;
    }

    private GoodsCategoryModel convertModelFromDO(GoodsCategoryDO goodsCategoryDO){
        if(goodsCategoryDO == null){
            return null;
        }
        GoodsCategoryModel goodsCategoryModel = new GoodsCategoryModel();
        BeanUtils.copyProperties(goodsCategoryDO,goodsCategoryModel);
        return goodsCategoryModel;
    }

    //把DOList转换未ModelList
    private List<GoodsCategoryModel> convertModelListFromDOList(List<GoodsCategoryDO> goodsCategoryDOList){
        List<GoodsCategoryModel> goodsCategoryModelList = goodsCategoryDOList.stream().map(goodsCategoryDO -> {
            GoodsCategoryModel goodsCategoryModel = this.convertModelFromDO(goodsCategoryDO);
            return goodsCategoryModel;
        }).collect(Collectors.toList());
        return goodsCategoryModelList;
    }
}
