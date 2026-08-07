package cn.vetech.center.hotel.link.supply.ylfx.v2.hotelstatic.request;

import java.util.List;

/**
 * 易旅分销 V2 酒店静态信息请求。
 *
 * @author 6161
 * @date 2026/08/07
 */
public class YlfxV2HotelInfosRequest {
    /** 客户编码 */
    private String customerCode;
    /** 酒店编码列表，单次最多 50 个 */
    private List<String> hotelCodes;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public List<String> getHotelCodes() {
        return hotelCodes;
    }

    public void setHotelCodes(List<String> hotelCodes) {
        this.hotelCodes = hotelCodes;
    }
}
