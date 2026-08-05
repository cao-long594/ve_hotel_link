package cn.vetech.center.hotel.link.api.hoteldtxyjd;

import cn.vetech.center.hotel.link.api.LinkHotelVO;

import java.util.LinkedList;
import java.util.List;

/**
 * @author vetech
 * @since 25/06/25
 */

public class HotelMarkAgreementVO extends LinkHotelVO {

    /**
     * 失败的酒店信息
     */
    private List<HotelMarkAgreemenFailItem> failHotelList =new LinkedList<>();


    public List<HotelMarkAgreemenFailItem> getFailHotelList() {
        return failHotelList;
    }

    public void setFailHotelList(List<HotelMarkAgreemenFailItem> failHotelList) {
        this.failHotelList = failHotelList;
    }
}
