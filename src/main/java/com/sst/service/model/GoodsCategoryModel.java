package com.sst.service.model;

import java.io.Serializable;
import java.util.List;

public class GoodsCategoryModel implements Serializable {
    private Integer id;

    private String name;

    private Integer parent;

    private String url;

    private Integer sequence;

    private List<GoodsCategoryModel> goodsCategoryModelList;

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

    public List<GoodsCategoryModel> getGoodsCategoryModelList() {
        return goodsCategoryModelList;
    }

    public void setGoodsCategoryModelList(List<GoodsCategoryModel> goodsCategoryModelList) {
        this.goodsCategoryModelList = goodsCategoryModelList;
    }
}
