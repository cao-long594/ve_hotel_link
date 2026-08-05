package cn.vetech.center.hotel.link.api.hotelgetjdfx.vo;

/**
 * @author gaojin
 */
public class HotelMap {
    /**
     * 房源商编号
     */
    private String fyid;
    /**
     * 房源商的酒店id
     */
    private String fyjdid;
    /**
     * 本地房型id
     */
    private String fxid;
    /**
     * 房源商的房型id
     */
    private String fyfxid;

    public String getFyid() {
        return fyid;
    }

    public void setFyid(String fyid) {
        this.fyid = fyid;
    }

    public String getFyjdid() {
        return fyjdid;
    }

    public void setFyjdid(String fyjdid) {
        this.fyjdid = fyjdid;
    }

    public String getFxid() {
        return fxid;
    }

    public void setFxid(String fxid) {
        this.fxid = fxid;
    }

    public String getFyfxid() {
        return fyfxid;
    }

    public void setFyfxid(String fyfxid) {
        this.fyfxid = fyfxid;
    }
}
