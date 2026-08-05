package cn.vetech.center.hotel.link.elong.common;

import cn.vetech.center.hotel.link.enums.HotelGysOrderStatusEnum;
import cn.vetech.center.hotel.link.enums.HotelJdpzEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaotengyu
 * @since 2021/6/10 20:25
 */
public class ElongHotelGeneralEnum {

    public enum ElongOrderStatusEnum {

        /***
         * 已确认
         */
        A("A", "已确认", HotelGysOrderStatusEnum.AFTER_CONFIRM.getCode()),
        /***
         * NO SHOW
         */
        B("B", "NO SHOW",HotelGysOrderStatusEnum.NOSHOW.getCode()),
        /***
         * 有预定未查到
         */
        B1("1", "有预定未查到",HotelGysOrderStatusEnum.BEFORE_CONFIRM.getCode()),
        /***
         * 待查
         */
        B2("2", "待查",HotelGysOrderStatusEnum.BEFORE_CONFIRM.getCode()),
        /***
         * 暂不确定
         */
        B3("3", "暂不确定",HotelGysOrderStatusEnum.BEFORE_CONFIRM.getCode()),
        /***
         * 已结帐
         */
        C("C", "已结帐",HotelGysOrderStatusEnum.OUT.getCode()),
        /***
         * 删除
         */
        D("D", "删除",HotelGysOrderStatusEnum.CANCEL.getCode()),
        /***
         * 取消
         */
        E("E", "取消",HotelGysOrderStatusEnum.CANCEL.getCode()),
        /***
         * 已入住
         */
        F("F", "已入住",HotelGysOrderStatusEnum.LIVE.getCode()),
        /***
         * 变价
         */
        G("G", "变价",HotelGysOrderStatusEnum.CANCEL.getCode()),
        /***
         * 变更
         */
        H("H", "变更",HotelGysOrderStatusEnum.CANCEL.getCode()),
        /***
         * 新单
         */
        N("N", "新单",HotelGysOrderStatusEnum.BEFORE_CONFIRM.getCode()),
        /***
         * 满房
         */
        O("O", "满房",HotelGysOrderStatusEnum.CANCEL.getCode()),
        /***
         * 特殊
         */
        S("S", "特殊",HotelGysOrderStatusEnum.BEFORE_CONFIRM.getCode()),
        /***
         * 特殊满房
         */
        U("U", "特殊满房",HotelGysOrderStatusEnum.CANCEL.getCode()),
        /***
         * 已审
         */
        V("V", "已审",HotelGysOrderStatusEnum.BEFORE_CONFIRM.getCode()),
        /***
         * 删除,另换酒店
         */
        Z("Z", "删除,另换酒店",HotelGysOrderStatusEnum.CANCEL.getCode()),
        ;

        /***
         * 状态code
         */
        private final String code;
        /***
         * 状态名称
         */
        private final String name;
        /**
         * HotelGysOrderStatusEnum
         */
        private final String gysOrderStatus;
        /**
         * 订单状态
         */
        private static final Map<String, String> map = new HashMap<>();

        static {
            for (ElongOrderStatusEnum statusEnum : ElongOrderStatusEnum.values()) {
                map.put(statusEnum.code,statusEnum.name);
            }
        }

        public static String getOrderStatusMs(String code) {
            return map.get(code);
        }

        /**
         * 订单状态
         */
        private static final Map<String, String> gysOrderStatusMap = new HashMap<>();

        static {
            for (ElongOrderStatusEnum statusEnum : ElongOrderStatusEnum.values()) {
                gysOrderStatusMap.put(statusEnum.code,statusEnum.gysOrderStatus);
            }
        }

        public static String getGysOrderStatus(String code) {
            return gysOrderStatusMap.get(code);
        }

        public String getCode() {
            return code;
        }



        public String getName() {
            return name;
        }

        public String getGysOrderStatus() {
            return gysOrderStatus;
        }

        ElongOrderStatusEnum(String code, String name, String gysOrderStatus) {
            this.code = code;
            this.name = name;
            this.gysOrderStatus = gysOrderStatus;
        }

    }

    /***
     * 交易过程
     */
    public enum CreditCardProcessTypeEnum {

