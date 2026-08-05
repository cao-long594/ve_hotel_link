package cn.vetech.center.hotel.link.elong.orderbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 联系人
 *
 * @author gaojin
 */
public class ElongBookContact implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 姓名
     * 在正式环境中姓名请不要填写“张三、李四、王五、test、测试”等，艺龙系统有对应姓名黑名单;
     * 也不能填写小姐、先生或女士。
     */
    @JsonProperty("Name")
    private String name;
    /**
     * Email
     */
    @JsonProperty("Email")
    private String email;
    /**
     * 手机号区号
     */
    @JsonProperty("MobileAreaCode")
    private String mobileAreaCode;
    /**
     * 手机
     */
    @JsonProperty("Mobile")
    private String mobile;
    /**
     * 电话。国家代码-地区代码-电话号码-分机号
     */
    @JsonProperty("Phone")
    private String phone;
    /**
     * 传真
     */
    @JsonProperty("Fax")
    private String fax;
    /**
     * 性别。Female 女，Maile 男, Unknown 保密
     */
    @JsonProperty("Gender")
    private String gender;
    /**
     * 名
     */
    private String firstName;
    /**
     * 姓
     */
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

    public String getMobileAreaCode() {
        return mobileAreaCode;
    }