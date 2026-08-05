package cn.vetech.center.hotel.link.api.hotelgetjdlb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 采购自定义配置
 *
 * @author luqs
 * @version v1.0
 */
public class PurchaseDiyConfDTO implements Serializable {
    private static final long serialVersionUID = 7196391973613040640L;

    /**
     * 差标，注：超此差标，价格过滤（若存在阶梯差标，则以阶梯差标为准）！！！
     */
    private BigDecimal travelStdFee;
    /**
     * 阶梯差标，注：超此差标，价格过滤！！！
     */
    private BigDecimal ladderTravelStdFee;
    /**
     * 过滤规则
     */
    private List<PurchasePriceFilterRuleDTO> filterRuleList;

    public BigDecimal getTravelStdFee() {
        return travelStdFee;
    }

    public void setTravelStdFee(BigDecimal travelStdFee) {
        this.travelStdFee = travelStdFee;
    }

    public BigDecimal getLadderTravelStdFee() {
        return ladderTravelStdFee;
    }

    public void setLadderTravelStdFee(BigDecimal ladderTravelStdFee) {
        this.ladderTravelStdFee = ladderTravelStdFee;
    }

    public List<PurchasePriceFilterRuleDTO> getFilterRuleList() {
        return filterRuleList;
    }

    public void setFilterRuleList(List<PurchasePriceFilterRuleDTO> filterRuleList) {
        this.filterRuleList = filterRuleList;
    }
}
