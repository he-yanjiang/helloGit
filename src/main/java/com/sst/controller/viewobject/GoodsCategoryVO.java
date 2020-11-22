package com.sst.controller.viewobject;

import com.sst.bean.GoodsCategoryDO;

import java.util.List;

public class GoodsCategoryVO {
    private Integer id;

    private String name;

    private Integer parent;

    private String url;

    private Integer sequence;

    private List<GoodsCategoryDO> goodsCategoryDOList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public List<GoodsCategoryDO> getGoodsCategoryDOList() {
        return goodsCategoryDOList;
    }

    public void setGoodsCategoryDOList(List<GoodsCategoryDO> goodsCategoryDOList) {
        this.goodsCategoryDOList = goodsCategoryDOList;
    }
}
