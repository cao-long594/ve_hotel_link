package cn.vetech.center.hotel.link.client.gys.mapping.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 酒店房型
 *
 * @author luqs
 * @version v1.0
 */
@ApiModel("酒店房型")
public class VeHotelRoomVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 房源编号
     */
    @ApiModelProperty(value = "房源编号", dataType = "string")
    private String supplyCode;
    /***
     * 房源简称
     */
    @ApiModelProperty(value = "房源简称", dataType = "string")
    private String supplyAbbr;
    /**
     * 房源酒店id
     */
    @ApiModelProperty(value = "房源酒店id", dataType = "string")
    private String supplyHotelId;
    /**
     * 房源城市id
     */
    @ApiModelProperty(value = "房源城市id", dataType = "string")
    private String cityId;
    /**
     *国家
     */
    private String country;

    /**
     * 房型信息列表
     */
    @ApiModelProperty(value = "房型信息列表", dataType = "java.util.List")
    private List<VeRoomBaseInfoVO> roomList;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getSupplyHotelId() {
        return supplyHotelId;
    }

    public void setSupplyHotelId(String supplyHotelId) {
        this.supplyHotelId = supplyHotelId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public List<VeRoomBaseInfoVO> getRoomList() {