package cn.vetech.center.hotel.link.elong.ratesearch.request;

import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 艺龙查询报价请求对象
 *
 * @author gaojin
 */
public class ElongRateSearchRequest extends ElongRequest {
    /**
     * 入住日期
     */
    @JsonProperty("ArrivalDate")
    private String arrivalDate;

    /**
     * 离店日期
     */
    @JsonProperty("DepartureDate")
    private String departureDate;

    /**
     * 酒店ID列表
     */
    @JsonProperty("HotelIds")
    private String hotelIds;

    /**
     * 房型编码
     */
    @JsonProperty("RoomTypeId")
    private String roomTypeId;

    /**
     * 价格计划id
     */
    @JsonProperty("RatePlanId")
    private String ratePlanId;

    /**
     * 支付方式。All-全部、SelfPay-现付、Prepay-预付
     */
    @JsonProperty("PaymentType")
    private String paymentType;

    /**
     * 其他条件。默认0
     */
    @JsonProperty("Options")
    private String options;
    /**
     * 成人数，国际专用
     */
    @JsonProperty("NumberOfAdults")
    private Integer numberOfAdults;
    /**
     * 房间数 国际专用
     */
    @JsonProperty("NumberOfRooms")
    private Integer numberOfRooms;
    /**
     * 儿童年龄
     */
    @JsonProperty("ChildAges")
    private List<Integer> childAges;
    /**
     * 会员openId
     */
    @JsonProperty("OpenId")
    private String openId;
    /**
     * 是否保存Littlemajiaid  国际特有字段, 当需要请求可定时，该字段设置为true，才能进行可定验证
     */
    @JsonProperty("SaveMajiaId")
    private Boolean saveMajiaId;

    public Boolean getSaveMajiaId() {
        return saveMajiaId;
    }

    public void setSaveMajiaId(Boolean saveMajiaId) {
        this.saveMajiaId = saveMajiaId;
    }

    public List<Integer> getChildAges() {
        return childAges;
    }

    public void setChildAges(List<Integer> childAges) {
        this.childAges = childAges;
    }

    public String getOpenId() {
   