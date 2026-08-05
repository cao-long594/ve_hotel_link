package cn.vetech.center.hotel.link.api.hoteldtxyjd;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.charge.hotel.HotelGngjEnum;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author vetech
 * @since 25/06/23
 */
@XmlRootElement(name = "request")
public class HotelMarkAgreementItemDTO extends LinkHotelDTO {

    private String gngj = HotelGngjEnum.GN.getName();

    /**
     * 价格来源
     * 1、手动标记
     * 2、供应商接口同步
     */
    private String source="2";
    /**
     * 房源名称
     */
    private String fymc;
    /**
     * 0 移除
     * 1 添加
     * 接口单体协议
     */
    private String optType;

    /**
     *酒店id类型
     */
    private String hotelIdType;

    /**
     *酒店id
     */
    private List<String> hotelIdList;

    public String getGngj() {
        return gngj;
    }

    public void setGngj(String gngj) {
        this.gngj = gngj;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getHotelIdType() {
        return hotelIdType;
    }

    public void setHotelIdType(String hotelIdType) {
        this.hotelIdType = hotelIdType;
    }

    public List<String> getHotelIdList() {
        return hotelIdList;
    }

    public void setHotelIdList(List<String> hotelIdList) {
        this.hotelIdList = hotelIdList;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }
}
