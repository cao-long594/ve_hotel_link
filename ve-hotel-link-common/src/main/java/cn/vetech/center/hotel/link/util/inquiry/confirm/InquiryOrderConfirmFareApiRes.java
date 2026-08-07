package cn.vetech.center.hotel.link.util.inquiry.confirm;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.inquiry.confirm.HotelLinkInquiryOrderConfirmVO;

/**
 * @author chengwanshan
 * @since 2023/6/30 9:42
 */
public class InquiryOrderConfirmFareApiRes {
    /**
     * 失败
     *
     * @return HotelLinkInquiryOrderConfirmVO
     */
    public static HotelLinkInquiryOrderConfirmVO fail() {
        HotelLinkInquiryOrderConfirmVO vo = new HotelLinkInquiryOrderConfirmVO();
        vo.setStatus(LinkHotelVO.FAIL);
        return vo;
    }

    /**
     * 失败
     *
     * @param msg 异常描述
     * @return HotelLinkInquiryOrderConfirmVO
     */
    public static HotelLinkInquiryOrderConfirmVO fail(String msg) {
        HotelLinkInquiryOrderConfirmVO vo = new HotelLinkInquiryOrderConfirmVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(msg);
        return vo;
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return HotelLinkInquiryOrderConfirmVO
     */
    public static HotelLinkInquiryOrderConfirmVO fail(String code, String msg) {
        HotelLinkInquiryOrderConfirmVO vo = new HotelLinkInquiryOrderConfirmVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorCode(code);
        vo.setErrorMsg(msg);
        return vo;
    }


    /**
     * 成功
     *
     * @return HotelLinkInquiryOrderConfirmVO
     */
    public static HotelLinkInquiryOrderConfirmVO success() {
        HotelLinkInquiryOrderConfirmVO vo = new HotelLinkInquiryOrderConfirmVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 成功
     *
     * @param vo vo
     * @return HotelLinkInquiryOrderConfirmVO
     */
    public static HotelLinkInquiryOrderConfirmVO success(HotelLinkInquiryOrderConfirmVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }
}
