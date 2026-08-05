package cn.vetech.center.hotel.link.api.orderbook.dto;

import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.UserVipExtInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.HotelBedInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchGift;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 预订下单
 *
 * @author gaojin
 */
public class LinkHotelOrderBookDTO extends LinkHotelRateSearchDTO {
    /**
     * 房间数量,有几个房间数量,对应几个OrderRoom节点数组
     * 每一订单房间数量最好不要超过5间
     */
    @ApiModelProperty(value = "房间数量,有几个房间数量,对应几个OrderRoom节点数组,每一订单房间数量最好不要超过5间", dataType = "string")
    private String numberOfRooms;
    /**
     * 客人数量
     */
    @ApiModelProperty(value = "客人数量", dataType = "string")
    private String numberOfCustomers;
    /**
     * 最早到店时间
     * 1、两者都是必填字段,可让用户选择两个时间点,也可以只让客人选择最晚到店时间系统根据下面的规则计算出最早到店时间
     * 2、最早到店时间范围:入住日6:00(建议14:00,因一般酒店接待开始时间是14点)-23:59
     * 3、最晚到店时间范围:入住日7:00-23:59和次日1:00-6:00，都必须是整点或半点或23:59
     * 4、最早到店时间须晚于当前时间,最晚到店时间须晚于最早到店时间，一般相差3个小时
     * 5、如果客人到店时间是入住日期的第二天的00:00┅06:00之间,请配置最早到店时间为入住日期的23:59,最晚到店时间为入住日期的第二天的06:00
     */
    @ApiModelProperty(value = "最早到店时间", dataType = "string")
    private String earliestArrivalTime;
    /**
     * 最晚到店时间
     */
    @ApiModelProperty(value = "最晚到店时间", dataType = "string")
    private String latestArrivalTime;
    /**
     * 与供应商结算成本总价
     */
    @ApiModelProperty(value = "与供应商结算成本总价,RatePlan的TotalPrice * 房间数 * 天数,单位是元", dataType = "string")
    private String totalPrice;
    /**
     * 与供应商结算成本总价
     */
    @ApiModelProperty(value = "与供应商结算成本总价,RatePlan的TotalPrice * 房间数 * 天数,单位是元", dataType = "string")
    private String jylsh;
    /**
     * 实际收客户价格，没传CustomerPrice会按艺龙的卖价开票
     */
    @ApiModelProperty(value = "实际收客户价格，没传CustomerPrice会按艺龙的卖价开票", dataType = "string")
    private String customerPrice;
    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额", dataType = "string")
    private String subPrice;
    /**
     * 担保类型
     * 11必须担保12条件触发担保13无需担保
     */
    @ApiModelProperty(value = "担保类型,11必须担保12条件触发担保13无需担保", dataType = "string")
    private String guaranteeType;
    /**
     * 担保金额
     * 需要担保就传值，无线担保为空
     */
    @ApiModelProperty(value = "担保金额,需要担保就传值,无线担保为空", dataType = "string")
    private String guaranteePrice;
    /**
     * 给酒店备注
     */
    @ApiModelProperty(value = "给酒店备注", dataType = "string")
    private String noteToHotel;
    /**
     * 给房源商备注
     */
    @ApiModelProperty(value = "给房源商备注", dataType = "string")
    private String noteToSupply;
    /**
     * 客人信息
     */
    @ApiModelProperty(value = "客人信息", dataType = "string")
    private List<BookOrderRoom> orderRooms;
    /**
     * 多天价格
     */
    @ApiModelProperty(value = "多天价格", dataType = "string")
    private List<BookNightlyRate> nightlyRates;
    /**
     * All=统一价Chinese=内宾价OtherForeign=外宾价HongKong=港澳台客人价Japanese=日本客人价
     */
    @ApiModelProperty(value = "All=统一价Chinese=内宾价OtherForeign=外宾价HongKong=港澳台客人价Japanese=日本客人价", dataType = "string")
    private String customerType;
    /**
     * 货币类型
     * 请和获取价格的地方保持一致
     */
    @ApiModelProperty(value = "货币类型,请和获取价格的地方保持一致", dataType = "string")
    private String currencyCode;
    /**
     * 最晚到店时间,是否为次日,0-不是,1-是次日
     */
    @ApiModelProperty(value = "最晚到店时间,是否为次日,0-不是,1-是次日", dataType = "string")
    private String isTomorrow;
    /**
     * IP,211.151.230.212
     * 请提供真实的客人IP,将更好控制恶意订单,获取最终用户IP的时候注意区分用户是否使用了代理
     */
    @ApiModelProperty(value = "IP,211.151.230.212", dataType = "string")
    private String customerIPAddress;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人", dataType = "string")
    private BookContact contact;
//    /**
//     * 确认入住类型
//     * NotAllowedConfirm 不允许确认(合作伙伴自查订单状态后自行联系客人)
//     * SMS_cn  ----艺龙发短信给客人,出现订单问题的时候会主动联系
//     *  NoNeed -- 艺龙发短信给客人,出现订单问题的时候不主动联系
//     * 注：除了NotAllowedConfirm,其余的选项艺龙都会发送短信,下单时如果输入了邮箱那么都会发送邮件
//     */
//    @ApiModelProperty(value = "确认入住人类型,SMS_cn:发短信给客人,出现订单问题的时候会主动联系", dataType = "string")
//    private String confirmationType;
//    /**
//     * 是否已担保或已付款,开通了公司担保业务的合作伙伴才能使用该属性
//     */
//    @ApiModelProperty(value = "是否已担保或已付款,开通了公司担保业务的合作伙伴才能使用该属性", dataType = "string")
//    private String isGuaranteeOrCharged;
//    /**
//     * 供应商卡号,公司担保业务时用
//     */
//    @ApiModelProperty(value = "供应商卡号,公司担保业务时用", dataType = "string")
//    private String supplyCardNo;
//    /**
//     * 是否需要发票。前台自付订单发票由酒店开具/ true/false
//     */
//    @ApiModelProperty(value = "是否需要发票。前台自付订单发票由酒店开具/ true/false", dataType = "string")
//    private String isNeedInvoice;
    /**
     * 发票信息
     */
    @ApiModelProperty(value = "发票信息", dataType = "string")
    private BookInvoice invoice;
    /**
     * 信用卡,需要担保时用
     */
    @ApiModelProperty(value = "信用卡,需要担保时用", dataType = "string")
    private BookCreditCard creditCard;
    /**
     *
     */
    @ApiModelProperty(value = "押金金额", dataType = "string")
    private String yjje;
    /**
     *
     */
    @ApiModelProperty(value = "押金支付方式:1.线上支付押金；2.线下支付押金给房东", dataType = "string")
    private String yjzffs;
//    /**
//     * 当预付产品使用结算价的时候,如果发票模式是酒店开发票,需要传入销售给客人的最终价格
//     */
//    @ApiModelProperty(value = "当预付产品使用结算价的时候,如果发票模式是酒店开发票,需要传入销售给客人的最终价格", dataType = "string")
//    private String customerPrice;
    /**
     *
     */
    @ApiModelProperty(value = "本地所在城市", dataType = "string")
    private String szcs;

