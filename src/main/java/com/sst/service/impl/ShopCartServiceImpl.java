package com.sst.service.impl;

import com.sst.bean.ItemDO;
import com.sst.bean.ShopCartDO;
import com.sst.mapper.ItemDOMapper;
import com.sst.mapper.ShopCartDOMapper;
import com.sst.service.IShopCartService;
import com.sst.service.model.ShopCartModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopCartServiceImpl implements IShopCartService {
    @Autowired
    private ShopCartDOMapper shopCartDOMapper;
    @Autowired
    private ItemDOMapper itemDOMapper;

    @Cacheable(value = "shopCart",key = "'shopCartByUser'+#userId")
    @Override
    public List<ShopCartModel> getShopCartByUserId(Integer userId){
        List<ShopCartDO> shopCartDOList = shopCartDOMapper.getShopCartByUserId(userId);
        List<ShopCartModel> shopCartModelList = shopCartDOList.stream().map(shopCartDO -> {
            ShopCartModel shopCartModel = this.convertCartModelFromCartDO(shopCartDO);
            return shopCartModel;
        }).collect(Collectors.toList());
        return shopCartModelList;
    }

    @Override
    public int addShopCart(ShopCartModel shopCartModel){
        ShopCartDO shopCartDO = this.convertCartDOFromCartModel(shopCartModel);
        int validation;
        if (this.validationShopCart(shopCartDO)!=null){
            validation = updateShopCart(this.validationShopCart(shopCartDO));

        }else {
            validation = shopCartDOMapper.insertSelective(shopCartDO);
        }
        return validation;
    }

    private ShopCartModel validationShopCart(ShopCartDO shopCartDO) {
        List<ShopCartModel> shopCartModelList = getShopCartByUserId(shopCartDO.getUserId());
        for (ShopCartModel shopCartModel2:shopCartModelList) {
            if (shopCartModel2.getItemId()==shopCartDO.getItemId()){
                shopCartModel2.setAmount(shopCartDO.getAmount()+shopCartModel2.getAmount());
                return shopCartModel2;
            }
        }
        return null;
    }


    @CacheEvict(value = "shopCart",key = "'shopCartByUser'+#userId")
    @Override
    public int deleteShopCart(Integer shopCartId,Integer userId){
        int validation = shopCartDOMapper.deleteByPrimaryKey(shopCartId);
        return validation;
    }

    @CacheEvict(value = "shopCart",key = "'shopCartByUser'+#userId")
    @Override
    public int updateShopCart(ShopCartModel shopCartModel){
        int validation = shopCartDOMapper.updateByPrimaryKeySelective(this.convertCartDOFromCartModel(shopCartModel));
        return validation;
    }

    private ShopCartModel convertCartModelFromCartDO(ShopCartDO shopCartDO){
        ShopCartModel shopCartModel = new ShopCartModel();
        BeanUtils.copyProperties(shopCartDO,shopCartModel);
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(shopCartModel.getItemId());
        shopCartModel.setGoodsId(itemDO.getGoodsId());
        shopCartModel.setTitle(itemDO.getTitle());
        shopCartModel.setImage(itemDO.getDefaultImage());
        shopCartModel.setPrice(itemDO.getPrice().multiply(new BigDecimal(shopCartModel.getAmount())));
        shopCartModel.setSpec(itemDO.getSpec());
        return shopCartModel;
    }

    private ShopCartDO convertCartDOFromCartModel(ShopCartModel shopCartModel){
        ShopCartDO shopCartDO = new ShopCartDO();
        BeanUtils.copyProperties(shopCartModel,shopCartDO);
        return shopCartDO;
    }

}
