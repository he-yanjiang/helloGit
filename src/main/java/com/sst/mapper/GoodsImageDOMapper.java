package com.sst.mapper;

import com.sst.bean.GoodsImageDO;

import java.util.List;

public interface GoodsImageDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsImageDO record);

    int insertSelective(GoodsImageDO record);

    GoodsImageDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsImageDO record);

    int updateByPrimaryKey(GoodsImageDO record);

    List<GoodsImageDO> getImageByGoodsId(Integer id);
}