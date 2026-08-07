package cn.vetech.center.hotel.link.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * ImmutablePair工具类
 *
 * @author luqs
 * @version v1.0
 **/
public class ImmutablePairUtils {
    private ImmutablePairUtils() {
    }

    /**
     * 构造ImmutablePair<Boolean, String>
     *
     * @param successFlag 是否成功
     * @param failMsg     失败提示
     * @return ImmutablePair<Boolean, String>
     */
    public static ImmutablePair<Boolean, String> build(boolean successFlag, String failMsg) {
        return new ImmutablePair<>(successFlag, successFlag ? null : StringUtils.defaultString(failMsg));
    }

    /**
     * 构造ImmutablePair<Boolean, String>
     *
     * @param successFlag 是否成功
     * @param failMsg     失败提示
     * @param successMsg  成功提示
     * @return ImmutablePair<Boolean, String>
     */
    public static ImmutablePair<Boolean, String> build(boolean successFlag, String failMsg, String successMsg) {
        return new ImmutablePair<>(successFlag, successFlag ? StringUtils.defaultString(successMsg) : StringUtils.defaultString(failMsg));
    }

    /**
     * 构造ImmutablePair<Boolean, String>
     *
     * @param pair    pair
     * @param failMsg 失败提示
     * @return ImmutablePair<Boolean, String>
     */
    public static ImmutablePair<Boolean, String> build(ImmutablePair<Boolean, String> pair, String failMsg) {
        return build(pair, failMsg, null);
    }

    /**
     * 构造ImmutablePair<Boolean, String>
     *
     * @param pair       pair
     * @param failMsg    失败提示
     * @param successMsg 成功提示
     * @return ImmutablePair<Boolean, String>
     */
    public static ImmutablePair<Boolean, String> build(ImmutablePair<Boolean, String> pair, String failMsg, String successMsg) {
        return new ImmutablePair<>(pair.getLeft(), Boolean.TRUE.equals(pair.getLeft()) ? StringUtils.defaultString(successMsg) : StringUtils.defaultString(failMsg));
    }
}
  