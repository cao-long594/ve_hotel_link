package cn.vetech.center.hotel.link.api.roommergedata.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

/**
 * 房型名称映射
 *
 * @author luqs
 * @version v1.0
 */
@XmlRootElement(name = "response")
public class RoomMergeDataVO extends LinkHotelVO {
    /**
     * 房型名称关键字
     */
    private Set<String> roomNameKeywordList;
    /**
     * 房型名称映射
     */
    private List<RoomNameMappingItem> roomNameMappingList;

    @XmlElementWrapper(name = "roomNameKeywordList")
    @XmlElement(name = "roomNameKeyword")
    public Set<String> getRoomNameKeywordList() {
        return roomNameKeywordList;
    }

    public void setRoomNameKeywordList(Set<String> roomNameKeywordList) {
        this.roomNameKeywordList = roomNameKeywordList;
    }

    @XmlElementWrapper(name = "roomNameMappingList")
    @XmlElement(name = "roomNameMapping")
    public List<RoomNameMappingItem> getRoomNameMappingList() {
        return roomNameMappingList;
    }

    public void setRoomNameMappingList(List<RoomNameMappingItem> roomNameMappingList) {
        this.roomNameMappingList = roomNameMappingList;
    }

}
