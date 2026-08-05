package cn.vetech.center.hotel.link.elong.orderbook.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author gaojin
 */
public class ElongBookCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 姓名
     */
    @JsonProperty("Name")
    private String name;
    /**
     * 性别
     * Female  女，Maile 男, Unknown 保密
     */
    @JsonProperty("Gender")
    private String gender;
    /**
     * 国籍
     * 有些酒店在预订规则BookingRule中规定需要提供国籍
     * 填写具体的国籍,如中国、日本、美国、USA等
     */
    @JsonProperty("Nationality")
    private String nationality;
    /**
     * 身份证
     */
    @JsonProperty("IdCardNo")
    private String idcard;
    /**
     * 证件类型,身份证 IdentityCard，护照 Passport，其他 Other
     */
    @JsonIgnore
    private String customerIdType;

    /**
     * 入住人证件类型   20200117新增
     * IdentityCard，身份证    Passport护照    Other 其它
     * 证件类型字段：cps房源和直连传入的字段不一样；cps传入的证件类型为idCardType；直连证件类型字段为：customerIdType
     */
    @JsonIgnore
    private String idCardType;

    /**
     * 身份证号码
     */
    @JsonIgnore
    private String customerIdNo;

    /**
     * 名
     */
    @JsonProperty("FirstName")
    private String firstName;
    /**
     * 姓
     */
    @JsonProperty("LastName")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerIdNo() {
        return customerIdNo;
    }

    public void setCustomerIdNo(String customerIdNo) {
        this.customerIdNo = customerIdNo;
    }

    public String getIdCardType() {
        return idCard