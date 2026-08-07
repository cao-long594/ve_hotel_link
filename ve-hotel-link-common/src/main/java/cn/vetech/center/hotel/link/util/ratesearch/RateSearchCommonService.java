package cn.vetech.center.hotel.link.util.ratesearch;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.util.ratesearch.cancelrule.CancelRuleContentService;
import cn.vetech.center.hotel.link.util.ratesearch.cancelrule.CancelRuleDescCutEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chengwanshan
 * @since 2022/8/10 17:27
 */
@Service
public class RateSearchCommonService {

    /**
     * 处理 简版的取消规则说明 字段qxgzsmcut的关键字
     */
    private static final String[] FREE_CANCEL_TEXT_ARR = new String[]{"免费取消", "免费退订"};
    /**
     * 若确实没有可免费取消的时间点，则会统一显示一个标签”有条件取消“
     */
    public static final String CONDITION_CANCEL_TEXT = "有条件取消";
    /**
     * 有免费取消，但无法解析具体时间点，限时取消
     */
    public static final String TIME_CANCEL_TEXT = "限时取消";

    /**
     * 取消规则描述的分割符
     */
    private static final String[] CANCEL_RULE_DESC_SPLIT_ARR = new String[]{SymbolConstant.SEMICOLON, SymbolConstant.FULL_SEMICOLON, SymbolConstant.COMMA, SymbolConstant.FULL_COMMA};

    /**
     * cancelRuleContentService注入
     */
    @Autowired
    private CancelRuleContentService cancelRuleContentService;

    /**
     * 限时取消时，处理简版的取消规则说明。注意：简版的取消规则说明，只要月 日 时分，”07月23日12:00前可免费取消“；若实在无法解析出来的，那就不要对这个字段赋值，保持原来不变
     *
     * @param description 取消规则描述
     * @param checkInDate 入住日期
     * @return ImmutablePair<String, String> left:取消规则简版描述，right：最晚免费取消时间
     */
    public ImmutablePair<String, String> convertCancelRuleDescCut(String description, String checkInDate) {
        try {
            if (StringUtils.isBlank(description)) {
                return ImmutablePair.of(null, null);
            }
            // 若确实没有可免费取消的时间点，则会统一显示一个标签”有条件取消“
            if (!StringUtils.containsAny(description, FREE_CANCEL_TEXT_ARR)) {
  return ImmutablePair.of(CONDITION_CANCEL_TEXT, null);
            }
            // 正则匹配
            CancelRuleDescCutEnum cancelRuleDescCutEnum = CancelRuleDescCutEnum.getCancelRuleDescCutEnum(description);
            if (cancelRuleDescCutEnum == null) {
                return ImmutablePair.of(null, null);
            }
            return cancelRuleContentService.handleCancelRuleDescCut(cancelRuleDescCutEnum, description, checkInDate);
        } catch (Exception e) {
            return ImmutablePair.of(null, null);
        }
    }
}
