package cn.vetech.center.hotel.link.api.enums;

/**
 * @author chengwanshan
 * @since 2023/12/19 15:04
 */
public enum HotelPayTypeEnum {
    // 0：现付，  1：企业支付， 2：一网通，  3：微信， 4：银联， 5：支付宝，  6：在线支付， 7：华夏银行扫码支付，  8：华夏银行快捷支付
    /**
     * 0。现付
     */
    CASH("0", "现付"),
    /**
     * 1.企业支付
     */
    ENTERPRISE_PAY("1", "企业支付"),
    /**
     * 2：一网通
     */
    NETCOM("2", "一网通"),
    /**
     * 3微信
     */
    WECHAT("3", "微信"),
    /**
     * 4银联
     */
    UNION_PAY("4", "银联"),
    /**
     * 5支付宝
     */
    ALI_PAY("5", "支付宝"),
    /**
     * 6：在线支付
     */
    ONLINE_PAYMENT("6", "在线支付"),
    /**
     * 7：华夏银行扫码支付
     */
    SCAN_CODE_PAYMENT("7", "华夏银行扫码支付"),
    /**
     * 8：华夏银行快捷支付
     */
    QUICK_PAYMENT("8", "华夏银行快捷支付"),

    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;

    private HotelPayTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
