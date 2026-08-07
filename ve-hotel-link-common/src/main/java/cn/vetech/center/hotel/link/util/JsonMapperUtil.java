package cn.vetech.center.hotel.link.util;

import cn.vetech.charge.cloud.modules.utils.reflect.ClassUtil;
import com.fasterxml.jackson.databind.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.vetech.charge.cloud.modules.utils.mapper.JsonMapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 基于JsonMapper工具类
 * </p>
 *
 * @author wangkai
 * @since 2020/11/5
 */
public class JsonMapperUtil {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(JsonMapperUtil.class);

    /**
     * 转json
     *
     * @param obj obj
     * @return java.lang.String
     */
    public static String toJsonStr(Object obj) {
        return JacksonUtils.toJsonWithNonEmpty(obj);
    }

    /**
     * json转实体
     *
     * @param str    str
     * @param tClass tClass
     * @param <T>    泛型
     * @return T 实体
     */
    public static <T> T fromJson(String str, Class<T> tClass) {
        return JacksonUtils.parseNonEmpty(str, tClass);
    }

    /**
     * 反序列化为list
     *
     * @param json json
     * @param t    t
     * @param <T>  泛型
     * @return java.util.List<T>
     */
    public static <T> List<T> fromJsonToList(String json, Class<T> t) {
        JavaType javaType = JsonMapper.defaultMapper().buildCollectionType(List.class, t);
        return JacksonUtils.parseNonEmpty(json, javaType);
    }

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj object
     * @return map
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            ClassUtil.makeAccessible(field);
            String fieldName = field.getName();
            try {
                Object value = field.get(obj);
                if (Objects.isNull(value)) {
                    continue;
                }
                map.put(fieldName, value);
            } catch (Exception ex) {
                logger.error("objectToMap 获取属性值异常；fieldName:{}", fieldName, ex);
            }
        }
        return map;
    }

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj object
     * @return map
     */
    public static Map<String, String> objectToMapForString(Object obj) {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            ClassUtil.makeAccessible(field);
            String fieldName = field.getName();
            try {
                Object value = field.get(obj);
                if (Objects.isNull(value)) {
                    continue;
                }
                map.put(fieldName, String.valueOf(value));
            } catch (Exception ex) {
                logger.error("objectToMap 获取属性值异常；fieldName:{}", fieldName, ex);
            }
        }
        return map;
    }

}