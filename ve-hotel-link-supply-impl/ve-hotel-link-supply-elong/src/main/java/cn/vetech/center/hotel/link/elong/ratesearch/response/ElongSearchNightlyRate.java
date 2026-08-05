package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ElongSearchNightlyRate {
    /**
     * 当天日期
     */
    @JsonProperty("Date")
    private String date;
    /**
     * 会员价。已经通过DRR的计算可以直接显示给客人。价格为-1表示不能销售。
     */
    @JsonProperty("Member")
    private String member;
    /**
     * 结算价。仅结算价模式下的预付产品
     */
    @JsonProperty("Cost")
    private String cost;
    /**
     * 库存状态。true 表示当天库存是否可用
     */
    @JsonProperty("Status")
    private String status;
    /**
     * 加床价。-1表示不能加床
     */
    @JsonProperty("AddBed")
    private String addBed;
    /**
     * 原始价格。未经过DRR计算过的原始价格，入参Options包含5的时候返回
     */
    @JsonProperty("Basis")
    private String basis;
    /**
     * 早餐数量
     */
    @JsonProperty("BreakfastCount")
    private String breakfastCount;
    /**
     * 每晚每间房价（含税费） 国际特有字段 国际 ①价格取Rate；②税费=Rate-MinRate；
     */
    @JsonProperty("Rate")
    private String rate;
    /**
     * 最小价（不含税费） 国际特有字段
     */
    @JsonProperty("minRate")
    private String minRate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getMinRate() {
        return minRate;
    }

    public void setMinRate(String minRate) {
        this.minRate = minRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = st