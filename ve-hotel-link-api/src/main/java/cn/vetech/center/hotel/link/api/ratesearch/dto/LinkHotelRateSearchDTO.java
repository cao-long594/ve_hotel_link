package cn.vetech.center.hotel.link.api.ratesearch.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 查询报价
 *
 * @author gaojin
 */
@XmlRootElement(name = "request")
public class LinkHotelRateSearchDTO extends LinkHotelDTO {
    /**
     * 查询来源
     * 可选值：FCC_QUERY(费控fcc查询报价)、APP_QUERY(费控无线端查询报价)、
     * FCB_QUERY(费控fcb查询报价)、FCC_RECHECK(费控fcc重查价格)、
     * FCB_RECHECK(费控fcb重查价格) 、LIST_LOWEST_PRICE 酒店列表最低价
     */
    private String searchSource;
    /**
     * 是否酒店列表查询，0为否 1是
     */
    private int hotelListSearch;
    /**
     * 是否副接口查询，0:否，1：是
     */
    private String assistantSearch = "0";
    /**
     * 获取缓存类型  0实时获取价格不取缓存  1获取cacheKey的缓存  (目前存值何sfxqq一致)
     * 增加这个字段的目的是解决缓存的价格计划为已失效的价格，cps需要判断如果该字段和sfxqq
     */
    private String cacheType;
    /**
     * 缓存key
     */
    private String cacheKey;
    /**
     * 房源商映射关系
     */
    @ApiModelProperty(value = "房源商映射关系", dataType = "string")
    private List<Mapper> mappers;
    /**
     * 入住日期：yyyy-mm-dd
     */
    @ApiModelProperty(value = "入住日期：yyyy-mm-dd", dataType = "string")
    private String checkInDate;
    /**
     * 离店日期：yyyy-mm-dd
     */
    @ApiModelProperty(value = "离店日期：yyyy-mm-dd", dataType = "string")
    private String checkOutDate;
    /**
     * 支付方式：0现付 1预付 2所有
     */
    @ApiModelProperty(value = "支付方式：0现付 1预付 2所有", dataType = "string")
    private String payment;
    /**
     * true：从价格计划库获取数据，false：从实时接口获取数据
     */
    @ApiModelProperty(value = "true：从价格计划库获取数据，false：从实时接口获取数据", dataType = "string")
    private boolean cache = false;
    /**
     * true：从价格计划库获取数据，false：从实时接口获取数据
     */
    @ApiModelProperty(value = "true：启用超时，false：关闭超时", dataType = "string")
    private boolean socketTimeout = true;
    /**
     * 是否有餐厅：1有餐厅，2无餐厅 0或空代表不确定是否有餐厅
     */
    private String sfyct;
    /**
     * 是否屏蔽cps价格
     */
    private String sfpbcpsjg;
    /**
     * 直连集团房源编号，多个英文逗号分隔，例如：31200852,31200855,31200860
     */
    private String zljtfybh;
    /**
     * 是否开启直连房源集团酒店不查询其他接口价格，0否，1开启，默认0，只有1开启时才会取zljtfybh字段进行过滤
     */
    private String zljtjdcqtjkjg;
    /**
     * 差标金额（cps让利规则匹配要用）
     */
    private Double cbje;
    /**
     * 酒店列表最低价
     */
    private Double jdlbzdj;
    /**
     * 酒店列表最低价报价信息
     */
    private String oriZdj;
    /**
     * 预订用户当前所在IP地址【必填】
     */
    private String ydrIp;
    /**
     * 预订用户当前所在百度纬度【非必填】
     */
    private String ydrLat;
    /**
     * 预订用户当前所在百度经度【非必填】
     */
    private String ydrLng;
    /**
     * 预订用户手机号【必填】
     */
    private String ydrPhoneNumber;
    /**
     * 预订终端枚举 Android、iPhone、PC\Touch
     */
    private String ydrYdzd;
    /**
     * 开启会员价的供应商列表，费控查询预订cps供应商使用
     */
    private List<String> kqhyjFysList;
    /**
     * 查询报价使用，费控用户查询会员价相关信息
     */
    private List<UserVipExtInfo> userVipExtInfoList;
    /**
     * 员工工号
     */
    private String yggh;
    /**
     * 百度坐标，如：43.175023436719,124.37977615232
     */
    private String bd;
    /**
     * 预订人保障级别
     */
    private String bookerProtectLevel;
    /**
     * 酒店本地时区
     */
    private String hotelLocalTimeZone;
    /**
     * 所在城市
     */
    private String szcs;
    /**
     * 销售渠道
     * 参考枚举 HotelSalesChannelEnum
     */
    private String salesChannel;
    /**
     * 销售场景
     * 参考枚举 HotelSalesScenarioEnum
     */
    private String salesScenario;
    /**
     * cps销售场景，如：商旅、福利、团队活动等，注：与“salesScenario”字段非同义
     */
    private String cpsSaleScene;
    /**
     * cps会员id
     */
    private String cpsMemberId;
    /**
     * 是否查询cps会员推广价（注：非会员时，才可查此价格！）
     */
    private boolean cpsMemberPmeQryFlag;
