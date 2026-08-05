package cn.vetech.center.hotel.link.api.ratesearch.vo;

/**
 * @author chengwanshan
 * @since 2024/12/5 19:49
 */
public class HotelLadderDeductionInfo {
    /**
     * 取消类型
     * FREE（免费取消）；LADDER（阶梯取消）；CANNOT_CANCEL（不可取消）
     * 参考枚举 HotelDeductionTypeEnum
     */
    private String deductionType;
    /**
     * 供应商原始时间，扣款开始时间
     * 免费取消类型的开始时间默认给1970-01-01 08:00:00
     * 例如：2024-04-17 15:57:40
     */
    private HotelTimeInfo originalStartDeductTime;
    /**
     * 供应商原始时间，扣款结束时间
     * 不可取消的结束时间默认给9999-12-31 23:59:59
     * 例如：2024-04-20 18:00:00
     */
    private HotelTimeInfo originalEndDeductTime;
    /**
     * 酒店当地时间，扣款开始时间
     * 免费取消类型的开始时间默认给1970-01-01 08:00:00
     * 例如：2024-04-17 15:57:40
     */
    private HotelTimeInfo localStartDeductTime;
    /**
     * 酒店当地时间，扣款结束时间
     * 不可取消的结束时间默认给9999-12-31 23:59:59
     * 例如：2024-04-20 18:00:00
     */
    private HotelTimeInfo localEndDeductTime;
    /**
     * 北京时间，扣款开始时间
     * 免费取消类型的开始时间默认给1970-01-01 08:00:00
     * 例如：2024-04-17 15:57:40
     */
    private HotelTimeInfo beijingStartDeductTime;
    /**
     * 北京时间，扣款结束时间
     * 不可取消的结束时间默认给9999-12-31 23:59:59
     * 例如：2024-04-20 18:00:00
     */
    private HotelTimeInfo beijingEndDeductTime;
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

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public HotelTimeInfo getOriginalStartDeductTime() {
        return originalStartDeductTime;
    }

    public void setOriginalStartDeductTime(HotelTimeInfo originalStartDeductTime) {
        this.originalStartDeductTime = originalStartDeductTime;
    }

    public HotelTimeInfo getOriginalEn