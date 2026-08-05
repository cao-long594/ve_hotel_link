package cn.vetech.center.hotel.link.api.hoteldtxyjd;

/**
 * @author vetech
 * @since 25/06/24
 */
public class HotelAgreementQueryItemVO {

    /**
     * 酒店id
     */
    private String veJdid;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 酒店英文名称
     */
    private String hotelNameEn;


    /**
     * 携程酒店id
     */
    private String xcwHotelId;


    /**
     * 艺龙酒店id
     */
    private String elongHotelId;

    /**
     * 美团
     */
    private String mtwHotelId;


    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 城市id
     */
    private String cityId;

    /**
     * expedia
     */
    private String expHotelId;

    /**
     * 协议类型 12单体协议；13集团协议
     */
    private String agreementType;
    /**
     *  2：接口托管单体协议酒店-非cps
     */
    private String dtxylx;

    /**
     * 酒店集团名称
     */
    private String hotelGroupName;

    public String getVeJdid() {
        return veJdid;
    }

    public void setVeJdid(String veJdid) {
        this.veJdid = veJdid;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelNameEn() {
        return hotelNameEn;
    }

    public void setHotelNameEn(String hotelNameEn) {
        this.hotelNameEn = hotelNameEn;
    }

    public String getXcwHotelId() {
        return xcwHotelId;
    }

    public void setXcwHotelId(String xcwHotelId) {
        this.xcwHotelId = xcwHotelId;
    }

    public String getElongHotelId() {
        return elongHotelId;
    }

    public void setElongHotelId(String elongHotelId) {
        this.elongHotelId = elongHotelId;
    }

    public String getMtwHotelId() {
        return mtwHotelId;
    }

    public void setMtwHotelId(String mtwHotelId) {
        this.mtwHotelId = mtwHotelId;
    }

   