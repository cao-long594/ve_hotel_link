package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author gaojin
 */
public class ElongSearchHotel {
    /**
     * 酒店编码，房源商酒店ID
     */
    @JsonProperty("HotelId")
    private String hotelId;
    /**
     * 最低价格
     */
    @JsonProperty("LowRate")
    private String lowRate;
    /**
     * 最低价格的货币
     */
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    /**
     * 距离搜索的时候有值
     */
    @JsonProperty("Distance")
    private String distance;
    /**
     * 酒店设施
     * 1-免费wifi、2-收费wifi、3-免费宽带、4-收费宽带、5-免费停车场
     * 6-收费停车场、7-免费接机服务、8-收费接机服务、9-室内游泳池、10-室外游泳池
     * 11-健身房、12-商务中心、13-会议室、14-酒店餐厅
     */
    @JsonProperty("Facilities")
    private String facilities;
    /**
     * 预订规则
     */
    @JsonProperty("BookingRules")
    private List<ElongSearchBookingRule> bookingRules;
    /**
     * 担保规则
     */
    @JsonProperty("GuaranteeRules")
    private List<ElongSearchGuaranteeRule> guaranteeRules;
    /**
     * 预付规则
     */
    @JsonProperty("PrepayRules")
    private List<ElongSearchPrepayRule> prepayRules;
    /**
     * 增值服务
     */
    @JsonProperty("ValueAdds")
    private List<ElongSearchValueAdd> valueAdds;
    /**
     * 促销规则
     */
    @JsonProperty("DrrRules")
    private List<ElongSearchDrrRule> drrRules;
    /**
     * 房间节点
     */
    @JsonProperty("Rooms")
    private List<ElongSearchRoom> rooms;
    /**
     * 酒店特殊信息提示
     */
    @JsonProperty("HAvailPolicys")
    private List<ElongSearchHAvailPolicy> hAvailPolicys;
    /**
     * 礼品优惠
     */
    @JsonProperty("Gifts")
    private List<ElongSearchGift> gifts;

    /**
     * 礼包套餐
     */
    @JsonProperty("GiftPackages")
    private List<ElongSearchGiftPackages> giftPackages;

    public List<ElongSearchGiftPackages> getGiftPackages() {
        return giftPackages;
    }

    public void s