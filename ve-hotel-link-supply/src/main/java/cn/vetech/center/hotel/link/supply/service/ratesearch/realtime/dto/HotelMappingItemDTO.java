package cn.vetech.center.hotel.link.supply.service.ratesearch.realtime.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 酒店映射
 *
 * @author luqs
 * @version v1.0
 */
public class HotelMappingItemDTO implements Serializable {

    private static final long serialVersionUID = -9143972724245771733L;
    /**
     * 房源编号
     */
    @NotBlank(message = "房源编号不可为空")
    private String supplyNo;
    /**
     * 房源简称
     */
    private String supplyAbbr;
    /**
     * 房源酒店id
     */
    @NotBlank(message = "房源酒店id不可为空")
    private String hotelId;

    public String getSupplyNo() {
        return supplyNo;
    }

    public void setSupplyNo(String supplyNo) {
        this.supplyNo = supplyNo;
    }

    public String getSupplyAbbr() {
        return supplyAbbr;
    }

    public void setSupplyAbbr(String supplyAbbr) {
        this.supplyAbbr = supplyAbbr;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
