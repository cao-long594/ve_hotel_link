package cn.vetech.center.hotel.link.api.hotelgetcdsdata;


import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.hotelgetcdsdata.vo.HotelDictionaryVO;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * @param <T>
 * @author lixuan
 * @since 2019/7/9.
 */
@XmlRootElement(name = "response")
@XmlSeeAlso({HotelDictionaryVO.class})
public class HotelLinkGetCdsDataVO<T>  extends LinkHotelVO {
    /**
     * 总数
     */
    private Integer total;
    /**
     *
     */
    private List<T> results;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
