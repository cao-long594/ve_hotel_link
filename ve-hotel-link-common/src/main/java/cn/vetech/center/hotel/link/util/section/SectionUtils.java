package cn.vetech.center.hotel.link.util.section;

import cn.vetech.center.hotel.link.util.section.model.TimeSection;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 分段工具类
 *
 * @author luqs
 * @version v1.0
 **/
public class SectionUtils {
    private SectionUtils() {
    }

    /**
     * 日期分段
     *
     * @param startDate   起始日期，格式：yyyy-MM-dd
     * @param endDate     结束日期，格式：yyyy-MM-dd
     * @param sectionDays 分段天数
     * @return List<TimeSection>
     */
    public static List<TimeSection> sectionDate(String startDate, String endDate, int sectionDays) {
        List<TimeSection> timeSectionList = new ArrayList<>();
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
            TimeSection timeSection = new TimeSection();
            timeSection.setStart(startDate);
            timeSection.setEnd(endDate);
            timeSectionList.add(timeSection);
            return timeSectionList;
        }

        int apartDays = VeDate.getTwoDay(endDate, startDate);
        if (apartDays <= sectionDays) {
            TimeSection timeSection = new TimeSection();
            timeSection.setStart(startDate);
            timeSection.setEnd(endDate);
            timeSectionList.add(timeSection);
            return timeSectionList;
        }

        int remainder = apartDays % sectionDays;
        int intNum = apartDays / sectionDays;
        int sectionNum = remainder == 0 ? intNum : intNum + 1;
        String startStr;
        String endStr;
        for (int i = 0; i < sectionNum; i++) {
            startStr = i == 0 ? startDate : VeDate.getNextDay(startDate, String.valueOf(i * sectionDays));
            endStr = (i == sectionNum - 1) ? endDate : VeDate.getNextDay(startDate, String.valueOf((i + 1) * sectionDays));
            TimeSection timeSection = new TimeSection();
            timeSection.setStart(startStr);
            timeSection.setEnd(endStr);
            timeSectionList.add(timeSection);
        }
        return timeSectionList;
    }

    /**
     * 分段
     *
     * @param list        集合
     * @param sectionSize 每段大小
     * @param <T>         泛型
     * @return List<List < T>>
     */
    public static <T> List<List<T>> splitList(List<T> list, int sectionSize) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        // 计算分割后的大小
        int maxSize = (list.size() + sectionSize - 1) / sectionSize;
        return Stream.iterate(0, n -> n + 1)
                .limit(maxSize)
                .parallel()
                .map(a -> list.parallelStream().skip((long)a * (long)sectionSize).limit(sectionSize).collect(Collectors.toList()))
                .filter(b -> !b.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * 日期分段
     *
     * @param startDate   起始日期，格式：yyyy-MM-dd
     * @param endDate     结束日期，格式：yyyy-MM-dd
     * @param sectionSec 分段秒数
     * @return List<TimeSection>
     */
    public static List<TimeSection> sectionDateByDuration(String startDate, String endDate, int sectionSec) {
        List<TimeSection> timeSectionList = new ArrayList<>();
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
            TimeSection timeSection = new TimeSection();
            timeSection.setStart(startDate);
            timeSection.setEnd(endDate);
            timeSectionList.add(timeSection);
            return timeSectionList;
        }

        int apartSec = VeDate.getTwoSec(endDate, startDate);
        if (apartSec <= sectionSec) {
            TimeSection timeSection = new TimeSection();
            timeSection.setStart(startDate);
            timeSection.setEnd(endDate);
            timeSectionList.add(timeSection);
            return timeSectionList;
        }

        int remainder = apartSec % sectionSec;
        int intNum = apartSec / sectionSec;
        int sectionNum = remainder == 0 ? intNum : intNum + 1;
        String startStr;
        String endStr;
        for (int i = 0; i < sectionNum; i++) {
            startStr = i == 0 ? startDate : VeDate.getPreSec(startDate, i * sectionSec);
            endStr = (i == sectionNum - 1) ? endDate : VeDate.getPreSec(startDate, (i + 1) * sectionSec);
            TimeSection timeSection = new TimeSection();
            timeSection.setStart(startStr);
            timeSection.setEnd(endStr);
            timeSectionList.add(timeSection);
        }
        return timeSectionList;
    }

}