    /**********************相比cps新增字段***********************/
    /**
     * 纳税人编号
     */
    private String nsrsbh;

    /**
     * 支付单号
     */
    private String paymentNo;
    /**
     * 支付账户
     */
    private String zfzh;

    /**
     * 1.企业支付 2：壹网通 3微信 4银联 5支付宝
     */
    private String paymentChannel;


    /*************************START**以下是京东方宿舍专用字段********************************/
    /**
     * 入住现地名称 例如B7
     */
    private String payPlace;
    /**
     * 入住现地代码 例如 107
     */
    private String payPlaceId;
    /**
     * 姓名
     */
    private String employeeName;
    /**
     * 是否BOE员工 0-否/1-是
     */
    private String boeFlag;
    /**
     * 工号
     */
    private String employeeId;
    /**
     * 职级
     */
    private String bandId;
    /**
     * 性别 0-男/1-女
     */
    private String sex;
    /**
     * 差旅审批单号
     */
    private String approvalNo;
    /*************************END**以上是京东方宿舍专用字段********************************/

    /**
     * 0否 1接口协议 2托管协议
     */
    private String sfxydd = "0";

    /*************************对接港捷旅开票接口字段(此处开票相关字段后期删除，勿用！！！)********************************/
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 配送方式
     */
    private String psfs;
    /**
     * 发票抬头
     */
    private String psFptt;
    /**
     * 纳税人识别号
     */
    private String psNsrsbh;
    /**
     * 发票类型
     */
    private String psFplx;
    /**
     * 收件人
     */
    private String psUserName;
    /**
     * 邮箱
     */
    private String psEmail;
    /**
     * 电话
     */
    private String psPhone;
    /**
     * 地址
     */
    private String psAddress;
    /**
     * 注册地址
     */
    private String companyAddress;
    /**
     * 注册电话
     */
    private String companyPhone;
    /**
     * 开户银行
     */
    private String companyBankName;
    /**
     * 开户银行卡号
     */
    private String companyBankNo;


