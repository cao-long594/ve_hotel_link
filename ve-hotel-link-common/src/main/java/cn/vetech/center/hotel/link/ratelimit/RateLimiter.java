package cn.vetech.center.hotel.link.ratelimt;


import cn.vetech.center.hotel.link.constant.NumConstant;

/**
 * @author wangkai
 * @since 2021年3月17日
 */
public class RateLimiter {

    /**
     * 时间范围 秒
     */
    private Integer time = 1;

    /**
     * 休眠时间 毫秒
     */
    private Integer sleep = NumConstant.NUM_500;

    /**
     * 最大容量
     */
    private Integer capacity = NumConstant.NUM_6;

    /**
     *最大获取次数
     */
    private Integer maxAttemp = NumConstant.NUM_1;


    /**
     * 限流key
     */
    private String key;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getSleep() {
        return sleep;
    }

    public void setSleep(Integer sleep) {
        this.sleep = sleep;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getMaxAttemp() {
        return maxAttemp;
    }

    public void setMaxAttemp(Integer maxAttemp) {
        this.maxAttemp = maxAttemp;
    }

}