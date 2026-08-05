package cn.vetech.center.hotel.link.api.hotelgetchineseinfo;

/**
 * @author xiaotengyu
 * @since 2023-05-31 14:05
 */
public class HotelGlobalVO {

    /**
     * 酒店名称
     */
    private String jdmc;
    /**
     * 地址
     */
    private String dz;
    /**
     * 语种
     */
    private String language;

    public String getJdmc() {
        return jdmc;
    }

    public void setJdmc(String jdmc) {
        this.jdmc = jdmc;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
