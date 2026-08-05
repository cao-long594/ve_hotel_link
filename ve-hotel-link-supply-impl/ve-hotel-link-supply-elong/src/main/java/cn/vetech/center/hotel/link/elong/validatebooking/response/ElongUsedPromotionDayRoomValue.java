package cn.vetech.center.hotel.link.elong.validatebooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2025/10/15 15:12
 */
public class ElongUsedPromotionDayRoomValue {
    /**
     * 房间号	String	Y
     */
    @JsonProperty("RoomNumber")
    private String roomNumber;
    /**
     * 促销明细    PromotionDetail[]	Y	此间促销明细
     */
    @JsonProperty("PromotionDetailList")
    private List<ElongPromotionDetail> promotionDetailList;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<ElongPromotionDetail> getPromotionDetailList() {
        return promotionDetailList;
    }

    public void setPromotionDetailList(List<ElongPromotionDetail> promotionDetailList) {
        this.promotionDetailList = promotionDetailList;
    }
}
