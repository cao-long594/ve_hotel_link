package cn.vetech.center.hotel.link.api.enums;

/**
 * @author chengwanshan
 * @since 2022/6/7 11:33
 */
public enum FilterProductTypeEnum {
    /**
     * 1代表过滤预付酒店开票产品
     */
    PREPAY_HOTEL_INVOICE("1", "过滤预付酒店开票产品"),
    /**
     * 2代表过滤预付供应商开票产品
     */
    PREPAY_SUPPLIER_INVOICE("2", "过滤预付供应商开票产品"),
    /**
     * 3代表过滤非协议产品
     */
    NON_AGREEMENT_PRICE("3", "过滤非协议产品"),
    ;

    /**
     * code
     */
    private final String code;
    /**
     * name
     */
    private final String name;

    private FilterProductTypeEnum(String code, String name) {
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
