package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author xiaotengyu
 * @since 2024-05-10 17:31
 */
public class ElongAdditionalTax {

    /**
     * 另付税和服务费总额	Decimal	Y
     */
    @JsonProperty("TotalAmountRmb")
    private String totalAmountRmb;
    /**
     * 另付税和服务费明细	AdditionalTaxItem[]	Y	参考AdditionalTaxItem节点
     */
    @JsonProperty("AdditionalTaxItems")
    private List<ElongAdditionalTaxItem> additionalTaxItems;

    public String getTotalAmountRmb() {
        return totalAmountRmb;
    }

    public void setTotalAmountRmb(String totalAmountRmb) {
        this.totalAmountRmb = totalAmountRmb;
    }

    public List<ElongAdditionalTaxItem> getAdditionalTaxItems() {
        return additionalTaxItems;
    }

    public void setAdditionalTaxItems(List<ElongAdditionalTaxItem> additionalTaxItems) {
        this.additionalTaxItems = additionalTaxItems;
    }
}
