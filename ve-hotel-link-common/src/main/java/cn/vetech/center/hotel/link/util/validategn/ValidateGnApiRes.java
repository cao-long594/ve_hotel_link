package cn.vetech.center.hotel.link.util.validategn;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.validategn.vo.LinkHotelValidateGnVO;
import cn.vetech.center.hotel.link.enums.ValidateGnResultEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chengwanshan
 * @since 2022/3/23 15:26
 */
public class ValidateGnApiRes {

    /**
     * 失败
     *
     * @return LinkHotelValidateGnVO
     */
    public static LinkHotelValidateGnVO fail() {
        LinkHotelValidateGnVO vo = new LinkHotelValidateGnVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setResult(ValidateGnResultEnum.NO_1.getCode());
        return vo;
    }

    /**
     * 失败
     *
     * @param msg 异常描述
     * @return LinkHotelValidateGnVO
     */
    public static LinkHotelValidateGnVO fail(String msg) {
        LinkHotelValidateGnVO vo = new LinkHotelValidateGnVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setResult(ValidateGnResultEnum.NO_1.getCode());
        vo.setErrorMsg(StringUtils.isBlank(msg) ? "下单前校验失败" : String.format("下单前校验，%s", msg));
        return vo;
    }


    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return LinkHotelValidateGnVO
     */
    public static LinkHotelValidateGnVO fail(String code, String msg) {
        LinkHotelValidateGnVO vo = new LinkHotelValidateGnVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setResult(ValidateGnResultEnum.NO_1.getCode());
        vo.setErrorCode(code);
        vo.setErrorMsg(StringUtils.isBlank(msg) ? "下单前校验失败" : String.format("下单前校验，%s", msg));
        return vo;
    }


    /**
     * 成功
     *
     * @return LinkHotelValidateGnVO
     */
    public static LinkHotelValidateGnVO success() {
        LinkHotelValidateGnVO vo = new LinkHotelValidateGnVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        vo.setResult(ValidateGnResultEnum.YES.getCode());
       return vo;
    }


    /**
     * 成功
     *
     * @param vo vo
     * @return LinkHotelValidateGnVO
     */
    public static LinkHotelValidateGnVO success(LinkHotelValidateGnVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        vo.setResult(ValidateGnResultEnum.YES.getCode());
        return vo;
    }
}
