package cn.vetech.center.hotel.link.elong.common;

/**
 * @author xiaotengyu
 * @since 2022-10-19 17:16
 */
public class ElongCancelRuleEnum {

    /**
     * 变更规则
     */
    public enum ChangeRuleEnum {

        /**
         * 不允许取消
         */
        PrepayNoChange("PrepayNoChange", "不允许取消"),
        /**
         * 限时取消
         */
        PrepayNeedSomeDay("PrepayNeedSomeDay", "限时取消"),
        /**
         * 定点免费取消
         */
        PrepayNeedOneTime("PrepayNeedOneTime", "定点免费取消");

        /**
         * 规则
         */
        private final String val;
        /**
         * 取消规则描述
         */
        private final String text;

        private ChangeRuleEnum(String val, String text) {
            this.val = val;
            this.text = text;
        }

        public String getVal() {
            return val;
        }



        public String getText() {
            return text;
        }


    }

    /**
     * 日期类型
     */
    public enum DateTypeEnum {

        /**
         * 入住日期
         */
        CheckInDay("CheckInDay", "入住日期"),
        /**
         * 在店日期
         */
        StayDay("StayDay", "在店日期");

        /**
         * 类型
         */
        private final String val;
        /**
         * 中文描述
         */
        private final String text;

        DateTypeEnum(String val, String text) {
            this.val = val;
            this.text = text;
        }

        public String getVal() {
            return val;
        }



        public String getText() {
            return text;
        }


    }

    /**
     * 用于 PrepayNeedSomeDay的Hour前扣款类型（一般不收罚金）。DeductFeesBefore为1表示扣费，0表示不扣费。
     */
    public enum DeductFeesBeforeEnum {

        /**
         * 免费
         */
        FREE("0"),
        /**
         * 收费
         */
        CHARGE("1")
        ;


        /**
         * 值
         */
        private final String val;

        DeductFeesBeforeEnum(String val) {
            this.val = val;
        }

        public String getVal() {
            return val;
        }


    }

}