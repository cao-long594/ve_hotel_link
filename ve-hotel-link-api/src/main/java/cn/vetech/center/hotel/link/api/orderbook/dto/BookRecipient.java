package cn.vetech.center.hotel.link.api.orderbook.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 发票邮寄信息
 *
 * @author gaojin
 */
public class BookRecipient implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 省份
     */
    @ApiModelProperty(value = "省份", dataType = "string")
    private String province;
    /**
     * 城市
     */
    @ApiModelProperty(value = "城市", dataType = "string")
    private String city;
    /**
     * 行政区
     */
    @ApiModelProperty(value = "行政区", dataType = "string")
    private String district;
    /**
     * 街道
     */
    @ApiModelProperty(value = "街道", dataType = "string")
    private String street;
    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编", dataType = "string")
    private String postalCode;
    /**
     * 收件人
     */
    @ApiModelProperty(value = "收件人", dataType = "string")
    private String name;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", dataType = "string")
    private String phone;
    /**
     * Email
     */
    @ApiModelProperty(value = "Email", dataType = "string")
    private String email;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCod