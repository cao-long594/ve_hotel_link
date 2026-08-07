package cn.vetech.center.hotel.link.util.orderdetail;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chengwanshan
 * @since 2022/3/30 16:13
 */
public class OrderDetailApiRes {

    /**
     * 失败
     *
     * @return LinkHotelOrderDetailVO
     */
    public static LinkHotelOrderDetailVO fail() {
        LinkHotelOrderDetailVO vo = new LinkHotelOrderDetailVO();
        vo.setStatus(LinkHotelVO.FAIL);
        return vo;
    }

    /**
     * 失败
     *
     * @param msg 异常描述
     * @return LinkHotelOrderDetailVO
     */
    public static LinkHotelOrderDetailVO fail(String msg) {
        LinkHotelOrderDetailVO vo = new LinkHotelOrderDetailVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(StringUtils.isBlank(msg) ? null : String.format("订单详情，%s", msg));
        return vo;
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return LinkHotelOrderDetailVO
     */
    public static LinkHotelOrderDetailVO fail(String code, String msg) {
        LinkHotelOrderDetailVO vo = new LinkHotelOrderDetailVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorCode(code);
        vo.setErrorMsg(StringUtils.isBlank(msg) ? null : String.format("订单详情，%s", msg));
        return vo;
    }


    /**
     * 成功
     *
     * @return LinkHotelOrderDetailVO
     */
    public static LinkHotelOrderDetailVO success() {
        LinkHotelOrderDetailVO vo = new LinkHotelOrderDetailVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 成功
     *
     * @param vo vo
     * @return LinkHotelOrderDetailVO
     */
    public static LinkHotelOrderDetailVO success(LinkHotelOrderDetailVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }
}
