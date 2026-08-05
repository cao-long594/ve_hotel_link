package cn.vetech.center.hotel.link.api.hotelgetjdlb.dto;

/**
 * @author vetech
 * @since 2023/12/1
 */
public class PriorityHotel {

    /**
     * 酒店id类型
     */
    private String customerType;

    /**
     * 酒店id
     */
    private String jdid;

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getJdid() {
        return jdid;
    }

    public void setJdid(String jdid) {
        this.jdid = jdid;
    }
}
