package cn.vetech.center.hotel.link.api.hotelgetjdfx;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.hotelgetjdfx.vo.HotelFx;
import cn.vetech.center.hotel.link.api.hotelgetjdfx.vo.HotelMap;
import cn.vetech.center.hotel.link.api.hotelgetjdfx.vo.RoomMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author xingyanyan
 * @since 2019/6/29 14:34
 */
@XmlRootElement(name = "response")
public class HotelLinkGetJdfxVO extends LinkHotelVO {
    /**
     *
     */
    private List<HotelMap> jdMaps;
    /**
     *
     */
    private List<RoomMap> fxMaps;
    /**
     *
     */
    private List<HotelFx> roomList;

    @XmlElementWrapper(name = "jdMaps")
    @XmlElement(name = "jdMap")
    public List<HotelMap> getJdMaps() {
        return jdMaps;
    }

    public void setJdMaps(List<HotelMap> jdMaps) {
        this.jdMaps = jdMaps;
    }

    @XmlElementWrapper(name = "fxMaps")
    @XmlElement(name = "fxMap")
    public List<RoomMap> getFxMaps() {
        return fxMaps;
    }

    public void setFxMaps(List<RoomMap> fxMaps) {
        this.fxMaps = fxMaps;
    }

    @XmlElementWrapper(name = "roomList")
    @XmlElement(name = "room")
    public List<HotelFx> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<HotelFx> roomList) {
        this.roomList = roomList;
    }
}
