package cn.vetech.center.hotel.link.util.ratesearch.cancelrule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chengwanshan
 * @since 2022/8/10 14:22
 */
public enum CancelRuleDescCutEnum {
    /**
     * 日期格式
     * 限时退订:2023-05-19 18:00:00前免费退订
     * 您在2023-05-19 18:00前可免费取消
     */
    DATE_LONG("dateLongCancelRuleStrategy", "日期格式", new Pattern[]{Pattern.compile("(.*)((\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}|\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})(|前)(|可|可以)(免费取消|免费变更取消|免费退订))(.*)")}),
    /**
     * 您可以在2024年10月14日12:00前免费取消或变更订单
     * 入住当天12:00前免费取消
     */
    DATE_DESC("dateDescCancelRuleStrategy", "日期文字描述", new Pattern[]{Pattern.compile("(.*)((((\\d{4}年(?:1[0-2]月|0?[1-9]月)(?:0?[1-9]|[12][0-9]|3[01])日)|入住当天)(?:[01]\\d|2[0-3]):[0-5]\\d)(|前)(|可|可以)(免费取消|免费变更取消|免费退订))(.*)")}),
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;
    /**
     *
     */
    private final Pattern[] pattern;

    private CancelRuleDescCutEnum(String code, String name, Pattern[] pattern) {
        this.code = code;
        this.name = name;
        this.pattern = pattern;
    }

    /**
     * @param cancelRuleDescCut cancelRuleDescCut
     * @return CancelRuleDescCutEnum
     */
    public static CancelRuleDescCutEnum getCancelRuleDescCutEnum(String cancelRuleDescCut) {
        CancelRuleDescCutEnum[] values = CancelRuleDescCutEnum.values();
        for (CancelRuleDescCutEnum descCutEnum : values) {
            Pattern[] patterns = descCutEnum.getPattern();
            for (Pattern pattern : patterns) {
                Matcher matcher = pattern.matcher(cancelRuleDescCut);
                if (matcher.find()) {
                    return descCutEnum;
                }
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
