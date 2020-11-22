package com.sst.mapper;

import com.sst.bean.ContentDO;

import java.util.List;

public interface ContentDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContentDO record);

    int insertSelective(ContentDO record);

    ContentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContentDO record);

    int updateByPrimaryKey(ContentDO record);

    List<ContentDO> getContentByCategoryId(Integer categoryId);
}