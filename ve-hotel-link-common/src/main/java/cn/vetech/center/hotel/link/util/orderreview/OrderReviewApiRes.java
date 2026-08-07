package cn.vetech.center.hotel.link.util.orderreview;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.orderreview.vo.LinkHotelOrderReviewVO;

/**
 * @author chengwanshan
 * @since 2026/7/28 11:37
 */
public class OrderReviewApiRes {

    /**
     * 失败
     *
     * @return LinkHotelOrderReviewVO
     */
    public static LinkHotelOrderReviewVO fail() {
        return fail(null);
    }

    /**
     * 失败
     *
     * @param msg      异常描述
     * @return LinkHotelOrderReviewVO
     */
    public static LinkHotelOrderReviewVO fail(String msg) {
        LinkHotelOrderReviewVO vo = new LinkHotelOrderReviewVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(msg);
        return vo;
    }


    /**
     * 成功
     *
     * @return LinkHotelOrderReviewVO
     */
    public static LinkHotelOrderReviewVO success() {
        LinkHotelOrderReviewVO vo = new LinkHotelOrderReviewVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 成功
     *
     * @param vo vo
     * @return LinkHotelOrderReviewVO
     */
    public static LinkHotelOrderReviewVO success(LinkHotelOrderReviewVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }
}
