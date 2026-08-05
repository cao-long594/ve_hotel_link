package cn.vetech.center.hotel.link.api.enums;

/**
 * @author chengwanshan
 * @since 2024/12/11 15:39
 */
public enum HotelDeductionTypeEnum {
//    FREE（免费取消）；LADDER（收费取消）；CANNOT_CANCEL（不可取消）
    FREE("FREE", "免费取消"),
    LADDER("LADDER", "收费取消"),
    CANNOT_CANCEL("CANNOT_CANCEL", "不可取消"),
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;

    private HotelDeductionTypeEnum(String code, String name) {
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
