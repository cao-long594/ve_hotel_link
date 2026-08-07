package cn.vetech.center.hotel.link.util.inquiry.create;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.inquiry.create.HotelLinkInquiryOrderCreateVO;

/**
 * @author chengwanshan
 * @since 2023/6/30 9:43
 */
public class InquiryOrderCreateApiRes {
    /**
     * 失败
     *
     * @return HotelLinkInquiryOrderCreateVO
     */
    public static HotelLinkInquiryOrderCreateVO fail() {
        HotelLinkInquiryOrderCreateVO vo = new HotelLinkInquiryOrderCreateVO();
        vo.setStatus(LinkHotelVO.FAIL);
        return vo;
    }

    /**
     * 失败
     *
     * @param msg 异常描述
     * @return HotelLinkInquiryOrderCreateVO
     */
    public static HotelLinkInquiryOrderCreateVO fail(String msg) {
        HotelLinkInquiryOrderCreateVO vo = new HotelLinkInquiryOrderCreateVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(msg);
        return vo;
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return HotelLinkInquiryOrderCreateVO
     */
    public static HotelLinkInquiryOrderCreateVO fail(String code, String msg) {
        HotelLinkInquiryOrderCreateVO vo = new HotelLinkInquiryOrderCreateVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorCode(code);
        vo.setErrorMsg(msg);
        return vo;
    }


    /**
     * 成功
     *
     * @return HotelLinkInquiryOrderCreateVO
     */
    public static HotelLinkInquiryOrderCreateVO success() {
        HotelLinkInquiryOrderCreateVO vo = new HotelLinkInquiryOrderCreateVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 成功
     *
     * @param vo vo
     * @return HotelLinkInquiryOrderCreateVO
     */
    public static HotelLinkInquiryOrderCreateVO success(HotelLinkInquiryOrderCreateVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }
}
