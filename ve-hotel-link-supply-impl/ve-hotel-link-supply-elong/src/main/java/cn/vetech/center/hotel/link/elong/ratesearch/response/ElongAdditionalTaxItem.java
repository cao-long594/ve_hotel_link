package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaotengyu
 * @since 2024-05-10 17:33
 */
public class ElongAdditionalTaxItem {

    /**
     * 另付税和服务费明细描述	String	Y
     */
    @JsonProperty("Description")
    private String description;
    /**
     * 另付税和服务费明细金额	Decimal	Y
     */
    @JsonProperty("Amount")
    private String amount;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
