package cn.vetech.center.hotel.link.supply.service.ratesearch.realtime.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * cps价格查询
 *
 * @author luqs
 * @version v1.0
 */
@XmlRootElement(name = "response")
public class CpsPriceSearchResponse extends LinkHotelVO {
    /**
     * 价格信息
     */
    List<CpsHotelPriceItemVO> hotelPriceItemList;

    @XmlElementWrapper(name = "hotelPriceItemList")
    @XmlElement(name = "hotelPriceItem")
    public List<CpsHotelPriceItemVO> getHotelPriceItemList() {
        return hotelPriceItemList;
    }

    public void setHotelPriceItemList(List<CpsHotelPriceItemVO> hotelPriceItemList) {
        this.hotelPriceItemList = hotelPriceItemList;
    }
}
