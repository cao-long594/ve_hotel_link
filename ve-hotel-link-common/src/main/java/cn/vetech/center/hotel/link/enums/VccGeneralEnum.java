package cn.vetech.center.hotel.link.enums;

/**
 * @author xiaotengyu
 * @since 2021/9/27 15:19
 */
public class VccGeneralEnum {

    public enum ApiSiteEnum {
        /**
         * 申请卡
         */
        APPLY("apply","申请卡"),
        /**
         * "卡注销
         */
        CANCEL("cancel","卡注销"),
        /**
         * "查询卡
         */
        QUERY("query","查询卡"),
        /**
         * "结算账单查询
         */
        SETTLEBILL("settlebill","结算账单查询"),
        /**
         * "账户余额查询
         */
        ACCOUNT("account","账户余额查询"),
        /**
         * "账户明细查询
         */
        ACCOUNTRECORD("accountRecord","账户明细查询"),

        ;

        private ApiSiteEnum(String url, String name) {
            this.url = url;
            this.name = name;
        }
        /**
         * code
         */
        private final String url;
        /**
         * name
         */
        private final String name;

        public String getUrl() {
            return url;
        }



        public String getName() {
            return name;
        }


    }
}
