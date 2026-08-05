package cn.vetech.center.hotel.link.entity;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author pengyefei
 * @version 1.0
 * @since 2023/3/15 10:22
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SignField {

    /**
     * 序号，从小到大
     *
     * @return int
     */
    int sortNum() default 0;
}
