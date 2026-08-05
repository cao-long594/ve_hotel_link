package cn.vetech.center.hotel.link.validate;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author zoujiming
 * 验证器工具类 支持一组条件的验证，减少代码中大量验证时的if
 * Created by vetech on 2018/3/21.
 */
public class Validators {

    /**
     * 验证器集合
     */
    private List<Validator> validators = new ArrayList<>();

    /**
     * 创建验证器工具类
     * @return Validators
     */
    public static Validators create() {
        return new Validators();
    }

    /**
     * 校验对象
     * @param data 验证的对象
     * @param message 期待的错误信息
     */
    public static void notNull(Object data,String message){
        Validators.create().append(data, Objects::nonNull,message).validate();
    }

    /**
     * 校验字符串
     * @param data 验证的字符串
     * @param message 期待的错误信息
     */
    public static void notStrBlank(String data, String message) {
        Validators.create().append(data, StringUtils::isNotBlank, message).validate();
    }

    /**
     * 添加验证
     * @param data 验证的数据
     * @param predicate 验证条件
     * @param <T> object type
     * @return Validators 验证器集合
     */
    public <T> Validators append(T data, Predicate<T> predicate) {
        return append(data, predicate, "");
    }

    /**
     * @param data 验证的数据
     * @param predicate 验证条件
     * @param errorMessage 自定义错误消息
     * @param <T> object type
     * @return Validators 验证器集合
     */
    public <T> Validators append(T data, Predicate<T> predicate, String errorMessage) {
        return append(Validator.create(data, predicate, errorMessage));
    }

    /**
     * @param data 验证的数据
     * @param predicate 验证条件
     * @param exceptionSupplier 自定义运行异常
     * @param <T> object type
     * @return 验证器集合
     */
    public <T> Validators append(T data, Predicate<T> predicate, Function<String, ? extends RuntimeException> exceptionSupplier) {
        return append(Validator.create(data, predicate, exceptionSupplier));
    }

    /**
     * @param validator 验证器
     * @return Validators
     */
    public Validators append(Validator validator) {
        validators.add(validator);
        return this;
    }

    /**
     * 执行验证
     */
    public void validate() {
        validators.forEach(validator -> {
            if (!validator.validate()) {
                throw validator.getThrowable();
            }
        });
    }

}
