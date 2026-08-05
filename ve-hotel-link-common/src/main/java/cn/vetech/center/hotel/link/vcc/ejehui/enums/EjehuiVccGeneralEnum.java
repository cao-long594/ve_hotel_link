package cn.vetech.center.hotel.link.vcc.ejiehui.enums;

/**
 * @author xiaotengyu
 * @since 2021/9/27 15:19
 */
public class EjiehuiVccGeneralEnum {

    public enum ApiSiteEnum {
        /**
         * 开卡接口
         */
        CARD_OPEN_INFO("card.open.info", "开卡接口"),
        /**
         * 销卡接口
         */
        CARD_CANCEL_INFO("card.cancel.info", "销卡接口"),
        /**
         * 开卡查询接口
         */
        CARD_QUERY_INFO("card.query.info", "开卡查询接口"),
        /**
         * 余额查询接口
         */
        ACCOUNT_BALANCE_INFO("account.balance.info", "余额查询接口"),
        /**
         * 交易明细获取接口
         */
        CARD_TRADE_INFO("card.trade.info", "交易明细获取接口"),
        /**
         * 修改卡片信息
         */
        CARD_CHANGE_INFO("card.change.info", "修改卡片信息"),
        /**
         * 卡余额查询
         */
        CARD_BALANCE_INFO("card.balance.info", "卡余额查询"),

        ;

        private ApiSiteEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        /**
         * code
         */
        private final String code;
        /**
         * name
         */
        private final String name;

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    public enum BaseCodeEnum {
        /**
         * 请求成功
         */
        CODE_000000("000000", "请求成功", "1"),
        CODE_000009("000009", "请求失败", "1"),
        CODE_999999("999999", "系统异常", "1"),

        CODE_100002("100002", "输入金额格式有误", "2"),
        CODE_100003("100003", "刷卡金额为空", "2"),
        CODE_100004("100004", "开卡所需额度不足", "2"),
        CODE_100005("100005", "卡使用次数为空", "2"),
        CODE_100006("100006", "境外卡风控产品类型错误", "2"),
        CODE_100007("100007", "境外卡使用时间错误", "2"),
        CODE_100008("100008", "开境内卡加成比例起始为0.1%（百分比只保留一位小数）", "2"),
        CODE_100009("100009", "刷卡币种为空", "2"),
        CODE_100010("100010", "卡结束时间格式错误", "2"),
        CODE_100011("100011", "开卡币种异常", "2"),
        CODE_100012("100012", "结算币种账户未开通", "2"),
        CODE_100013("100013", "开境外卡CARDTYPE,填写不正确", "2"),
        CODE_100014("100014", "开卡失败", "2"),
        CODE_100015("100015", "未查询到开卡临时汇率，请联系商务", "2"),
        CODE_100016("100016", "结算币种为空", "2"),
        CODE_100017("100017", "备注不可超出100字符", "2"),

        CODE_200001("200001", "此申卡请求号，无开卡记录", "3"),

        CODE_300001("300001", "卡号不存在", "4"),
        CODE_300002("300002", "销卡失败", "4"),
        CODE_300003("300003", "存在授权记录未清算，无法销卡", "4"),

        CODE_500001("500001", "此申卡请求号，无开卡记录", "5"),

        CODE_600001("600001", "此卡不存在", "6"),

        CODE_800001("800001", "CVV验证值错误", "7"),
        CODE_800002("800002", "交易币种验证值错误", "7"),
        CODE_800003("800003", "账户可用余额不足", "7"),
        CODE_800004("800004", "有效期请大于当前日期", "7"),
        CODE_800005("800005", "额度增加不得小于0", "7"),
        CODE_800006("800006", "卡信息修改失败", "7"),
        CODE_800007("800007", "没有需要修改的卡信息", "7"),
        CODE_800008("800008", "有效期时间格式错误", "7"),
        CODE_800009("800009", "增加额度金额格式有误", "7"),
        CODE_800010("800010", "此卡不允许修改有效期", "7"),
        CODE_800011("800011", "有效期请大于或等于原卡结束时间", "7"),

        ;
        private final String code;
        private final String name;
        /**
         * 1:公共状态码-公共码
         * 2:业务码状态-公共码
         * 3:业务码状态-开卡查询接口
         * 4:业务码状态-销卡接口
         * 5:业务码状态-卡状态查询
         * 6:业务码状态-卡余额查询
         * 7:业务码状态-修改开片额度
         */
        private final String type;

        BaseCodeEnum(String code, String name, String type) {
            this.code = code;
            this.name = name;
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }
}