package cn.vetech.center.hotel.link.api.ratesearch.vo;

/**
 * @author chengwanshan
 * @since 2024/12/10 17:14
 */
public class HotelBedConfiguration {
    /**
     * 床型类型
     */
    private String type;
    /**
     * 床型尺寸
     */
    private String size;
    /**
     * 床型数量
     */
    private Integer quantity;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
