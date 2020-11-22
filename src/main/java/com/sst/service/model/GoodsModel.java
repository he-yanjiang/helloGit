package com.sst.service.model;

import com.sst.bean.GoodsImageDO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GoodsModel implements Serializable {
    private Integer id;

    private String goodsName;

    //商品状态 审核
    private Integer auditStatus;
    //是否显示
    private Integer isMarketable;
    //副标题
    private String caption;
    //一级分类
    private Integer category1Id;
    //二级分类
    private Integer category2Id;
    //三级分类
    private Integer category3Id;
    //默认图片
    private String smallPic;
    //默认价格
    private BigDecimal price;
    //商品描述
    private String introduction;
    //包装列表
    private String packageList;
    //售后服务
    private String saleService;
    //商品规格
    private List<SpecificationModel> specificationModelList;
    //sku
    private List<ItemModel> itemModelList;
    //spuImage
    private List<GoodsImageDO> goodsImageDOList;
    //销量
    private Integer sales;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(Integer isMarketable) {
        this.isMarketable = isMarketable;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Integer category1Id) {
        this.category1Id = category1Id;
    }

    public Integer getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Integer category2Id) {
        this.category2Id = category2Id;
    }

    public Integer getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Integer category3Id) {
        this.category3Id = category3Id;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPackageList() {
        return packageList;
    }

    public void setPackageList(String packageList) {
        this.packageList = packageList;
    }

    public String getSaleService() {
        return saleService;
    }

    public void setSaleService(String saleService) {
        this.saleService = saleService;
    }

    public List<SpecificationModel> getSpecificationModelList() {
        return specificationModelList;
    }

    public void setSpecificationModelList(List<SpecificationModel> specificationModelList) {
        this.specificationModelList = specificationModelList;
    }

    public List<ItemModel> getItemModelList() {
        return itemModelList;
    }

    public void setItemModelList(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }

    public List<GoodsImageDO> getGoodsImageDOList() {
        return goodsImageDOList;
    }

    public void setGoodsImageDOList(List<GoodsImageDO> goodsImageDOList) {
        this.goodsImageDOList = goodsImageDOList;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }
}
