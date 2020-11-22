package com.sst.service;

import com.sst.service.model.GoodsModel;

import java.util.List;

public interface IGoodsService {
    //通过一级分类获取商品列表
    List<GoodsModel> getGoodsListByOne(Integer category1Id);

    //通过二级分类获取商品列表
    List<GoodsModel> getGoodsListByTwo(Integer category2Id);

    //通过三级分类获取商品列表
    List<GoodsModel> getGoodsListByThree(Integer category3Id);

    //获取商品详细信息
    GoodsModel getGoodsDetails(Integer goodsId);
}
