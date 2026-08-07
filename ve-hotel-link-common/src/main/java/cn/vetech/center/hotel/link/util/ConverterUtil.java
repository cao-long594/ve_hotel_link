package cn.vetech.center.hotel.link.util;

import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

/**
 * @author chengwanshan
 * @since 2020/12/18 17:16
 */
public class ConverterUtil {
    /**
     * 处理最早 最晚到点时间
     * @param longDate 1
     * @return s
     */
    public static String executeDdsj(String longDate){
        if(StringUtils.isBlank(longDate)){
            return null;
        }
        Calendar c=VeDate.strToCalendar(longDate);
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int minute=c.get(Calendar.MINUTE);
        NumberFormat f=new DecimalFormat("00");
        return f.format(hour)+":"+f.format(minute);
    }
}
