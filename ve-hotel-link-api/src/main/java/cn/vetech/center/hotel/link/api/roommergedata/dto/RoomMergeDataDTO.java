package cn.vetech.center.hotel.link.api.roommergedata.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 房型名称映射
 *
 * @author luqs
 * @version v1.0
 */
@XmlRootElement(name = "request")
public class RoomMergeDataDTO  extends LinkHotelDTO {

    /**
     * 本地酒店id
     */
    @NotBlank(message = "本地酒店id不可为空")
    private String localHotelId;

    public String getLocalHotelId() {
        return localHotelId;
    }

    public void setLocalHotelId(String localHotelId) {
        this.localHotelId = localHotelId;
    }
}
