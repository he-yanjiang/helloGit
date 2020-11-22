package com.sst.service.impl;

import com.sst.bean.GoodsDO;
import com.sst.bean.ItemDO;
import com.sst.bean.SpecificationDO;
import com.sst.mapper.*;
import com.sst.service.IGoodsService;
import com.sst.service.model.GoodsModel;
import com.sst.service.model.ItemModel;
import com.sst.service.model.SpecificationModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    GoodsDOMapper goodsDOMapper;
    @Autowired
    ItemDOMapper itemDOMapper;
    @Autowired
    GoodsImageDOMapper goodsImageDOMapper;
    @Autowired
    SpecificationDOMapper specificationDOMapper;
    @Autowired
    SpecificationOptionDOMapper specificationOptionDOMapper;
    @Autowired
    SkuSpecificationDOMapper skuSpecificationDOMapper;

    //通过一级分类获取商品列表
    @Override
    public List<GoodsModel> getGoodsListByOne(Integer category1Id){
        List<GoodsDO> goodsDOList = goodsDOMapper.getGoodsListByOne(category1Id);
        return this.convertGoodsModelListFromGoodsDOList(goodsDOList);
    }

    //通过二级分类获取商品列表
    @Override
    public List<GoodsModel> getGoodsListByTwo(Integer category2Id){
        List<GoodsDO> goodsDOList = goodsDOMapper.getGoodsListByTwo(category2Id);
        return this.convertGoodsModelListFromGoodsDOList(goodsDOList);
    }

    //通过三级分类获取商品列表
    @Override
    public List<GoodsModel> getGoodsListByThree(Integer category3Id){
        List<GoodsDO> goodsDOList = goodsDOMapper.getGoodsListByThree(category3Id);
        return this.convertGoodsModelListFromGoodsDOList(goodsDOList);
    }

    //获取商品详细信息
    @Override
    public GoodsModel getGoodsDetails(Integer goodsId){
        GoodsModel goodsModel = this.convertGoodsModelFromGoodsDO(goodsDOMapper.selectByPrimaryKey(goodsId));
        List<ItemModel> itemModelList = this.convertItemModelListFromItemDOList(itemDOMapper.getItemByGoodsId(goodsModel.getId()));
        goodsModel.setItemModelList(itemModelList);
        goodsModel.setGoodsImageDOList(goodsImageDOMapper.getImageByGoodsId(goodsModel.getId()));
        goodsModel.setSpecificationModelList(this.convertSpecModelListFromSpecDOList(specificationDOMapper.getSpecByGoodsId(goodsModel.getId())));
        return goodsModel;
    }

    //将GoodsDOList转换成GoodsModelList
    private List<GoodsModel> convertGoodsModelListFromGoodsDOList(List<GoodsDO> goodsDOList){
        List<GoodsModel> goodsModelList = goodsDOList.stream().map(goodsDO -> {
            GoodsModel goodsModel = this.convertGoodsModelFromGoodsDO(goodsDO);
            return goodsModel;
        }).collect(Collectors.toList());
        return goodsModelList;
    }

    //将specificationDOList转换成SpecificationModelList
    private List<SpecificationModel> convertSpecModelListFromSpecDOList(List<SpecificationDO> specificationDOList){
        List<SpecificationModel> specificationModelList = specificationDOList.stream().map(specificationDO -> {
            SpecificationModel specificationModel = this.convertSpecificationModelFromSpecificationDO(specificationDO);
            specificationModel.setSpecificationOptionDOList(specificationOptionDOMapper.getBySpecId(specificationModel.getId()));
            return specificationModel;
        }).collect(Collectors.toList());
        return specificationModelList;
    }

    private SpecificationModel convertSpecificationModelFromSpecificationDO(SpecificationDO specificationDO) {
        SpecificationModel specificationModel = new SpecificationModel();
        BeanUtils.copyProperties(specificationDO,specificationModel);
        return specificationModel;
    }

    //将ItemDOList转换成ItemModelList
    private List<ItemModel> convertItemModelListFromItemDOList(List<ItemDO> itemDOList){
        List<ItemModel> itemModelList = itemDOList.stream().map(itemDO -> {
            ItemModel itemModel = this.convertItemModelFromItemDO(itemDO);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    private GoodsModel convertGoodsModelFromGoodsDO(GoodsDO goodsDO){
        GoodsModel goodsModel = new GoodsModel();
        BeanUtils.copyProperties(goodsDO,goodsModel);
        return goodsModel;
    }

    private ItemModel convertItemModelFromItemDO(ItemDO itemDO){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO,itemModel);
        String[] spec = itemDO.getSpec().split(",");
        List resultList = new ArrayList<>(spec.length);
        for (int i = 0; i<spec.length;i++){
            resultList.add(spec[i]);
        }
        itemModel.setSpec(resultList);
        //itemModel.setSkuSpecificationDOList(skuSpecificationDOMapper.getSpecificationByItemId(itemDO.getId()));
        return itemModel;
    }



}
