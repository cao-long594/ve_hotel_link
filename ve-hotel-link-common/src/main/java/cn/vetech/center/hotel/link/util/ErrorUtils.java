package cn.vetech.center.hotel.link.util;

import io.netty.util.internal.ThrowableUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 异常工具类
 *
 * @author luqs
 * @version v1.0
 */
public class ErrorUtils {

    private ErrorUtils() {
    }

    /**
     * 获取异常堆栈信息
     *
     * @param e 异常
     * @return String
     */
    public static String getStackTrace(Throwable e) {
        return getStackTrace(e, 0);
    }

    /**
     * 获取异常堆栈信息
     *
     * @param e      异常
     * @param maxLen 最大长度
     * @return String
     */
    public static String getStackTrace(Throwable e, int maxLen) {
        String errorMsg = ThrowableUtil.stackTraceToString(e);
        if (maxLen < 1) {
            return errorMsg;
        }
        return StringUtils.substring(errorMsg, 0, Math.min(errorMsg.length(), maxLen));
    }

}
