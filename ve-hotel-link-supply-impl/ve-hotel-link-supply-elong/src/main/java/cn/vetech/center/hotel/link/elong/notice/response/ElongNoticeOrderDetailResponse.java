package cn.vetech.center.hotel.link.elong.notice.response;

/**
 * @author chengwanshan
 * @since 2021/7/14 16:55
 */
public class ElongNoticeOrderDetailResponse {
    /**
     * int	N   0:表示接收成功   -1:表示接收失败
     * 建议：接收成功，立马返回结果，异步进行处理。
     */
    private int code;
    /**
     * String	N	说明：发送失败需要给出失败信息
     */
    private String errorMsg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
