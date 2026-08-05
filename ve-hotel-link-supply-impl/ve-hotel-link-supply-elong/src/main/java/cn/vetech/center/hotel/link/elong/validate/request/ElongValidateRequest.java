package cn.vetech.center.hotel.link.elong.validate.request;

import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 艺龙订单详情请求对象
 *
 * @author gaojin
 */
public class ElongValidateRequest extends ElongRequest {
    /**
     * 入住日期
     */
    @JsonProperty("ArrivalDate")
    private String arrivalDate;
    /**
     * 离店日期
     */
    @JsonProperty("DepartureDate")
    private String departureDate;
    /**
     * 最早到店时间
     */
    @JsonProperty("EarliestArrivalTime")
    private String earliestArrivalTime;
    /**
     * 最晚到店时间
     */
    @JsonProperty("LatestArrivalTime")
    private String latestArrivalTime;
    /**
     * 酒店编号
     */
    @JsonProperty("HotelId")
    private String hotelId;
    /**
     * 房型编号
     */
    @JsonProperty("RoomTypeID")
    private String roomTypeID;
    /**
     * 产品编号
     */
    @JsonProperty("RatePlanId")
    private String ratePlanId;
    /**
     * 总价
     */
    @JsonProperty("TotalPrice")
    private String totalPrice;
    /**
     * 房间数量
     */
    @JsonProperty("NumberOfRooms")
    private String numberOfRooms;
    /**
     * 马甲Id
     */
    @JsonProperty("LittleMajiaId")
    private String littleMajiaId;
    /**
     * 商品唯一标示
     */
    @JsonProperty("GoodsUniqId")
    private String goodsUniqId;

    /**
     * 儿童年龄	Int[]	Y	国际特有字段(版本10.0)
     */
    @JsonProperty("ChildAges")
    private String childAges;
    /**
     * 成人数	Int	N	国际特有字段(版本10.0)
     */
    @JsonProperty("NumberOfAdults")
    private Integer numberOfAdults;
    /**
     * 酒店code	String	N	国际特有字段(版本10.0)
     */
    @JsonProperty("HotelCode")
    private String hotelCode;
    /**
     * 供应商id	String	N	国际特有字段(版本10.0)
     */
    @JsonProperty("SupplierId")
    private String supplierId;
    /**
     * 二级供应商id	String	N	国际特有字段(版本10.0)
     */
    @JsonProperty("SubSupplierId")
private String subSupplierId;
    /**
     * 商品库shopperid	String	N	国际特有字段(版本10.0)
     */
    @JsonProperty("ShopperProductId")
    private String shopperProductId;
    /**
     * 币种	Enum	N	国际特有字段(版本10.0)
     */
    @JsonProperty("CurrencyCode")
    private String currencyCode;

    /**
     * 会员 openId
     */
    @JsonProperty("OpenId")
    private String openId;