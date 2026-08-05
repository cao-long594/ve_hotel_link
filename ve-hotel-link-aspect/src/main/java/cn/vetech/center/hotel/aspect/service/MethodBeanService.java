package cn.vetech.center.hotel.aspcet.service;


import cn.vetech.center.hotel.aspcet.bean.MethodBean;
import org.aspectj.lang.ProceedingJoinPoint;

public interface MethodBeanService {
    /**
     * 前置通知 目前方法执行前执行
     * @param methodBean 方法信息
     */
    default void before(MethodBean methodBean){};

    /**
     * 后置通知  目前方法正常返回无异常时执行
     * @param methodBean 方法信息
     */
    default void afterReturn(MethodBean methodBean){}

    /**
     * 异常通知  目前方法异常时执行
     * @param methodBean 方法信息
     */
    default void afterThrow(MethodBean methodBean){}

    /**
     * 最终通知 目前方法是否异常都执行
     * @param methodBean 方法信息
     */
    default void after(MethodBean methodBean){}

    /**
     * 环绕通知 在目标方法执行之前和之后都可以执行额外代码的通知
     * @param methodBean 方法信息
     * @param point 1
     * @return 1
     * @throws Throwable 1
     */
    default Object around(MethodBean methodBean, ProceedingJoinPoint point) throws Throwable {return point.proceed();}
}
