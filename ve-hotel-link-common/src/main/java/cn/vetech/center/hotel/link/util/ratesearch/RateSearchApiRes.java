package cn.vetech.center.hotel.link.util.ratesearch;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.enums.HotelErrorCodeEnum;
import cn.vetech.center.hotel.link.api.member.vo.LinkHotelRegistrationVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;

/**
 * @author chengwanshan
 * @since 2022/3/8 15:29
 */
public class RateSearchApiRes {

    /**
     * 返回
     *
     * @return LinkHotelRateSearchVO
     */
    public static LinkHotelRateSearchVO fail() {
        LinkHotelRateSearchVO vo = new LinkHotelRateSearchVO();
        vo.setStatus(LinkHotelVO.FAIL);
        return vo;
    }

    /**
     * 返回
     *
     * @param msg 异常描述
     * @return LinkHotelRateSearchVO
     */
    public static LinkHotelRateSearchVO fail(String msg) {
        LinkHotelRateSearchVO vo = new LinkHotelRateSearchVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(msg);
        return vo;
    }


    /**
     * 返回
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return LinkHotelRateSearchVO
     */
    public static LinkHotelRateSearchVO fail(String code, String msg) {
        LinkHotelRateSearchVO vo = new LinkHotelRateSearchVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorCode(code);
        vo.setErrorMsg(msg);
        return vo;
    }

    /**
     * 成功
     *
     * @return LinkHotelRateSearchVO
     */
    public static LinkHotelRateSearchVO success() {
        LinkHotelRateSearchVO vo = new LinkHotelRateSearchVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 成功
     *
     * @param vo vo
     * @return LinkHotelRateSearchVO
     */
    public static LinkHotelRateSearchVO success(LinkHotelRateSearchVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 会员注册失败
     *
     * @param msg 错误信息
     * @return LinkHotelRegistrationVO
     */
    public static LinkHotelRegistrationVO failRegistration(String msg) {
        LinkHotelRegistrationVO vo = new LinkHotelRegistrationVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(msg);
        return vo;
    }

    /**
     * 会员注册失败
     *
     * @return LinkHotelRegistrationVO
     */
    public static LinkHotelRateSearchVO failOverclock(HotelErrorCodeEnum codeEnum) {
        LinkHotelRateSearchVO vo = new LinkHotelRateSearchVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorCode(codeEnum.getCode());
        vo.setErrorMsg(codeEnum.getErrorms());
        return vo;
    }
}
