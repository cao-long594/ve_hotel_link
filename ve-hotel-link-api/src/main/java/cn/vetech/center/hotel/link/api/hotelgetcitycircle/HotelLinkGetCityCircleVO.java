package cn.vetech.center.hotel.link.api.hotelgetcitycircle;


import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.hotelgetcitycircle.vo.CityCircle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author chenming
 * @version [1.0, 六月 12, 2017]
 */
@XmlRootElement(name = "response")
public class HotelLinkGetCityCircleVO extends LinkHotelVO {
    /**
     * 商圈集合
     */
    private List<CityCircle> syqList;
    /**
     * 商业区
     */
    private List<CityCircle> bizDistList;

    @XmlElementWrapper(name = "syqList")
    @XmlElement(name = "syq")
    public List<CityCircle> getSyqList() {
        return syqList;
    }

    public void setSyqList(List<CityCircle> syqList) {
        this.syqList = syqList;
    }

    @XmlElementWrapper(name = "bizDistList")
    @XmlElement(name = "bizDist")
    public List<CityCircle> getBizDistList() {
        return bizDistList;
    }

    public void setBizDistList(List<CityCircle> bizDistList) {
        this.bizDistList = bizDistList;
    }

}
