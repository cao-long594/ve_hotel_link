package cn.vetech.center.hotel.link.elong.orderdetail.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author by  chenchao on 2018/1/4.
 */
public class Customer {
    /**
     * 客人姓名
     */
    @JsonProperty("Name")
    private String name;
    /**
     *
     */
    @JsonProperty("ConfirmationNumber")
    private String confirmationNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
}
