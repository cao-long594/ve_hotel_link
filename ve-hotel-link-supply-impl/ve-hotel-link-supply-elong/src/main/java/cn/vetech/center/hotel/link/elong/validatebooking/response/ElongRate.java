package cn.vetech.center.hotel.link.elong.validatebooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2025/10/15 15:09
 */
public class ElongRate {
    /**
     * 酒店编号	String(8)	N	这几个属性是业务主键
     */
    @JsonProperty("HotelID")
    private String hotelID;
    /**
     * 房型编号	String(10)	N
     */
    @JsonProperty("RoomTypeId")
    private String roomTypeId;
    /**
     * 产品编号	Int	N
     */
    @JsonProperty("RateplanId")
    private String rateplanId;
    /**
     * 开始时间	Date	N
     */
    @JsonProperty("StartDate")
    private String startDate;
    /**
     * 结束时间	Date	N
     */
    @JsonProperty("EndDate")
    private String endDate;
    /**
     * 酒店编码	String(8)	Y	v1.13新增
     */
    @JsonProperty("HotelCode")
    private String hotelCode;
    /**
     * 状态	Boolean	N	false--无效、true--有效
     */
    @JsonProperty("Status")
    private String status;
    /**
     * 平日卖价	Decimal	N	-1代表此房无价，无价和满房都不能进行预订
     */
    @JsonProperty("Member")
    private String member;
    /**
     * 周末卖价	Decimal	N	同上，在周末时使用此价格，周末设置参考hotel.data.rp接口
     */
    @JsonProperty("Weekend")
    private String weekend;
    /**
     * 平日结算价	Decimal	N	同上，开通了结算价模式的接入方才可以使用
     */
    @JsonProperty("MemberCost")
    private String memberCost;
    /**
     * 周末结算价	Decimal	N	同上，开通了结算价模式的接入方才可以使用
     */
    @JsonProperty("WeekendCost")
    private String weekendCost;
    /**
     * 加床价	Decimal	Y	V1.01新增 -1代表不能加床，0-免费加床，大于0表示加床的费用
     */
    @JsonProperty("AddBed")
    private String addBed;
    /**
     * 价格ID	Long	Y	V1.08新增
     */
    @JsonProperty("PriceID")
    private String priceID;
    /**
     * 货币类型	String	Y	V1.08新增     参考Currency
     */
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    /**
     * 每间的同程促销明细	UsedPromotionDayRoomValue[]	Y	每间的同程促销明细,参考 UsedPromotionDayRoomValue节点
     */
    @JsonProperty("UsedPromotionDayRoomValues")
    private List<ElongUsedPromotionDayRoomValue> usedPromotionDayRoomValues;
