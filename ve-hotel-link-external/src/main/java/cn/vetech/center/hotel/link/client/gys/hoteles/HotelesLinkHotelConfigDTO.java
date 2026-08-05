package cn.vetech.center.hotel.link.client.gys.hoteles;

/**
 * @author xiaotengyu
 * @since 2022-06-08 16:06
 */
public class HotelesLinkHotelConfigDTO {

    /**
     * 商户编号
     */
    private String shbh;
    /**
     * 状态 1 可以，0:不可用
     */
    private String zt;
    /**
     * 房源编号
     */
    private String fybh;
    /**
     * 房源简称
     */
    private String zhmc;

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getZhmc() {
        return zhmc;
    }

    public void setZhmc(String zhmc) {
        this.zhmc = zhmc;
    }
}
