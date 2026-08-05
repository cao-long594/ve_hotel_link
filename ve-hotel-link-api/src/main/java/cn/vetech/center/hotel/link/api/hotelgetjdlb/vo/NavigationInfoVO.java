package cn.vetech.center.hotel.link.api.hotelgetjdlb.vo;

import java.io.Serializable;

/**
 * 导航信息
 *
 * @author luqs
 * @version v1.0
 */
public class NavigationInfoVO implements Serializable {
    /**
     * 导航类型，1：导航 2：步行
     */
    private String navType;
    /**
     * 里程（m）
     */
    private Double mileage;
    /**
     * 里程（km）
     */
    private Double mileageKm;
    /**
     * 耗时（分钟）
     */
    private Double consumeTime;
    /**
     * 耗时（小时）
     */
    private Double consumeTimeHour;

    public String getNavType() {
        return navType;
    }

    public void setNavType(String navType) {
        this.navType = navType;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getMileageKm() {
        return mileageKm;
    }

    public void setMileageKm(Double mileageKm) {
        this.mileageKm = mileageKm;
    }

    public Double getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Double consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Double getConsumeTimeHour() {
        return consumeTimeHour;
    }

    public void setConsumeTimeHour(Double consumeTimeHour) {
        this.consumeTimeHour = consumeTimeHour;
    }
}
