package cn.vetech.center.hotel.link.util.ordercancel;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.ordercancel.vo.LinkHotelOrderCancelVO;

/**
 * @author chengwanshan
 * @since 2022/3/30 19:24
 */
public class OrderCancelApiRes {

    /**
     * 失败
     *
     * @return LinkHotelOrderCancelVO
     */
    public static LinkHotelOrderCancelVO fail() {
        LinkHotelOrderCancelVO vo = new LinkHotelOrderCancelVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setSuccesss(LinkHotelVO.FAIL);
        return vo;
    }

    /**
     * 失败
     *
     * @param msg 异常描述
     * @return LinkHotelOrderCancelVO
     */
    public static LinkHotelOrderCancelVO fail(String msg) {
        LinkHotelOrderCancelVO vo = new LinkHotelOrderCancelVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setSuccesss(LinkHotelVO.FAIL);
        vo.setErrorMsg(msg);
        return vo;
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return LinkHotelOrderCancelVO
     */
    public static LinkHotelOrderCancelVO fail(String code, String msg) {
        LinkHotelOrderCancelVO vo = new LinkHotelOrderCancelVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setSuccesss(LinkHotelVO.FAIL);
        vo.setErrorCode(code);
        vo.setErrorMsg(msg);
        return vo;
    }


    /**
     * 成功
     *
     * @return LinkHotelOrderCancelVO
     */
    public static LinkHotelOrderCancelVO success() {
        LinkHotelOrderCancelVO vo = new LinkHotelOrderCancelVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        vo.setSuccesss(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 成功
     *
     * @param vo vo
     * @return LinkHotelOrderCancelVO
     */
    public static LinkHotelOrderCancelVO success(LinkHotelOrderCancelVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        vo.setSuccesss(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 失败 取消中
     *
     * @param msg  异常描述
     * @return LinkHotelOrderCancelVO
     */
    public static LinkHotelOrderCancelVO failCanceling(String msg) {
        LinkHotelOrderCancelVO vo = new LinkHotelOrderCancelVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setSuccesss(LinkHotelVO.FAIL);
        vo.setErrorMsg(msg);
        vo.setCanceling("1");
        return vo;
    }
}