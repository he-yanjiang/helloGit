package com.sst.mapper;

import com.sst.bean.ShopCartDO;

import java.util.List;

public interface ShopCartDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopCartDO record);

    int insertSelective(ShopCartDO record);

    ShopCartDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopCartDO record);

    int updateByPrimaryKey(ShopCartDO record);

    List<ShopCartDO> getShopCartByUserId(Integer userId);
}