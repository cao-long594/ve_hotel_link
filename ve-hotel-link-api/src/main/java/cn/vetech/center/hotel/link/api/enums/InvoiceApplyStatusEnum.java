package cn.vetech.center.hotel.link.api.enums;

/**
 * @author chengwanshan
 * @since 2020/11/7 14:23
 */
public enum InvoiceApplyStatusEnum {

    /**
     * 申请中
     */
    APPLYING("0", "申请中"),
    /**
     * 已开票
     */
    DONE("1", "已开票"),
    /**
     * 待人工处理
     */
    MANUAL_HANDING("2", "待人工处理"),
    /**
     * 已取消
     */
    CANCEL_APPLY("3", "取消");

    /**
     * 代码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    /**
     * @param code 代码
     * @param name 名称
     */
    private InvoiceApplyStatusEnum(String code, String name) {
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
