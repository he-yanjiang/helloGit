package com.sst.mapper;

import com.sst.bean.GoodsCategoryDO;

import java.util.List;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsCategoryDO record);

    int insertSelective(GoodsCategoryDO record);

    GoodsCategoryDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsCategoryDO record);

    int updateByPrimaryKey(GoodsCategoryDO record);

    List<GoodsCategoryDO> selectByParent(Integer id);
}