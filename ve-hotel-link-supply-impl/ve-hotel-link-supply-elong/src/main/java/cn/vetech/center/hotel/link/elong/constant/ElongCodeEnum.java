package cn.vetech.center.hotel.link.elong.constant;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author chengwanshan
 * @since 2024/8/12 10:08
 */
public class ElongCodeEnum {

    public enum CommonEnum {
        /**
         * 0:成功
         */
        SUCCESS("0", "成功"),
        ;
        /**
         *
         */
        private final String code;
        /**
         *
         */
        private final String name;

        private CommonEnum(String code, String name) {
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

    public enum OrderBookEnum {
        /**
         *
         */
        H001044_5029("H001044-5029", "H001044-5029|下单失败,产品不可订", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20005),
        /**
         *
         */
        H001184("H001184", "H001184|客人实付金额CustomerPrice需不可小于当前限价产品卖价", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001),
        /**
         *
         */
        H001180("H001180", "H001180|客人实付金额CustomerPrice需不可大于当前限价产品卖价", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001),
        /**
         *
         */
        H001044_7018("H001044-7018", "H001044-7018|下单失败,底价异常", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001),
        /**
         * H001083|产品不可订(401:nb成单时当成单前调用,-直连满房)
         * H001083|产品不可订(7010:国际产品不可定)
         * H001083|产品不可订(501:商品库验价失败,异常或者未返回)
         */
        H001083("H001083", "H001083|产品不可订", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20005),
        /**
         * H001044-3001|下单失败,直连满房，入、离店日期期间（2025-07-09 00:00:00~2025-07-10 00:00:00）房量不充足！
         */
        H001044_3001("H001044-3001", "H001044-3001|下单失败", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001),
        /**
         *
         */
        H001168("H001168", "H001168|英文名字至少需要3个字符", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001),
        /**
         *
         */
        H001020("H001020", "H001020|联系人/入住人中包含敏感词", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001),
        ;

        /**
         *
         */
        private final String code;
        /**
         *
         */
        private final String name;
        /**
         *
         */
        private final HotelOrderBookErrorCodeEnum hotelOrderBookErrorCodeEnum;

        private OrderBookEnum(String code, String name, HotelOrderBookErrorCodeEnum hotelOrderBookErrorCodeEnum) {
            this.code = code;
            this.name = name;
            this.hotelOrderBookErrorCodeEnum = hotelOrderBookErrorCodeEnum;
        }

        private static final Map<String, OrderBookEnum> orderBookEnumMap = new HashMap<>();

        static {
            for (OrderBookEnum errorCodeEnum : OrderBookEnum.values()) {
                orderBookEnumMap.put(errorCodeEnum.getCode(), errorCodeEnum);
            }
        }

        /**
         * @param code code
         * @return HotelOrderBookErrorCodeEnum
         */
        public static HotelOrderBookErrorCodeEnum getErrorCodeEnumByCode(String code) {
            if (StringUtils.isBlank(code)) {
                return HotelOrderBookErrorCodeEnum.GYSE_UN_10003;
            }
            String[] split = StringUtils.split(code, SymbolConstant.VERTICAL_SIGN);
            OrderBookEnum orderBookEnum = orderBookEnumMap.get(split[0]);
            if (Objects.nonNull(orderBookEnum) && StringUtils.startsWith(code, orderBookEnum.getName())) {
                return orderBookEnum.getHotelOrderBookErrorCodeEnum();
            }
            return HotelOrderBookErrorCodeEnum.GYSE_UN_10003;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public HotelOrderBookErrorCodeEnum getHotelOrderBookErrorCodeEnum() {
            return hotelOrderBookErrorCodeEnum;
        }
    }


    public enum ValidateEnum {
        /**
         *
         */
        H001083("H001083", "H001083|产品不可订(401:nb成单时当成单前调用,-直连满房)", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20002),
        /**
         *
         */
        H001084_2("H001084-2", "H001084-2|总价(TotalPrice)错误，应该不小于结算价:***", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20004),
        /**
         *
         */
        H001144("H001144", "H001144|获取促销产品失败-找不到指定产品,请确认传入正确的goods_uniq_id直连无返回", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20005),
        /**
         *
         */
        H001144_1("H001144-1", "H001144-1|获取产品失败-产品不可定", HotelOrderBookErrorCodeEnum.GYSE_CFMD_20005),
        ;

        /**
         *
         */
        private final String code;
        /**
         *
         */
        private final String name;
        /**
         *
         */
        private final HotelOrderBookErrorCodeEnum hotelOrderBookErrorCodeEnum;

        private ValidateEnum(String code, String name, HotelOrderBookErrorCodeEnum hotelOrderBookErrorCodeEnum) {
            this.code = code;
            this.name = name;
            this.hotelOrderBookErrorCodeEnum = hotelOrderBookErrorCodeEnum;
        }

        private static final Map<String, ValidateEnum> orderBookEnumMap = new HashMap<>();

        static {
            for (ValidateEnum errorCodeEnum : ValidateEnum.values()) {
                orderBookEnumMap.put(errorCodeEnum.getCode(), errorCodeEnum);
            }
        }

        /**
         * @param code code
         * @return HotelOrderBookErrorCodeEnum
         */
        public static HotelOrderBookErrorCodeEnum getErrorCodeEnumByCode(String code) {
            if (StringUtils.isBlank(code)) {
                return HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001;
            }
            String[] split = StringUtils.split(code, SymbolConstant.VERTICAL_SIGN);
            ValidateEnum validateEnum = orderBookEnumMap.get(split[0]);
            if (Objects.nonNull(validateEnum) && StringUtils.startsWith(code, validateEnum.getName())) {
                return validateEnum.getHotelOrderBookErrorCodeEnum();
            }
            return HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public HotelOrderBookErrorCodeEnum getHotelOrderBookErrorCodeEnum() {
            return hotelOrderBookErrorCodeEnum;
        }
    }
}

 