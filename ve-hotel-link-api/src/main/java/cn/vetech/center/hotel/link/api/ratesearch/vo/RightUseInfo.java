package cn.vetech.center.hotel.link.api.ratesearch.vo;

/**
 * @author chengwanshan
 * @since 2025/10/13 9:11
 */
public class RightUseInfo {
    /**
     * 日期，格式：yyyy-MM-dd
     */
    private String useNightTime;
    /**
     * 当天用户可使用最大次数，（负数表示不限制）
     */
    private String maxUseNum;

    public String getUseNightTime() {
        return useNightTime;
    }

    public void setUseNightTime(String useNightTime) {
        this.useNightTime = useNightTime;
    }

    public String getMaxUseNum() {
        return maxUseNum;
    }

    public void setMaxUseNum(String maxUseNum) {
        this.maxUseNum = maxUseNum;
    }
}
