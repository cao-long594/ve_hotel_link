package cn.vetech.center.hotel.link.util;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.charge.cloud.modules.utils.collection.MapUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 集合工具类
 *
 * @author luqs
 * @version v1.0
 */
public class CollectUtils {
    private CollectUtils() {
    }

    /**
     * 若null则空集合
     *
     * @param list 集合
     * @param <T>  范型
     * @return List<T>
     */
    public static <T> List<T> emptyIfNull(List<T> list) {
        return Optional.ofNullable(list).orElse(new ArrayList<>());
    }

    /**
     * 转换成数组
     *
     * @param list 集合
     * @return T[]
     */
    public static String[] toArray(List<String> list) {
        if (Objects.isNull(list)) {
            return new String[0];
        }
        return Optional.of(list).get().toArray(new String[0]);
    }

    /**
     * 非空时执行
     *
     * @param collection       集合
     * @param notEmptyConsumer 处理函数
     * @param <T>              泛型
     * @param <F>              参数泛型
     */
    public static <T, F extends Collection<T>> void handleIfNotEmpty(F collection, Consumer<F> notEmptyConsumer) {
        handleIfNotEmpty(collection, notEmptyConsumer, () -> {
        });
    }

    /**
     * 非空时执行
     *
     * @param collection 集合
    * @param <T>        泛型
     */
    public static <T> void handleEachIfNotEmpty(Collection<T> collection, Consumer<T> consumer) {
        handleIfNotEmpty(collection, t -> t.forEach(consumer), () -> {
        });
    }

    /**
     * 非空时执行
     *
     * @param collection       集合
     * @param notEmptyConsumer 非空处理函数
     * @param emptyRunnable    空处理函数
     * @param <T>              参数泛型
     * @param <F>              参数泛型
     */
    public static <T, F extends Collection<T>> void handleIfNotEmpty(F collection, Consumer<F> notEmptyConsumer, Runnable emptyRunnable) {
        if (CollectionUtils.isNotEmpty(collection)) {
            notEmptyConsumer.accept(collection);
        } else {
            emptyRunnable.run();
        }
    }

    /**
     * 非空时执行
     *
     * @param collection       集合
     * @param notEmptySupplier 非空处理函数
     * @param emptySupplier    空处理函数
     * @param <T>              参数泛型
     * @param <R>              结果泛型
     * @return R
     */
    public static <T, R> R applyIfNotEmpty(Collection<T> collection, Supplier<R> notEmptySupplier, Supplier<R> emptySupplier) {
        if (CollectionUtils.isNotEmpty(collection)) {
            return notEmptySupplier.get();
        } else {
            return emptySupplier.get();
        }
    }

    /**
     * 非空时执行
     *
     * @param list         集合
     * @param notEmptyFunc 非空处理函数
     * @param <T>          参数泛型
     * @param <R>          结果泛型
     * @return List<R>
     */
    public static <T, R> List<R> applyEachIfNotEmpty(Collection<T> list, Function<T, R> notEmptyFunc) {
        return applyIfNotEmpty(list, t -> t.stream().map(notEmptyFunc).filter(Objects::nonNull).collect(Collectors.toList()), Collections::emptyList);
    }

    /**
     * 非空时执行
     *
     * @param collection    集合
     * @param notEmptyFunc  非空处理函数
     * @param emptySupplier 空处理函数
     * @param <T>           参数泛型
     * @param <F>           参数泛型
     * @param <F>           参数泛型
     * @param <R>           结果泛型
     * @return R
     */
    public static <T, F extends Collection<T>, R> R applyIfNotEmpty(F collection, Function<F, R> notEmptyFunc, Supplier<R> emptySupplier) {
        if (CollectionUtils.isNotEmpty(collection)) {
            return notEmptyFunc.apply(collection);
        } else {
            return emptySupplier.get();
        }
    }

    /**
     * 非空时执行
     *
     * @param collection   集合
     * @param notEmptyFunc 非空处理函数
     * @param <T>          参数泛型
     * @param <F>          参数泛型
     * @param <R>          结果泛型
     * @return List<R>
     */
    public static <T, F extends Collection<T>, R> List<R> applyIfNotEmpty(F collection, Function<F, List<R>> notEmptyFunc) {
        return applyIfNotEmpty(collection, notEmptyFunc, Collections::emptyList);
    }

    /**
     * string 转换成 list
     *
     * @param obj           对象
     * @param splitFunction 字符串函数
     * @param listConsumer  集合函数
     * @param <T>           对象泛型
     */
    public static <T extends Object> void splitStr2List(T obj, Function<T, String> splitFunction, BiConsumer<T, List<String>> listConsumer) {
        splitStr2List(obj, splitFunction, listConsumer, () -> {
        });
    }

