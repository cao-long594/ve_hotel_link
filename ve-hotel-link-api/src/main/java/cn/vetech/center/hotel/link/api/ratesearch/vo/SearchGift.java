package cn.vetech.center.hotel.link.api.ratesearch.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.util.List;

/**
 * 送礼活动
 *
 * @author SongJun 8963
 */
public class SearchGift implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 关联RatePlan.GiftId
     */
    @ApiModelProperty(value = "关联RatePlan.GiftId", dataType = "string")
    private String giftId;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", dataType = "string")
    private String startDate;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", dataType = "string")
    private String endDate;
    /**
     * CheckinDate:入住日 BookingDate:预订日 StayDate:在店日
     */
    @ApiModelProperty(value = "CheckinDate:入住日 BookingDate:预订日 StayDate:在店日", dataType = "string")
    private String dateType;
    /**
     * 星期设置
     */
    @ApiModelProperty(value = "星期设置", dataType = "string")
    private String weekSet;
    /**
     * 活动内容
     */
    @ApiModelProperty(value = "活动内容", dataType = "string")
    private String giftContent;
    /**
     * 1 送餐相关 2 延迟退房 3 送礼品 4 设施服务（如免费健身、送洗衣等）5免费接站/接机 6 送折扣/抵扣券 7 送旅游/门票 8 其他
     */
    @ApiModelProperty(value = "1 送餐相关 2 延迟退房 3 送礼品 4 设施服务（如免费健身、送洗衣等）5免费接站/接机 6 送折扣/抵扣券 7 送旅游/门票 8 其他", dataType = "string")
    private String giftTypes;
    /**
     * 小时数
     */
    @ApiModelProperty(value = "小时数", dataType = "string")
    private String hourNumber;
    /**
     * 几点之前参加 或者几点之后参加 或者 24点都参加
     */
    @ApiModelProperty(value = "几点之前参加 或者几点之后参加 或者 24点都参加", dataType = "string")
    private String hourType;
    /**
     * 送礼方式:
     * 1：直送一次、2：每晚都送、3：可选、其它
     */
    @ApiModelProperty(value = "送礼方式:1：直送一次、2：每晚都送、3：可选、其它", dataType = "string")
    private String wayOfGiving;
    /**
     * 送礼方式为其他的时候，送礼活动的名称
     */
    @ApiModelProperty(value = "送礼方式为其他的时候，送礼活动的名称", dataType = "string")
    private String wayOfGivingOther;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", dataType = "string")
    private String description;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", dataType = "string")
    private String giftName;
    /**
     * 权益使用限制
     */
    @ApiModelProperty(value = "权益使用限制", dataType = "string")
    private List<RightUseInfo> rightUseInfoList;
    /**
     * 可使用次数
     */
    private String quantity;
