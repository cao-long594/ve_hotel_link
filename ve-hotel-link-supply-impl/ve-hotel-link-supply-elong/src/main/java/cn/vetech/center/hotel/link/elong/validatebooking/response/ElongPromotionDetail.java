package cn.vetech.center.hotel.link.elong.validatebooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2025/10/15 15:14
 */
public class ElongPromotionDetail {
    /**
     * 促销金额	Decimal	Y
     */
    @JsonProperty("Amount")
    private String amount;
    /**
     * 促销类型	Int	Y	促销类型 9:立减
     */
    @JsonProperty("PromotionType")
    private String promotionType;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }
}
