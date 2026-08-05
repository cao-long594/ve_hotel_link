package cn.vetech.center.hotel.link.api.hotelgetcdsdata.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author gaojin
 */
public class HotelDictionaryVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号", dataType = "string")
    private String id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", dataType = "string")
    private String mc;
    /**
     * 英文名称
     */
    @ApiModelProperty(value = "英文名称", dataType = "string")
    private String ywmc;
    /**
     * 顺序号
     */
    @ApiModelProperty(value = "顺序号", dataType = "string")
    private String sxh;
    /**
     * 上级ID
     */
    @ApiModelProperty(value = "上级ID", dataType = "string")
    private String parid;
    /**
     * 类别
     */
    @ApiModelProperty(value = "类别", dataType = "string")
    private String lb;
    /**
     * 备用
     */
    private String by1;
    /**
     * 备用
     */
    private String by2;
    /**
     * 备用
     */
    private String by3;
    /**
     * 备用
     */
    private String by4;
    /**
     * 备用
     */
    private String by5;
    /**
     * 备用
     */
    private String by6;

    /**
     * 是否集团 1代表酒店集团 0非集团
     */
    @ApiModelProperty(value = "是否集团 1代表酒店集团 0非集团", dataType = "string")
    private String sfjt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getYwmc() {
        return ywmc;
    }

    public void setYwmc(String ywmc) {
        this.ywmc = ywmc;
    }

    public String getSxh() {
        return sxh;
    }

    public void setSxh(String sxh) {
        this.sxh = sxh;
    }

    public String getParid() {
    