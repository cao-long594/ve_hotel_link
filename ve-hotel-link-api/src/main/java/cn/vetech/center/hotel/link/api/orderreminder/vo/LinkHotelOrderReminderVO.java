package cn.vetech.center.hotel.link.api.orderreminder.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author chengwanshan
 * @since 2024/10/16 17:50
 */
public class LinkHotelOrderReminderVO extends LinkHotelVO {
    /**
     * 供应商订单号
     */
    @ApiModelProperty(value = "供应商订单号", dataType = "string")
    private String orderId;
    /**
     * 催单预计反馈时间
     */
    @ApiModelProperty(value = "催单预计反馈时间", dataType = "string")
    private String createTime;
    /**
     * 催单结果码
     * 0 催单成功
     * 1 系统繁忙，请稍后重试
     * 2 催单失败，订单号不存在
     * 3 催单失败，订单非预定中状态
     * 4 催单时限内无法催单
     * 5 已催单，无需重复催单
     * 20 其它
     * 30 供应商未实现此接口
     */
    @ApiModelProperty(value = "催单结果码", dataType = "string")
    private String code;
    /**
     * 催单结果描述
     */
    @ApiModelProperty(value = "催单结果描述", dataType = "string")
    private String desc;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
