package cn.vetech.center.hotel.link.util.ratesearch.cancelrule;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author chengwanshan
 * @since 2022/8/10 15:30
 */
@Service
public class CancelRuleContentService {
    /**
     *
     */
    @Autowired
    private Map<String, ICancelRuleStrategy> cancelRuleStrategyMap;

    /**
     * @param cancelRuleDescCutEnum cancelRuleDescCutEnum
     * @param description           取消规则说明
     * @param checkInDate           入住日期
     * @return String
     */
    public ImmutablePair<String, String> handleCancelRuleDescCut(CancelRuleDescCutEnum cancelRuleDescCutEnum, String description, String checkInDate) {
        ICancelRuleStrategy iCancelRuleStrategy = cancelRuleStrategyMap.get(cancelRuleDescCutEnum.getCode());
        if (iCancelRuleStrategy == null) {
            return ImmutablePair.of(null, null);
        }
        return iCancelRuleStrategy.convertCancelRuleDescCut(cancelRuleDescCutEnum, description, checkInDate);
    }
}
