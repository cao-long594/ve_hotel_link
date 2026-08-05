package cn.vetech.center.hotel.link.constant;

import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;

/**
 * 酒店link常量类
 *
 * @author luqs
 * @version v1.0
 **/
public class HotelLinkConstant {
    private HotelLinkConstant() {
    }

    /**
     * 国家名称：中国
     */
    public static final String COUNTRY_CHINA_NAME = "中国";
    /**
     * 供应商默认错误码
     */
    public static final String ERROR_CODE = HotelOrderBookErrorCodeEnum.GYSE55555.getCode();
    /**
     * 供应商参数名：tcext
     */
    public static final String SUPPLY_PARAM_NAME_TCEXT = "tcext";

    /**
     * 应用名称
     */
    public static final String APPLICATION_NAME = "ve-hotel-link";
    /**
     * 北京时区
     */
    public static final String BEIJIN_TIMEZONE = "UTC+08:00";
    /**
     * 欧洲时区
     */
    public static final String EUROPE_TIMEZONE = "UTC+01:00";

    /**
     * 欧洲时区
     */
    public static final String UTC_TIMEZONE = "UTC+";
    /**
     * 当
     */
    public static class DefaultUser {
        /**
         * * 联系人邮箱
         */
        public static final String EMIAL = "8013@vetech.cn";

        /**
         * 联系人姓名
         */
        public static final String USER_NAME = "祝官兵";
        /**
         * 联系人电话
         */
        public static final String PHONE = "18672789703";


    }

    public static class CancelRule {
        /**
         * 默认最晚取消时间
         */
        public static final String LATEST_CANCEL_TIME = "1970-01-01 00:00:00";
    }
}
