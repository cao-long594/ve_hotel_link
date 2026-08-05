package cn.vetech.center.hotel.link.api.collectToCps;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

/**
 * @author vetech
 * @since 2024/4/26
 */
public class RateCollectGzipDTO extends LinkHotelDTO {

    /**
     *价格信息
     */
    private String rateInfoGzipStr;



    public String getRateInfoGzipStr() {
        return rateInfoGzipStr;
    }

    public void setRateInfoGzipStr(String rateInfoGzipStr) {
        this.rateInfoGzipStr = rateInfoGzipStr;
    }
}
