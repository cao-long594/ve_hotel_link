package cn.vetech.center.hotel.link.util;

import cn.vetech.charge.cloud.modules.utils.mapper.JsonMapper;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;

import java.util.List;

/**
 * json工具类
 *
 * @author luqs
 * @version v1.0
 **/
public class JacksonUtils {

    private static JsonMapper jsonMapperNonEmpty;

    private static JsonMapper jsonMapperDefault;

    private static JsonMapper jsonMapperNonNull;

    static {
        jsonMapperNonEmpty = new JsonMapper(Include.NON_EMPTY, true);
        jsonMapperNonNull = new JsonMapper(Include.NON_NULL,true);
        jsonMapperDefault = new JsonMapper();
    }

    private JacksonUtils() {
    }

    /**
     * 序列化
     *
     * @param obj 对象
     * @return String
     */
    public static String toJsonWithNonEmpty(Object obj) {
        return jsonMapperNonEmpty.toJson(obj);
    }

    /**
     * 反序列化
     *
     * @param json  json
     * @param clazz 类
     * @param <T>   泛型
     * @return T
     */
    public static <T> T parseNonEmpty(String json, Class<T> clazz) {
        return jsonMapperNonEmpty.fromJson(json, clazz);
    }

    /**
     * 反序列化
     *
     * @param json  json
     * @param javaType type
     * @param <T>   泛型
     * @return T
     */
    public static <T> T parseNonEmpty(String json, JavaType javaType) {
        return jsonMapperNonEmpty.fromJson(json, javaType);
    }

    /**
     * 反序列化
     *
     * @param json  json
     * @param typeReference reference
     * @param <T>   泛型
     * @return T
     */
    public static <T> T parseNonEmpty(String json, TypeReference typeReference) {
        return jsonMapperNonEmpty.fromJson(json, typeReference);
    }

    /**
     * 序列化
     *
     * @param obj 对象
     * @return String
     */
   public static String toJsonWithNonNull(Object obj) {
        return jsonMapperNonNull.toJson(obj);
    }

    /**
     * 反序列化
     *
     * @param json  json
     * @param clazz 类
     * @param <T>   泛型
     * @return T
     */
    public static <T> T parseNonNull(String json, Class<T> clazz) {
        return jsonMapperNonNull.fromJson(json, clazz);
    }

    /**
     * 反序列化
     * @param json json 字符串
     * @param typeReference type
     * @param <T> 泛型
     * @return 对象
     */
    public static <T> T parseNonNull(String json, TypeReference typeReference) {
        return jsonMapperNonNull.fromJson(json, typeReference);
    }

    /**
     * 序列化
     *
     * @param obj 对象
     * @return String
     */
    public static String toJsonWithDefault(Object obj) {
        return jsonMapperDefault.toJson(obj);
    }

    /**
     * 反序列化
     *
     * @param json  json
     * @param clazz 类
     * @param <T>   泛型
     * @return T
     */
    public static <T> T parseDefault(String json, Class<T> clazz) {
        return jsonMapperDefault.fromJson(json, clazz);
    }

    /**
     * 反序列化
     *
     * @param json  json
     * @param typeReference 类
     * @param <T>   泛型
     * @return T
     */
    public static <T> T parseDefault(String json, TypeReference typeReference) {
        return jsonMapperDefault.fromJson(json, typeReference);
    }

    /**
     * 反序列化为list
     *
     * @param listJsonStr roomImgJson
     * @param t           t
     * @param <T>         泛型
     * @return java.util.List<T>
     */
    public static <T> List<T> fromJsonToList(String listJsonStr, Class<T> t) {
        JavaType javaType = JsonMapper.defaultMapper().buildCollectionType(List.class, t);
        return JsonMapper.nonEmptyMapper().fromJson(listJsonStr, javaType);
    }

}