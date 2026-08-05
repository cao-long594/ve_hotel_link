package cn.vetech.center.hotel.link.client.gys.cps.dto;

/**
 * @author vetech
 * @since 2024/4/26
 */
public class RatePlanCollectGzipDTO {

    /**
     * 本地酒店id
     */
    private String loclHotelId;

    /**
     * 国内国际
     */
    private String gngj;

    /**
     * 房源编号
     */
    private String fybh;

    /**
     * 房源简称
     */
    private String fyjc;

    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 数据来源 RATE_SEARCH,REAL_TIME_PRICE,MIN_PRICE
     */
    private String source;
    /**
     * 折扣
     */
    private String discount;
    /**
     * 是否会员价
     */
    private String sfhyj;
    /**
     * 会员id，如果没有则使用会员手机号生成md5
     */
    private String hyId;
    /**
     * 商户编号
     */
    private String businessNo;

    /**
     *
     */
    private String shbh;
    /**
     * 价格信息压缩
     */
    private String linkHotelRatesearchVOGzipStr;

    public RatePlanCollectGzipDTO() {
    }

    public RatePlanCollectGzipDTO(String fybh, String fyjc, String hotelId, String source, String discount, String sfhyj, String loclHotelId, String linkHotelRatesearchVOGzipStr) {
        this.fybh = fybh;
        this.fyjc = fyjc;
        this.hotelId = hotelId;
        this.source = source;
        this.discount = discount;
        this.sfhyj = sfhyj;
        this.loclHotelId = loclHotelId;
        this.linkHotelRatesearchVOGzipStr = linkHotelRatesearchVOGzipStr;
    }

    public String getLoclHotelId() {
        return loclHotelId;
    }

    public void setLoclHotelId(String loclHotelId) {
        this.loclHotelId = loclHotelId;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }


    public String getFyjc() {
        return fyjc;
    }

    public void setFyjc(String fyjc) {
        this.fyjc = fyjc;
    }

    public String getHotelId() {
        return hotelId