package com.sst.controller;

import com.sst.bean.UserDO;
import com.sst.error.BusinessException;
import com.sst.error.EmBusinessError;
import com.sst.response.CommonReturnType;
import com.sst.service.IShopCartService;
import com.sst.service.IUserService;
import com.sst.service.model.ShopCartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials = "true",allowedHeaders = "*")
public class ShopController extends BaesController{

    @Autowired
    private IShopCartService iShopCartService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping("/addItemToCart")
    public CommonReturnType addItemToCart(@RequestBody ShopCartModel shopCartModel) throws BusinessException {
        int validation = iShopCartService.addShopCart(shopCartModel);
        if (validation!=1){
            throw new BusinessException(EmBusinessError.CART_UPDATE_FAILED);
        }
        return CommonReturnType.create(null);
    }

    @RequestMapping("/deleteItemToCart")
    public CommonReturnType deleteItemToCart(@RequestParam("shopCartId") Integer shopCartId,@RequestHeader("Authorization") String token) throws BusinessException {
        UserDO userDO = iUserService.getUserByToken(token);
        int validation = iShopCartService.deleteShopCart(shopCartId,userDO.getId());
        if (validation!=1){
            throw new BusinessException(EmBusinessError.CART_UPDATE_FAILED);
        }
        return CommonReturnType.create(null);
    }

    @RequestMapping("/getCart")
    public CommonReturnType getCartByUserId(@RequestHeader("Authorization") String token) throws BusinessException {
        UserDO userDO = iUserService.getUserByToken(token);
        List<ShopCartModel> shopCartModelList = iShopCartService.getShopCartByUserId(userDO.getId());
        return CommonReturnType.create(shopCartModelList);
    }

    @RequestMapping("/UpdateItemToCart")
    public CommonReturnType UpdateItemToCart(@RequestBody ShopCartModel shopCartModel) throws BusinessException {
        int validation = iShopCartService.updateShopCart(shopCartModel);
        if (validation!=1){
            throw new BusinessException(EmBusinessError.CART_UPDATE_FAILED);
        }
        return CommonReturnType.create(null);
    }

}
