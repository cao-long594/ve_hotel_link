package cn.vetech.center.hotel.link.util.orderdetail;

import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.util.VeDateUtils;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author chengwanshan
 * @since 2021/12/14 13:49
 */
public class OrderDetailCommonUtils {

    private OrderDetailCommonUtils() {
    }

    /**
     * 处理真实订单信息，实际入离日期、实际间夜数、实际房费
     *
     * @param vo               vo
     * @param realCheckInDate  实际入住日期
     * @param realCheckOutDate 实际离店日期
     * @param realAmount       实际房费
     * @param realDays         实际间夜数
     */
    public static void convertRealOrderInfo(LinkHotelOrderDetailVO vo, String realCheckInDate, String realCheckOutDate, String realAmount, String realDays) {
        // 处理实际入离日期
        String checkInDate = convertRealCheckInDate(realCheckInDate);
        // 处理实际离店日期
        String checkOutDate = convertRealCheckOutDate(realCheckOutDate);
        // 续住会出现 比如订单入离日期为2023-10-14 2023-10-15
        // 实际入离时间 2023-10-14 06:51:30 2023-10-14 12:09:18
        // 上方逻辑条件不满足处理后，checkInDate和checkOutDate入离时间还是一样日期，这种情况就不赋值实际入离日期，默认下单日期不改变
        if (StringUtils.equals(checkInDate, checkOutDate)) {
            vo.setTotalPrice(realAmount);
            vo.setSjffzj(realAmount);
            return;
        }
        vo.setCheckInDate(checkInDate);
        vo.setCheckOutDate(checkOutDate);
        // 处理实际间夜数
        int sjjys = 0;
        if (StringUtils.isNotBlank(realDays)) {
            sjjys = NumberUtils.toInt(realDays);
        } else if (StringUtils.isNotBlank(checkInDate) && StringUtils.isNotBlank(checkOutDate) && StringUtils.isNotBlank(vo.getNumberOfRooms())) {
            sjjys = VeDate.getTwoDay(checkOutDate, checkInDate) * NumberUtils.toInt(vo.getNumberOfRooms());
        }
        vo.setSjjys(sjjys > 0 ? sjjys : null);
        vo.setSjrzrq(realCheckInDate);
        vo.setSjldrq(realCheckOutDate);

    }

    /**
     * 处理实际入住日期，第二天凌晨入住
     *
     * @param realCheckInDate 实际入住日期
     * @return String
     */
    private static String convertRealCheckInDate(String realCheckInDate) {
        if (StringUtils.isBlank(realCheckInDate)) {
            return null;
        }
        // 客人如果凌晨0-6点入住，需做特殊处理
        String dateStr = VeDateUtils.convertDate(realCheckInDate);
        if (StringUtils.isBlank(dateStr) || dateStr.length() != NumConstant.NUM_10) {
            return null;
        }
        String sTime = dateStr + " 00:00:00";
        String eTime = dateStr + " 06:00:00";
        int sDay = VeDate.getTwoSec(sTime, realCheckInDate);
        int eDay = VeDate.getTwoSec(eTime, realCheckInDate);
        if (sDay < 0 && eDay > 0) {
            dateStr = VeDate.getNextDay(dateStr, "-1");
        }
        return dateStr;
    }

    /**
     * 处理实际离店日期
     *
     * @param realCheckOutDate 实际离店日期
     * @return String
     */
    private static String convertRealCheckOutDate(String realCheckOutDate) {
        if (StringUtils.isBlank(realCheckOutDate)) {
            return null;
        }
        // 客人如果当天下午18-24点离店，需做特殊处理
        String dateStr = VeDateUtils.convertDate(realCheckOutDate);
        if (StringUtils.isBlank(dateStr) || dateStr.length() != NumConstant.NUM_10) {
            return null;
        }
        String sTime = dateStr + " 18:00:00";
        String eTime = dateStr + " 23:59:59";
        int sDay = VeDate.getTwoSec(sTime, realCheckOutDate);
        int eDay = VeDate.getTwoSec(eTime, realCheckOutDate);
        if (sDay < 0 && eDay > 0) {
            dateStr = VeDate.getNextDay(dateStr, "1");
        }
        return dateStr;
    }
}