        /**
         * 授权
         */
        Auth("Auth", "授权"),
        /**
         * 取消授权
         */
        CancelAuth("CancelAuth", "取消授权"),
        /**
         * 授权后扣款
         */
        Charge("Charge", "授权后扣款"),
        /**
         * 退款
         */
        Refund("Refund", "退款"),
        /**
         * 直接扣款
         */
        DirectCharge("DirectCharge", "直接扣款"),
        ;
        /***
         * code
         */
        private final String code;
        /***
         * name
         */
        private final String name;

        private CreditCardProcessTypeEnum(String code, String name) {
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

    /***
     * 交易状态
     */
    public enum CreditCardStatusEnum {
        /**
         * 未处理
         */
        UnProcess("UnProcess", "未处理"),
        /**
         * 成功
         */
        Succeed("Succeed", "成功"),
        /**
         * 处理中
         */
        Processing("Processing", "处理中"),
        /**
         * 失败;
         */
        Fail("Fail", "失败;");
        /***
         * code
         */
        private final String code;
        /***
         * name
         */
        private final String name;

        private CreditCardStatusEnum(String code, String name) {
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

    public enum IdentificationEnum {
        /**
         * 无特殊验证要求（默认值）
         */
        I0("0","无特殊验证要求（默认值）"),
        /**
         * 整个订单至少传一个身份证
         */
        I1("1","整个订单至少传一个身份证"),
        /**
         * 订单中每个房间至少传一个证件
         */
        I2("2","订单中每个房间至少传一个证件"),
        /**
         * 订单中每个房间至少传一个身份证
         */
        I3("3","订单中每个房间至少传一个身份证"),
        /**
         * 每个客人传一个身份证
         */
        I4("4","每个客人传一个身份证"),
        ;

        private IdentificationEnum(String code, String name) {
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

    public enum ProductTypeEnum{
        /**
         * 限时抢购
         */
        P3("3","限时抢购"),
        /**
         * 钟点房
         */
        P4("4","钟点房"),
        /**
         * 订单中每个房间至少传一个证件
         */
        P5("5","手机专享"),
        /**
         * 下单需提供入住人身份证（Identification字段已包含此逻辑，涉及到身份信息验证的可统一按照Identification字段来处理）
         */
        P6("6","铂涛产品"),
        /**
         * 只能与景点门票打包销售，默认不吐出
         */
        P17("17","景酒打包产品"),
        /**
         * 床位房类型此处已无效，判断床位房请解析RatePlanName或者Room节点的Name字段，其中只要有一个字段包含“床位”字样即为床位房 ）
         */
        P25("25","床位房"),
        /**
         * 未知（艺龙内部使用）
         */
        P99("99","未知（艺龙内部使用）"),
        ;

        private ProductTypeEnum(String code, String name) {
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

    /**
     *
     */
    public enum ElongOrderShowStatusEnum {
        /***
         * 担保失败
         */
        A("1","担保失败"),
        /**
         * 等待担保
         */
        B("2","等待担保"),
        /**
         * 等待确认
         */
        C("4","等待确认"),
        /**
         * 等待支付
         */
        D("8","等待支付"),
        /**
         * 等待核实入住
         */
        E("16","等待核实入住"),
        /**
         * 酒店拒绝订单
         */
        F("32","酒店拒绝订单"),
        /**
         * 未入住
         */
        G("64","未入住"),
        /**
         * 已经离店
         */
        H("128","已经离店"),
        /**
         * 已经取消
         */
        I("256","已经取消"),
        /**
         * 已经确认
         */
        J("512","已经确认"),
        /**
         * 已经入住
         */
        K("1024","已经入住"),
        /**
         * 正在担保-处理中
         */
        L("2048","正在担保-处理中"),
        /**
         * 正在支付-处理中
         */
        M("4096","正在支付-处理中"),
        /**
         * 支付失败
         */
        N("8192","支付失败"),
        ;

        /***
         * 状态code
         */
        private final String code;
        /***
         * 状态名称
         */
        private final String name;
        /**
         * 订单状态
         */
        private static Map<String, String> map = new HashMap<>();

        static {
            for (ElongOrderShowStatusEnum statusEnum : ElongOrderShowStatusEnum.values()) {
                map.put(statusEnum.code,statusEnum.name);
            }
        }

        public static String getOrderStatusMs(String code) {
            return map.get(code);
        }

        public String getCode() {
            return code;
        }



        public String getName() {
            return name;
        }



        private ElongOrderShowStatusEnum(String c, String n) {
            this.code = c;
            this.name = n;
        }

    }

    /**
     * 1.酒店详情
     * 2.房型
     * 3.图片
     * 4.当前不可销售的rp不出现在结果里（该选项多个酒店也有效）
     * 5. 每日价格数组输出未DRR计算的原始价格
     * 7. 返回汇率信息 ExchangeRateList
     * 8. 经纬度返回百度坐标
     * 9. 仅返回钟点房
     * 11.同时返回钟点房和其他产品
     * 12.如果是现付产品则返回GuaranteeResult，如果是预付产品则返回PrepayResult
     * 一般情况下传入1,2,4
     *
     * 请按需传入，多返回信息会影响报文长度和响应时间，在调用大的情况下，静态信息（1,2,3）建议使用静态相关接口增量落地（hotel.static.list、hotel.static.info）
     */
    public enum ElongSearchOptionEnum{
        /**
         * 酒店详情
         */
        O1("1","酒店详情"),

        /**
         * 房型
         */
        O2("2","房型"),

        /**
         * 图片
         */
        O3("3","图片"),

        /**
         * 当前不可销售的rp不出现在结果里（该选项多个酒店也有效）
         */
        O4("4","当前不可销售的rp不出现在结果里（该选项多个酒店也有效）"),

        /**
         *  每日价格数组输出未DRR计算的原始价格
         */
        O5("5","每日价格数组输出未DRR计算的原始价格"),

        /**
         *  返回汇率信息 ExchangeRateList
         */
        O7("7","返回汇率信息 ExchangeRateList"),

        /**
         *  经纬度返回百度坐标
         */
        O8("8","经纬度返回百度坐标"),

        /**
         *  仅返回钟点房
         */
        O9("9","仅返回钟点房"),

        /**
         * 同时返回钟点房和其他产品
         */
        O11("11","同时返回钟点房和其他产品"),

        /**
         * 如果是现付产品则返回GuaranteeResult，如果是预付产品则返回PrepayResult
         */
        O12("12","如果是现付产品则返回GuaranteeResult，如果是预付产品则返回PrepayResult");
        ;

        private ElongSearchOptionEnum(String val, String desc) {
            this.val = val;
            this.desc = desc;
        }

        /**
         * val
         */
        private final String val;
        /**
         * desc
         */
        private final String desc;

        public String getVal() {
            return val;
        }



        public String getDesc() {
            return desc;
        }


    }

    /**
     * 艺龙取消类型
     */
    public enum ElongCancelTypeEnum {

        /**
         * 免费取消
         */
        C1("1"),

        /**
         * 收费取消
         */
        C2("2"),

        /**
         * 限时取消
         */
        C3("3"),

        /**
         * 不可取消
         */
        C4("4");
        ;

        private ElongCancelTypeEnum(String val) {
            this.val = val;
        }

        /**
         * 值
         */
        private final String val;

        public String getVal() {
            return val;
        }


    }

    /**
     * 挂牌酒店
     */
    public enum HotelLevelEnum
    {
        /**
         * 特牌
         */
        HL1("1","特牌", HotelJdpzEnum.TP.getVal()),
        /**
         * 金牌
         */
        HL2("2","金牌",HotelJdpzEnum.JP.getVal()),
        /**
         * 银牌
         */
        HL3("3","银牌", StringUtils.EMPTY),
        /**
         * 蓝牌
         */
        HL4("4","蓝牌",StringUtils.EMPTY),
        /**
         * 非挂牌
         */
        HL0("0","非挂牌",StringUtils.EMPTY),
        ;
        /**
         * 值
         */
        private final String val;
        /**
         * 备注
         */
        private final String desc;

        /**
         * 本地挂牌编码
         */
        private final String localVal;

        /**
         * 获取酒店品质
         * @param hotelLevel 艺龙 hotelLevel
         * @return 本地酒店品质
         */
        public static String getHotelLevel(String hotelLevel){
            if(StringUtils.isBlank(hotelLevel)){
                return StringUtils.EMPTY;
            }
            return Arrays.stream(HotelLevelEnum.values())
                    .filter(level->StringUtils.equalsIgnoreCase(level.getVal(),hotelLevel))
                    .findFirst()
                    .map(level->level.getLocalVal()).orElse(StringUtils.EMPTY);
        }

        public String getLocalVal() {
            return localVal;
        }



        public String getVal() {
            return val;
        }



        public String getDesc() {
            return desc;
        }



        private HotelLevelEnum(String val, String desc, String localVal) {
            this.val = val;
            this.desc = desc;
            this.localVal = localVal;
        }
    }

}