package cn.vetech.center.hotel.link.api.ratesearch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 价格计划
 *
 * @author gaojin
 */
public class SearchRatePlan implements Serializable {
    /**
     * 日志记录类
     */
//    private static Logger logger = LoggerFactory.getLogger(SearchRatePlan.class);
    private static final long serialVersionUID = 1L;
    /**
     * 房源商编号 31200801
     */
    @ApiModelProperty(value = "房源商编号 31200801", dataType = "string")
    private String bh;
    /**
     * 房源商名称 艺龙
     */
    @ApiModelProperty(value = "房源商编号 艺龙", dataType = "string")
    private String fymc;
    /**
     * 房源商英文名称
     */
    @ApiModelProperty(value = "房源商英文名称", dataType = "string")
    private String fyjc;
    /**
     * 控制类型
     */
    @ApiModelProperty(value = "控制类型：0和空 无控制，1大于等于，2等于，3不可销售", dataType = "string")
    private String kzlx;
    /**
     * 房源商酒店ID
     */
    @ApiModelProperty(value = "房源商酒店ID", dataType = "string")
    private String hotelId;
//    /**
//     * 该房型报价来源,供应商ID
//     */
//    @ApiModelProperty(value = "房型报价来源,供应商ID", dataType = "string")
//    private String supplyId;
//    /**
//     * 该房型报价来源,供应商名称
//     */
//    @ApiModelProperty(value = "房型报价来源,供应商名称", dataType = "string")
//    private String supplyName;
    /**
     * 产品编号,价格计划ID
     */
    @ApiModelProperty(value = "产品编号,价格计划ID", dataType = "string")
    private String ratePlanId;
    /**
     * 产品名称,价格计划名称
     */
    @ApiModelProperty(value = "产品名称,价格计划名称", dataType = "string")
    private String ratePlanName;

