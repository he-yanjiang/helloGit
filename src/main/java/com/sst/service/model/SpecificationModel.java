package com.sst.service.model;

import com.sst.bean.SpecificationOptionDO;

import java.io.Serializable;
import java.util.List;

public class SpecificationModel implements Serializable {
    private Integer id;

    private Integer goodsId;

    private String name;

    private List<SpecificationOptionDO> specificationOptionDOList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpecificationOptionDO> getSpecificationOptionDOList() {
        return specificationOptionDOList;
    }

    public void setSpecificationOptionDOList(List<SpecificationOptionDO> specificationOptionDOList) {
        this.specificationOptionDOList = specificationOptionDOList;
    }
}
