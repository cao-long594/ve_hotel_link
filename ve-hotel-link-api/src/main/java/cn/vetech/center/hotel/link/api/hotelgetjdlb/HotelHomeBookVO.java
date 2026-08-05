package cn.vetech.center.hotel.link.api.hotelgetjdlb;

/**
 * @author tianjie
 * 非查询酒店列表参数，但是是前端查询酒店列表必穿参数，专供费控平台使用
 * 酒店首页查询，需要的差旅相关信息
 * 酒店book请求参数封装类（原controller方法添加了大量的string参数，使用统一的实体类封装请求）
 */
public class HotelHomeBookVO extends HotelLinkGetJdlbDTO {
    /**
     * 审批单号
     */
    private String ccsqdh;
    /**
     * 拼房数据
     */
    private String pfdata;
    /**
     * 审批单号 包含的数据
     */
    private String spdhData;
    /**
     * 出行人数据
     */
    private String cxridsJson;
    /**
     * 出行人ids
     */
    private String cxrids;
    /**
     * 出行人部门ids
     */
    private String cxrbmids;
    /**
     *clsx
     */
    private String clsx;
    /**
     * jd_cxr_data
     */
    private String jdCxrData;
    /**
     *ifsqd
     */
    private String ifsqd;
    /**
     *   cbzxs
     */
    private String cbzxs;
    /**
     *minPrice
     */
    private String minPrice;
    /**
     *clyy
     */
    private String clyy;

    public String getCcsqdh() {
        return ccsqdh;
    }

    public void setCcsqdh(String ccsqdh) {
        this.ccsqdh = ccsqdh;
    }

    /**
     * jdfp
     */




    public String getPfdata() {
        return pfdata;
    }

    public void setPfdata(String pfdata) {
        this.pfdata = pfdata;
    }

    public String getSpdhData() {
        return spdhData;
    }

    public void setSpdhData(String spdhData) {
        this.spdhData = spdhData;
    }

    public String getCxridsJson() {
        return cxridsJson;
    }

    public void setCxridsJson(String cxridsJson) {
        this.cxridsJson = cxridsJson;
    }

    public String getCxrids() {
        return cxrids;
    }

    public void setCxrids(String cxrids) {
        this.cxrids = cxrids;
    }

    public String getClsx() {
        return clsx;
    }

    public void setClsx(String clsx) {
        this.clsx = clsx;
    }

    public String getJdCxrDat