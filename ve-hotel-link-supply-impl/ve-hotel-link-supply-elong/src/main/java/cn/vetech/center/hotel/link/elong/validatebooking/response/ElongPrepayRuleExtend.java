package cn.vetech.center.hotel.link.elong.validatebooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2025/10/15 14:36
 */
public class ElongPrepayRuleExtend {
    /**
     * 开始时间    Date	N	按入住日匹配，入住日在开始时间和结束时间之间，且符合周有效规则，即为命中此规则
     */
    @JsonProperty("StartDate")
    private String startDate;
    /**
     * 结束时间    Date	N
     */
    @JsonProperty("EndDate")
    private String endDate;
    /**
     * 周有效设置   String  N
     */
    @JsonProperty("WeekSet")
    private String weekSet;
    /**
     * 取消费用类型  Int     Y   0:跟随取消费用 1:订单全额（目前只有0）
     */
    @JsonProperty("NoshowPenalty")
    private String noshowPenalty;
    /**
     * 取消规则列表	PenaltyWindowType[]	N   参见PenaltyWindowType节点；解析示例：点此查看
     */
    @JsonProperty("PenaltyRuleList")
    private List<ElongPenaltyWindowType> penaltyRuleList;
    /**
     * 规则类型	Int     Y   1：长期规则；2：特殊规则，优先看特殊规则
     */
    @JsonProperty("SubId")
    private String subId;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getWeekSet() {
        return weekSet;
    }

    public void setWeekSet(String weekSet) {
        this.weekSet = weekSet;
    }

    public String getNoshowPenalty() {
        return noshowPenalty;
    }

    public void setNoshowPenalty(String noshowPenalty) {
        this.noshowPenalty = noshowPenalty;
    }

    public List<ElongPenaltyWindowType> getPenaltyRuleList() {
        return penaltyRuleList;
    }

    public void setPenaltyRuleList(List<ElongPenaltyWindowType> penaltyRuleList) {
        this.penaltyRuleList = penaltyRuleList;
    }

    public String getSubId() {