package com.sst.mapper;

import com.sst.bean.SpecificationDO;

import java.util.List;

public interface SpecificationDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecificationDO record);

    int insertSelective(SpecificationDO record);

    SpecificationDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecificationDO record);

    int updateByPrimaryKey(SpecificationDO record);

    List<SpecificationDO> getSpecByGoodsId(Integer id);
}