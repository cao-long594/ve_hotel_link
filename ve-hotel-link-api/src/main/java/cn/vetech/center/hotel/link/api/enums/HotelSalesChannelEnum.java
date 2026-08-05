package cn.vetech.center.hotel.link.api.enums;

/**
 * @author chengwanshan
 * @since 2024/11/28 16:41
 */
public enum HotelSalesChannelEnum {
    WEBSITE("website", "Standard website accessed from the customer's computer"),
    AGENT_TOOL("agent_tool", "Your own agent tool used by your call center or retail store agent"),
    MOBILE_APP("mobile_app", "An application installed on a phone or tablet device"),
    MOBILE_WEB("mobile_web", "A web browser application on a phone or tablet device"),
    META("meta", "Rates will be passed to and displayed on a 3rd party comparison website"),
    CACHE("cache", "Rates will be used to populate a local cache"),
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;

    private HotelSalesChannelEnum(String code, String name) {
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
