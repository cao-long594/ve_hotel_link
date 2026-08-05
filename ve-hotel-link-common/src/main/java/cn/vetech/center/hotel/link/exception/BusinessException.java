package cn.vetech.center.hotel.link.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by lipeng on 2018/3/21 19:20
 * Comment
 * @author lipeng
 */
public class BusinessException extends Exception {

    /**
     *
     */
    private String data;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    public BusinessException(String message, String data) {
        super(message);
        this.data=data;
    }

    public BusinessException(String message, String data, Throwable cause) {
        super(message, cause);
        this.data=data;
    }

    /**
     *
     * @return string
     */
    public String getMessageAndData() {
        if(StringUtils.isBlank(data)){
            return super.getMessage();
        }
        return super.getMessage()+":"+data;
    }
}