    /**
     * 是否标记过滤 0:未标记  1:已标记
     */
    @ApiModelProperty(value = "是否标记过滤", dataType = "string")
    private String status = "0";
    /**
     * 销售房型编号,下单用,艺龙为RoomTypeId字段
     */
    @ApiModelProperty(value = "销售房型编号,下单用,艺龙为RoomTypeId字段", dataType = "string")
    private String roomId;
    /**
     * GOOD("0", "良好"),TENSION("1", "紧张"), FULL("2", "满房"),UNKNOW("3", "需确认");
     * currentAlloment 房量限额,入住时间内不能超售的最小值,当大于0小于5时,表示目前仅剩的房量,0表示房量充足
     */
    @ApiModelProperty(value = "首日房态", dataType = "string")
    private String firstStatus;
    /**
     * 综合房态
     */
    @ApiModelProperty(value = "综合房态", dataType = "string")
    private String allStatus = "0";
    /**
     * 房态说明
     */
    @ApiModelProperty(value = "房态说明", dataType = "string")
    private String allStatusDesc;
    /**
     * 房型信息的补充说明 11必须担保12条件触发担保13无需担保21不可取消22限时取消23免费取消24无取消规则
     */
    @ApiModelProperty(value = "房型信息的补充说明 11必须担保12条件触发担保13无需担保21不可取消22限时取消23免费取消24无取消规则", dataType = "string")
    private String suffixName;
    /**
     * 供应商酒店编码
     */
    @ApiModelProperty(value = "供应商酒店编码", dataType = "string")
    private String hotelCode;
    /**
     * 客人类型 All=统一价
     * Chinese=内宾价,需提示客人“须持大陆身份证入住”
     * OtherForeign=外宾价,需提示客人“须持国外护照入住”
     * HongKong=港澳台客人价,需提示客人“须持港澳台身份证入住”
     * Japanese=日本客人价,需提示客人“须持日本护照入住”
     */
    @ApiModelProperty(value = "客人类型", dataType = "string")
    private String customerType;
    /**
     * 首日房价
     */
    @ApiModelProperty(value = "首日房价", dataType = "string")
    private String firstPrice;
    /**
     * 供应商成本价:为预付,且是代理人房源才有
     */
    @ApiModelProperty(value = "供应商成本价:为预付,且是代理人房源才有", dataType = "string")
    private String firstCostPrice;
    /**
     * 首日优惠金额（目前仅用于前端展示）
     */
    @ApiModelProperty(value = "首日优惠金额", dataType = "string")
    private BigDecimal firstDisAmount;
    /**
     * 支付类型:0现付 1预付
     */
    @ApiModelProperty(value = "支付类型:0现付 1预付", dataType = "string")
    private String payment;
    /**
     * 产品特性类型,可逗号分隔,目前取值:3-限时抢购4-钟点房5-手机专享 6-铂涛产品，下单需提供入住人身份证 25-床位房 99-未知，艺龙内部使用，一般代理可忽略
     */
    @ApiModelProperty(value = "产品特性类型,可逗号分隔,目前取值:3-限时抢购4-钟点房5-手机专享 6-铂涛产品，下单需提供入住人身份证 25-床位房 99-未知，艺龙内部使用，一般代理可忽略", dataType = "string")
    private String productTypes;
    /**
     * 是否今日特价,IsLastMinuteSale==true的时候再判断StartTime和EndTime
     */
    @ApiModelProperty(value = "是否今日特价,IsLastMinuteSale==true的时候再判断StartTime和EndTime", dataType = "string")
    private String isLastMinuteSale;
    /**
     * 剩余可售房间数为空不限制
     */
    @ApiModelProperty(value = "剩余可售房间数", dataType = "string")
    private String syfjsl;
    /**
     * 每天可以销售的开始时间为空不限制
     */
    @ApiModelProperty(value = "销售的开始时间为空不限制", dataType = "string")
    private String startTime;
    /**
     * 每天可以销售的结束时间为空不限制
     */
    @ApiModelProperty(value = "销售的结束时间为空不限制", dataType = "string")
    private String endTime;
    /**
     * 销售规则转换前发票模式
     */
    private String zhqinvoiceMode;
    /**
     * 预定最少数量。默认值:1
     */
    @ApiModelProperty(value = "预定最少数量。默认值:1", dataType = "string")
    private String minAmount;
    /**
     * 预定最多数量。为空不限制
     */
    @ApiModelProperty(value = "预定最多数量。为空不限制", dataType = "string")
    private String maxAmount;
    /**
     * 最少入住天数。默认值:1
     */
    @ApiModelProperty(value = "最少入住天数。默认值:1", dataType = "string")
    private String minDays;
    /**
     * 最多入住天数。为空不限制
     */
    @ApiModelProperty(value = "最多入住天数。为空不限制", dataType = "string")
    private String maxDays;
    /**
     * 总价,价格为-1表示不能销售
     */
    @ApiModelProperty(value = "总价,价格为-1表示不能销售", dataType = "string")
    private String totalRate;
    /**
     * 总自签价格低价  这个字段供下单 入库成本价
     */
    @ApiModelProperty(value = "总自签价格低价", dataType = "string")
    private String totalZqjgdj;
    /**
     * 日均价,价格为-1表示不能销售
     */
    @ApiModelProperty(value = "日均价,价格为-1表示不能销售", dataType = "string")
    private String averageRate;
    /**
     * 促销前的日均价
     */
    @ApiModelProperty(value = "促销前的日均价", dataType = "string")
    private String averageBaseRate;
    /**
     * 货币
     */
    @ApiModelProperty(value = "货币", dataType = "string")
    private String currencyCode;
    /**
     * 币种名称
     */
    private String currencyName;
    /**
     * 入住人国籍说明
     */
    @ApiModelProperty(value = "入住人国籍说明", dataType = "string")
    private String rzrgjsm;
    /**
     * 货币兑换人民币汇率
     */
    @ApiModelProperty(value = "货币兑换人民币汇率", dataType = "string")
    private String currencyRate;
    /**
     * 优惠券,暂无数据
     */
    @ApiModelProperty(value = "优惠券,暂无数据", dataType = "string")
    private String coupon;
    /**
     * 含餐份数,B_1
     * 早B中L晚D其他Q
     * Q其他，在特定时间内才有
     */
    @ApiModelProperty(value = "含餐份数", dataType = "string")
    private String freeMeal;
    /**
     * 含餐份数描述，根据freeMeal转换
     */
    @ApiModelProperty(value = "含餐份数描述", dataType = "string")
    private String freeMealMs;
    /**
     * 特殊其他早餐描述
     * Q其他，在特定时间内才有
     */
    @ApiModelProperty(value = "特殊其他早餐描述", dataType = "string")
    private String freeMealDesc;
    /**
     * 预付产品发票模式Hotel酒店开具;Elong艺龙开具;为空表示这个产品没有发票
     */
    @ApiModelProperty(value = "预付产品发票模式Hotel酒店开具;Elong艺龙开具;为空表示这个产品没有发票", dataType = "string")
    private String invoiceMode;
    /**
     * 是否及时确认  0:延迟确认，1:即时确认
     */
    @ApiModelProperty(value = "是否及时确认", dataType = "string")
    private String instantConfirmation;

