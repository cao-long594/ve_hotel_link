package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2021/12/14 17:45
 */
public class ElongSearchGiftPackages {
    /**
     * 礼包套餐ID	   	关联RatePlan.PkgProductids
     */
    @JsonProperty("PkgProductId")
    private String pkgProductId;
    /**
     * 礼包套餐类型	   	0：礼包，1：套餐
     */
    @JsonProperty("PkgType")
    private String pkgType;
    /**
     * 礼包套餐名字
     */
    @JsonProperty("PkgProductName")
    private String pkgProductName;
    /**
     * 礼包套餐特别说明
     */
    @JsonProperty("RuleDescriptionAdditional")
    private String ruleDescriptionAdditional;
    /**
     * 礼包套餐图片	   	参考Picture节点
     */
    @JsonProperty("Pictures")
    private List<ElongSearchPicture> pictures;
    /**
     * X产品列表	   	参考XProduct节点
     */
    @JsonProperty("XProducts")
    private List<ElongSearchXProduct> products;

    public String getPkgProductId() {
        return pkgProductId;
    }

    public void setPkgProductId(String pkgProductId) {
        this.pkgProductId = pkgProductId;
    }

    public String getPkgType() {
        return pkgType;
    }

    public void setPkgType(String pkgType) {
        this.pkgType = pkgType;
    }

    public String getPkgProductName() {
        return pkgProductName;
    }

    public void setPkgProductName(String pkgProductName) {
        this.pkgProductName = pkgProductName;
    }

    public String getRuleDescriptionAdditional() {
        return ruleDescriptionAdditional;
    }

    public void setRuleDescriptionAdditional(String ruleDescriptionAdditional) {
        this.ruleDescriptionAdditional = ruleDescriptionAdditional;
    }

    public List<ElongSearchPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<ElongSearchPicture> pictures) {
        this.pictures = pictures;
    }

    public Li