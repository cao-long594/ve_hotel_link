package cn.vetech.center.hotel.link.api.ratesearch.vo;

/**
 * @author chengwanshan
 * @since 2024/12/5 20:09
 */
public class HotelTimeInfo {
    /**
     * 时间
     * 例如：2024-04-20 18:00:00
     */
    private String time;
    /**
     * 时区
     * 例如：UTC+8:00
     */
    private String timeZone;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
