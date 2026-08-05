package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author gaojin
 */
public class ElongSearchRatePlan {
    /**
     * 产品编号。价格计划id
     */
    @JsonProperty("RatePlanId")
    private String ratePlanId;
    /**
     * 产品名称
     */
    @JsonProperty("RatePlanName")
    private String ratePlanName;
    /**
     * 销售状态。false-不可销售（可能是满房、部分日期满房、缺少价格）、true-可销售
     */
    @JsonProperty("Status")
    private String status;
    /**
     * 销售房型编号。用于hotel.order.create中的入参RoomTypeId
     */
    @JsonProperty("RoomTypeId")
    private String roomTypeId;
    /**
     * 供应商房型附加名称。房型信息的补充说明
     */
    @JsonProperty("SuffixName")
    private String suffixName;
    /**
     * 供应商酒店编码
     */
    @JsonProperty("HotelCode")
    private String hotelCode;
    /**
     * 客人类型。All=统一价 Chinese=内宾价，需提示客人“须持大陆身份证入住”；
     * OtherForeign=外宾价，需提示客人“须持国外护照入住”；
     * HongKong=港澳台客人价，需提示客人“须持港澳台身份证入住”；
     * Japanese=日本客人价，需提示客人“须持日本护照入住”
     */
    @JsonProperty("CustomerType")
    private String customerType;
    /**
     * 房量限额
     * 入住时间内不能超售的最小值
     * 当大于0小于5时，表示目前仅剩的房量;0表示房量充足
     */
    @JsonProperty("CurrentAlloment")
    private String currentAlloment;
    /**
     * 否支持即时确认 Boolean
     */
    @JsonProperty("instantConfirmation")
    private String instantConfirmation;
    /**
     * SelfPay-前台现付、Prepay-预付
     */
    @JsonProperty("PaymentType")
    private String paymentType;
    /**
     * 对应的预订规则编号
     */
    @JsonProperty("BookingRuleIds")
    private String bookingRuleIds;
    /**
     * 对应的担保规则编号
     */
    @JsonProperty("GuaranteeRuleIds")
    private String guaranteeRuleIds;
    /**
     * 对应的预付规则编号
     */
    @JsonProperty("PrepayRuleIds")
    private String prepayRuleIds;
    /**
     * 对应的促销规则编号
     */
    @JsonProperty("DrrRuleIds")
    private String drrRuleIds;
    /**
      * 对应的增值服务编号
     */
    @JsonProperty("ValueAddIds")
    private String valueAddIds;
    /**
     * 礼品ID
     */
    @JsonProperty("GiftIds")
    private String giftIds;
    /**
     * 酒店特殊信息提示的编号
     */
    @JsonProperty("HAvailPolicyIds")
    private String hAvailPolicyIds;
    /**
     * 产品特性类型。可逗号分隔，目前取值： 3-限时抢购 4-钟点房 5-手机专享
     * 6-铂涛产品，下单需提供入住人身份证
     * 17-景酒打包产品，只能与景点门票打包销售，默认不吐出
     * 25-床位房（ 床位房类型此处已无效，判断床位房请解析RatePlanName或者Room节点的Name字段，其中只要有一个字段包含“床位”字样即为床位房 ）
     * 99-未知，艺龙内部使用，一般代理可忽略
     */
    @JsonProperty("ProductTypes")
    private String productTypes;
    /**
     * 是否今日特价
     * IsLastMinuteSale ==true的时候再判断StartTime和EndTime
     */
    @JsonProperty("IsLastMinuteSale")
    private String isLastMinuteSale;
    /**
     * 每天可以销售的开始时间
     */
    @JsonProperty("StartTime")
    private String startTime;
    /**
     * 每天可以销售的结束时间
     */
    @JsonProperty("EndTime")
    private String endTime;
    /**
     * 预定最少数量。默认值：1
     */
    @JsonProperty("MinAmount")
    private String minAmount;
    /**
     * 最少入住天数。默认值：1
     */
    @JsonProperty("MinDays")
    private String minDays;
    /**
     * 最多入住天数。默认值：365
     */
    @JsonProperty("MaxDays")
    private String maxDays;
    /**
     * 总价。已经通过DRR的计算可以直接显示给客人。价格为-1表示不能销售。
     */
    @JsonProperty("TotalRate")
    private String totalRate;
    /**
     * 日均价。已经通过DRR的计算可以直接显示给客人。价格为-1表示不能销售。
     */
    @JsonProperty("AverageRate")
    private String averageRate;
    /**
     * 促销前的日均价
     */
    @JsonProperty("AverageBaseRate")
    private String averageBaseRate;
    /**
     * 货币
     */
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    /**
     * 优惠券。暂无数据
     */
    @JsonProperty("Coupon")
    private String coupon;
    /**
     * 预付产品发票模式Hotel酒店开具;Elong艺龙开具;为空表示这个产品没有发票
     */
    @JsonProperty("InvoiceMode")
    private String invoiceMode;
    /**
     * 每天价格数组
     */
    @JsonProperty("NightlyRates")
    private List<ElongSearchNightlyRate> nightlyRates;
    /**
     * 产品唯一标识（API成单使用） 促销产品专用
     */
    @JsonProperty("Littlemajiaid")
    private String littlemajiaid;
    /**
     * 商品唯一标识  促销产品专用
     */
    @JsonProperty("GoodsUniqId")
    private String goodsUniqId;

