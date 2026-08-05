package cn.vetech.center.hotel.link.elong.validatebooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2025/10/15 15:48
 */
public class ElongLadderParse {
    /**
     * 开始时间	long	N	北京时间时间戳，秒(非毫秒)，例如：1749636000
     */
    @JsonProperty("BeginTime")
    private String beginTime;
    /**
     * 结束时间	long	N	北京时间时间戳，秒(非毫秒)，例如：4102329600
     */
    @JsonProperty("EndTime")
    private String endTime;
    /**
     * 扣费类型	int	N	0:不扣费；1:金额；2：比例；3：首晚房费；4：全额房费；请注意：hotel.detail、hotel.data.booking、hotel.order.detail接口中CutType、CutValue存在不一致的情况，对客展示建议直接使用具体扣费金额：AmountRmb（兜底当扣费金额AmountRmb大于订单金额时，展示不可取消）
     */
    @JsonProperty("CutType")
    private String cutType;
    /**
     * 扣费值	Decimal	N	原始币种
     */
    @JsonProperty("CutValue")
    private String cutValue;
    /**
     * 扣费值	Decimal	N	国际现付的是原币，预付对客的是人民币，预付对酒店的是原币。
     */
    @JsonProperty("Amount")
    private String amount;
    /**
     * 短文案	String	Y
     */
    @JsonProperty("ShortDesc")
    private String shortDesc;
    /**
     * 扣费值	Decimal	N	人民币 
     */
    @JsonProperty("AmountRmb")
    private String amountRmb;
    /**
     * 汇率	Decimal	Y
     */
    @JsonProperty("ExchangeRate")
    private String exchangeRate;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCutType() {
        return cutType;
    }

    public void setCutType(String cutType) {
        this.cutType = cutType;
    }

    public String getCutValue() {
        return cutValue;
    }

    public void setCutValue(String cutValue) {
        this.cutValue = cutValue;
    }

    public String getAmount() {
        return amount;
 