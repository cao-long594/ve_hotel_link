package cn.vetech.center.hotel.link.supply.ylfx.orderbook.request;

/**
 * @author 6161
 * @date 2024/07/25
 */
public class CheckinPerson {
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 证件类型：1. 身份证 2. 护照 3. 回乡证 4. 台胞证 5. 港澳居民身份证 6. 其他
     */
    private String idCardType;
    /**
     * 证件号码
     */
    private String idCardNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }
}
