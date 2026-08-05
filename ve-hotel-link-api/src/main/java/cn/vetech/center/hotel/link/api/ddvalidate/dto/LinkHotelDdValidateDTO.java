package cn.vetech.center.hotel.link.api.ddvalidate.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

/**
 * @author : xiangmeng
 * @since : 9:03 2021/7/28
 */
public class LinkHotelDdValidateDTO  extends LinkHotelDTO {
    /**
     * 订单校验类型
     */
    private String ddjylx;
    /**
     * cps订单号
     */
    private String cpsddbh;
    /**
     * 采购订单号
     */
    private String cgddbh;

    public String getCpsddbh() {
        return cpsddbh;
    }

    public void setCpsddbh(String cpsddbh) {
        this.cpsddbh = cpsddbh;
    }

    public String getCgddbh() {
        return cgddbh;
    }

    public void setCgddbh(String cgddbh) {
        this.cgddbh = cgddbh;
    }
}
