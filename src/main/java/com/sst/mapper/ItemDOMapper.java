package com.sst.mapper;

import com.sst.bean.ItemDO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemDO record);

    int insertSelective(ItemDO record);

    ItemDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKey(ItemDO record);

    List<ItemDO> getItemByGoodsId(Integer id);
}