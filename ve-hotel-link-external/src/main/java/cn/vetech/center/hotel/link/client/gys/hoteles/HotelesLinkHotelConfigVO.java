package cn.vetech.center.hotel.link.client.gys.hoteles;

import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @author xiaotengyu
 * @since 2022-06-08 16:05
 */
public class HotelesLinkHotelConfigVO {

    /**
     * 房源商编号
     */

    @ApiModelProperty(value = "房源商编号", dataType = "string")
    private String fybh;
    /**
     * 房源商名称
     */
    @ApiModelProperty(value = "房源商名称", dataType = "string")
    private String fymc;
    /**
     * 房源商简称
     */
    @ApiModelProperty(value = "房源商简称", dataType = "string")
    private String fyjc;
    /**
     * 0关闭1开启
     */
    @ApiModelProperty(value = "0关闭1开启", dataType = "string")
    private String zt;
    /**
     * 0不可下单1可下单
     */
    @ApiModelProperty(value = "下单状态", dataType = "string")
    private String xdzt;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", dataType = "string")
    private String bz;
    /**
     * 商户编号
     */
    @ApiModelProperty(value = "商户编号", dataType = "string")
    private String shbh;
    /**
     * 房源商类型 1 酒店 2 民宿
     */
    @ApiModelProperty(value = "房源商类型 1 酒店 2 民宿", dataType = "string")
    private String fylx;

    /**
     * 0关闭1开启
     */
    @ApiModelProperty(value = "开启缓存", dataType = "string")
    private String kqhc;

    /**
     * 参数名 参数值
     */
    private Map<String,String> configMap;

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getFyjc() {
        return fyjc;
    }

    public void setFyjc(String fyjc) {
        this.fyjc = fyjc;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getXdzt() {
        return xdzt;
