package com.sst.mapper;

import com.sst.bean.SkuSpecificationDO;

import java.util.List;

public interface SkuSpecificationDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkuSpecificationDO record);

    int insertSelective(SkuSpecificationDO record);

    SkuSpecificationDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkuSpecificationDO record);

    int updateByPrimaryKey(SkuSpecificationDO record);

    List<SkuSpecificationDO> getSpecificationByItemId(Integer id);
}