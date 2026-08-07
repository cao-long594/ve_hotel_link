package cn.vetech.center.hotel.link.util.ratesearch.cancelrule;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.util.VeDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chengwanshan
 * @since 2022/8/10 14:39
 */
@Service
public class DateDescCancelRuleStrategy implements ICancelRuleStrategy {

    @Override
    public CancelRuleDescCutEnum getStrategyType() {
        return CancelRuleDescCutEnum.DATE_DESC;
    }
    /**
     * 0
     */
    public static final int NUM_0 = 0;
    /**
     * 1
     */
    public static final int NUM_1 = 1;
    /**
     * 2
     */
    public static final int NUM_2 = 2;
    /**
     * 3
     */
    public static final int NUM_3 = 3;
    /**
     * 5
     */
    public static final int NUM_5 = 5;
    /**
     * 13
     */
    public static final int NUM_13 = 13;
    /**
     * 16
     */
    public static final int NUM_16 = 16;

    @Override
    public ImmutablePair<String, String> convertCancelRuleDescCut(CancelRuleDescCutEnum cancelRuleDescCutEnum, String description, String checkInDate) {
        // 解析出取消时间
        String cancelRuleDescCutAfter = convertCancelTime(description, cancelRuleDescCutEnum);
        if (StringUtils.isBlank(cancelRuleDescCutAfter)) {
            return ImmutablePair.of("", "");
        }
        // 格式化日期
        cancelRuleDescCutAfter = convertDateEnd(cancelRuleDescCutAfter, checkInDate);

        if (StringUtils.isBlank(checkInDate) || !StringUtils.equals(VeDateUtils.convertDate(checkInDate), VeDateUtils.convertDate(cancelRuleDescCutAfter))) {
            return ImmutablePair.of(handleCancelRuleDescCut(cancelRuleDescCutAfter), cancelRuleDescCutAfter);
        }

       // 入住当天
        String[] cancelTimes = cancelRuleDescCutAfter.split(" ");
        if (cancelTimes.length <= NUM_1) {
            return ImmutablePair.of("", cancelRuleDescCutAfter);
        }
        // HH:mm
        String stringTime = cancelTimes[NUM_1];
        if (stringTime.length() < NUM_5) {
            return ImmutablePair.of("", cancelRuleDescCutAfter);
        }
        String substring = StringUtils.substring(stringTime, NUM_0, NUM_5);
        return ImmutablePair.of("入住当天" + substring + "前可免费取消", cancelRuleDescCutAfter);
    }

    /**
     * 根据免费取消时间点转换简版的取消规则说明
     *
     * @param cancelTime 免费取消时间点
     * @return String
     */
    private static String handleCancelRuleDescCut(String cancelTime) {
        if (StringUtils.isBlank(cancelTime)) {
            return null;
        }
        String[] cancelTimes = cancelTime.split(" ");
        if (cancelTimes.length <= NUM_1) {
            return null;
        }
        // yyyy-MM-dd
        String stringDateShort = cancelTimes[NUM_0];
        // HH:mm
        String stringTime = cancelTimes[NUM_1];

        String[] stringDateShorts = stringDateShort.split(SymbolConstant.MIDDLE_LINE);
        if (stringDateShorts.length <= NUM_2) {
            return null;
        }
        if (stringTime.length() < NUM_5) {
            return null;
        }
        String substring = StringUtils.substring(stringTime, NUM_0, NUM_5);
        return stringDateShorts[NUM_1] + "月" + stringDateShorts[NUM_2] + "日" + substring + "前可免费取消";
    }

    /**
     * @param cancelRuleDescCut 取消规则描述
     * @return String
     */
    private String convertCancelTime(String cancelRuleDescCut, CancelRuleDescCutEnum cancelRuleDescCutEnum) {
        if (StringUtils.isEmpty(cancelRuleDescCut)) {
            return null;
        }

        String dateStr = null;
        Pattern[] patterns = cancelRuleDescCutEnum.getPattern();
        for (Pattern pattern : patterns) {
            dateStr = compileDateStr(pattern, cancelRuleDescCut);
            if (StringUtils.isNotBlank(dateStr)) {
                break;
            }
        }
        return dateStr;
    }

    /**
     * @param pattern           pattern
     * @param cancelRuleDescCut 取消规则描述
     * @return String
     */
    private String compileDateStr(Pattern pattern, String cancelRuleDescCut) {
        Matcher matcher = pattern.matcher(cancelRuleDescCut);
        int groupCount = matcher.groupCount();
        if (matcher.find() && groupCount > NUM_2) {
            return matcher.group(NUM_3);
        }
        return null;
    }

    /**
     * yyyy-MM-dd HH:mm 转换成 yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd HH 转换成 yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr dateStr
     * @return String
     */
    private String convertDateEnd(String dateStr, String checkInDate) {
        if (StringUtils.contains(dateStr, "入住当天")) {
            return convertDateEndNow(dateStr, checkInDate);
        }
        dateStr =  StringUtils.replace(dateStr, "年", "-");
        dateStr = StringUtils.replace(dateStr, "月", "-");
        dateStr = StringUtils.replace(dateStr, "日", " ");
        if (StringUtils.length(dateStr) == NUM_13) {
            return dateStr + ":00:00";
        }
        if (StringUtils.length(dateStr) == NUM_16) {
            return dateStr + ":00";
        }
        return dateStr;
    }

    /**
     * yyyy-MM-dd HH:mm 转换成 yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd HH 转换成 yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr dateStr
     * @return String
     */
    private String convertDateEndNow(String dateStr, String checkInDate) {
        // 格式化年月日
        String dateToStr = VeDateUtils.convertDate(checkInDate);
        // 格式化时分秒
        String timeToStr = StringUtils.replace(dateStr, "入住当天", "");
        dateStr = dateToStr + " " + timeToStr;
        if (StringUtils.length(dateStr) == NUM_13) {
            return dateStr + ":00:00";
}
        if (StringUtils.length(dateStr) == NUM_16) {
            return dateStr + ":00";
        }
        return dateStr;
    }

}
