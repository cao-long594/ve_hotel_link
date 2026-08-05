package cn.vetech.center.hotel.link.api.hotelgetjdlb.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * 获取酒店列表
 *
 * @author zhangheng
 * @version 2019-1-31
 */
public class HotelJdb {
    /**
     * 酒店id
     */
    private String jdid;
    /**
     * 房源商的酒店ID
     */
    private String hotelid;
    /**
     * 酒店实际来源1:中航信2:代理人3:ASMS4:艺龙5:携程6捷旅7假日
     */
    private String jdgygx;
    /**
     * 酒店资料维护的供应商id
     */
    private String jdcsly;
    /**
     * 状态 3：已上架 4：已下架 6已删除
     */
    private String zt;
    /**
     * 酒店查询次数
     */
    private String jdcxcs;
    /**
     * 酒店中文名称
     */
    private String jdzwmc;
    /**
     * 酒店英文名称
     */
    private String jdywmc;
    /**
     * 酒店星级
     */
    private String xj;
    /**
     * 酒店推荐星级
     */
    private String tjxj;
    /**
     * 推荐星级名称
     */
    private String xjName;
    /**
     * 是否推荐星级0否1是
     */
    private String istjxj;
    /**
     * 酒店品牌
     */
    private String jdpp;
    /**
     * 酒店地址
     */
    private String jddz;
    /**
     * 所在国家
     */
    private String szgj;
    /**
     * 所在省份
     */
    private String szsf;

    /**
     * 所在省份
     */
    private String szsfzwmc;
    /**
     * 所在城市
     */
    private String szcs;
    /**
     * 行政区
     */
    private String xzq;
    /**
     * 商业区
     */
    private String syq;
    /**
     * 所在城市名称
     */
    private String szcsmc;
    /**
     * 所在行政区名称
     */
    private String xzqmc;
    /**
     * 所在商圈名称
     */
    private String syqmc;
    /**
     * 固定电话
     */
    private String gddh;
    /**
     * 传真号码
     */
    private String czhm;
    /**
     * 酒店主图
     */
    private String jdzy;
    /**
     * 最低价 num
     */
    private String zdj;
    /**
     * 原始最低价
     */
    private String oriZdj;
    /**
     * 艺龙房型对比 num
     */
    private String elfxbl;
    /**
     * 酒店开业时间
     */
    private String kysj;
    /**
     * 装修时间
     */
    private String zxsj;
    /**
     * 酒店基础设施列表集合
     */
    private String jdjcsslbjh;
    /**
     * 酒店休闲设施列表集合
     */
    private String jdxxsslbjh;
    /**
     * 房型设施列表集合
     */
    private String fxsslbjh;
    /**
     * 酒店服务列表集合
     */
    private String jdfwlbjh;
    /**
     * 特别提示
     */
    private String tbts;
    /**
     * 酒店简介
     */
    private String jdjj;
    /**
     * 酒店主题
     */
    private String jdztlb;
    /**
     * 酒店标签
     */
    private String jdbqlb;

