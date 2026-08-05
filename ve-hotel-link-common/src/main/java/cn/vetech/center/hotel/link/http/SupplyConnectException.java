package cn.vetech.center.hotel.link.http;

/**
 * 通讯异常
 *
 * @author houya
 */
public class SupplyConnectException extends Exception {
    private static final long serialVersionUID = 1L;

    public SupplyConnectException() {
    }

    public SupplyConnectException(String message) {
        super(message);
    }

    public SupplyConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public SupplyConnectException(Throwable cause) {
        super(cause);
    }

    public SupplyConnectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
