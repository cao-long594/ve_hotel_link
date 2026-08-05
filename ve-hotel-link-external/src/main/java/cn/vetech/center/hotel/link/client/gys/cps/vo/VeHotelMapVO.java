package cn.vetech.center.hotel.link.client.gys.cps.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author gaojin
 */
public class VeHotelMapVO implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * 房源商的酒店ID
     */
    @ApiModelProperty(value = "房源商的酒店ID", dataType = "string")
    private String hotelid;

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

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }
}
