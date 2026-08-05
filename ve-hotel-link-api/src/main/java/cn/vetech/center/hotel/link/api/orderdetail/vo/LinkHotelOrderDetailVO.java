package cn.vetech.center.hotel.link.api.orderdetail.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单详
 *
 * @author SongJun 8963
 */
public class LinkHotelOrderDetailVO extends LinkHotelVO {
    /**
     * 入住日期
     */
    @ApiModelProperty(value = "入住日期", dataType = "string")
    private String checkInDate;
    /**
     * 离店日期
     */
    @ApiModelProperty(value = "离店日期", dataType = "string")
    private String checkOutDate;
    /**
     * 房源商返回的原始状态
     */
    @ApiModelProperty(value = "房源商返回的原始状态", dataType = "string")
    private String trueStatus;
    /**
     * 房源商返回的原始状态描述
     */
    @ApiModelProperty(value = "房源商返回的原始状态描述", dataType = "string")
    private String trueStatusMs;
    /**
     * 对用户展示的订单状态
     */
    @ApiModelProperty(value = "对用户展示的订单状态", dataType = "string")
    private String showStatus;
    /**
     * 实际总价,现付少住延住后,实际总价与订单上不相
     */
    @ApiModelProperty(value = "实际总价,现付少住延住后,实际总价与订单上不相", dataType = "string")
    private String totalPrice;
    /**
     * 入住人
     */
    @ApiModelProperty(value = "入住人", dataType = "string")
    private String contact;
    /**
     * 间夜数据
     */
    @ApiModelProperty(value = "间夜数据", dataType = "string")
    private List<DetailNightlyRate> nightlyRates;
    /**
     * 房间数量
     */
    @ApiModelProperty(value = "房间数量", dataType = "string")
    private String numberOfRooms;
    /**
     * 支付类型 0现付 1预付
     */
    @ApiModelProperty(value = "支付类型 0现付 1预付", dataType = "string")
    private String payment;
    /**
     * 担保状态
     */
    @ApiModelProperty(value = "担保状态", dataType = "string")
    private String guaranteeStatus;
    /**
     * 接口供应商退款金额，用于拉取艺龙携程订单退款金额,整数单位分，100代表一元
     */
    @ApiModelProperty(value = "", dataType = "string")
    private int jkgystkje;
    /**
     * 接口供应商退款/罚金说明
     */
    @ApiModelProperty(value = "", dataType = "string")
    private String jkgystksm;
    /**
     * 供应商罚金，用于拉取艺龙例如noshow产生扣款的金额；整数单位分，100代表一元
     */
    @ApiModelProperty(value = "", dataType = "string")
    private int jkgysfj;
    /**
     * 罚金或退款币种，三字码
     */
    @ApiModelProperty(value = "", dataType = "string")
    private String fjbz;
    /**
     * 房源商接口返回的信息
     */
    @ApiModelProperty(value = "房源商接口返回的信息", dataType = "string")
    private String hotelResponse;

    /**
     * 供应订单总价
     */
    @ApiModelProperty(value = "供应订单总价", dataType = "string")
    private String gyddzj;
    /**
     * 房东真实联系方式
     */
    @ApiModelProperty(value = "房东真实联系方式", dataType = "string")
    private String fdzslxfs;
    /**
     * 房屋详细地址
     */
    @ApiModelProperty(value = "房屋详细地址", dataType = "string")
    private String fwxydz;
    /**
     *
     */
    @ApiModelProperty(value = "押金金额额", dataType = "string")
    private String yjje;
    /**
     * //1.线上支付押金；2.线下支付押金给房东
     */
    @ApiModelProperty(value = "押金支付方式", dataType = "string")
    private String yjzffs;


    /**
     * 现在很多供应商可以输出酒店确认号，也就是酒店前台系统的订单号，有的客人需要知道这个号码，所以需要解析这个东西 并且要传到前端入库到订单表里面
     */
    @ApiModelProperty(value = "酒店确认号", dataType = "string")
    private String jdqrh;


