package cn.vetech.center.hotel.link.elong.orderdetail.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author by  chenchao on 2018/1/4.
 */
public class OrderRoom {
    /**
     *客人信息
     */
    @JsonProperty("Customers")
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
