package cn.vetech.center.hotel.link.api.hotelgetjdfx;


import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author xingyanyan
 * @since 2019/6/29 15:00
 */
@XmlRootElement(name = "request")
public class HotelLinkGetJdfxDTO extends LinkHotelDTO {
    /**
     * //0返回房型详细信息及映射关系信息，1表示房型映射关系 2房型未映射的数据
     */
    private String infoType;
    /**
     * //酒店id
     */
    private String jdid;
    /**
     * //0，表示查询所有的房型，在订单修改及预订的时候重选房型用上
     */
    private String fxid;
    /**
     *要获取的房源编号
     */
    private List<String> fybhs;

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getJdid() {
        return jdid;
    }

    public void setJdid(String jdid) {
        this.jdid = jdid;
    }

    public String getFxid() {
        return fxid;
    }

    public void setFxid(String fxid) {
        this.fxid = fxid;
    }

    public List<String> getFybhs() {
        return fybhs;
    }

    public void setFybhs(List<String> fybhs) {
        this.fybhs = fybhs;
    }
}
