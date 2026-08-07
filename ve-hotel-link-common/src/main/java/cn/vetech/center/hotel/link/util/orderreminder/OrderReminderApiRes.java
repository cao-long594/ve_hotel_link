package cn.vetech.center.hotel.link.util.orderreminder;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.orderreminder.vo.LinkHotelOrderReminderVO;
import cn.vetech.center.hotel.link.enums.orderreminder.HotelOrderReminderErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author chengwanshan
 * @since 2022/3/30 19:24
 */
public class OrderReminderApiRes {

    /**
     * 失败
     *
     * @return LinkHotelOrderReminderVO
     */
    public static LinkHotelOrderReminderVO fail() {
        return fail(null, null);
    }

    /**
     * 失败
     *
     * @param msg 异常描述
     * @return LinkHotelOrderReminderVO
     */
    public static LinkHotelOrderReminderVO fail(String msg) {
        return fail(null, msg);
    }

    /**
     * 失败
     *
     * @param codeEnum codeEnum
     * @return LinkHotelOrderReminderVO
     */
    public static LinkHotelOrderReminderVO fail(HotelOrderReminderErrorCodeEnum codeEnum) {
        return fail(codeEnum, null);
    }

    /**
     * 失败
     *
     * @param codeEnum 状态码
     * @param msg      异常描述
     * @return LinkHotelOrderReminderVO
     */
    public static LinkHotelOrderReminderVO fail(HotelOrderReminderErrorCodeEnum codeEnum, String msg) {
        if (Objects.isNull(codeEnum)) {
            codeEnum = HotelOrderReminderErrorCodeEnum.OR_1;
        }
        LinkHotelOrderReminderVO vo = new LinkHotelOrderReminderVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(StringUtils.defaultString(msg, codeEnum.getName()));
        vo.setCode(codeEnum.getCode());
        return vo;
    }


    /**
     * 成功
     *
     * @return LinkHotelOrderReminderVO
     */
    public static LinkHotelOrderReminderVO success() {
        LinkHotelOrderReminderVO vo = new LinkHotelOrderReminderVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        vo.setCode(HotelOrderReminderErrorCodeEnum.OR_0.getCode());
        return vo;
    }

    /**
     * 成功
     *
     * @param vo vo
     * @return LinkHotelOrderReminderVO
     */
    public static LinkHotelOrderReminderVO success(LinkHotelOrderReminderVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        vo.setCode(HotelOrderReminderErrorCodeEnum.OR_0.getCode());
        return vo;
    }
}
