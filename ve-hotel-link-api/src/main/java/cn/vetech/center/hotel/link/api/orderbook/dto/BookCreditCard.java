package cn.vetech.center.hotel.link.api.orderbook.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 信用卡信息
 *
 * @author gaojin
 */
public class BookCreditCard implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 卡号
     */
    @ApiModelProperty(value = "卡号", dataType = "string")
    private String number;
    /**
     * cvv
     */
    @ApiModelProperty(value = "cvv", dataType = "string")
    private String cvv;
    /**
     * 有效期-年
     */
    @ApiModelProperty(value = "有效期-年", dataType = "string")
    private String expirationYear;
    /**
     * 有效期-月
     */
    @ApiModelProperty(value = "有效期-月", dataType = "string")
    private String expirationMonth;
    /**
     * 持卡人
     */
    @ApiModelProperty(value = "持卡人", dataType = "string")
    private String holderName;
    /**
     * 证件类型。身份证 0， 护照 1，军人证 2， 其他 3
     */
    @ApiModelProperty(value = "身份证 0， 护照 1，军人证 2， 其他 3", dataType = "string")
    private String idType;
    /**
     * 信用卡类型
     */
    @ApiModelProperty(value = "信用卡类型", dataType = "string")
    private String cardType;
    /**
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码", dataType = "string")
    private String idNo;
    /**
     * 持卡人手机
     */
    @ApiModelProperty(value = "持卡人手机", dataType = "string")
    private String tel;

    /***************相比cps新增字段******************/
    /**
     * 发卡银行id
     */
    @ApiModelProperty(value = "发卡银行id", dataType = "string")
    private String fkyhid;
    /**
     * 发卡银行
     */
    @ApiModelProperty(value = "发卡银行", dataType = "string")
    private String fkyh;
    /***************相比cps新增字段******************/


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv