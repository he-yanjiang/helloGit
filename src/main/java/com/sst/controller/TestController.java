package com.sst.controller;

import com.sst.bean.ContentCategoryDO;
import com.sst.error.BusinessException;
import com.sst.service.*;
import com.sst.service.model.GoodsCategoryModel;
import com.sst.service.model.GoodsModel;
import com.sst.service.model.ShopCartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("test")
@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials = "true",allowedHeaders = "*")
public class TestController extends BaesController{
    @Autowired
    IContentService iContentService;
    @Autowired
    IGoodsService iGoodsService;
    @Autowired
    IGoodsCategoryService iGoodsCategoryService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IShopCartService iShopCartService;

    @RequestMapping("/test1")
    public List<ContentCategoryDO> test1(){
        List<ContentCategoryDO> contentCategory = iContentService.getContentCategory();
        return contentCategory;
    }
    @RequestMapping("/test2")
    public List<GoodsModel> test2(){
        List<GoodsModel> goodsModelList = iGoodsService.getGoodsListByOne(2 );
        return goodsModelList;
    }

    @RequestMapping("/test3")
    public List<GoodsCategoryModel> getGoodsCategoryAll(){
        List<GoodsCategoryModel> onePrimaryList = iGoodsCategoryService.getGoodsCategoryAll();
        return onePrimaryList;
    }

    @RequestMapping("/test4")
    public GoodsModel test4(){
        GoodsModel goodsModel = iGoodsService.getGoodsDetails(2);
        return goodsModel;
    }

    @RequestMapping("/test5")
    public String test5(){
        String token = "";
        try {
            token = iUserService.loginByPassword("15281022297","4QrcOUm6Wau+VuBX8g+IPg==");
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return token;
    }

    @RequestMapping("/test6")
    public List<ShopCartModel> test6(){
        List<ShopCartModel> shopCartByUserId = iShopCartService.getShopCartByUserId(7);
        return shopCartByUserId;
    }
    @RequestMapping("test7")
    public String test7() throws BusinessException {
        iShopCartService.deleteShopCart(5,7);
        //List<ShopCartModel> shopCartByUserId = iShopCartService.getShopCartByUserId(7);
        return "成功";
    }

    @RequestMapping("test8")
    public String test8() throws Exception {
        iUserService.getOtp("15281022297");
        return "成功1";
    }
    @RequestMapping("test9")
    public String test9() {
        String a = null;
        a.equals("123");
        return a;
    }
    //接收时间格式参数
    @RequestMapping("test10")
    public String test10(@DateTimeFormat(pattern = "yyyy-MM-dd")Date time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time2 = format.format(time);
        System.out.println(time2);
        return time2;
    }
}
