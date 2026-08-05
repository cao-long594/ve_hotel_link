package cn.vetech.center.hotel.link.api.enums;

/**
 * @author chengwanshan
 * @since 2024/12/11 15:20
 */
public enum HotelCancelRuleEnum {
//    UN_KNOWN（未知）；FREE（免费取消）；TIME_LIMIT（限时取消）；NOT_ALLOWED（不可取消）
    UN_KNOWN("UN_KNOWN", "未知"),
    FREE("FREE", "免费取消"),
    TIME_LIMIT("TIME_LIMIT", "限时取消"),
    NOT_ALLOWED("NOT_ALLOWED", "不可取消"),
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;

    private HotelCancelRuleEnum(String code, String name) {
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
