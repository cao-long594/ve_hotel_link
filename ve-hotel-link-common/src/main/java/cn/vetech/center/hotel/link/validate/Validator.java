package cn.vetech.center.hotel.link.validate;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 验证器
 * @author zoujiming
 * @param <T> object type
 * Created by vetech on 2018/3/21.
 *
 */
public class Validator<T> {
    /**
     *
     */
    private T data;
    /**
     *
     */
    private Predicate<T> predicate;
    /**
     *
     */
    private String errorMessage;
    /**
     *
     */
    private Function<String, ? extends RuntimeException> exceptionSupplier;

    /**
     * 创建一个验证器
     * @param data 验证的数据
     * @param predicate 验证条件
     * @param errorMessage 自定义错误消息
     * @param <T> object type
     * @return validator
     */
    public static <T> Validator create(T data, Predicate<T> predicate, String errorMessage) {
        Validator<T> validator = new Validator<>();
        validator.data = data;
        validator.predicate = predicate;
        validator.errorMessage = errorMessage;
        validator.exceptionSupplier = RuntimeException::new;

        return validator;
    }

    /**
     * 创建一个验证器
     * @param data 验证的数据
     * @param predicate 验证条件
     * @param exceptionSupplier 自定义运行异常
     * @param <T> object type
     * @return validator
     */
    public static <T> Validator create(T data, Predicate<T> predicate, Function<String, ? extends RuntimeException> exceptionSupplier) {
        Validator<T> validator = new Validator<>();
        validator.data = data;
        validator.predicate = predicate;
        validator.errorMessage = "";
        validator.exceptionSupplier = exceptionSupplier;

        return validator;
    }

    /**
     * 验证
     * @return boolean
     */
    public boolean validate() {
        return predicate.test(data);
    }

    /**
     * 返回运行异常
     * @return RuntimeException
     */
    public RuntimeException getThrowable() {
        return exceptionSupplier.apply(errorMessage);
    }

}
