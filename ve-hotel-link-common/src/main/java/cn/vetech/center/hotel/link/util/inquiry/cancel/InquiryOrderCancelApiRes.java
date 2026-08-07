package cn.vetech.center.hotel.link.util.inquiry.cancel;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.inquiry.cancel.HotelLinkInquiryOrderCancelVO;

/**
 * @author chengwanshan
 * @since 2023/6/30 9:37
 */
public class InquiryOrderCancelApiRes {
    /**
     * 失败
     *
     * @return HotelLinkInquiryOrderCancelVO
     */
    public static HotelLinkInquiryOrderCancelVO fail() {
        HotelLinkInquiryOrderCancelVO vo = new HotelLinkInquiryOrderCancelVO();
        vo.setStatus(LinkHotelVO.FAIL);
        return vo;
    }

    /**
     * 失败
     *
     * @param msg 异常描述
     * @return HotelLinkInquiryOrderCancelVO
     */
    public static HotelLinkInquiryOrderCancelVO fail(String msg) {
        HotelLinkInquiryOrderCancelVO vo = new HotelLinkInquiryOrderCancelVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(msg);
        return vo;
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return HotelLinkInquiryOrderCancelVO
     */
    public static HotelLinkInquiryOrderCancelVO fail(String code, String msg) {
        HotelLinkInquiryOrderCancelVO vo = new HotelLinkInquiryOrderCancelVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorCode(code);
        vo.setErrorMsg(msg);
        return vo;
    }


    /**
     * 成功
     *
     * @return HotelLinkInquiryOrderCancelVO
     */
    public static HotelLinkInquiryOrderCancelVO success() {
        HotelLinkInquiryOrderCancelVO vo = new HotelLinkInquiryOrderCancelVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 成功
     *
     * @param vo vo
     * @return HotelLinkInquiryOrderCancelVO
     */
    public static HotelLinkInquiryOrderCancelVO success(HotelLinkInquiryOrderCancelVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }
}
