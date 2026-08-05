package cn.vetech.center.hotel.link.client.gys.mapping.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 房型检索
 *
 * @author luqs
 * @version v1.0
 */
@ApiModel("房型检索")
public class VeRoomSearchDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 房源编号
     */
    @ApiModelProperty(value = "房源编号", dataType = "string")
    private String supplyCode;
    /**
     * 房源简称
     */
    @ApiModelProperty(value = "房源简称", dataType = "string")
    private String supplyAbbr;
    /**
     * 酒店id列表
     */
    @ApiModelProperty(value = "酒店id列表", dataType = "java.util.List")
    private List<String> hotelIdList;
    /**
     * 国内国际：0：国际；1：国内
     */
    private String gngj;

    public String getGngj() {
        return gngj;
    }

    public void setGngj(String gngj) {
        this.gngj = gngj;
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
    }

    public String getSupplyAbbr() {
        return supplyAbbr;
    }

    public void setSupplyAbbr(String supplyAbbr) {
        this.supplyAbbr = supplyAbbr;
    }

    public List<String> getHotelIdList() {
        return hotelIdList;
    }

    public void setHotelIdList(List<String> hotelIdList) {
        this.hotelIdList = hotelIdList;
    }
}
