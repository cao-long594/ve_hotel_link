package cn.vetech.center.hotel.link.elong.orderbook.request;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 信用卡
 *
 * @author gaojin
 */
public class ElongBookCreditCard implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 卡号
     */
    @JsonProperty("Number")
    private String number;
    /**
     * CVV
     */
    @JsonProperty("CVV")
    private String cvv;
    /**
     * 有效期-年
     */
    @JsonProperty("ExpirationYear")
    private String expirationYear;
    /**
     * 有效期-月
     */
    @JsonProperty("ExpirationMonth")
    private String expirationMonth;
    /**
     * 持卡人
     */
    @JsonProperty("HolderName")
    private String holderName;
    /**
     * 证件类型
     * 身份证 IdentityCard，护照 Passport，其他 Other
     */
    @JsonProperty("IdType")
    private String idType;
    /**
     * 证件号码
     */
    @JsonProperty("IdNo")
    private String idNo;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType