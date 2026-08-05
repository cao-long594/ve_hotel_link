package cn.vetech.center.hotel.aspcet.util;

import cn.vetech.charge.cloud.modules.utils.reflect.ClassUtil;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author lipeng
 */
public class ProxyUtil {


    /**
     * 获取 目标对象
     *
     * @param proxy 代理对象
     * @return 1
     * @throws Exception 1
     */
    public static Object getTarget(Object proxy) throws Exception {
        if(proxy==null){
            return null;
        }
        if (!AopUtils.isAopProxy(proxy)) {
            return proxy;
        }
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else {
            return getCglibProxyTargetObject(proxy);
        }
    }

    /**
     * 是否为代理对象
     * @param proxy 1
     * @return 1
     */
    public static boolean isProxy(Object proxy){
        if(proxy==null){
            return false;
        }
        if(AopUtils.isAopProxy(proxy)){
            return true;
        }
        if(AopUtils.isJdkDynamicProxy(proxy)){
            return true;
        }
        if(AopUtils.isCglibProxy(proxy)){
            return true;
        }
        return false;
    }

    /**
     * 获取cglib动态代理 真实对象
     * @param proxy 1
     * @return 1
     * @throws Exception 1
     */
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        ClassUtil.makeAccessible(h);
        Object dynamicAdvisedInterceptor = h.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        ClassUtil.makeAccessible(advised);

       Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

        return target;
    }

    /**
     * 获取jdk动态代理 真实对象
     * @param proxy 1
     * @return 1
     * @throws Exception 1
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        ClassUtil.makeAccessible(h);
        AopProxy aopProxy = (AopProxy) h.get(proxy);

        Field advised = aopProxy.getClass().getDeclaredField("advised");
        ClassUtil.makeAccessible(advised);

        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();

        return target;
    }

    /**
     * 获取代理对象真实的className
     * @param o 1
     * @return 1
     */
    public static String getClassName(Object o) throws Exception {
        if(o==null){
            return null;
        }
        Object target = ProxyUtil.getTarget(o);
        if (Objects.isNull(target)){
            return null;
        }
        return target.getClass().getSimpleName();
    }
}