package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response;

/**
 * 易旅分销 V2 餐食信息
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2RateSearchMeal {
    /**
     * 早餐数量
     */
    private Integer breakfastCount;
    /**
     * 午餐数量
     */
    private Integer lunchCount;
    /**
     * 晚餐数量
     */
    private Integer dinnerCount;

    public Integer getBreakfastCount() {
        return breakfastCount;
    }

    public void setBreakfastCount(Integer breakfastCount) {
        this.breakfastCount = breakfastCount;
    }

    public Integer getLunchCount() {
        return lunchCount;
    }

    public void setLunchCount(Integer lunchCount) {
        this.lunchCount = lunchCount;
    }

    public Integer getDinnerCount() {
        return dinnerCount;
    }

    public void setDinnerCount(Integer dinnerCount) {
        this.dinnerCount = dinnerCount;
    }
}
