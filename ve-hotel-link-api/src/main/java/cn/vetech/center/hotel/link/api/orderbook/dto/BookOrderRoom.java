package cn.vetech.center.hotel.link.api.orderbook.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 入住人房间节点
 *
 * @author gaojin
 */
public class BookOrderRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 入住人集合
     */
    @ApiModelProperty(value = "入住人集合", dataType = "string")
    private List<BookCustomer> customers;
    /**
     * 房间序号
     */
    @ApiModelProperty(value = "房间序号", dataType = "string")
    private String fjxh;

    public List<BookCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<BookCustomer> customers) {
        this.customers = customers;
    }

    public String getFjxh() {
        return fjxh;
    }

    public void setFjxh(String fjxh) {
        this.fjxh = fjxh;
    }
}
