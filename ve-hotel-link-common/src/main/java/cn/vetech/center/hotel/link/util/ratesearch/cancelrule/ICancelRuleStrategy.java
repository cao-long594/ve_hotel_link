package cn.vetech.center.hotel.link.util.ratesearch.cancelrule;

import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * @author chengwanshan
 * @since 2022/8/10 14:15
 */
public interface ICancelRuleStrategy {
    /**
     * @return cancelRuleDescCutEnum
     */
    CancelRuleDescCutEnum getStrategyType();

    /**
     * 转换取消规则简版
     *
     * @param cancelRuleDescCutEnum cancelRuleDescCutEnum
     * @param description           取消规则描述
     * @param checkInDate           入住日期
     * @return String
     */
    ImmutablePair<String, String> convertCancelRuleDescCut(CancelRuleDescCutEnum cancelRuleDescCutEnum, String description, String checkInDate);
}
