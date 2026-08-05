package cn.vetech.center.hotel.link.elong.orderbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author gaojin
 */
public class ElongBookOrderRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 入住人信息
     * 每个房间的入住人。每个房间至少一个入住人，可以多人。
     * 该字段为数组 转xml时注意自定义一个节点
     * 参考Customer节点
     */
    @JsonProperty("Customers")
    private List<ElongBookCustomer> customers;

    public List<ElongBookCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<ElongBookCustomer> customers) {
        this.customers = customers;
    }
}
