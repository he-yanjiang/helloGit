package com.sst.service.impl;

import com.sst.bean.ContentCategoryDO;
import com.sst.mapper.ContentCategoryDOMapper;
import com.sst.mapper.ContentDOMapper;
import com.sst.service.IContentService;
import com.sst.service.model.ContentModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements IContentService {
    @Autowired
    ContentCategoryDOMapper contentCategoryDOMapper;
    @Autowired
    ContentDOMapper contentDOMapper;

    //获取广告分类
    @Override
    public List<ContentCategoryDO> getContentCategory(){
        List<ContentCategoryDO> contentCategoryDOList = contentCategoryDOMapper.selectContentCategoryAll();
        return contentCategoryDOList;
    }

    //首页获取广告分类及类容
    @Override
    @Cacheable(value = "content")
    public List<ContentModel> getContentAndCategory(){
        List<ContentCategoryDO> contentCategoryDOList = contentCategoryDOMapper.selectContentCategoryAll();
        List<ContentModel> contentModelList = contentCategoryDOList.stream().map(contentCategoryDO -> {
            ContentModel contentModel = this.convertModelFromDO(contentCategoryDO);
            return contentModel;
        }).collect(Collectors.toList());
        return contentModelList;

    }

    //将ContentCategoryDO转换为ContentModel
    private ContentModel convertModelFromDO(ContentCategoryDO contentCategoryDO){
        ContentModel contentModel = new ContentModel();
        BeanUtils.copyProperties(contentCategoryDO,contentModel);
        contentModel.setContentDOList(contentDOMapper.getContentByCategoryId(contentCategoryDO.getId()));
        return contentModel;
    }





}
