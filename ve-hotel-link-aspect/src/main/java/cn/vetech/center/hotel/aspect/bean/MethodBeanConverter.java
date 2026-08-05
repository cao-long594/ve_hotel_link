package cn.vetech.center.hotel.aspcet.bean;

import cn.vetech.center.hotel.aspcet.util.ProxyUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class MethodBeanConverter {

    private static final Logger LOGGER= LoggerFactory.getLogger(MethodBeanConverter.class);

    /**
     * 通过spring point 转换出 MethodBean
     * @param point 1
     * @return 1
     */
    public static MethodBean convert(ProceedingJoinPoint point){
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Class clazz=null;
        try {
            clazz= ProxyUtil.getTarget(point.getTarget()).getClass();
        } catch (Exception e) {
            LOGGER.error("异常",e);
        }
        Method method=methodSignature.getMethod();
        String[] paramsName=methodSignature.getParameterNames();
        Object[] paramsValue=point.getArgs();
        Map<String,Object> params=new LinkedHashMap<>();
        Arrays.asList(paramsName).stream().forEach(paramName->params.put(paramName,paramsValue[params.size()]));
        MethodBean methodBean=new MethodBean();
        methodBean.setClazz(clazz);
        methodBean.setMethod(method);
        methodBean.setParams(params);
        return methodBean;
    }
}
