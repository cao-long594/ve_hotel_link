package cn.vetech.center.hotel.link.elong.orderdetail.response;

import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchDayMeal;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author gaojin
 */
public class ElongOrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单编号
     */
    @JsonProperty("OrderId")
    private String orderId;
    /**
     * 酒店编号
     */
    @JsonProperty("HotelId")
    private String hotelId;
    /**
     * 酒店名称
     */
    @JsonProperty("HotelName")
    private String hotelName;
    /**
     * 房型编号
     */
    @JsonProperty("RoomTypeId")
    private String roomTypeId;
    /**
     * 房型名称
     */
    @JsonProperty("RoomTypeName")
    private String roomTypeName;
    /**
     * 产品编号
     */
    @JsonProperty("RatePlanId")
    private String ratePlanId;
    /**
     * 产品名称
     */
    @JsonProperty("RatePlanName")
    private String ratePlanName;
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
     * 订单状态
     * A-已确认,B-NO SHOW, B1-有预定未查到,B2-待查,B3-暂不确定,C-已结帐
     * D-删除,E-取消,F-已入住,G-变价,H-变更,N-新单,O-满房,S-特殊
     * U-特殊满房,V-已审,Z-删除,另换酒店
     */
    @JsonProperty("Status")
    private String status;
    /**
     * 对用户展示的订单状态
     * 请将这个状态展示给客人；某些状态下的详细信息会出现在NoteToGuest。
     * 1 -- 担保失败,2 -- 等待担保,4 -- 等待确认,8 -- 等待支付,16 -- 等待核实入住
     * 32 -- 酒店拒绝订单,64 -- 未入住,128 -- 已经离店,256 -- 已经取消,512 -- 已经确认
     * 1024 -- 已经入住,2048 -- 正在担保-处理中,4096 -- 正在支付-处理中,8192 - 支付失败
     */
    @JsonProperty("ShowStatus")
    private String showStatus;
    /**
     * 下一次确认反馈时间点
     * 当ShowStatus=4等待确认的时候，系统会在这个时间点前更新确认相关的内容
     * 如果感觉这个时间点太长，可以调用hotel.order.promote进行催确认
     */
    @JsonProperty("ConfirmPoint")
    private String confirmPoint;
    /**
     * 客人类型
     * All=统一价
     * Chinese =内宾价，需提示客人“须持大陆身份证入住”；
     * OtherForeign =外宾价，需提示客人“须持国外护照入住”；
     * HongKong   =港澳台客人价，需提示客人“须持港澳台身份证入住”；
     * Japanese=日本客人价，需提示客人“须持日本护照入住”
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
     * 给客人的备注
     */
    @JsonProperty("NoteToGuest")
    private String noteToGuest;
    /**
     * 订单产生的罚金
     */
    @JsonProperty("PenaltyToCustomer")
    private String penaltyToCustomer;
    /**
     * 罚金货币类型
     */
    @JsonProperty("PenaltyCurrencyCode")
    private String penaltyCurrencyCode;
    /**
     * 预订时间
     */
    @JsonProperty("CreationDate")
    private String creationDate;
    /**
     * 当前是否可以取消
     */
    @JsonProperty("IsCancelable")
    private String isCancelable;
    /**
     * 最晚取消时间
     */
    @JsonProperty("CancelTime")
    private String cancelTime;
    /**
     * 是否有发票信息
     */
    @JsonProperty("HasInvoice")
    private String hasInvoice;
    /**
     * 预付订单的发票开具模式
     * Elong-艺龙开发票、Hotel-酒店开发票
     * 前台自付产品都是酒店开发票，这里的过滤是针对预付产品
     * 如果结果中的InvoiceMode为空表示不开发票
     * 需要注意Elong艺龙开发票其实是艺龙可以提供代开发票服务，如果需要开通，请联系商务
     */
    @JsonProperty("InvoiceMode")
    private String invoiceMode;
    /**
     * 换算为人民币的订单总卖价
     */
    @JsonProperty("TotalPriceExchanged")
    private String totalPriceExchanged;
    /**
     * 换算为人民币的订单总底价
     */
    @JsonProperty("TotalCostPriceExchanged")
    private String totalCostPriceExchanged;
    /**
     * 是否及时确认
     */
    @JsonProperty("IsInstantConfirm")
    private String isInstantConfirm;
    /**
     * 代理自己的订单号
     */
    @JsonProperty("AffiliateConfirmationId")
    private String affiliateConfirmationId;
    /**
     * 预付订单线下退款金额
     */
@JsonProperty("RefundAmount")
    private String refundAmount;
    /**
     * 总价
     */
    @JsonProperty("TotalPrice")
    private String totalPrice;
    /**
     * 销售给客人的最终价格
     */
    @JsonProperty("CustomerPrice")
    private String customerPrice;
    /**
     * 房间数量
     */
    @JsonProperty("NumberOfRooms")
    private String numberOfRooms;
    /**
     * 错误信息
     */
    @JsonProperty("ErrorMsg")
    private String errorMsg;
    /**
     *
     */
    @JsonProperty("OrderRooms")
    private List<OrderRoom> orderRooms;//房间信息
    /***
     * 信用卡信息
     */
    @JsonProperty("CreditCard")
    private ElongCreditCard creditCard;
    /**
     * 多次退款详情
     */
    @JsonProperty("refundDetail")
    private ElongRefundDetail refundDetail;

    /**
     * 房型名称
     */
    @JsonProperty("RoomName")
    private String roomName;

    /**
     * 餐食
     */
    @JsonProperty("Meals")
    private List<ElongSearchDayMeal> dayMealTable;
    /**
     * 订单关联的酒店信息
     */
    @JsonProperty("OrderHotel")
    private ElongOrderHotel orderHotel;