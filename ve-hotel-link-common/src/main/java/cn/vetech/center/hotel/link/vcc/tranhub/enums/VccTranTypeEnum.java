package cn.vetech.center.hotel.link.vcc.tranhub.enums;

/**
 * @author chengwanshan
 * @since 2025/8/4 14:15
 */
public enum VccTranTypeEnum {
    AUTH("AUTH", "授权交易"),
    AUTHREVERSAL("AUTHREVERSAL", "授权撤销"),
    SETTLE("SETTLE", "交易"),
    REFUND("REFUND", "退款"),
    ;

    private final String code;
    private final String name;

    private VccTranTypeEnum(String code, String name) {
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
