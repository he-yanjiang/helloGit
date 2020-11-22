package com.sst.controller;

import com.sst.response.CommonReturnType;
import com.sst.service.IContentService;
import com.sst.service.IGoodsCategoryService;
import com.sst.service.IGoodsService;
import com.sst.service.model.ContentModel;
import com.sst.service.model.GoodsCategoryModel;
import com.sst.service.model.GoodsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("goods")
@RequestMapping("/goods")
@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials = "true",allowedHeaders = "*")
public class GoodsController extends BaesController{
    @Autowired
    IGoodsCategoryService iGoodsCategoryService;
    @Autowired
    IContentService iContentService;
    @Autowired
    IGoodsService iGoodsService;

    @RequestMapping("/getGoodsOnePrimary")
    public CommonReturnType getGoodsOnePrimary(){
        List<GoodsCategoryModel> onePrimaryList = iGoodsCategoryService.getOnePrimary();
        return CommonReturnType.create(onePrimaryList);
    }

    @RequestMapping("/getSubdirectories")
    public CommonReturnType getSubdirectories(@RequestParam("id")Integer id){
        List<GoodsCategoryModel> goodsCategoryModelList = iGoodsCategoryService.getSubdirectories(id);
        System.out.println(goodsCategoryModelList.stream().count());

        return CommonReturnType.create(goodsCategoryModelList);
    }

    @RequestMapping("/getGoodsCategoryAll")
    public CommonReturnType getGoodsCategoryAll(){
        List<GoodsCategoryModel> onePrimaryList = iGoodsCategoryService.getGoodsCategoryAll();
        return CommonReturnType.create(onePrimaryList);
    }

    //获取广告分类及类容
    @RequestMapping("/getContent")
    public CommonReturnType getContent(){
        List<ContentModel> contentModels = iContentService.getContentAndCategory();
        return CommonReturnType.create(contentModels);
    }
    //获取商品详细信息
    @RequestMapping("/getGoodsDetails")
    public CommonReturnType getGoodsDetails(@RequestParam("goodsId") Integer goodsId){
        GoodsModel goodsDetails = iGoodsService.getGoodsDetails(goodsId);
        return CommonReturnType.create(goodsDetails);
    }

    //通过一级分类获取商品列表
    @RequestMapping("/getGoodsListByOne")
    public CommonReturnType  getGoodsListByOne(@RequestParam("categoryId") Integer categoryId){
        List<GoodsModel>  goodsModelList= iGoodsService.getGoodsListByOne(categoryId);
        return CommonReturnType.create(goodsModelList);
    }

    //通过二级分类获取商品列表
    @RequestMapping("/getGoodsListByTwo")
    public CommonReturnType  getGoodsListByTwo(@RequestParam("categoryId") Integer categoryId){
        List<GoodsModel>  goodsModelList= iGoodsService.getGoodsListByTwo(categoryId);
        return CommonReturnType.create(goodsModelList);
    }

    //通过三级分类获取商品列表
    @RequestMapping("/getGoodsListByThree")
    public CommonReturnType  getGoodsListByThree(@RequestParam("categoryId") Integer categoryId){
        List<GoodsModel>  goodsModelList= iGoodsService.getGoodsListByThree(categoryId);
        return CommonReturnType.create(goodsModelList);
    }


}
