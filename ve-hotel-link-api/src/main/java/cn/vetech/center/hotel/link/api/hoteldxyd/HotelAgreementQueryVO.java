package cn.vetech.center.hotel.link.api.hoteldtxyjd;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author vetech
 * @since 25/06/24
 */
@XmlRootElement(name = "response")
public class HotelAgreementQueryVO {

    private Integer total;

    private Integer size;

    private Integer current;

    /**
     * 集团协议的房源编号
     */
    private List<String> hotelGroupList;
    /**
     *
     */
    private List<HotelAgreementQueryItemVO> itemVOList;


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public List<HotelAgreementQueryItemVO> getItemVOList() {
        return itemVOList;
    }

    public void setItemVOList(List<HotelAgreementQueryItemVO> itemVOList) {
        this.itemVOList = itemVOList;
    }

    public List<String> getHotelGroupList() {
        return hotelGroupList;
    }

    public void setHotelGroupList(List<String> hotelGroupList) {
        this.hotelGroupList = hotelGroupList;
    }
}
