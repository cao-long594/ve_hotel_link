package cn.vetech.center.hotel.link.api.orderreview.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

/**
 * @author chengwanshan
 * @since 2026/7/28 11:19
 */
public class LinkHotelOrderReviewDTO extends LinkHotelDTO {

    /**
     * 订单编号
     */
    private String ddbh;
    /**
     * 实际入住信息是否与订单相符 0相符 1不相符
     */
    private String sjrzxxsfxf;
    /**
     * 信息不符理由
     */
    private String xxbfly;
    /**
     * 签字确认图片或者url
     */
    private String qzqrtp;

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }

    public String getSjrzxxsfxf() {
        return sjrzxxsfxf;
    }

    public void setSjrzxxsfxf(String sjrzxxsfxf) {
        this.sjrzxxsfxf = sjrzxxsfxf;
    }

    public String getXxbfly() {
        return xxbfly;
    }

    public void setXxbfly(String xxbfly) {
        this.xxbfly = xxbfly;
    }

    public String getQzqrtp() {
        return qzqrtp;
    }

    public void setQzqrtp(String qzqrtp) {
        this.qzqrtp = qzqrtp;
    }
}