    /**
     * 挂牌价 增加挂牌价 挂牌价取第一天的第一个就可以，只做个参考（产品说的）
     * 需求id:202104160226
     */
    @ApiModelProperty(value = "挂牌价", dataType = "string")
    private String gpj;
    /**
     * 每天价格集合
     */
    @ApiModelProperty(value = "每天价格集合", dataType = "string")
    private List<SearchNightlyRate> nightlyRates;
    /**
     * 预订规则
     */
    @ApiModelProperty(value = "预订规则", dataType = "string")
    private List<SearchBookingRule> bookingRules;
    /**
     * 担保规则
     */
    @ApiModelProperty(value = "担保规则", dataType = "string")
    private List<SearchGuaranteeRule> guaranteeRules;
    /**
     * 预付规则
     */
    @ApiModelProperty(value = "预付规则", dataType = "string")
    private List<SearchPrepayRule> prepayRules;
    /**
     * 酒店特殊信息提示
     */
    @ApiModelProperty(value = "酒店特殊信息提示", dataType = "string")
    private List<SearchHAvailPolicy> havailPolicys;
    /**
     * 附加费
     */
    @ApiModelProperty(value = "附加费", dataType = "string")
    private List<SearchAdditional> additionals;
    /**
     * 供应商原始额外税费合计，多个是 和 的关系，   中国移动是直接展示酒店当地税费币种
     */
    private List<FeeInfo> totalAdditionalFeeList;
    /**
     * 额外税费合计，注意要转为 人民币，单位元
     */
    private BigDecimal ewsfhj;
    /**
     * 额外税费说明，  这个字段用来存 额外的税/费详情说明，目前只用在下单时入库 ，不用于页面展示，页面展示仅需要输出ewsfhj ，说明文本由前端自行拼接。
     */
    private String ewsfsm;
    /**
     * cps展示的额外税费说明，  这个字段用来存 额外的税/费详情说明，目前只用在下单时入库 ，不用于页面展示，页面展示仅需要输出ewsfhj ，说明文本由前端自行拼接。
     */
    private String cpsewsfsm;
    /**
     * 预付税费，税费合计（人民币）
     */
    private String sfhj;
    /**
     * 预付税费，税费说明（拼接的文本，不展示，考虑控润，导致页面上会对不上）
     */
    private String sfsm;
    /**
     * 预付税费，供应商原始税费币种
     */
    private String gysyssfbz;
    /**
     * 预付税费，供应商原始税费合计
     */
    private String gysyssfhj;

