package cn.vetech.center.hotel.link.api.hoteldtxyjd;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author vetech
 * @since 25/06/24
 */
@XmlRootElement(name = "request")
public class HotelAgreementQueryDTO extends LinkHotelDTO {

    /**
     * 条数
     */
    private Integer size;

    /**
     * 当前
     */
    private Integer current;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 酒店id
     */
    private List<String> veJdidList;

    /**
     * 酒店id类型
     */
    private String hotelIdType;

    /**
     * 酒店id
     */
    private List<String> hotelIdList;

    /**
     * 根据传入的集团协议的房源编号
     */

    private List<String> hotelGroupList;


    /**
     *
     */
    private String cityId;


    /**
     * 接口协议类：12接口托管单体协议（指企业单个的协议酒店价格）；13接口托管集团协议（指企业的集团协议价格例如如家、亚朵等
     */
    private String agreementType;
    /**
     *    2本地单体协议
     */
    private String dtxylx;


    /**
     * 上一页最后一个酒店id 导出的第二页需要
     */
    private String prevJdid;

    /**
     * 是否为导出 导出为1
     */
    private String fcExport;



    @Override
    public String getHotelName() {
        return hotelName;
    }

    @Override
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<String> getVeJdidList() {
        return veJdidList;
    }

    public void setVeJdidList(List<String> veJdidList) {
        this.veJdidList = veJdidList;
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

    public String getAgree