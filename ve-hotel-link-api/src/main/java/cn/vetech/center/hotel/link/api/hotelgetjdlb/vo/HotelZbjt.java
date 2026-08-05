package cn.vetech.center.hotel.link.api.hotelgetjdlb.vo;

/**
 * 酒店周边交通，参考携程节点：TransportantionInfos
 * @author zhangheng
 * @version 2019-4-23
 */
public class HotelZbjt {

    /**
     *  周边交通名称，TransportantionInfos[0].Name
     */
    private String zbjtmc;
    /**
     * 周边交通类型，TransportantionInfos[0].Type
     */
    private String zbjtlx;
    /**
     *  周边交通经度，TransportantionInfos[0].Coordinates.LAT
     */
    private String zbjtjd;
    /**
     *  周边交通纬度，TransportantionInfos[0].Coordinates.LNG
     */
    private String zbjtwd;
    /**
     * 周边交通距离，TransportantionInfos[0].Distance
     */
    private String zbjtjl;
    /**
     * 周边交通描述，TransportantionInfos[0].Directions
     */
    private String zbjtms;
    /**
     * 周边交通方式，TransportantionInfos[0].TransportationType
     */
    private String zbjtfs;
    /**
     * 周边交通时间，TransportantionInfos[0].TimeTaken
     */
    private String zbjtsj;


    public String getZbjtmc() {
        return zbjtmc;
    }

    public void setZbjtmc(String zbjtmc) {
        this.zbjtmc = zbjtmc;
    }

    public String getZbjtlx() {
        return zbjtlx;
    }

    public void setZbjtlx(String zbjtlx) {
        this.zbjtlx = zbjtlx;
    }

    public String getZbjtjd() {
        return zbjtjd;
    }

    public void setZbjtjd(String zbjtjd) {
        this.zbjtjd = zbjtjd;
    }

    public String getZbjtwd() {
        return zbjtwd;
    }

    public void setZbjtwd(String zbjtwd) {
        this.zbjtwd = zbjtwd;
    }

    public String getZbjtjl() {
        return zbjtjl;
    }

    public void setZbjtjl(String zbjtjl) {
        this.zbjtjl = zbjtjl;
    }

    public String getZbjtms() {
        return zbjtms;
    }

    public void setZbjtms(String zbjtms) {
        this.zbjtms = zbjtms;
    }

    public String getZbjtfs() {
        return zbjtfs;
    }

    public void setZbjtfs(String zbjtfs) {
        this.zbjtfs = zbjtfs;
