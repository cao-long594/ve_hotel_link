package cn.vetech.center.hotel.link.elong.ordercancel.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author gaojin
 */
public class ElongOrderCancel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 取消请求处理结果
     * 注意，是三个s。
     * 此处返回true的时候表示艺龙已经收到了请求
     * 是否取消成功需要等订单进入到删除状态
     */
    @JsonProperty("Successs")
    private String successs;
    /**
     * 错误信息
     */
    @JsonProperty("ErrorMsg")
    private String errorMsg;

    /**
     * 取消罚金
     */
    @JsonProperty("PenaltyAmount")
    private String penaltyAmount;

    public String getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(String penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String getSuccesss() {
        return successs;
    }

    public void setSuccesss(String successs) {
        this.successs = successs;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