    /**
     * 礼品
     */
    @ApiModelProperty(value = "礼品", dataType = "string")
    private List<SearchGift> gifts;
    /**
     * 权益
     */
    @ApiModelProperty(value = "权益", dataType = "string")
    private List<SearchGift> rights;
    /**
     * 现付返点
     */
    @ApiModelProperty(value = "现付返点", dataType = "string")
    private double xffd;
    /**
     * 预付返点
     */
    @ApiModelProperty(value = "预付返点", dataType = "string")
    private double yffd;
    /**
     * 现付返现上限
     */
    @ApiModelProperty(value = "现付返现上限", dataType = "integer")
    private Double xffxsx;
    /**
     * 预付返现上限
     */
    @ApiModelProperty(value = "预付返现上限", dataType = "integer")
    private Double yffxsx;
    /**
     * 总房价：供应商返回的总房价
     */
    @ApiModelProperty(value = "总房价：供应商返回的总房价", dataType = "integer")
    private Double zfj;
    /**
     * 价格预订类型  为空或0 指标准预订流程 、1：查价格需要传入房间人数（例如好巧）这种类型需要弹出房间人数输入框实时拉取接口价格
     */
    @ApiModelProperty(value = "价格预订类型", dataType = "string")
    private String jgydlx;
    /**
     * 价格床型描述（目前国际酒店用）
     */
    @ApiModelProperty(value = "价格床型描述", dataType = "string")
    private String ratePlanCxms;
    /**
     * 发票信息 参考HotelFpxxEnum
     *  0 服务商开票
     *  1 酒店前台开票
     */
    private String fpxx;
    /**
     * 发票类型 参考HotelFplxEnum
     * 发票类型 0增值税专用发票 1增值税普通发票 2增值税专用和
     */
    private String fplx;
    /**
     * 发票费率
     */
    private String fpfl;//发票费率

    /**
     * 供应商的原始房型名称
     */
    @ApiModelProperty(value = "供应商的原始房型名称", dataType = "string")
    private String fyfxmc;

    /**
     * 房型描述
     */
    @ApiModelProperty(value = "房型描述", dataType = "string")
    private String fxDescription;
    /**
     * 楼层
     */
    @ApiModelProperty(value = "楼层", dataType = "string")
    private String fxFloor;
    /**
     * 面积
     */
    @ApiModelProperty(value = "面积", dataType = "string")
    private String fxRoomArea;
    /**
     * 宽带
     */
    @ApiModelProperty(value = "宽带", dataType = "string")
    private String fxBroadNet;
    /**
     * 床型
     */
    @ApiModelProperty(value = "床型", dataType = "string")
    private String fxBedType;
    /**
     * 床型描述
     */
    @ApiModelProperty(value = "床型描述", dataType = "string")
    private String fxBedDesc;
    /**
     * 房间最大入住人数
     */
    @ApiModelProperty(value = "房间最大入住人数", dataType = "string")
    private String fxCapacity;

    /**
     * 房型预订特别提示
     */
    private String fxydtbts;
    /**
     * 房型设施
     */
    private String fxss;
    /**
     * 价格原始房型id
     */
    private String jgjhfxid;
    /**
     * 酒店品质
     */
    private String jdpz;

    /**
     * 自供自采是否需要下单到CPS 0:否    1：是
     */
    @ApiModelProperty(value = "自供自采是否需要下单到CPS 0:否    1：是", dataType = "string")
    private String zgzcsfxdcps;
    /**
     * 1 接口协议 （CPS自供自采 OTA接口托管协议） 2本地单体协议 3集团协议
     */
    private String xyjglx;

    /**
     * CPS原始供应商编号 例如elong、 jltour
     */
    private String cpsysgys;

    /**
     * cps 原始的房源简称（供应商编号） 托管的也可
     */
    private String cpsfyjc;

    /**
     * CPS原始供应商名称，例如艺龙、深捷旅
     */
    private String cpsysgysmc;

    /**********************相比cps新增字段*************************/
    /**
     * 供应商平台 费控使用
     */
    private String gyspt;

    /**
     * 供应商返回顺序
     */
    private String fhsd="99";
    /**
     * 是否需要身份证   是否需要身份证预订0或者空代表不需要身份证，1必须身份证预订；
     */
    private String needIdentityCard ;

    /**********************相比cps新增字段*************************/

    //add by tyxiao 2020/12/17 协议酒店新增字段 begin
    /***
     * 接口协议类：12接口托管单体协议（指企业单个的协议酒店价格）；13接口托管集团协议（指企业的集团协议价格例如如家、亚朵等）
     */
    private String jkxylx;
    /**
     * 酒店办理入住时间
     */
    private String jdblrzsj;

