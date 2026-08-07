package cn.vetech.center.hotel.link.util.ratesearch.pojo;

import cn.vetech.center.hotel.link.api.enums.HotelDeductionTypeEnum;
import cn.vetech.center.hotel.link.api.ratesearch.vo.FeeInfo;

/**
 * @author chengwanshan
 * @since 2024/12/20 16:56
 */
public class SearchLadderDeductionInfo {
    /**
     * 取消类型
     * FREE（免费取消）；LADDER（阶梯取消）；CANNOT_CANCEL（不可取消）
     * 参考枚举 HotelDeductionTypeEnum
     */
    private HotelDeductionTypeEnum deductionType;
    /**
     * 酒店本地时区
     */
    private String hotelLocalTimeZone;
    /**
     *
     */
    private String startDateStr;
    /**
     *
     */
    private String startTimeZoneStr;
    /**
     *
     */
    private String endDateStr;
    /**
     *
     */
    private String endTimeZoneStr;
    /**
     * 扣款金额
     */
    private FeeInfo originPrice;
    /**
     * 扣除前几晚
     * 例如：扣除首晚，传1
     */
    private String nights;
    /**
     * 扣款比例
     * 例如：30%
     */
    private String percent;

    public FeeInfo getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(FeeInfo originPrice) {
        this.originPrice = originPrice;
    }

    public String getNights() {
        return nights;
    }

    public void setNights(String nights) {
        this.nights = nights;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public HotelDeductionTypeEnum getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(HotelDeductionTypeEnum deductionType) {
        this.deductionType = deductionType;
    }

    public String getHotelLocalTimeZone() {
        return hotelLocalTimeZone;
    }

    public void setHotelLocalTimeZone(String hotelLocalTimeZone) {
        this.hotelLocalTimeZone = hotelLocalTimeZone;
    }

    public String getStartDateStr() {
        return sta