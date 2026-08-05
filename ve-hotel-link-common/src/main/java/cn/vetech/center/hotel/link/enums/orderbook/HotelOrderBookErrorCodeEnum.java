package cn.vetech.center.hotel.link.enums.orderbook;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 需要锁单的状态码必须以 GYSE_UN 开头
 *
 * @author chengwanshan
 * @since 2024/6/5 15:31
 */
public enum
HotelOrderBookErrorCodeEnum {
    /**
     * 需要锁单，发送http请求时、出现了异常
     */
    GYSE_UN_10001("GYSE_UN_10001", "请求异常", true),
    /**
     * 需要锁单，转换供应商响应参数出现异常
     */
    GYSE_UN_10002("GYSE_UN_10002", "响应参数转换异常", true),
    /**
     * 需要锁单，供应商响应内容不清晰，根据供应商下单响应参数无法判断供应商侧实际下单结果的
     */
    GYSE_UN_10003("GYSE_UN_10003", "下单异常", true),
    /**
     * 需要锁单，供应商特殊响应码（需要反查），有些供应商明确告知哪些响应码需要反查或者重试的
     */
    GYSE_UN_10004("GYSE_UN_10004", "下单异常", true),
    /**
     * 需要锁单且需要反查，必须按照供应商要求反查订单详情一段时间
     */
    GYSE_UN_10005("GYSE_UN_10005", "下单异常", true),

    /**
     * 不需要锁单，像下单前验价接口调用失败，供应商下单接口返回明确的下单失败响应码，且不需要锁单的场景
     * 废弃，请使用GYSE_CFMD_20001
     */
    @Deprecated
    GYSE55555("GYSE55555", "下单失败", false),

    //**********                   以下状态码，验价、下单接口通用                   ************//
    /**
     * 不需要锁单，像下单前验价接口调用失败，供应商下单接口返回明确的下单失败响应码，且不需要锁单的场景
     */
    GYSE_CFMD_20001("GYSE_CFMD_20001", "下单失败", false),
    /**
     * 满房
     */
    GYSE_CFMD_20002("GYSE_CFMD_20002", "满房", false),
    /**
     * 有库存，但库存不足
     */
    GYSE_CFMD_20003("GYSE_CFMD_20003", "库存不足", false),
    /**
     * 变价，包含房价、早餐信息、取消规则等价格信息发生变化
     */
    GYSE_CFMD_20004("GYSE_CFMD_20004", "变价", false),
    /**
     * 产品无效或关房
     */
    GYSE_CFMD_20005("GYSE_CFMD_20005", "产品无效或关房", false),
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
     * 是否需要锁单
     */
    private final boolean lockOrder;

    private HotelOrderBookErrorCodeEnum(String code, String name, boolean lockOrder) {
        this.code = code;
        this.name = name;
        this.lockOrder = lockOrder;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isLockOrder() {
        return lockOrder;
    }

    /**
     *
     */
    private static final Map<String, HotelOrderBookErrorCodeEnum> codeEnumMap = new HashMap<>();

    static {
        for (HotelOrderBookErrorCodeEnum codeEnum : HotelOrderBookErrorCodeEnum.values()) {
            codeEnumMap.put(codeEnum.getCode(), codeEnum);
        }
    }

    /**
     * 是否需要锁单
     *
     * @param code code
     * @return boolean
     */
    public static boolean getIsLockOrder(String code) {
        HotelOrderBookErrorCodeEnum codeEnum = codeEnumMap.get(code);
        if (Objects.isNull(codeEnum)) {
            return true;
        }
        return codeEnum.isLockOrder();
    }

    /**
     * 下单接口调用验价接口处理错误码使用
     *
     * @param code code
     * @return HotelOrderBookErrorCodeEnum
     */
    public static HotelOrderBookErrorCodeEnum getByCodeForValidate(String code) {
        if (StringUtils.isBlank(code)) {
            return HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001;
        }
        HotelOrderBookErrorCodeEnum codeEnum = codeEnumMap.get(code);
        if (Objects.isNull(codeEnum)) {
            return HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001;
        }
        return codeEnum;
    }
}