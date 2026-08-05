package cn.vetech.center.hotel.link.supply.service.aspect;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.enums.ValidateResultEnum;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author vetech
 * @since 2023/11/13
 */
@Component
@Aspect
@Order(11)
public class ValidateAspect extends RateBaseAspect {

    /**
     * 切面表达式
     *
     * @param dto dto
     */
    @Pointcut("execution(* cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService.validate(..))&&args(dto)")
    public void pointCut(LinkHotelValidateDTO dto) {

    }
    /**
     * 切面
     *
     * @param joinPoint joinPoint
     * @param dto       dto
     * @return java.lang.Object
     */
    @Around(value = "pointCut(dto)")
    public Object around(ProceedingJoinPoint joinPoint, LinkHotelValidateDTO dto) throws Throwable {
        Object proceed = joinPoint.proceed();
        if (Objects.isNull(proceed)){
            return proceed;
        }
        if (proceed instanceof LinkHotelValidateVO ){
            LinkHotelValidateVO vo = (LinkHotelValidateVO) proceed;
            if (!StringUtils.equals(vo.getResult(),ValidateResultEnum.YES.getCode())||vo.getStatus()!=LinkHotelVO.SUCCESS){
                cacheValidateFaildHotel(dto);
            }
        }
        return proceed;
    }


}
