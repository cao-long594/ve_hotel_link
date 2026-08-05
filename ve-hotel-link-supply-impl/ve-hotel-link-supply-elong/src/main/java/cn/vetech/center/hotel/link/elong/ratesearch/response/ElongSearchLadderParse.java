package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author xiaotengyu
 * @since 2023-02-03 14:29
 */
public class ElongSearchLadderParse {

    /**
     * 此时间点之前免费取消	long	N	不可取消时:-28800
     */
    @JsonProperty("BeginTime")
    private String beginTime;
    /**
     * 此时间点之后不可取消	long	N	不可取消时:-28800
     */
    @JsonProperty("EndTime")
    private String endTime;
    /**
     * 扣费类型	int	N	0:不扣费；1:金额；2：比例；3：首晚房费；
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
    private BigDecimal amount;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getShortDesc(