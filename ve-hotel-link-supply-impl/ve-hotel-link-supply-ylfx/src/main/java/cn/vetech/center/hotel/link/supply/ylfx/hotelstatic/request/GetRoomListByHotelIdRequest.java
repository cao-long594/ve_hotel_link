package cn.vetech.center.hotel.link.supply.ylfx.hotelstatic.request;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseRequest;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class GetRoomListByHotelIdRequest extends YlfxBaseRequest {
    /**
     * 酒店ID
     */
    private String hotelId;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
