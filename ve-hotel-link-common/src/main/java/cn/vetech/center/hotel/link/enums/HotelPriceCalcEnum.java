package cn.vetech.center.hotel.link.enums;

/**
 * 酒店价格计算相关枚举
 *
 * @author luqs
 * @version v1.0
 */
public class HotelPriceCalcEnum {

    private HotelPriceCalcEnum() {
    }

    /**
     * 列表价格获取方式
     */
    public enum ListPriceGetWay {
        /**
         * 0：批量酒店同步获取
         */
        BATCH_SYNC_GET("0", "批量酒店获取"),
        /**
         * 1：单个酒店异步获取
         */
        SINGLE_ASYNC_GET("1", "单个酒店异步获取"),
        ;

        /**
         * 编码
         */
        private final String code;
        /**
         * 名称
         */
        private final String name;

        ListPriceGetWay(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public boolean equalsCode(String s) {
            return this.code.equals(s);
        }
    }

    /**
     * 请求来源
     */
    public enum RequestSource {
        /**
         * 费控系统
         */
        CHARGE("charge", "费控系统"),
        /**
         * 独立link-ve分发
         */
        VE("ve", "独立link-ve分发"),
        ;

        /**
         * 编码
         */
        private final String code;
        /**
         * 名称
         */
        private final String name;

        RequestSource(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public boolean equalsCode(String s) {
            return this.code.equals(s);
        }
    }
}
