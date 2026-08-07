package cn.vetech.center.hotel.link.util.hotelGuestReviews;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.hotelguestreviews.vo.HotelGuestReviewsVO;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chengwanshan
 * @since 2024/12/27 16:05
 */
public class HotelGuestReviewsApiRes {

    /**
     * 失败
     *
     * @return HotelGuestReviewsVO
     */
    public static HotelGuestReviewsVO fail() {
        HotelGuestReviewsVO vo = new HotelGuestReviewsVO();
        vo.setStatus(LinkHotelVO.FAIL);
        return vo;
    }

    /**
     * 失败
     *
     * @param msg 异常描述
     * @return HotelGuestReviewsVO
     */
    public static HotelGuestReviewsVO fail(String msg) {
        HotelGuestReviewsVO vo = new HotelGuestReviewsVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(StringUtils.isBlank(msg) ? "下单前校验失败" : String.format("下单前校验，%s", msg));
        return vo;
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return HotelGuestReviewsVO
     */
    public static HotelGuestReviewsVO fail(String code, String msg) {
        HotelGuestReviewsVO vo = new HotelGuestReviewsVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorCode(code);
        vo.setErrorMsg(StringUtils.isBlank(msg) ? "下单前校验失败" : String.format("下单前校验，%s", msg));
        return vo;
    }

    /**
     * 成功
     *
     * @return HotelGuestReviewsVO
     */
    public static HotelGuestReviewsVO success() {
        HotelGuestReviewsVO vo = new HotelGuestReviewsVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 成功
     *
     * @param vo vo
     * @return HotelGuestReviewsVO
     */
    public static HotelGuestReviewsVO success(HotelGuestReviewsVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }
}
