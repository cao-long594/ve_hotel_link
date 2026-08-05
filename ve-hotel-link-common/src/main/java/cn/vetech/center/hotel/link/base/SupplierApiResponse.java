package cn.vetech.center.hotel.link.base;

/**
 * 供应商api响应
 *
 * @param <T> 泛型
 * @author luqs
 * @version v1.0
 **/
public class SupplierApiResponse<T> {
    /**
     * 响应对象
     */
    private T apiResponse;
    /**
     * 原始响应
     */
    private String origResponse;

    public SupplierApiResponse() {
    }

    public SupplierApiResponse(T apiResponse, String origResponse) {
        this.apiResponse = apiResponse;
        this.origResponse = origResponse;
    }

    public T getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(T apiResponse) {
        this.apiResponse = apiResponse;
    }

    public String getOrigResponse() {
        return origResponse;
    }

    public void setOrigResponse(String origResponse) {
        this.origResponse = origResponse;
    }
}
