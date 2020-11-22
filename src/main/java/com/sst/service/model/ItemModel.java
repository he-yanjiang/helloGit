package com.sst.service.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ItemModel implements Serializable {
    private Integer id;

    private String title;

    private BigDecimal price;
    //库存
    private Integer stock;
    //图片
    private String defaultImage;
    //是否启用
    private Integer status;

    private Date crateTime;

    private Date updateTime;
    //是否默认
    private Integer isDefault;
    //spuId
    private Integer goodsId;
    //sku详情
    private List spec;

    //private List<SkuSpecificationDO> skuSpecificationDOList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public List getSpec() {
        return spec;
    }

    public void setSpec(List spec) {
        this.spec = spec;
    }

    /*    public List<SkuSpecificationDO> getSkuSpecificationDOList() {
        return skuSpecificationDOList;
    }

    public void setSkuSpecificationDOList(List<SkuSpecificationDO> skuSpecificationDOList) {
        this.skuSpecificationDOList = skuSpecificationDOList;
    }*/
}