    /**
     * string 转换成 list
     *
     * @param obj           对象
     * @param splitFunction 字符串函数
     * @param listConsumer  集合函数
     * @param callback      转换成功回调
     * @param <T>           对象泛型
     */
    public static <T extends Object> void splitStr2List(T obj, Function<T, String> splitFunction, BiConsumer<T, List<String>> listConsumer, Runnable callback) {
        splitStr2List(obj, splitFunction, listConsumer, SymbolConstant.COMMA, callback);
    }

    /**
     * string 转换成 list
     *
     * @param obj           对象
     * @param splitFunction 字符串函数
     * @param listConsumer  集合函数
     * @param separator     分隔符
     * @param <T>           对象泛型
     */
    public static <T extends Object> void splitStr2List(T obj, Function<T, String> splitFunction, BiConsumer<T, List<String>> listConsumer, String separator) {
        splitStr2List(obj, splitFunction, listConsumer, separator, () -> {
        });
    }

    /**
     * string 转换成 list
     *
     * @param obj           对象
     * @param splitFunction 字符串函数
     * @param listConsumer  集合函数
     * @param separator     分隔符
     * @param callback      转换成功回调
     * @param <T>           对象泛型
     */
    public static <T extends Object> void splitStr2List(T obj, Function<T, String> splitFunction, BiConsumer<T, List<String>> listConsumer, String separator, Runnable callback) {
        if (Objects.isNull(obj)) {
            return;
        }

        String splitVal = splitFunction.apply(obj);
        String[] splitArr = StringUtils.split(splitVal, separator);
        if (ArrayUtils.isEmpty(splitArr)) {
            return;
        }

        listConsumer.accept(obj, Lists.newArrayList(splitArr));
        if (Objects.nonNull(callback)) {
            callback.run();
        }
    }

    /**
     * string 转换成 list
     *
     * @param str 字符串
     * @return List<String>
     */
    public static List<String> splitStr2List(String str) {
        return splitStr2List(str, SymbolConstant.COMMA);
    }

    /**
     * string 转换成 list
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return List<String>
     */
    public static List<String> splitStr2List(String str, String separator) {
        String[] splitArr = StringUtils.split(str, separator);
        if (ArrayUtils.isEmpty(splitArr)) {
            return Collections.emptyList();
        }
        return Lists.newArrayList(splitArr);
    }

    public static <P, T> void add(P p, Function<P, List<T>> function, BiConsumer<P, List<T>> consumer, T t) {
        if (CollectionUtils.isEmpty(function.apply(p))) {
            consumer.accept(p, new ArrayList<>());
            }
        function.apply(p).add(t);
    }

    public static <P, T> void add(P p, Function<P, List<T>> function, BiConsumer<P, List<T>> consumer, List<T> list) {
        if (CollectionUtils.isEmpty(function.apply(p))) {
            consumer.accept(p, new ArrayList<>());
        }
        function.apply(p).addAll(list);
    }

    /**
     * 不存在时添加
     *
     * @param list 集合
     * @param str  待添加元素
     */
    public static void addIfNotExist(List<String> list, String str) {
        addIfNotExist(list, str, StringUtils::isNotBlank, StringUtils::equals);
    }

    /**
     * 不存在时添加
     *
     * @param list            集合
     * @param p               待添加元素
     * @param canAddPredicate 是否可添加
     * @param eqPredicate     是否存在
     * @param <P>             元素泛型
     */
    public static <P extends Object> void addIfNotExist(List<P> list, P p, Predicate<P> canAddPredicate, BiPredicate<P, P> eqPredicate) {
        if (!canAddPredicate.test(p)) {
            return;
        }
        if (list.stream().noneMatch(item -> eqPredicate.test(item, p))) {
            list.add(p);
        }
    }

    /**
     * 获取value
     *
     * @param key key 可由逗号分隔
     * @param map map
     * @return String
     */
    public static String getValByKey(String key, Map<String, String> map) {
        return getValByKey(key, map, SymbolConstant.COMMA);
    }

    /**
     * 获取value
     *
     * @param key       key
     * @param map       map
     * @param separator 分隔符
     * @return String
     */
    public static String getValByKey(String key, Map<String, String> map, String separator) {
        if (StringUtils.isBlank(key) || MapUtil.isEmpty(map)) {
            return StringUtils.EMPTY;
        }

        return Arrays.stream(StringUtils.split(key, StringUtils.defaultString(separator)))
                .map(map::get)
                .filter(StringUtils::isNotBlank)
                .distinct()
                .collect(Collectors.joining(StringUtils.defaultString(separator)));
    }
}