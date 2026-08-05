package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2021/12/14 17:53
 */
public class ElongSearchXProduct {
    /**
     * X产品ID   	X产品即一个具体的礼包套餐
     */
    @JsonProperty(value = "XProductId")
    private String productId;
    /**
     * X产品名字
     */
    @JsonProperty(value = "XProductName")
    private String productName;
    /**
     * X产品类型	有"食"，"享" 两种类型
     */
    @JsonProperty(value = "TypeName")
    private String typeName;
    /**
     * X产品数量
     */
    @JsonProperty(value = "Quantity")
    private String quantity;
    /**
     * X产品接待时间
     */
    @JsonProperty(value = "ReceptionTimes")
    private String receptionTimes;
    /**
     * X产品适用人数
     */
    @JsonProperty(value = "Capacity")
    private String capacity;
    /**
     * X产品预订电话
     */
    @JsonProperty(value = "BookingPhone")
    private String bookingPhone;
    /**
     * X产品预订规则
     */
    @JsonProperty(value = "AppointPolicy")
    private String appointPolicy;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getReceptionTimes() {
        return receptionTimes;
    }

    public void setReceptionTimes(String receptionTimes) {
        this.receptionTimes = receptionTimes;
    }

    public String g