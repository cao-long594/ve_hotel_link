package cn.vetech.center.hotel.link.api.hotelgetjdgjhxx;

public class HotelGjhVO {

    /**
     * 酒店ID
     */
    private String jdid;
    /**
     * 酒店名称
     */
    private String jdmc;
    /**
     * 酒店地址
     */
    private String dz;
    /**
     * 语种
     */
    private String veLanguage;
    /**
     * 酒店简介
     */
    private String jj;
    /**
     * 特别提示
     */
    private String tbts;
    /**
     * 酒店正常
     */
    private String jdzclist;
    /**
     * 支付方式
     */
    private String payCards;

    public String getJdid() {
        return jdid;
    }

    public void setJdid(String jdid) {
        this.jdid = jdid;
    }

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

    public String getVeLanguage() {
        return veLanguage;
    }

    public void setVeLanguage(String veLanguage) {
        this.veLanguage = veLanguage;
    }

    public String getJj() {
        return jj;
    }

    public void setJj(String jj) {
        this.jj = jj;
    }

    public String getTbts() {
        return tbts;
    }

    public void setTbts(String tbts) {
        this.tbts = tbts;
    }

    public String getJdzclist() {
        return jdzclist;
    }

    public void setJdzclist(String jdzclist) {
        this.jdzclist = jdzclist;
    }

    public String getPayCards() {
        return payCards;
    }

    public void setPayCards(String payCards) {
        this.payCards = payCards;
    }
}
