package cn.vetech.center.hotel.link.api.conferencehotel.list.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 会议室酒店列表
 *
 * @author luqs
 * @version v1.0
 */
@XmlRootElement(name = "response")
public class ConferenceHotelListVO extends LinkHotelVO {
    private static final long serialVersionUID = -4787140286318011320L;

    /**
     * 翻页起始页码
     */
    private int start = 0;
    /**
     * 每页条数
     */
    private int count = 0;
    /**
     * 总条数
     */
    private long total = 0;
    /**
     * 酒店信息
     */
    private List<ConferenceHotelItem> hotelList;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<ConferenceHotelItem> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<ConferenceHotelItem> hotelList) {
        this.hotelList = hotelList;
    }
}
