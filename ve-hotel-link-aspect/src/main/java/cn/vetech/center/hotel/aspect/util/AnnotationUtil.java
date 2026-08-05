package cn.vetech.center.hotel.aspcet.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author lipeng
 */
public class AnnotationUtil {
    /**
     * 获取方法注解
     * @param method 1
     * @param annotationClass 1
     * @param <T> 1
     * @return 1
     */
    public static <T> T getAnnotationOnMethod(Method method, Class<? extends Annotation> annotationClass) {
        if (method.isAnnotationPresent(annotationClass)) {
            return (T) method.getAnnotation(annotationClass);
        }
        return null;
    }

}
