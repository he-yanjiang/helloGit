package com.sst.service;

import com.sst.error.BusinessException;
import com.sst.service.model.ShopCartModel;

import java.util.List;

public interface IShopCartService {

    List<ShopCartModel> getShopCartByUserId(Integer userId);

    int addShopCart(ShopCartModel shopCartModel) throws BusinessException;

    int deleteShopCart(Integer shopCart,Integer userId) throws BusinessException;

    int updateShopCart(ShopCartModel shopCartModel);
}
