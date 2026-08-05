package cn.vetech.center.hotel.link.elong.ratesearch.response;

import java.util.List;

/**
 * @author xiaotengyu
 * @since 2021/10/18 16:57
 */
public class ElongExtend {

    /**
     * 艺龙限价 扩展
     */
    private List<NightPriceExtend> priceExtends;
    /**
     * 限价类型
     */
    private Integer priceLimitedType;

    /**
     * 产品唯一标识，该RatePlan包含促销信息（DayPromotions和PromotionFlags）时，通过hotel.order.create接口成单时使用，需要联系商务开通权限
     * 请求为国际酒店时，用于标识产品
     */
    private String littlemajiaid;
    /**
     * 供应商酒店编码
     */
    private String hotelCode;
    /**
     * 供应商id
     */
    private String supplierId;
    /**
     * 二级供应商id
     */
    private String subSupplierId;
    /**
     * 商品库shopperid
     */
    private String shopperProductId;
    /**
     * 币种
     */
    private String currencyCode;
    /**
     * 含餐份数,B_1
     * 早B中L晚D其他Q
     * Q其他，在特定时间内才有
     */
    private String freeMeal;
    /**
     * 最晚取消时间格式YYYY-MM-DD HH:MM:SS
     * 注意：这个时间必须是最晚免费取消时间
     */
    private String zwqxsj;
    /**
     * 是否港澳台
     */
    private String sfgat;
    /**
     * 成人数，国际专用
     */
    private Integer numberOfAdults;
    /**
     * 房间数 国际专用
     */
    private Integer numberOfRooms;
    /**
     * 儿童年龄
     */
    private List<Integer> childAges;

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public List<Integer> getChildAges() {
        return childAges;
    }

    public void setChildAges(List<Integer> childAges) {
        this.childAges = childAges;
    }

    public String getSfgat() {
        return sfgat;
    }

    public void set