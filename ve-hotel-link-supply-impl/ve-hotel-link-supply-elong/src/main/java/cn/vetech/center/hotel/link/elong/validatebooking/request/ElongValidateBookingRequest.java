package cn.vetech.center.hotel.link.elong.validatebooking.request;

import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2025/9/29 17:26
 */
public class ElongValidateBookingRequest extends ElongRequest {
    /**
     * 入住日期
     * 要求使用yyyy-MM-dd格式, 其他格式不保证永久可用，例如:2022-12-09，其他格式不保证持续支持
     */
    @JsonProperty("ArrivalDate")
    private String arrivalDate;
    /**
     * 离店日期
     * 要求使用yyyy-MM-dd格式, 其他格式不保证永久可用，例如:2022-12-09，其他格式不保证持续支持
     */
    @JsonProperty("DepartureDate")
    private String departureDate;
    /**
     * 酒店编号
     */
    @JsonProperty("HotelId")
    private String hotelId;
    /**
     * 酒店编码
     */
    @JsonProperty("HotelCode")
    private String hotelCode;
    /**
     * 展示房型编号
     * 允许为空，当传入时会校验房型编号绑定关系
     */
    @JsonProperty("RoomId")
    private String roomId;
    /**
     * 销售房型编码
     */
    @JsonProperty("RoomTypeID")
    private String roomTypeID;
    /**
     * 产品编号
     */
    @JsonProperty("RatePlanId")
    private String ratePlanId;
    /**
     * 支付类型
     * SelfPay--前台自付
     * Prepay--预付
     */
    @JsonProperty("PaymentType")
    private String paymentType;
    /**
     * 返回的价格是否通过DRR计算促销后的
     * 默认为false；如果为true则返回的Rates节点里面的价格为DRR计算后的促销后的，false则为原始价格。促销产品调用时，需要透传该字段。可全部产品传true
     */
    @JsonProperty("IsRatesWithDRR")
    private Boolean isRatesWithDRR;
    /**
     * 调用监控
     * 正常用户调用不用传，批量调用传入32，方便艺龙方对接口进行监控。
     * 正常用户调用指的是由用户下单流程触发的校验接口调用，批量调用、非正常用户调用指分销商自己进行踩雷策略或其他操作引起的校验接口调用。
     */
    @JsonProperty("MethodType")
    private String methodType;
    /**
     * 马甲Id
     * 从hotel.detail接口获取，用于促销，促销产品调用时，需要透传该字段；
     * 新接入搜索模式必传；建议历史已接入搜索模式分销商也将此字段全部传回；国内酒店马甲Id不再有30分钟限制
     * 搜索模式所有产品必传
     */
    @JsonProperty("LittleMajiaId")
    private String littleMajiaId;
    /**
     * 商品唯一标示
     * 从hotel.detail接口获取，用于促销，促销产品调用时，需要透传该字段；
     * 新接入搜索模式必传；建议历史已接入搜索模式分销商也将此字段全部传回
     * 搜索模式所有产品必传
     */
    @JsonProperty("GoodsUniqId")
    private String goodsUniqId;
    /**
     * 房间数量
     * 当促销产品调用时，需要传如具体房间数量
     */
    @JsonProperty("NumberOfRooms")
    private String numberOfRooms;
    /**
     * 最早到店时间
     * 请传入最早到店时间和最晚到店时间（不传时默认逻辑是：当天预订时，当前时间>=23点，最早最晚到店时间赋值为当天的23点59分，当前时间<23点时，最早到店时间为当前时间+30分钟，最晚到店时间为当前时间+60分钟；非当天预订时，最早到店时间为入住日的14点，最晚到店时间为入住日的15点）. 要求使用yyyy-MM-dd HH:mm:ss格式 其他格式不保证永久可用，注意最早到店时间需要在hotel.static.info接口的ArrivalTime和LatestArrivalTime之间
     */
    @JsonProperty("EarliestArrivalTime")
    private String earliestArrivalTime;
    /**
     * 最晚到店时间
     * 请传入最早到店时间和最晚到店时间，请注意保证搜索时传入此参数与试单和成单时一致，否则对应担保规则结果会不尽相同，到店时间更改，担保类型为到店时间担保的订单取消规则和担保规则均可能发生变化. 要求使用yyyy-MM-dd HH:mm:ss格式 其他格式不保证永久可用，注意最早到店时间需要在hotel.static.info接口的ArrivalTime和LatestArrivalTime之间且小于最早到店时间
     */
    @JsonProperty("LatestArrivalTime")
    private String latestArrivalTime;
    /**
     * 会员openId
     */
    private String openId;