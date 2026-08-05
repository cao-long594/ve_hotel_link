package cn.vetech.center.hotel.link.api.tdsq.dto;


import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author xingyanyan
 */
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class LinkHotelCgtdsqDTO extends LinkHotelDTO {
    /**
     * 原订单号，CPS原订单编号
     */
    private String ddbh;
    /**
     * 退房原因
     */
    private String tfyy;
    /**
     * 退款房间信息
     */
    @XmlElementWrapper(name = "tfxxs")
    @XmlElement(name = "tfxx")
    private List<Tfxx> tfxxs;
    /**
     * 供应商编号
     */
    private String gyshbh;
    /**
     * asms退单号
     */
    private String asmstdh;
    /**
     * 服务商服务费
     */
    private String fwfFws;
    /**
     * 退房原因凭证附件，例如疾病照片等
     */
    private List<String> tfyyfjList;
    /**
     * FCB订票员编号
     */
    private String dpyyhbh;
    /**
     * 入住日期：yyyy-mm-dd
     */
    private String checkInDate;
    /**
     * 离店日期：yyyy-mm-dd
     */
    private String checkOutDate;
    /**
     * 申请类型
     * 1 超时整单取消
     * 2 提前离店
     */
    private String applyType;

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getDpyyhbh() {
        return dpyyhbh;
    }

    public void setDpyyhbh(String dpyyhbh) {
        this.dpyyhbh = dpyyhbh;
    }

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String d