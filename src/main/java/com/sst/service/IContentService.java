package com.sst.service;

import com.sst.bean.ContentCategoryDO;
import com.sst.service.model.ContentModel;

import java.util.List;

public interface IContentService {
    //获取广告分类
    List<ContentCategoryDO> getContentCategory();

    //首页获取广告分类及类容
    List<ContentModel> getContentAndCategory();
}
