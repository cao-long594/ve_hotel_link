package cn.vetech.center.hotel.link.api.roommergedata.vo;

import java.io.Serializable;
import java.util.Set;

/**
 * 房型名称映射
 *
 * @author luqs
 * @version v1.0
 */
public class RoomNameMappingItem implements Serializable {
    private static final long serialVersionUID = 1350783295234694433L;

    /**
     * 本地房型名称
     */
    private String localRoomName;
    /**
     * 供应房型名称
     */
    private Set<String> supplyRoomNameList;

    public String getLocalRoomName() {
        return localRoomName;
    }

    public void setLocalRoomName(String localRoomName) {
        this.localRoomName = localRoomName;
    }

    public Set<String> getSupplyRoomNameList() {
        return supplyRoomNameList;
    }

    public void setSupplyRoomNameList(Set<String> supplyRoomNameList) {
        this.supplyRoomNameList = supplyRoomNameList;
    }
}