    /**
     * 如果是铂涛产品，不能通过此字段判断，不管true还是false，都要传入身份证。
     * 如果不是铂涛产品，指该RatePlan在下单的时候，是否需要传入入住人的身份证号信息，如果该字段不为空且为true，则在成单时必须传入身份证号
     */
    @JsonProperty("NeedIdNo")
    private boolean needIdNo;
    /**
     * 艺龙需要过滤 值为 5047801（途家的供应商ID）
     */
    @JsonProperty("SupplyId")
    private String supplyId;

    /**
     * 0-无特殊验证要求（默认值）
     * 1-整个订单至少传一个身份证
     * 2-订单中每个房间至少传一个证件
     * 3-订单中每个房间至少传一个身份证
     * 4-每个客人传一个身份证
     */
    @JsonProperty("Identification")
    private String identification;

    /**
     * 表示本RatePlan是否为限价产品 ，限价产品必须按照艺龙给出的售价进行售卖，即按照TotalRate指定的价格卖给客人
     * false --- 非限价
     * true --- 限价
     */
    @JsonProperty("isPriceLimittedProduct")
    private Boolean isPriceLimittedProduct;
    /**
     * 限价类型
     */
    @JsonProperty("PriceLimitedType")
    private Integer priceLimitedType;

    /**
     * 餐食
     */
    @JsonProperty("meals")
    private ElongSearchMeals meals;

    /**
     * 可入住人数
     */
    @JsonProperty("xStayPeopleNum")
    private String xStayPeopleNum;

    /**
     * PrepayResult
     */
    @JsonProperty("PrepayResult")
    private ElongSearchPrepayResult prepayResult;

    /**
     * 1:特牌 2:金牌 3:银牌 4:蓝牌 0:非挂牌
     */
    @JsonProperty("HotelLevel")
    private String hotelLevel;

    /**
     * 供应商id	String	Y	国际特有字段(版本10.0)
     */
    @JsonProperty("SupplierId")
    private String supplierId;
    /**
     * 二级供应商id	String	Y	国际特有字段(版本10.0)
     */
    @JsonProperty("SubSupplierId")
    private String subSupplierId;
    /**
     * 商品库shopperid	String	Y	国际特有字段(版本10.0)
     */
    @JsonProperty("ShopperProductId")
    private String shopperProductId;
    /**
     * 是否支持专票 true支持 false不支持
     */
    @JsonProperty("SupportSpecialInvoice")
    private Boolean supportSpecialInvoice;
    /**
     * 膳食 参考Board节点
     * 国际特有字段
     */
    @JsonProperty("Board")
    private ElongBoard board;
    /**
     * 房间面积 国际特有字段
     */
    @JsonProperty("RoomSquareMetres")
    private String roomSquareMetres;
    /**
     * 床型描述
     */
    @JsonProperty("BedDescription")
    private String bedDescription;
    /**
     * 床型标准化描述 床型标准化后的文本-床型分组，国际特有字段
     */
    @JsonProperty("BedTypeAssociationalFilter")
    private String bedTypeAssociationalFilter;

