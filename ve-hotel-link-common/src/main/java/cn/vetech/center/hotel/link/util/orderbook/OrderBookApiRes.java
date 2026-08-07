package cn.vetech.center.hotel.link.util.orderbook;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.orderbook.vo.LinkHotelOrderBookVO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.constant.HotelLinkConstant;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookInverseQueryCodeEnum;
import cn.vetech.center.hotel.link.util.VeStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Objects;

/**
 * @author chengwanshan
 * @since 2022/3/23 20:23
 */
public class OrderBookApiRes {

    /**
     * status为-1时，errorMsg为空或包含超时、null、异常， cps-hotel会锁单
     */
    public static final String LOCK_ORDER_MSG = "下单异常";
    /**
     * 下单默认失败描述
     */
    public static final String ORDER_MSG = "下单失败";

    /**
     * 失败，反查、锁单
     *
     * @return LinkHotelOrderBookVO
     */
    @Deprecated
    public static LinkHotelOrderBookVO fail() {
        LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(LOCK_ORDER_MSG);
        return vo;
    }

    /**
     * 失败，反查、锁单
     *
     * @param msg 异常描述
     * @return LinkHotelOrderBookVO
     */
    @Deprecated
    public static LinkHotelOrderBookVO fail(String msg) {
        LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(StringUtils.isBlank(msg) ? LOCK_ORDER_MSG : String.join(SymbolConstant.FULL_COMMA, LOCK_ORDER_MSG, msg));
        return vo;
    }

    /**
     * 失败，反查、锁单
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return LinkHotelOrderBookVO
     */
    @Deprecated
    public static LinkHotelOrderBookVO fail(String code, String msg) {
        LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorCode(code);
        vo.setErrorMsg(StringUtils.isBlank(msg) ? LOCK_ORDER_MSG : String.join(SymbolConstant.FULL_COMMA, LOCK_ORDER_MSG, msg));
        return vo;
    }


    /**
     * 失败，不反查、不锁单
     *
     * @return LinkHotelOrderBookVO
     */
    @Deprecated
    public static LinkHotelOrderBookVO failNoRequery() {
        LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(ORDER_MSG);
        vo.setGysErrorCode(HotelLinkConstant.ERROR_CODE);
        return vo;
    }

    /**
     * 失败，不反查、不锁单
     *
     * @param msg 异常描述
     * @return LinkHotelOrderBookVO
     */
    @Deprecated
    public static LinkHotelOrderBookVO failNoRequery(String msg) {
        LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorMsg(StringUtils.defaultIfBlank(msg, ORDER_MSG));
        vo.setGysErrorCode(HotelLinkConstant.ERROR_CODE);
        return vo;
    }

    /**
     * 失败，不反查、不锁单
     *
     * @param code 状态码
     * @param msg  异常描述
     * @return LinkHotelOrderBookVO
     */
    @Deprecated
    public static LinkHotelOrderBookVO failNoRequery(String code, String msg) {
        LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setErrorCode(code);
        vo.setErrorMsg(StringUtils.defaultIfBlank(msg, ORDER_MSG));
        vo.setGysErrorCode(HotelLinkConstant.ERROR_CODE);
        return vo;
    }

    /**
     * 失败
     *
     * @param requery   是否反查，true:反查    false:不反查
     * @param lockOrder 是否锁单，true:锁单    false:不锁单
     * @return LinkHotelOrderBookVO
     */
    @Deprecated
    public static LinkHotelOrderBookVO fail(boolean requery, boolean lockOrder) {
    LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
        vo.setStatus(LinkHotelVO.FAIL);
        if (!requery) {
            vo.setGysErrorCode(HotelLinkConstant.ERROR_CODE);
        }
        vo.setErrorMsg(ORDER_MSG);
        if (lockOrder) {
            vo.setErrorMsg(LOCK_ORDER_MSG);
        }
        return vo;
    }