    //add by tyxiao 2020/12/17 协议酒店新增字段 end
    /**
     * 【达利集团生产】协议酒店离店时间需要根据企业签署的实际协议，可进行配置离店时间
     *  2021/1/27 新增字段酒店办理离店时间
     */
    private String jdblldsj;

    /**
     * 参考HotelGysFplxEnum
     * 供应商发票类型 0 普票  1专票    2 普票或专票
     */
    private String gysfplx;
    /**
     * 供应商发票费率 0.06 代表6个点的发票
     */
    private String gysfpfl;

    /**
     *协议Id；asma端使用
     */
    private String xyid;

    /**
     * 扩展字段，存json格式字符串 供应商下单标记扩展字段
     */
    private String gysxdbj;
    /**
     * 是否cps预会员价，即非cps会员
     */
    private boolean cpsPreMemberPriceFlag;
    /**
     * 是否cps会员价
     */
    private boolean cpsMemberPriceFlag;
    /**
     * 是否会员价。   0或空:不是；1:是
     */
    private String sfhyj;
    /**
     * 会员标签说明
     */
    private String hybqsm;
    /**
     * 使用会员提示语
     */
    private String syhytsy;
    /**
     * 房源商会员信息
     */
    private SearchUserVipExtInfo searchUserVipExtInfo;
    /**
     * 自签房源协议类型：0:CPS自签；2：企业差旅协议
     */
    private String zqxylx;
    /**
     * 价格来源
     */
    private String jgly;
    /**
     * 接口协议类型（注：此字段可能为开发误加字段，后续可能删除！！！）
     */
    @Deprecated
    private String jgxylx;
    /**
     * 是否为托管供应商
     */
    private Boolean managed;
    /**
     * 订单确认时长描述
     */
    private String confirmInfo;
    /**
     * 是否开启在线退单 1:代表开启，可在线申请退费；2：代表C端不可申请退房
     */
    private String sfkzxtd;
    /**
     * 查询报价请求时间
     */
    private String requestTime;
    /**
     * 查询报价时间
     */
    private String returnTime;
    /**
     * 线下向酒店特殊支付类型
     * 6：离店支付
     * 7：入住后支付
     */
    private String tszflx;
    /**
     * 自签-企业协议是否匹配控润  1：匹配控润；0或者空：不匹配控润  默认0
     */
    private String qyxykr;
    /**
     * cps响应的请求id 这个作为入参传入到 查询报价接口 或者下单接口
     */
    private String cpsPreRepTraceid;
    /**
     * 总价（原始币种）
     */
    private FeeInfo originTotalRate;
    /**
     * 价格标签列表
     */
    private List<HotelPriceTag> priceTagList;
    /**
     * 床型列表，一个房型存在多种床型时，返回给C端，客人可选择床型预订，下单接口需要传这个床型信息
     * 每间客房均提供床型说明：针对不确定床型或者有多个床型的时候，页面要支持用户可以选择
     */
    private List<HotelBedInfo> bedInfoList;
    /**
     * 取消规则明细
     */
    private List<HotelLadderDeductionInfo> ladderDeductionInfoList;
    /**
     * 取消规则明细的补充说明，比如：供应商存在指定日期不可退款，比取消规则优先级高
     */
    private String ladderDeductionExtraDesc;
    /**
     * 供应商预订条款
     */
    private String gysydtk;
    /**
     * 是否打包价。   true:是，   其他：不是
     */
    private Boolean packagePrice;
    /**
     * 进入订单填写页要先调用验价接口，1：需要，其他不需要
     */
    private String priceValidationInfo;
    /**
     * 结算币种 存CNY 、HKD
     */
    private String settleCurrency;
    /**
     * 结算币种兑换人民币汇率
     */
    private BigDecimal settleCurrencyRate;
    /**
     * 结算币种保留小数位
     */
    private Integer settleCurrencyScale;
    /**
     * 是否需要联系人邮箱,   0或者空 代表不需要，1需要
     */
    private String needContactEmail;
    /**
     * 是否埋名        1：是，0或其他：否
     */
    private String hideIdentity;


