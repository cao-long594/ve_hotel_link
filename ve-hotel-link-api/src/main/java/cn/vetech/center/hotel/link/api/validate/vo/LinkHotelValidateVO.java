package cn.vetech.center.hotel.link.api.validate.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchNightlyRate;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 下单前数据校验
 *
 * @author gaojin
 */
public class LinkHotelValidateVO extends LinkHotelVO {
    /**
     * 验证结果 1可预订 0不可预订
     */
    @ApiModelProperty(value = "验证结果 1可预订 0不可预订", dataType = "string")
    private String result;
    /**
     * 最新订单总金额
     */
    @ApiModelProperty(value = "最新订单总金额", dataType = "string")
    private String totalprice;
    /**
     * 状态 1需要担保 0无需担保
     */
    @ApiModelProperty(value = "状态 1需要担保 0无需担保", dataType = "string")
    private String isVouch;
    /**
     *担保类型
     */
    @ApiModelProperty(value = "担保类型;1峰时担保,2全额担保,3超时担保,4一律担保,5手机担保为无需担保", dataType = "string")
    private String guaranteeType;
    /**
     * 担保金额
     */
    @ApiModelProperty(value = "担保金额", dataType = "string")
    private String amount;
    /**
     * 担保金额的货币类型
     */
    @ApiModelProperty(value = "担保金额的货币类型", dataType = "string")
    private String currencyCode;
    /**
     * 担保规则描述
     */
    @ApiModelProperty(value = "担保规则描述", dataType = "string")
    private String description;
    /**
     * 取消时间
     */
    @ApiModelProperty(value = "取消时间", dataType = "string")
    private String cancelTime;
    /**
     * 房间节点
     */
    @ApiModelProperty(value = "房间节点", dataType = "string")
    private List<SearchRoom> rooms;
    /**
     * 产品变价，服务商内部已经更新价格，可以重新查询预订， 0：不冻结价格计划，其他：冻结价格计划
     */
    private String retry;
    /**
     * 拓展字段，供应商验价接口返回，下单接口需要传
     */
    private String expand;

    public String getRetry() {
        return retry;
    }

    public void setRetry(String retry) {
        this.retry = retry;
    }

    public String getExpand() {
        return 