    /**
     * 失败
     *
     * @param msg       失败信息
     * @param requery   是否反查，true:反查    false:不反查
     * @param lockOrder 是否锁单，true:锁单    false:不锁单
     * @return LinkHotelOrderBookVO
     */
    @Deprecated
    public static LinkHotelOrderBookVO fail(String msg, boolean requery, boolean lockOrder) {
        LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
        vo.setStatus(LinkHotelVO.FAIL);
        if (!requery) {
            vo.setGysErrorCode(HotelLinkConstant.ERROR_CODE);
        }
        vo.setErrorMsg(StringUtils.defaultIfBlank(msg, ORDER_MSG));
        if (lockOrder) {
            vo.setErrorMsg(StringUtils.isBlank(msg) ? LOCK_ORDER_MSG : String.join(SymbolConstant.FULL_COMMA, LOCK_ORDER_MSG, msg));
        }
        return vo;
    }


    /**
     * 成功
     *
     * @return LinkHotelOrderBookVO
     */
    public static LinkHotelOrderBookVO success() {
        LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }
    /**
     * 成功
     *
     * @param vo vo
     * @return LinkHotelOrderBookVO
     */
    public static LinkHotelOrderBookVO success(LinkHotelOrderBookVO vo) {
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * @param errorCodeEnum errorCodeEnum
     * @param msg           msg
     * @param requery       供应商订单详情接口是否支持反查
     * @return LinkHotelOrderBookVO
     */
    private static LinkHotelOrderBookVO fail(HotelOrderBookErrorCodeEnum errorCodeEnum, String msg, boolean requery) {
        if (Objects.isNull(errorCodeEnum)) {
            errorCodeEnum = HotelOrderBookErrorCodeEnum.GYSE_UN_10003;
        }
        LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
        vo.setStatus(LinkHotelVO.FAIL);
        vo.setGysErrorCode(errorCodeEnum.getCode());
        vo.setErrorMsg(StringUtils.isBlank(msg) ? errorCodeEnum.getName() : VeStringUtil.joinWithFullCommaIsNotBlank(errorCodeEnum.getName(), msg));
        vo.setInverseQuery(requery ? HotelOrderBookInverseQueryCodeEnum.YES.getCode() : HotelOrderBookInverseQueryCodeEnum.NO.getCode());
        return vo;
    }

    /**
     * 供应商订单详情接口支持反查 使用
     *
     * @param errorCodeEnum errorCodeEnum
     * @return LinkHotelOrderBookVO
     */
    public static LinkHotelOrderBookVO failSupportInverseQuery(HotelOrderBookErrorCodeEnum errorCodeEnum) {
        return failSupportInverseQuery(errorCodeEnum, null);
    }

    /**
     * 供应商订单详情接口支持反查 使用
     *
     * @param errorCodeEnum errorCodeEnum
     * @param msg           失败信息
     * @return LinkHotelOrderBookVO
     */
    public static LinkHotelOrderBookVO failSupportInverseQuery(HotelOrderBookErrorCodeEnum errorCodeEnum, String msg) {
        return fail(errorCodeEnum, msg, true);
    }

    /**
     * 供应商订单详情接口不支持反查 使用
     *
     * @param errorCodeEnum errorCodeEnum
     * @return LinkHotelOrderBookVO
     */
    public static LinkHotelOrderBookVO failNotSupportInverseQuery(HotelOrderBookErrorCodeEnum errorCodeEnum) {
        return failNotSupportInverseQuery(errorCodeEnum, null);
    }

    /**
     * 供应商订单详情接口不支持反查 使用
     *
     * @param errorCodeEnum errorCodeEnum
     * @param msg           失败信息
     * @return LinkHotelOrderBookVO
     */
    public static LinkHotelOrderBookVO failNotSupportInverseQuery(HotelOrderBookErrorCodeEnum errorCodeEnum, String msg) {
        return fail(errorCodeEnum, msg, false);
    }


    /**
     * 下单前验价是否成功
     *
     * @param validateVO 验价标准响应
     * @return true-成功 false-失败
     */
    public static ImmutablePair<Boolean, String> checkValidateVO(LinkHotelValidateVO validateVO) {
        if (Objects.isNull(validateVO)) {
            return ImmutablePair.of(false, null);
        }
        if (!StringUtils.equals("1", validateVO.getResult()) || validateVO.getStatus() != LinkHotelVO.SUCCESS) {
            return ImmutablePair.of(false, validateVO.getErrorMsg());
        }
        return ImmutablePair.of(true, null);
    }
}

