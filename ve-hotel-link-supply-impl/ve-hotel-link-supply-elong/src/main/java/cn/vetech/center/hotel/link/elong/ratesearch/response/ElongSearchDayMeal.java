package cn.vetech.center.hotel.link.elong.ratesearch.response;

/**
 * @author chengwanshan
 * @since 2021/12/14 10:33
 */
public class ElongSearchDayMeal {
    /**
     * 餐食的日期      yyyy-MM-dd格式，例如2021-08-12
     */
    private String date;
    /**
     * 是否使用动态餐食	 为true取dynamicMealDesc；为false取breakfastDesc、lunchDesc、dinnerDesc;
     */
    private Boolean useDynamicMeal;
    /**
     * 动态餐食描述		例如：3种餐食(3选2)
     */
    private String dynamicMealDesc;
    /**
     * 早餐数量		例如：0
     */
    private Integer breakfastShare;
    /**
     * 早餐描述
     */
    private String breakfastDesc;
    /**
     * 午餐数量	例如：1
     */
    private Integer lunchShare;
    /**
     * 午餐描述		例如：1份午餐
     */
    private String lunchDesc;
    /**
     * 晚餐数量		例如：2
     */
    private Integer dinnerShare;
    /**
     * 晚餐描述		例如：2份晚餐
     */
    private String dinnerDesc;
    /**
     * 到天餐食描述		例如：2份早餐/间
     */
    private String dayMealCopyWriting;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getUseDynamicMeal() {
        return useDynamicMeal;
    }

    public void setUseDynamicMeal(Boolean useDynamicMeal) {
        this.useDynamicMeal = useDynamicMeal;
    }

    public String getDynamicMealDesc() {
        return dynamicMealDesc;
    }

    public void setDynamicMealDesc(String dynamicMealDesc) {
        this.dynamicMealDesc = dynamicMealDesc;
    }

    public Integer getBreakfastShare() {
        return breakfastShare;
    }

    public void setBreakfastShare(Integer breakfastShare) {
        this.breakfastShare = breakfastShare;
    }

    public String getBreakfastDesc() {
        return breakfastDesc;
    }

    public void setBreakfastDesc(String breakfastDesc) {
        this.breakfastDesc = breakfastDesc;
    }

    public Integer getLunchShare() {
        return lunchShare;
    }

    pub