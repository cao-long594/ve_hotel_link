package cn.vetech.center.hotel.link.api.orderbook.dto;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 联系人节点
 *
 * @author gaojin
 */
public class BookContact implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 姓名
     * 在正式环境中姓名请不要填写“张三、李四、test、测试、小姐、先生、女士”等
     */
    @ApiModelProperty(value = "姓名,在正式环境中姓名请不要填写“张三、李四、test、测试、小姐、先生、女士”等", dataType = "string")
    private String name;
    /**
     * Email
     */
    @ApiModelProperty(value = "Email", dataType = "string")
    private String email;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机", dataType = "string")
    private String mobile;
    /**
     * 电话。国家代码-地区代码-电话号码-分机号
     */
    @ApiModelProperty(value = "电话。国家代码-地区代码-电话号码-分机号", dataType = "string")
    private String phone;
    /**
     * 传真
     */
    @ApiModelProperty(value = "传真", dataType = "string")
    private String fax;
    /**
     * 性别。Female 女，Maile 男, Unknown 保密
     */
    @ApiModelProperty(value = "性别。Female 女，Maile 男, Unknown 保密", dataType = "string")
    private String gender;
    /**
     * 联系人手机国际编码
     */
    private String mobileGjdm;

    public String getMobileGjdm() {
        return mobileGjdm;
    }

    public void setMobileGjdm(String mobileGjdm) {
        this.mobileGjdm = mobileGjdm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String