    /**
     * 是否有窗	Boolean	Y	国际特有字段
     */
    @JsonProperty("HasWindow")
    private String hasWindow;
    /**
     * 吸烟偏好描述	String	Y	国际特有字段
     */
    @JsonProperty("SmokingDesc")
    private String smokingDesc;
    /**
     * 房间可住儿童年龄	Int	Y	国际特有字段
     */
    @JsonProperty("RoomChildAge")
    private String roomChildAge;
    /**
     * 房间最大入住人数	Int	Y	国际特有字段
     */
    @JsonProperty("RoomMaxPax")
    private String roomMaxPax;
    /**
     * 房间最大可住成人数	Int	Y	国际特有字段
     */
    @JsonProperty("AdultOccupancyPerRoom")
    private String adultOccupancyPerRoom;
    /**
     * 房间最大可住儿童数	Int	Y	国际特有字段
     */
    @JsonProperty("ChildrenOccupancyPerRoom")
    private String childrenOccupancyPerRoom;
    /**
     * 入住需知	String	Y	国际特有字段
     */
    @JsonProperty("CheckInInstructions")
    private String checkInInstructions;
    /**
     * 额外人员费用(附加费)	Decimal	Y	国际特有字段
     */
    @JsonProperty("ExtraPersonFee")
    private String extraPersonFee;
    /**
     * 额外人员费用(附加费人民币)	Decimal	Y	国际特有字段
     */
    @JsonProperty("ExtraPersonFeeRMB")
    private String extraPersonFeeRMB;
    /**
     * 床型信息	InterBedGroup[]	Y	参考InterBedGroup节点国际特有字段
     */
    @JsonProperty("BedGroups")
    private List<ElongInterBedGroup> bedGroups;
    /**
     * 网络描述	String	Y	国际特有字段
     */
    @JsonProperty("InternetDesc")
    private String internetDesc;
    /**
     * 另付税和服务费 参考AdditionalTax节点国际特有字段
     */
    @JsonProperty("AdditionalTax")
    private ElongAdditionalTax additionalTax;

    public String getBedDescription() {
        return bedDescription;
    }

    public void setBedDescription(String bedDescription) {
        this.bedDescription = bedDescription;
    }

    public ElongBoard getBoard() {
        return board;
    }

    public void setBoard(ElongBoard board) {
        this.board = board;
    }

    public String getRoomSquareMetres() {
        return roomSquareMetres;
    }

    public void setRoomSquareMetres(String roomSquareMetres) {
        this.roomSquareMetres = roomSquareMetres;
    }

    public String getBedTypeAssociationalFilter() {
        return bedTypeAssociationalFilter;
    }

    public void setBedTypeAssociationalFilter(String bedTypeAssociationalFilter) {
        this.bedTypeAssociationalFilter = bedTypeAssociationalFilter;
    }

    public String getHasWindow() {
        return hasWindow;
    }

    public void setHasWindow(String hasWindow) {
        this.hasWindow = hasWindow;
    }

    public String getSmokingDesc() {
        return smokingDesc;
    }

    public void setSmokingDesc(String smokingDesc) {
        this.smokingDesc = smokingDesc;
    }

    public String getRoomChildAge() {
        return roomChildAge;
    }

    public void setRoomChildAge(String roomChildAge) {
        this.roomChildAge = roomChildAge;
    }
