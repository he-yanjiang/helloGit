package com.sst.mapper;

import com.sst.bean.ContentCategoryDO;

import java.util.List;

public interface ContentCategoryDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContentCategoryDO record);

    int insertSelective(ContentCategoryDO record);

    ContentCategoryDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContentCategoryDO record);

    int updateByPrimaryKey(ContentCategoryDO record);

    List<ContentCategoryDO> selectContentCategoryAll();
}