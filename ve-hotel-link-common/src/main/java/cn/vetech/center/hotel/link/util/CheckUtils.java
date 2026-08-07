package cn.vetech.center.hotel.link.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @author xiaotengyu
 * @since 2021/9/13 16:59
 */
public class CheckUtils {

    /**
     * notEmpty
     * @param obj obj
     * @param message message
     */
    public static void notEmpty(Object obj, String message) {
        if (obj == null){
            throw new IllegalArgumentException(message + " must be specified");
        }
        if (obj instanceof String && obj.toString().trim().length()==0){
            throw new IllegalArgumentException(message + " must be specified");
        }
        if (obj.getClass().isArray() && Array.getLength(obj)==0){
            throw new IllegalArgumentException(message + " must be specified");
        }
        if (obj instanceof Collection && ((Collection)obj).isEmpty()){
            throw new IllegalArgumentException(message + " must be specified");
        }
        if (obj instanceof Map && ((Map)obj).isEmpty()){
            throw new IllegalArgumentException(message + " must be specified");
        }
    }

    /**
     * not null
     * @param object ojbect
     * @param message message
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
