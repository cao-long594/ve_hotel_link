package cn.vetech.center.hotel.link.elong.validatebooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2025/10/15 14:39
 */
public class ElongPenaltyWindowType {
    /**
     * 扣款类型	Int	N	0:百分比 1:晚数 2:首晚百分比
     */
    @JsonProperty("PenaltyType")
    private String penaltyType;
    /**
     * 罚金	Double	N	PenaltyType 为 0,2时，此值为两位小数。PenaltyType 为1是，此值使用时会取整，按整数处理。
     */
    @JsonProperty("PenaltyValue")
    private String penaltyValue;
    /**
     * 规则时间分割起始点	String	N	单位分钟，第一个点为1439280
     */
    @JsonProperty("Deadline")
    private String deadline;

    public String getPenaltyType() {
        return penaltyType;
    }

    public void setPenaltyType(String penaltyType) {
        this.penaltyType = penaltyType;
    }

    public String getPenaltyValue() {
        return penaltyValue;
    }

    public void setPenaltyValue(String penaltyValue) {
        this.penaltyValue = penaltyValue;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
