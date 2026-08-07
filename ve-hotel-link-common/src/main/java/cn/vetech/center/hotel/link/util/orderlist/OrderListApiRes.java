package cn.vetech.center.hotel.link.util.orderlist;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.orderlist.vo.LinkHotelOrderListVO;

/**
 * @author chengwanshan
 * @since 2022/3/30 19:24
 */
public class OrderListApiRes {

    /**
     * 失败
     *
     * @return LinkHotelOrderListVO
     */
    public static LinkHotelOrderListVO fail() {
        LinkHotelOrderListVO vo = new LinkHotelOrderListVO();
        vo.setStatus(LinkHotelVO.FAIL);
        return vo;
    }

    /**
     * 失败
     *
     * @param msg 异常描述
     * @return LinkHotelOrderListVO
     */
    public static LinkHotelOrderListVO fail(String msg) {
        LinkHotelOrderListVO vo = new LinkHotelOrderListVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(msg);
        return vo;
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return LinkHotelOrderListVO
     */
    public static LinkHotelOrderListVO fail(String code, String msg) {
        LinkHotelOrderListVO vo = new LinkHotelOrderListVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorCode(code);
        vo.setErrorMsg(msg);
        return vo;
    }

    /**
     * 成功
     *
     * @return LinkHotelOrderListVO
     */
    public static LinkHotelOrderListVO success() {
        LinkHotelOrderListVO vo = new LinkHotelOrderListVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 成功
     *
     * @param vo vo
     * @return LinkHotelOrderListVO
     */
    public static LinkHotelOrderListVO success(LinkHotelOrderListVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }
}
