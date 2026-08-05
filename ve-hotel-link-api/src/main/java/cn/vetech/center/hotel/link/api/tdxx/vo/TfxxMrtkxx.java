package cn.vetech.center.hotel.link.api.tdxx.vo;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * 每日退单信息bean
 * @author xiangmeng
 */
public class TfxxMrtkxx {
    /**
     * 取消入住日期
     */
    private Date qxrzrq;
    /**
     * 当日房价  Cps对采购控润后的销售价
     */
    private Double fj;
    /**
     *审核应退房费
     */
    private Double ptshtff;

    @XmlElement(name = "qxrzrq")
    public Date getQxrzrq() {
        return qxrzrq;
    }

    public void setQxrzrq(Date qxrzrq) {
        this.qxrzrq = qxrzrq;
    }

    @XmlElement(name = "fj")
    public Double getFj() {
        return fj;
    }

    public void setFj(Double fj) {
        this.fj = fj;
    }

    @XmlElement(name = "ptshtff")
    public Double getPtshtff() {
        return ptshtff;
    }

    public void setPtshtff(Double ptshtff) {
        this.ptshtff = ptshtff;
    }
}
