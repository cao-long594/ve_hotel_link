package cn.vetech.center.hotel.link.elong.orderbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author gaojin
 */
public class ElongBookOrderValidation implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 校验类型
     * 逗号分隔的数字：
     * 0:不进行校验 1:早餐数量校验 2：取消时间校验 3：担保金额校验
     * 如果校验所有：Type=1,2,3
     */
    @JsonProperty("Type")
    private String type;
    /**
     * 担保金额
     */
    @JsonProperty("GuaranteeAmount")
    private String guaranteeAmount;
    /**
     * 最晚取消时间
     */
    @JsonProperty("CancelTime")
    private String cancelTime;
    /**
     * 每日早餐数量列表
     */
    @JsonProperty("DateBreakFastList")
    private List<ElongBookDateBreakFast> dateBreakFastList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(String guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public List<ElongBookDateBreakFast> getDateBreakFastList() {
        return dateBreakFastList;
    }

    public void setDateBreakFastList(List<ElongBookDateBreakFast> dateBreakFastList) {
        this.dateBreakFastList = dateBreakFastList;
    }
}
