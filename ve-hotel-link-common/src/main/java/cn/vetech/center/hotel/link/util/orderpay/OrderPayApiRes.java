package cn.vetech.center.hotel.link.util.orderpay;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.orderpay.vo.LinkHotelOrderPayVO;

/**
 * @author chengwanshan
 * @since 2022/3/30 19:24
 */
public class OrderPayApiRes {

    /**
     * 失败
     *
     * @return LinkHotelOrderCancelVO
     */
    public static LinkHotelOrderPayVO fail() {
        LinkHotelOrderPayVO vo = new LinkHotelOrderPayVO();
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
    public static LinkHotelOrderPayVO fail(String msg) {
        LinkHotelOrderPayVO vo = new LinkHotelOrderPayVO();
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
    public static LinkHotelOrderPayVO fail(String code, String msg) {
        LinkHotelOrderPayVO vo = new LinkHotelOrderPayVO();
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
    public static LinkHotelOrderPayVO success() {
        LinkHotelOrderPayVO vo = new LinkHotelOrderPayVO();
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
    public static LinkHotelOrderPayVO success(LinkHotelOrderPayVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        vo.setSuccesss(LinkHotelVO.SUCCESS);
        return vo;
    }
}
