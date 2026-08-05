package cn.vetech.center.hotel.link.api.hotelgetjdlb.dto;

/**
 * @author liven
 * @since 2018/7/10 16:50
 * 描述：
 **/
public class JdGlszDto {

    /**
     * 主键 ID
     */
    private String id;
    /**
     * 渠道 ASMS,SpS,B2G ,B2C
     */
    private String qd;
    /**
     * 合作网站站点 B2C时有合作站点号
     */
    private String hzwz;
    /**
     * 总公司ID
     */
    private String zgs;
    /**
     * 客户ID B2G时：差旅会员ID，单个
     */
    private String khid;
    /**
     * 控制类型 1分控,2个控 B2G时，有分控和个控，其它都只有分控
     */
    private String kzlx;
    /**
     * 适用国家编号
     */
    private String sygj;
    /**
     * 所在城市
     */
    private String szcs;
    /**
     * 所在城市名称
     */
    private String szcsmc;
    /**
     * 行政区
     */
    private String xzq;
    /**
     * 过滤无行政区酒店  1是 0否（默认）
     */
    private String glwxzqjd;
    /**
     * 酒店名称
     */
    private String jdzwmc;
    /**
     * 酒店ID
     */
    private String jdid;
    /**
     * 酒店名称关键字
     */
    private String jdmcgjz;
    /**
     * 酒店主题
     */
    private String jdzt;
    /**
     * 星级
     */
    private String xj;
    /**
     * 星级类型，默认0挂牌星级+推荐星级，1仅挂牌星级，2仅推荐星级；
     */
    private String xjlx;
    /**
     * 品牌
     */
    private String pp;
    /**
     * 现付预付 0现付，1预付
     */
    private String xfyf;
    /**
     * 适用酒店分类：0：非协议；1：全部；2：非集团协议；3：非单体协议
     */
    private String syjdfl;
    /**
     *
     */
    private String zt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQd() {
        return qd;
    }

    public void setQd(String qd) {
        this.qd = qd;
    }

    public String getHzwz() {
        return hzwz;
    }

    public void setHzwz(String hzwz) {
        this.hzwz = hzwz;
    }

    public String getZgs() {
        return zgs;
    }

    public void setZgs(String zgs) {
        