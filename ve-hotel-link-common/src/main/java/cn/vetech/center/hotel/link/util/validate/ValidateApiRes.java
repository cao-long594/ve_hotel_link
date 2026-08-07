package cn.vetech.center.hotel.link.util.validate;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.enums.ValidateResultEnum;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;
import cn.vetech.center.hotel.link.util.VeStringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author chengwanshan
 * @since 2022/3/23 15:26
 */
public class ValidateApiRes {

    /**
     * 失败
     *
     * @return LinkHotelValidateVO
     */
    public static LinkHotelValidateVO fail() {
        return fail(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001, null, null);
    }

    /**
     * 失败
     *
     * @param msg 异常描述
     * @return LinkHotelValidateVO
     */
    public static LinkHotelValidateVO fail(String msg) {
        return fail(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001, null, msg);
    }


    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return LinkHotelValidateVO
     */
    public static LinkHotelValidateVO fail(String code, String msg) {
        return fail(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001, code, msg);
    }


    /**
     * 成功
     *
     * @return LinkHotelValidateVO
     */
    public static LinkHotelValidateVO success() {
        LinkHotelValidateVO vo = new LinkHotelValidateVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        vo.setResult(ValidateResultEnum.YES.getCode());
        return vo;
    }


    /**
     * 成功
     *
     * @param vo vo
     * @return LinkHotelValidateVO
     */
    public static LinkHotelValidateVO success(LinkHotelValidateVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        vo.setResult(ValidateResultEnum.YES.getCode());
        return vo;
    }

    /**
     * @param errorCodeEnum errorCodeEnum
     * @param msg           msg
     * @return LinkHotelValidateVO
     */
    public static LinkHotelValidateVO fail(HotelOrderBookErrorCodeEnum errorCodeEnum, String code, String msg) {
        if (Objects.isNull(errorCodeEnum)) {
            errorCodeEnum = HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001;
        }
        LinkHotelValidateVO vo = new LinkHotelValidateVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setResult(ValidateResultEnum.NO.getCode());
        vo.setErrorCode(code);
        vo.setGysErrorCode(errorCodeEnum.getCode());
        vo.setErrorMsg(StringUtils.isBlank(msg) ? errorCodeEnum.getName() : VeStringUtil.joinWithFullCommaIsNotBlank(errorCodeEnum.getName(), msg));
        return vo;
    }

    /**
     * 供应商订单详情接口支持反查 使用
     *
     * @param errorCodeEnum errorCodeEnum
     * @return LinkHotelOrderBookVO
     */
    public static LinkHotelValidateVO fail(HotelOrderBookErrorCodeEnum errorCodeEnum) {
        return fail(errorCodeEnum, null, null);
    }

    /**
     * @param errorCodeEnum errorCodeEnum
     * @param msg           msg
     * @return LinkHotelValidateVO
     */
    public static LinkHotelValidateVO fail(HotelOrderBookErrorCodeEnum errorCodeEnum, String msg) {
        return fail(errorCodeEnum, null, msg);
    }
}
