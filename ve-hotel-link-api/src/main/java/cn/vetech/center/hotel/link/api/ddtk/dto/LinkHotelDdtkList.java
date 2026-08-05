package cn.vetech.center.hotel.link.api.ddtk.dto;

/**
 * @author chengwanshan
 * @since 2021/1/19 9:38
 */
public class LinkHotelDdtkList {
    /**
     * Decimal	是	退款金额【单个支付方式的退款】
     */
    private String tkje;
    /**
     * String	否	支付方式：1公司月结2一网通3微信4银联5支付宝
     */
    private String tkfs;
    /**
     * String	否	退款流水号
     */
    private String tklsh;
    /**
     * 支付宝、微信等退款流水号
     */
    private String ystklsh;
    /**
     * 金额类型：1服务费
     */
    private String tklx;

    public String getTklx() {
        return tklx;
    }

    public void setTklx(String tklx) {
        this.tklx = tklx;
    }

    public String getYstklsh() {
        return ystklsh;
    }

    public void setYstklsh(String ystklsh) {
        this.ystklsh = ystklsh;
    }

    public String getTkje() {
        return tkje;
    }

    public void setTkje(String tkje) {
        this.tkje = tkje;
    }

    public String getTkfs() {
        return tkfs;
    }

    public void setTkfs(String tkfs) {
        this.tkfs = tkfs;
    }

    public String getTklsh() {
        return tklsh;
    }

    public void setTklsh(String tklsh) {
        this.tklsh = tklsh;
    }
}
