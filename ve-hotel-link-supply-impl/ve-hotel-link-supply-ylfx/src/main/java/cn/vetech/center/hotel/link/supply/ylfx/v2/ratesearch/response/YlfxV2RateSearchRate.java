package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response;

import java.util.List;

/**
 * 易旅分销 V2 可订价格计划
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2RateSearchRate {
    /**
     * 报价编码
     */
    private String rateCode;
    /**
     * 报价中文名称
     */
    private String rateNameCn;
    /**
     * 报价英文名称
     */
    private String rateNameEn;
    /**
     * 是否即时确认：1 是
     */
    private Integer instantConfirm;
    /**
     * 币种
     */
    private String currencyCode;
    /**
     * 总价
     */
    private String totalPrice;
    /**
     * 总税费
     */
    private String totalTaxAndFee;
    /**
     * 每日价格列表
     */
    private List<YlfxV2RateSearchDailyPrice> dailyPriceList;
    /**
     * 取消政策列表
     */
    private List<YlfxV2RateSearchCancelPolicy> cancelPolicies;
    /**
     * 餐食信息
     */
    private YlfxV2RateSearchMeal meal;
    /**
     * 酒店附加费列表
     */
    private List<YlfxV2RateSearchHotelFee> hotelFees;

    public String getRateCode() {
        return rateCode;
    }

    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    public String getRateNameCn() {
        return rateNameCn;
    }

    public void setRateNameCn(String rateNameCn) {
        this.rateNameCn = rateNameCn;
    }

    public String getRateNameEn() {
        return rateNameEn;
    }

    public void setRateNameEn(String rateNameEn) {
        this.rateNameEn = rateNameEn;
    }

    public Integer getInstantConfirm() {
        return instantConfirm;
    }

    public void setInstantConfirm(Integer instantConfirm) {
        this.instantConfirm = instantConfirm;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalTaxAndFee() {
        return totalTaxAndFee;
    }

    public void setTotalTaxAndFee(String totalTaxAndFee) {
        this.totalTaxAndFee = totalTaxAndFee;
    }

    public List<YlfxV2RateSearchDailyPrice> getDailyPriceList() {
        return dailyPriceList;
    }

    public void setDailyPriceList(List<YlfxV2RateSearchDailyPrice> dailyPriceList) {
        this.dailyPriceList = dailyPriceList;
    }

    public List<YlfxV2RateSearchCancelPolicy> getCancelPolicies() {
        return cancelPolicies;
    }

    public void setCancelPolicies(List<YlfxV2RateSearchCancelPolicy> cancelPolicies) {
        this.cancelPolicies = cancelPolicies;
    }

    public YlfxV2RateSearchMeal getMeal() {
        return meal;
    }

    public void setMeal(YlfxV2RateSearchMeal meal) {
        this.meal = meal;
    }

    public List<YlfxV2RateSearchHotelFee> getHotelFees() {
        return hotelFees;
    }

    public void setHotelFees(List<YlfxV2RateSearchHotelFee> hotelFees) {
        this.hotelFees = hotelFees;
    }
}
