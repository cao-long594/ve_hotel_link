package cn.vetech.center.hotel.link.api.hotelgetjdlb;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.hotelgetjdlb.dto.HotelPreference;
import cn.vetech.center.hotel.link.api.hotelgetjdlb.dto.JdGlszDto;
import cn.vetech.center.hotel.link.api.hotelgetjdlb.dto.PriorityHotel;
import cn.vetech.center.hotel.link.api.hotelmap.dto.HotelMapDistributionGetDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.VipParamInfoToCpsAsmsDTO;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

/**
 * 获取酒店列表DTO
 *
 * @author zhangheng
 * @version 2019-1-31
 */
@XmlRootElement(name = "request")
public class HotelLinkGetJdlbDTO extends LinkHotelDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 查询类型,0上架,1024下架,1023全部,5推荐,6热门,7精选,8周边
     */
    @ApiModelProperty(value = "查询类型", example = "")
    private String infoType;
    /**
     * 酒店中文名称
     */
    @ApiModelProperty(value = "酒店中文名称", example = "")
    private String jdzwmc;
    /**
     * 所在城市BH
     */
    @NotBlank
    @ApiModelProperty(value = "所在城市BH", required = true, example = "10458")
    private String szcs;
    /**
     * 入住日期
     */
    @NotBlank
    @ApiModelProperty(value = "入住日期：yyyy-MM-dd", required = true, example = "2019-01-31")
    private String ksrq;
    /**
     * 离店日期
     */
    @NotBlank
    @ApiModelProperty(value = "离店日期：yyyy-MM-dd", required = true, example = "2019-02-01")
    private String jsrq;
    /**
     * 房源编号
     */
    @ApiModelProperty(value = "房源编号")
    private String fybh;
    /**
     * 酒店品牌
     */
    @ApiModelProperty(value = "酒店品牌", example = "")
    private String jdpp;
    /**
     * 实报实销酒店品牌，注：若指定了实报实销酒店品牌，则该品牌的酒店不受价格范围限制（查询指定酒店集团/品牌除外），默认排序时，该品牌的酒店在价格范围内，则在第一梯队优先展示，其余则在第二梯队展示；
     */
    private List<String> actualReimburseHotelBrandIdList;
    /**
     * 酒店主题
     */
    @ApiModelProperty(value = "酒店主题", example = "")
    private String jdzt;
    /**
     * 商圈
     */
    @ApiModelProperty(value = "商圈", example = "")
    private String syq;
    /**
     * 行政区
     */
    @ApiModelProperty(value = "行政区", example = "")
    private String xzq;
    /**
     * 星级,2、3、4、5,多个用逗号隔开
     */
    @ApiModelProperty(value = "星级,2、3、4、5,多个用逗号隔开", example = "")
    private String xj;
    /**
     * 百度经度
     */
    @ApiModelProperty(value = "百度经度", example = "")
    private String bdjd;
    /**
     * 百度纬度
     */
    @ApiModelProperty(value = "百度纬度", example = "")
    private String bdwd;
    /**
     * 距离目的点公里,精确到3位小数
     */
    @ApiModelProperty(value = "距离目的点公里,精确到3位小数", example = "")
    private double jl;
    /**
     * 价格范围始
     */
    @ApiModelProperty(value = "价格范围始", example = "")
    private String jgs;

    /**
     * 差标
     */
    private Double travelStandard;
    /**
     * 价格范围止
     */
    @ApiModelProperty(value = "价格范围止", example = "")
    private String jgz;
    /**
     *
     */
    @ApiModelProperty(value = "按房型设施ID查询酒店,多个用逗号分隔", example = "")
    private String fxsslbjh;
    /**
     *
     */
    @ApiModelProperty(value = "按基础设施ID查询酒店,多个用逗号分隔", example = "")
    private String jdsslbjh;
    /**
     *
     */
    @ApiModelProperty(value = "按休闲设施ID查询酒店,多个用逗号分隔", example = "")
    private String jdxxsslbjh;
    /**
     *
     */
    @ApiModelProperty(value = "按服务ID查询酒店,多个用逗号分隔", example = "")
    private String jdfwlbjh;
    /**
     * 酒店主题类别
     */
    @ApiModelProperty(value = "酒店主题类别", example = "")
    private String jdztlb;
    /**
     * 酒店标签类别
     */
    @ApiModelProperty(value = "酒店标签类别", example = "")
    private String jdbqlb;
    /**
     * 排序
     */
    @ApiModelProperty(value = "0默认排序,+1最低门市价升序,-2星级降序,-1门市价降序,+2星级升序,+3距离升序,+4评分升序,-4评分降序", example = "")
    private String sortType;
    /**
     * 这里的start赋值方式：(pageDTO.getCurrent() - 1) * pageDTO.getSize()
     */
    @ApiModelProperty(value = "分页起始页码,从0开始", required = true, example = "")
    private int start = 0;
    /**
     * 分页每页条数
     */
    @ApiModelProperty(value = "分页每页条数", required = true, example = "20")
    private int count = 20;
    /**
     *
     */
    @ApiModelProperty(value = "供应商渠道", example = "")
    private String gyqd;
    /**
     *
     */
    @ApiModelProperty(value = "价格区间", example = "")
    private String jgqj;
    /**
     *
     */
    @ApiModelProperty(value = "设施服务", example = "")
    private String ssfw;
    /**
     *
     */
    @ApiModelProperty(value = "查询类型", example = "")
    private String arrTypeId;
    /**
     * 类型  0商圈 1行政区  2县级市 3poi 4关键字  5:酒店品牌 6：酒店集团
     */
    @ApiModelProperty(value = "查询类型", example = "")
    private String arrType;
    /**
     *
     */
    @ApiModelProperty(value = "酒店类型", example = "")
    private String hotelType;
    /**
     *
     */
    @ApiModelProperty(value = "协议酒店编号", example = "")
    private String xyjdid;
    /**
     * 酒店分类：0或空 全部，1 仅客户协议酒店，2仅CPS自签酒店，3客户协议和cps自签
     */
    @ApiModelProperty(value = "酒店分类：0或空 全部，1 仅客户协议酒店，2仅CPS自签酒店，3客户协议和cps自签", example = "")
    private String jdfl;
    /**
     * 是否查询单体协议酒店
     */
    @ApiModelProperty(value = "是否查询单体协议酒店", example = "")
    private String sfdt;

    /**
     * 支付方式 0现付 1预付
     */
    @ApiModelProperty(value = "支付方式 0现付 1预付", example = "")
    private String payment;
    /**
     * 平台
     */
    @ApiModelProperty(value = "平台", example = "")
    private String pt;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id", example = "")
    private String khid;
    /**
     * 请求渠道
     */
    @ApiModelProperty(value = "请求渠道  0:直销  1：分销  2：差旅", example = "")
    private String qqqd;//请求渠道  0:直销  1：分销  2：差旅
    /**
     * 渠道
     */
    @ApiModelProperty(value = "渠道", example = "")
    private String qd;
    /**
     * 商户编号
     */
    @ApiModelProperty(value = "商户编号", example = "")
    private String shbh;
    /**
     * 供应商编号，多个用逗号分隔
     */
    @ApiModelProperty(value = "供应商编号，多个用逗号分隔", example = "")
    private String cxgysbh;
    /**
     * 供应商控制类型  1：只查询cxgysbh里对应的供应商来源  2：全部
     */
    @ApiModelProperty(value = "供应商控制类型  1：只查询cxgysbh里对应的供应商来源  2：全部", example = "")
    private String gyskzlx;

    /**
     * 特殊标记供应商
     */
    @ApiModelProperty(value = "特殊标记供应商", example = "")
    private String tsbsgys;
    /**
     * 统计查询是否有价格 0是无价格 1是有价格
     */
    @ApiModelProperty(value = "统计查询是否有价格 0是无价格 1是有价格", example = "")
    private String sfyjg;
    /**
     * 企业的公司编号
     */
    @ApiModelProperty(value = "企业的公司编号", example = "")
    private String compid;
    /**
     * 县级市
     */
    @ApiModelProperty(value = "县级市", example = "")
    private String xjs;
    /**
     * 酒店集团编号
     */
    @ApiModelProperty(value = "酒店集团编号", example = "")
    private String jdjtbh;
    /**
     *
     */
    private List<JdGlszDto> glgzlist;
    /***************************************************B2G专用业务字段***********************************************************/
    private String jkpt;//用于判断是接口还是平台
    /**
     *
     */
    private String ygys;//因公因私预订
    /**
     * 符合差标酒店，当传1的时候，需要计算差标，用于列表查询价格范围，jgs=0，jgz=差标
     */
    private String fhcbjd;
    /**
     * 出行人id，多个逗号拼接，列表页面用于查询 符合差标 的逻辑，后台会从中获取最高职级的人去查询差标
     */
    private String cxrids;
    /**
     * （经度，维度在一个字段传，用英文逗号或者斜线隔开，例如112.021,30.231） 经度 ， 维度
     * 计算距离百度经纬度
     * （应用场景：1、计算当前位置距酒店距离；2、计算某地标比如：某城市市中心距酒店距离；），
     * 这个字段跟bdjd、bdwd这2个字段肯定不会同时出现
     */
    private String jsjlbdjwd;
    /**
     *
     */
    private String syfw;// 0或者空 代表全部酒店  1代表只获取精选酒店 2代表只获取推荐酒店 3代表只获取热门酒店
    private String xzqmc;//行政区名称
    private String jdppmc;//酒店品牌名称
    private String syqmc;//商业区名称

    /**
     * poi是酒店的时候查询  传jdid 把这个酒店排在前面
     */
    private String searchJdid;

    /**
     * 需求id:202106070322
     * 地级城市是否查询下级县市
     * 0/空代表是默认搜索下级县市；1代表否，不搜索下级城市
     */
    private String sfcxxjcs;
    /**
     * 指定酒店清单查询；(入参是酒店ID，多个英文逗号分隔，代表输出的只能是这些酒店ID里面的酒店)  add by xty  20211110新增
     */
    private String zdjdqdcx;
    /**
     * 申请单 酒店id 多个用，分割
     */
    private String sqdjdid;
    /**
     * 起始推荐价格
     */
    private BigDecimal recPriceStart;
    /**
     * 截止推荐价格
     */
    private BigDecimal recPriceEnd;
    /**
     * 适用人群，0、仅内宾  1、外宾适用（含内宾和港澳台） 2、港澳台客人适用（含内宾，除港澳台以外的客人不适用）3、香港客人适用（含内宾，除香港以外的客人不适用） 4、台湾客人适用（含内宾，除台湾以外的客人不适用）  5、澳门客人适用（含内宾，除澳门以外的客人不适用）
     */
    private String suitCrowd;
    /**
     * xyjdsfsglxz 协议酒店是否受过滤限制  endPrice 在商户配置中
     */
    private String xyjdsfsglxz;
    /**
     * 1:无论有没有最低价都返回指定酒店；0或空 有最低价时才返回指定酒店  指定酒店：searchJdid有值或者 arrTypeId的值为酒店ID
     */
    private String appointHotelReturnType;
    /**
     * 是否开启实时价格计算 如果是false cds这边不能计算 在费控侧计算最低价
     */
    private Boolean openRealTimePriceCalc;
    /**
     * 是否屏蔽cps价格
     */
    private String sfpbcpsjg;
    /**
     * 优先排序酒店
     */
    private List<PriorityHotel> priorityHotels;

    /**
     * 酒店偏好
     */
    private HotelPreference hotelPreference;
    /**
     * 用户vip信息
     */
    private VipParamInfoToCpsAsmsDTO vipParamInfoDTO;
    /**
     * 酒店地图参数
     */
    private HotelMapDistributionGetDTO hotelMapDTO;
    /**
     * 距离排序类型，0：直线距离、1：导航距离、2：步行距离
     */
    private String distanceSortingType;
    /**
     * 装修时间，Y0：今年；Y-1：去年；Y-2：前年；Y0,-3：近3年；Y0,-5：近5年；Y0,-8：近8年
     */
    private String decorateTime;
    /**
     * 开业时间，Y0：今年；Y-1：去年；Y-2：前年；Y0,-3：近3年；Y0,-5：近5年；Y0,-8：近8年
     */
    private String openingTime;
    /**
     * 开业/装修时间，Y0：今年；Y-1：去年；Y-2：前年；Y0,-3：近3年；Y0,-5：近5年；Y0,-8：近8年
     */
    private String openOrDecorateTime;
    /**
     * 住宿类型
     */
    private List<String> accTypeList;
    /**
     * 评分
     */
    private String hotelScore;

    public String getAppointHotelReturnType() {
        return appointHotelReturnType;
    }

    public void setAppointHotelReturnType(String appointHotelReturnType) {
        this.appointHotelReturnType = appointHotelReturnType;
    }