    /**
     * 预订人法人公司
     */
    private String ydFrgs;

    /**
     * 加密CPS数据 费控--->cps
     */
    private String cpsencryptData;
    /**
     * 加密cps摘要 费控--->cps
     */
    private String cpsMd5Summary;
    /**
     * 混合支付标识：空或0表示否，1表示是
     * 

1.当订单超标时，员工使多种支付方式支付时，此标识为1；
     * 

2.混合支付时，各种支付方式以zfList为准。在服务费加到支付金额的情况这里也会传1；
     */
    private String hhzfbs;
    /**
     * 支付集合
     */
    private List<BookZfxx> zfList;

    /**********************相比cps新增字段***********************/

    /**********************同程ext标准接口新增字段 begin***********************/
    /**
     * 服务费服务费
     */
    private String fwsfwf;

    /********************同程ext标准接口新增字段 end***********************/

    /**
     * 协议id；asms下单时使用
     */
    private String xyid;

    /**
     * 扩展字段，存json格式字符串 供应商下单标记扩展字段
     */
    private String gysxdbj;
    /**
     * 下单使用，会员价下单信息
     */
    private UserVipExtInfo userVipExtInfo;

    /**
     * 是否供服一体，1:是 ，非必传
     */
    private String clySfgfyt;
    /**
     * 发票备注前缀
     */
    private String fpbzqz;
    /**
     * FCB订票员编号
     */
    private String dpyyhbh;
    /**
     * 费控订单类别，用于区分是否是手工单下单
     * 1：查询预订 2：手工单
     */
    private String fkddlb;
    /**
     * 供应商发票类型，手工单下单入参
     */
    private String gysfplx;
    /**
     * 供应商发票费率，手工单下单入参
     */
    private String gysfpfl;
    /**
     * 房型名称
     */
    private String fxmc;
    /**
     * 手工单早餐说明
     */
    private String sgdzcsm;
    /**
     * 酒店电话
     */
    private String jddh;
    /**
     * 酒店地址
     */
    private String jddz;
    /**
     * 关联单号
     */
    private String gldh;
    /**
     * 差旅在线支付，1:是在线支付，   其他：月结
     */
    private String clzxzf;
    /**
     * 询价单下单新增，供应商报价单编号，如果是通过报价单下单的就会传这个值
     */
    private String gysbjdid;
    /**
     * 混付时必填，list 中支持两个，一个公账，一个个人，只有两笔支付
     */
    private List<MixPayInfo> mixPayInfoList;
    /**
     * 拓展字段，供应商验价接口返回，下单接口需要传
     */
    private String expand;
    /**
     * 异常下单标记    当传1时表示是国内酒店下单前验价失败且已经提醒过客户了，客户选择继续下单，后续验价失败不拦截
     */
    private String ycxdbj;
    /**
     * 预订人姓名
     */
    private String ydr;
    /**
     * 预订人保障级别
     */
    private String ydrbzjb;
    /**
     * 预付房费在线开票金额控制：0仅开订单在线支付部分（混合支付在线支付部分或全额在线支付金额），1全部房费（包含月结金额+在线支付金额），2仅开全额在线支付订单（混合支付或者月结支付无开票金额）
     */
    private String yjsfkykp;
    /**
     * cps询价单号
     */
    private String cpsInquiryId;
    /**
     * cps报价单号
     */
    private String cpsQuotationId;
    /**
     * cps报价详情id
     */
    private String cpsDemandCostId;
    /**
     * 订单来源语种
     */
    private String ddlyyz;
    /**
     * 床型信息
     */
    private HotelBedInfo bedInfo;
    /**
     * 平台服务费
     */
    private String ptfwf;
    /**
     * 权益信息，可选的权益信息，处理美团权益信息新增
     */
    private List<SearchGift> rights;
    /**
     * 预订人会员id，即cps平台会员id
     */
    private String bookerMemberId;
    /**
     * 抵扣积分
     */
    private int deductScore;
    /**
     * 积分单位，如：积分
     */
    private String scoreUnit;
    /**
     * 积分抵扣金额
     */
    private BigDecimal deductAmount;
    /**
     * 优惠券总金额
     */
    private BigDecimal couponAmount;
    /**
     * 优惠信息（优惠券+积分）
     */
    private List<BookDiscountInfo> discountInfoList;