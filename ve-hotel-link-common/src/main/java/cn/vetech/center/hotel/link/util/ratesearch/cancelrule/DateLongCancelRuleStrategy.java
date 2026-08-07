package cn.vetech.center.hotel.link.util.ratesearch.cancelrule;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.constant.NumConstant;
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
public class DateLongCancelRuleStrategy implements ICancelRuleStrategy {

    @Override
    public CancelRuleDescCutEnum getStrategyType() {
        return CancelRuleDescCutEnum.DATE_LONG;
    }

    @Override
    public ImmutablePair<String, String> convertCancelRuleDescCut(CancelRuleDescCutEnum cancelRuleDescCutEnum, String description, String checkInDate) {
        // 解析出取消时间
        String cancelRuleDescCutAfter = convertCancelTime(description, cancelRuleDescCutEnum);
        if (StringUtils.isBlank(cancelRuleDescCutAfter)) {
            return ImmutablePair.of(null, null);
        }
        // 格式化日期
        cancelRuleDescCutAfter = convertDateEnd(cancelRuleDescCutAfter);

        if (StringUtils.isBlank(checkInDate) || !StringUtils.equals(VeDateUtils.convertDate(checkInDate), VeDateUtils.convertDate(cancelRuleDescCutAfter))) {
            return ImmutablePair.of(handleCancelRuleDescCut(cancelRuleDescCutAfter), cancelRuleDescCutAfter);
        }

        // 入住当天
        String[] cancelTimes = cancelRuleDescCutAfter.split(" ");
        if (cancelTimes.length <= NumConstant.NUM_1) {
            return ImmutablePair.of(null, cancelRuleDescCutAfter);
        }
        // HH:mm
        String stringTime = cancelTimes[NumConstant.NUM_1];
        if (stringTime.length() < NumConstant.NUM_5) {
            return ImmutablePair.of(null, cancelRuleDescCutAfter);
        }
        String substring = StringUtils.substring(stringTime, NumConstant.NUM_0, NumConstant.NUM_5);
        return ImmutablePair.of("入住当天" + substring + "前可免费取消", cancelRuleDescCutAfter);
    }

    /**
     * yyyy-MM-dd HH:mm 转换成 yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd HH 转换成 yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr dateStr
     * @return String
     */
    private String convertDateEnd(String dateStr) {
        if (StringUtils.length(dateStr) == NumConstant.NUM_13) {
            return dateStr + ":00:00";
        }
        if (StringUtils.length(dateStr) == NumConstant.NUM_16) {
            return dateStr + ":00";
        }
        return dateStr;
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
        if (cancelTimes.length <= NumConstant.NUM_1) {
            return null;
        }
        // yyyy-MM-dd
        String stringDateShort = cancelTimes[NumConstant.NUM_0];
        // HH:mm
        String stringTime = cancelTimes[NumConstant.NUM_1];

        String[] stringDateShorts = stringDateShort.split(SymbolConstant.MIDDLE_LINE);
        if (stringDateShorts.length <= NumConstant.NUM_2) {
            return null;
        }
        if (stringTime.length() < NumConstant.NUM_5) {
            return null;
        }
        String substring = StringUtils.substring(stringTime, NumConstant.NUM_0, NumConstant.NUM_5);
        return stringDateShorts[NumConstant.NUM_1] + "月" + stringDateShorts[NumConstant.NUM_2] + "日" + substring + "前可免费取消";
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
        if (matcher.find() && groupCount > NumConstant.NUM_2) {
            return matcher.group(NumConstant.NUM_3);
        }
        return null;
    }

}
