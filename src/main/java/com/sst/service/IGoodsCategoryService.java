package com.sst.service;

import com.sst.error.BusinessException;
import com.sst.service.model.GoodsCategoryModel;

import java.util.List;

public interface IGoodsCategoryService {
    //添加商品类目
    boolean creatGoodsCategory(GoodsCategoryModel goodsCategoryModel) throws BusinessException;

    //查询一级分类
    List<GoodsCategoryModel> getOnePrimary();

    //查询下级分类
    List<GoodsCategoryModel> getSubdirectories(Integer id);

    //用户端获取整个分类
    List<GoodsCategoryModel> getGoodsCategoryAll();

    //修改商品类目
    boolean updateGoodsCategoryById(GoodsCategoryModel goodsCategoryModel) throws BusinessException;

    //删除商品类目
    boolean deleteGoodsCategoryById(Integer id) throws BusinessException;
}
