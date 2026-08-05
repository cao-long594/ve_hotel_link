package cn.vetech.center.hotel.link.api.guarantee.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 取消订单
 *
 * @author gaojin
 */
public class LinkHotelGuaranteeVO extends LinkHotelVO {
    /**
     * 状态 0无须担保 1需要担保
     */
    @ApiModelProperty(value = "状态 0无须担保 1需要担保", dataType = "string")
    private String isVouch;
    /**
     * 担保金额
     */
    @ApiModelProperty(value = "担保金额", dataType = "string")
    private String amount;
    /**
     * 担保规则描述
     */
    @ApiModelProperty(value = "担保规则描述", dataType = "string")
    private String ruleDesc;
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期", dataType = "string")
    private String startDate;
    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期", dataType = "string")
    private String endDate;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", dataType = "string")
    private String startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", dataType = "string")
    private String endTime;
    /**
     * 最晚取消时间
     */
    @ApiModelProperty(value = "最晚取消时间", dataType = "string")
    private String cancelTime;

    /**
     * 免费取消时间
     */
    @ApiModelProperty(value = "免费取消时间", dataType = "string")
    private String freeCancelTime;
    /**
     * 只代表取消时间处于免费取消时间跟最晚取消时间之间产生的罚金金额，早于免费取消时间不收罚金，晚于最晚取消100%罚金
     */
    @ApiModelProperty(value = "罚金金额", dataType = "string")
    private String penaltyAmount;

    /*******************相比cps新增字段********************/
    /**
     *
     */
    private String dbgzid;//担保规则ID
    /**
     *
     */
    private String dbgz;//担保规则描述
    /*******************相比cps新增字段********************/

    public String getIsVouch() {
        return isVouch;
    }

    public void setIsVouch(String isVouch) {
        this.isVouch = isVouch;
    }

    public String getAmount() {
      