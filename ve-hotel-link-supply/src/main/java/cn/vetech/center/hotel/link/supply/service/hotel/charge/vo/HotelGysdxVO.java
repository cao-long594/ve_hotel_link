package cn.vetech.center.hotel.link.supply.service.hotel.charge.vo;

import java.io.Serializable;

/**
 * @author liven
 * @since 2018/8/9 15:58
 * 描述：供应商映射关系对象
 **/
public class HotelGysdxVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商编号
     */
    private String gysbh;

    /**
     * 供应商名称
     */
    private String gysmc;
    /**
     *
     */
    private String hotelid;

    public String getGysbh() {
        return gysbh;
    }

    public void setGysbh(String gysbh) {
        this.gysbh = gysbh;
    }

    public String getGysmc() {
        return gysmc;
    }

    public void setGysmc(String gysmc) {
        this.gysmc = gysmc;
    }

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }
}