    /*************************************相比cps新增字段********************************************/
    /**
     * 实际入住日期
     */
    @ApiModelProperty(value = "实际入住日期", dataType = "string")
    private String sjrzrq;
    /**
     * 实际离店日期
     */
    @ApiModelProperty(value = "实际离店日期", dataType = "string")
    private String sjldrq;

    /**
     * 实际间夜数
     */
    @ApiModelProperty(value = "实际间夜数", dataType = "string")
    private Integer sjjys;
    /**
     * 实际房费总价
     */
    @ApiModelProperty(value = "实际房费总价", dataType = "string")
    private String sjffzj;

    /**
     * String	拒单原因
     */
    @ApiModelProperty(value = "拒单原因", dataType = "string")
    private String cancelReason;
    /**
     * BigDecimal	取消罚金
     */
    @ApiModelProperty(value = "取消罚金", dataType = "string")
    private BigDecimal penaltyToCustomer;
    /**
     * Decimal	担保金额
     */
    @ApiModelProperty(value = "担保金额", dataType = "bigDecimal")
    private BigDecimal guaranteeRate;

    /*************************************相比cps新增字段********************************************/

    /*************************************本地订单编号查询外部订单编号新增********************************************/
    /**
     * 房源商订单号
     */
    @ApiModelProperty(value = "房源商订单号", dataType = "string")
    private String orderId;
    /**
     * 胜意订单ID
     */
    @ApiModelProperty(value = "胜意订单ID", dataType = "string")
    private String localOrderId;
    /*************************************本地订单编号查询外部订单编号新增********************************************/

    /**
     * 发票信息
     */
    @ApiModelProperty(value = "发票信息", dataType = "string")
    private DetailInvoice invoice;
    /**
     * 是否取消中   空或者0表示非取消中，1表示取消中
     */
    private String isCanceling;

    /**
     * 第三方（如中航结）支付流水号 (美团)
     * add by xiaotengyu 20210902
     */
    @JsonProperty("thirdPartyPayFlow")
    private String thirdPartyPayFlow;

    //新增字段 2021/12/21 CPS跳转B系统补单优化需求-费控手工单增加CPS 订单信息校验功能 begin
    /**
     * 城市ID
     */
    private String szcs;
    /**
     *  酒店ID
     */
    private String jdid;
    /**
     *是否担保
     */
    private String sfdb;
    /**
     * 采购商编号
     */
    private String cgshbh;
    /**
     * 发票信息集合
     */
    private List<InvoiceStatusNotifyVO> invoiceList;

    //新增字段 2021/12/21 CPS跳转B系统补单优化需求-费控手工单增加CPS 订单信息校验功能 end

    /**
     * 支付流水集合
     */
    private List<PaymentInfo> paymentInfoList;
    /**
     *
     */
    private String gysddh;

    //新增字段 2023-04-06 Link对CPS状态同步接口增加字段 供应商房型名称返回给CPS
    /**
     * 供应商酒店名称
     */
    private String gysjdmc;
    /**
     * 供应商房型名称
     */
    private String gysfxmc;
    /**
     * 供应商早餐数量
     */
    private String gyszc;
    /**
     * 商务卡卡号
     */
    private String uatpkh;
    /**
     * 房间信息集合
     */
    private List<HotelOrderRoomInfo> orderRoomInfoList;

    /**
     * 最晚免费取消时间
     */
    private String zwqxsj;

    /********************     处理那种CPS支付状态没有推送到费控的问题，订单状态同步的时候如果CPS支付了·费控没有支付，要在费控这边处理之前的CPS支付通知的逻辑 *******/
    /**
     * 付款否,0未付1已退2已支付
     */
    protected String fkf;
    /**
     * 外部交易流水号
     */
    private String wbjylsh;
    /**
     * 付款方式
     */
    private String fkfs;
    /**
     * 付款方式名称
     */
    private String fkfsmc;
    /**
     * 付款金额
     */
    private BigDecimal fkje;
    /********************     处理那种CPS支付状态没有推送到费控的问题，订单状态同步的时候如果CPS支付了·费控没有支付，要在费控这边处理之前的CPS支付通知的逻辑 *******/
    /**
     * 酒店地址
     */
    private String hotelAddress;
    /**
     * 酒店电话
     */
    private String hotelPhone;