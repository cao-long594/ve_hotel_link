package cn.vetech.center.hotel.link.supply.ylfx.ratesearch.request;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseRequest;

/**
 * @author 6161
 * @date 2024/07/23
 */
public class GetProductPriceDailyRequest extends YlfxBaseRequest {
    /**
     * 酒店ID
     */
    private String hotelId;
    /**
     * 开始日期，格式yyyyMMdd
     */
    private String beginDate;
    /**
     * 结束日期，格式yyyyMMdd
     */
    private String endDate;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
