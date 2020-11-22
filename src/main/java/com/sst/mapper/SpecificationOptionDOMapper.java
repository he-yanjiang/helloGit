package com.sst.mapper;

import com.sst.bean.SpecificationOptionDO;

import java.util.List;

public interface SpecificationOptionDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecificationOptionDO record);

    int insertSelective(SpecificationOptionDO record);

    SpecificationOptionDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecificationOptionDO record);

    int updateByPrimaryKey(SpecificationOptionDO record);

    List<SpecificationOptionDO> getBySpecId(Integer id);
}