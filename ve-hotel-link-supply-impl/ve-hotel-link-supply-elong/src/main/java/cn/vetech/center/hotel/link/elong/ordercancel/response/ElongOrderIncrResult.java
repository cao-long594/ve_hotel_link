package cn.vetech.center.hotel.link.elong.ordercancel.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author xiaotengyu
 * @since 2022-07-27 11:26
 */
public class ElongOrderIncrResult {

    @JsonProperty("Orders")
    private List<ElongOrderIncrInfo> orders;

    public List<ElongOrderIncrInfo> getOrders() {
        return orders;
    }

    public void setOrders(List<ElongOrderIncrInfo> orders) {
        this.orders = orders;
    }
}
