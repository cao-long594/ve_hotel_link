package cn.vetech.center.hotel.link.elong.orderbook.request;

import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 艺龙订单预订请求对象
 *
 * @author gaojin
 */
public class ElongOrderBookRequest extends ElongRequest {
    /**
     * 合作伙伴订单确认号
     * 合作伙伴订单号
     * 不同的订单这个值不一样，同一个订单发送多次请求也须用同一个值
     * 多次发送请求间隔需要在45秒以上。
     */
    @JsonProperty("AffiliateConfirmationId")
    private String affiliateConfirmationId;
    /**
     * 酒店编号
     */
    @JsonProperty("HotelId")
    private String hotelId;
    /**
     * 房型编号
     * 注意区分RoomId和RoomTypeId
     * 如果为实时搜索模式，对应的是hotel.list、hotel.detail接口中的RatePlan.RoomTypeId
     */
    @JsonProperty("RoomTypeId")
    private String roomTypeId;
    /**
     * 产品编号
     */
    @JsonProperty("RatePlanId")
    private String ratePlanId;
    /**
     * 入住日期
     * 如果是凌晨入住，那么入住日期是前一天
     */
    @JsonProperty("ArrivalDate")
    private String arrivalDate;
    /**
     * 离店日期
     */
    @JsonProperty("DepartureDate")
    private String departureDate;
    /**
     * 客人类型
     * All=统一价
     * Chinese=内宾价
     * OtherForeign=外宾价
     * HongKong=港澳台客人价
     * Japanese=日本客人价
     * 需要和RatePlanId对应的RatePlan中的该属性一致
     * 参考hotel.list#RatePlan、hotel.detail#RatePlan、hotel.data.rp#RatePlan
     */
    @JsonProperty("CustomerType")
    private String customerType;
    /**
     * 付款类型
     * SelfPay-前台现付、Prepay-预付
     */
    @JsonProperty("PaymentType")
    private String paymentType;
    /**
     * 房间数量
     * 有几个房间，对应OrderRoom数组节点数量，每一订单房间数量最好不要超过5间
     */
    @JsonProperty("NumberOfRooms")
    private String numberOfRooms;
    /**
     * 客人数量
     * 客人数量需要大于等于房间数量
     */
    @JsonProperty("NumberOfCustomers")
    private String numberOfCustomers;
    /**
     * 最早到店时间
     * 1、两者都是必填字段,可让用户选择两个时间点，也可以只让客人选择最晚到店时间，系统根据下面的规则计算出最早到店时间。
      * 2、最早到店时间范围：入住日7:00(建议14:00,因一般酒店接待开始时间是14点)-23:59；
     * 最晚到店时间范围：入住日7:00-23:59和次日1:00-6:00;都必须是整点或半点或23:59。
     * 3、最早到店时间须晚于当前时间, 最晚到店时间须晚于最早到店时间，一般相差3个小时。
     * 4、如果客人凌晨预订凌晨入住，则入住的是前一天的房间，须设置入住日期为前一天，
     * 最早到店时间为入住日期的23:59，最晚到店时间为入住日期的第二天的06:00。
     * 5、最早到店和最晚到店时间是完整的日期时间类型，如2017-03-16 13:06:30，不要只传入时间13:06:30，否则会报错。
     */
    @JsonProperty("EarliestArrivalTime")
    private String earliestArrivalTime;
    /**
     * 最晚到店时间
     */
    @JsonProperty("LatestArrivalTime")
    private String latestArrivalTime;
    /**
     * 货币类型
     * 请和获取价格的地方保持一致
     */
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    /**
     * 总价
     * RatePlan的TotalPrice * 房间数 * 天数；单位是元
     */
    @JsonProperty("TotalPrice")
    private String totalPrice;
    /**
     * 客人访问IP
     */
    @JsonProperty("CustomerIPAddress")
    private String customerIPAddress;
    /**
     * 是否已担保或已付款
     * 开通了公司担保业务的合作伙伴才能使用该属性
     * 当贵公司确定对客人扣款成功后，才可以设置为true，否则设置为false
     */
    @JsonProperty("IsGuaranteeOrCharged")
    private String isGuaranteeOrCharged;
    /**
     * 确认类型
     * NotAllowedConfirm 不允许确认(合作伙伴自查订单状态后自行联系客人)
     * SMS_cn  ----艺龙发短信给客人,出现订单问题的时候会主动联系
     *  NoNeed -- 艺龙发短信给客人,出现订单问题的时候不主动联系
     * 注：除了NotAllowedConfirm，其余的选项艺龙都会发送短信，下单时如果输入了邮箱那么都会发送邮件
     */
    @JsonProperty("ConfirmationType")
    private String confirmationType;
    /**
     * 给酒店备注
     */
    @JsonProperty("NoteToHotel")
    private String noteToHotel;
    /**
     * 给艺龙备注
     */
    @JsonProperty("NoteToElong")
    private String noteToElong;
    /**
     * 是否需要发票
     * 前台现付订单发票由酒店开具；
     * 预付订单的发票有两种开具方式：收款方开具或酒店开具。
     * 收款方开具发票的，艺龙可提供代开服务，需联系商务开通相关权限
     */
    @JsonProperty("IsNeedInvoice")
    private String isNeedInvoice;
    /**
     * 客人信息
     * 该节点的个数应和NumberOfRooms相等
     */
    @JsonProperty("OrderRooms")
    private List<ElongBookOrderRoom> orderRooms;
    /**
     * 发票信息
     * 当IsNeedInvoice为true时填写。 将由普通快递投递
     */
    @JsonProperty("Invoice")
    private ElongBookInvoice invoice;
    /**
     * 联系人
     */
    @JsonProperty("Contact")
    private ElongBookContact contact;
    /**
     * 信用卡
     * 担保订单和预付订单才须传信用卡。
     * 如果IsCreateOrderOnly或IsGuaranteeOrCharged为true，则一定不能传信用卡信息
     */
    @JsonProperty("CreditCard")
    private ElongBookCreditCard creditCard;
    /**
     * 仅创建订单
     * 需要请求的版本大于v1.20
     *  true - 表示本次请求只创建订单，不提供支付信息
     * 订单创建成功后，请求者再通过hotel.order.pay提供支付信息
     * false - 订单信息和支付一起传入
     */
    @JsonProperty("IsCreateOrderOnly")
    private String isCreateOrderOnly;
    /**
     * 销售给客人的最终价格
     * 当预付产品使用结算价的时候，如果发票模式是酒店开发票，需要传入销售给客人的最终价格
     * 传入的是人民币的值，非人民币报价的产品需要自行先根据艺龙汇率进行转换
     * 担保订单不需要传入这个字段。
     */
    @JsonProperty("CustomerPrice")
    private String customerPrice;
    /**
     * 订单数据校验
     * 对早餐、担保金额和取消时间进行校验, 传入代表校验，否则不校验
     */
    @JsonProperty("OrderValidation")
    private ElongBookOrderValidation orderValidation;
//    /**
//     * 促销信息
//     * 优惠信息，仅为提示信息，一般不需要。前台自付是返现，预付是立减
//     */
//    @JsonProperty("Coupon")
//    private CouponInfo coupon;
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
     * 会员openId
     */
    @JsonProperty("OpenId")
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getChildAges() {
        return childAges;
    }

    public void setChildAges(String childAges) {
        this.childAges = childAges;
    }

    public Integer getNumberOfAdults() {
        return nu