    /**
     * 酒店标签名称列表
     */
    private List<String> jdbqmclb;
    /**
     * 适用人群，0、仅内宾  1、外宾适用（含内宾和港澳台） 2、港澳台客人适用（含内宾，除港澳台以外的客人不适用）3、香港客人适用（含内宾，除香港以外的客人不适用） 4、台湾客人适用（含内宾，除台湾以外的客人不适用）  5、澳门客人适用（含内宾，除澳门以外的客人不适用）
     */
    private String suitCrowd;
    /**
     * 国内国际,1国内/2港澳/3国际
     */
    private String gngjlb;
    /**
     * 精选推广,0:否1:是
     */
    private String jxtg;
    /**
     * 推荐推广,0:否1:是
     */
    private String tjtg;
    /**
     * 热门推广,0:否1:是
     */
    private String rmtg;
    /**
     * 促销推广,0:否1:是
     */
    private String cxtg;
    /**
     * 主推推广,0:否1:是
     */
    private String zttg;
    /**
     * 周边交通
     */
    private String zbjt;
    /**
     * 百度经度
     */
    private String bdjd;
    /**
     * 百度纬度
     */
    private String bdwd;
    /**
     * google经度
     */
    private String ggjd;
    /**
     * google纬度
     */
    private String ggwd;
    /**
     *
     */
    private String jl;
    /**
     * 同城投诉率对比
     */
    private String tctsldb;
    /**
     * 同城成功率对比
     */
    private String tccgldb;
    /**
     * 同城确认率对比
     */
    private String tcqrldb;
    /**
     * 同城总评分对比
     */
    private String tczpfdb;
    /**
     * 投诉率
     */
    private String pftsl;
    /**
     * 成功率
     */
    private String pfcgl;
    /**
     * 确认率
     */
    private String pfqrl;
    /**
     * 总评分
     */
    private String pfzpf;
    /**
     *
     */
    private String bd;
    /**
     *
     */
    private String gg;
    /**
     * 酒店数量
     */
    private Integer jdsl;
    /**
     * 可售酒店数量
     */
    private Integer ksjdsl;
    /**
     * 可售计划数量
     */
    private Integer ksjhsl;
    /**
     * 平均售价
     */
    private Double pjsj;
    /**
     * 现付价格计划数量
     */
    private Integer xfjgjhsl;
    /**
     * 现付平均价
     */
    private Double xfpjj;
    /**
     * 预付价格计划数量
     */
    private Integer yfjgjhsl;
    /**
     * 预付平均价
     */
    private Double yfpjj;
    /**
     * 酒店品牌名称
     */
    private String jdppmc;
    /**
     * 基础设施名称
     */
    private List<String> jcssmclb;
    /**
     * 休闲设施名称
     */
    private List<String> xxssmclb;
    /**
     * 房型设施名称
     */
    private List<String> fxssmclb;
    /**
     * 服务设施名称
     */
    private List<String> fwssmclb;
    /**
     * 酒店主题类别名称
     */
    private List<String> jdztmclb;
    /**
     * 是否协议
     */
    private String sfxy;
    /**
     * 是否自供自采 0：否   1：是
     */
    private String sfzgzc;
    /**
     * 是否自签
     */
    private String sfcpszq;
    /**
     * 会议室设施
     */
    private String hysss;
    /**
     * 会场标签
     */
    private String hcbq;
    /**
     * 会议室数量
     */
    private String hyssl;
    /**
     * 房间数量
     */
    private String fjsl;
    /**
     * 标间数量
     */
    private String bjsl;
    /**
     * 单人间数量
     */
    private String drjsl;
    /**
     * 会议室列表
     */
    private List<HotelHysdx> hyjdlist;
    /**
     * 供应商映射关系list
     */
    private List<HotelGysdx> gyslist;
    /**
     * 是否满房 1 满房 0 未满房
     */
    private String sfmf;
    /**
     * 品牌名称
     */
    private String ppmc;
    /**
     * 是否有餐厅：1有餐厅，2无餐厅 0或空代表不确定是否有餐厅
     */
    private String sfyct;
    /**
     * 客房数量
     */
    private String kfsl;

    /**==========【酒店信息元素补充项目】新增字段==========**/
    /**
     * 酒店楼层高度
     */
    private String jdlcgd;
    /**
     * 酒店附近路口
     */
    private String jdfjlk;
    /**
     * 酒店英文地址
     */
    private String jdywdz;
    /**
     * 酒店邮政编码
     */
    private String jdyzbm;
    /**
     * 酒店英文简介
     */
    private String jdywjj;
    /**
     * 酒店电子邮箱，多个逗号分隔
     */
    private String jddzyx;
    /**
     * 酒店网站地址
     */
    private String jdwzdz;
    /**
     * 是否接待外宾 1 仅内宾  2 内宾和外宾
     */
    private String sfjdwb;
    /**
     * 资质证件 URL图片地址
     */
    private String zzzjdz;
    /**
     * 酒店集团编号
     */
    private String jdjtbh;
    /**
     * 酒店集团名称
     */
    private String jdjtmc;
    /**
     * 酒店集团简介
     */
    private String jdjtjj;
    /**
     * 酒店集团logo
     */
    private String jdjtLogo;
    /**
     * 酒店可支付的方式，信用卡的名称，多个用英文逗号分隔，例如  万事达信用卡，银联卡等
     */
    private String jdkyzffs;
    /**
     * 酒店可支付的方式code，对应jdkyzffs的code，多个用英文逗号分隔，例如  1,2,3
     */
    private String jdkyzffscode;
    /**
     * 酒店周边交通集合
     */
    private List<HotelZbjt> zbjtList;
    /**
     * 酒店政策集合
     */
    private List<HotelJdzc> jdzcList;
    /**
     * 协议类型 12单体协议；13集团协议
     */
    private String protocolType;
    /**
     * 单体协议类型，1:本地单体协议（自营酒店） 2：接口托管单体协议酒店-非cps 3:接口托管单体协议酒店-cps
     */
    private String dtxylx;

