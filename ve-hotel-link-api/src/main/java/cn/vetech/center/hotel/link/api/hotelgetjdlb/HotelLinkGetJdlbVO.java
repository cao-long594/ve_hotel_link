package cn.vetech.center.hotel.link.api.hotelgetjdlb;


import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.hotelgetjdlb.vo.HotelJdbExt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 获取酒店列表VO
 *
 * @author zhangheng
 * @version 2019-1-31
 */
@XmlRootElement(name = "response")
public class HotelLinkGetJdlbVO extends LinkHotelVO {
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
     * cps响应的请求id 这个作为入参传入到 查询报价接口
     */
    private String cpsPreRepTraceid;

    /**
     *
     */
    private List<HotelJdbExt> hotelList;

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

    @XmlElementWrapper(name = "hotelList")
    @XmlElement(name = "hotel")

    public List<HotelJdbExt> getHotelList() {
        return hotelList;
    }

    public String getCpsPreRepTraceid() {
        return cpsPreRepTraceid;
    }

    public void setCpsPreRepTraceid(String cpsPreRepTraceid) {
        this.cpsPreRepTraceid = cpsPreRepTraceid;
    }

    public void setHotelList(List<HotelJdbExt> hotelList) {
        this.hotelList = hotelList;
    }
}
