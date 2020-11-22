package com.sst.mapper;

import com.sst.bean.GoodsDO;

import java.util.List;

public interface GoodsDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsDO record);

    int insertSelective(GoodsDO record);

    GoodsDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsDO record);

    int updateByPrimaryKey(GoodsDO record);

    List<GoodsDO> getGoodsListByOne(Integer category1Id);

    List<GoodsDO> getGoodsListByTwo(Integer category2Id);

    List<GoodsDO> getGoodsListByThree(Integer category3Id);
}