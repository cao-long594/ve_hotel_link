package cn.vetech.center.hotel.link.elong.orderbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 发票配送信息
 *
 * @author gaojin
 */
public class ElongBookRecipient implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 省份
     */
    @JsonProperty("Province")
    private String province;
    /**
     * 城市
     */
    @JsonProperty("City")
    private String city;
    /**
     * 行政区
     */
    @JsonProperty("District")
    private String district;
    /**
     * 街道
     */
    @JsonProperty("Street")
    private String street;
    /**
     * 邮编
     */
    @JsonProperty("PostalCode")
    private String postalCode;
    /**
     * 收件人
     */
    @JsonProperty("Name")
    private String name;
    /**
     * 电话
     */
    @JsonProperty("Phone")
    private String phone;
    /**
     * Email
     */
    @JsonProperty("Email")
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
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

   