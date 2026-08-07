package cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook.request;

/**
 * 易旅分销 V2 下单入住人
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2OrderBookPax {
    /**
     * 姓
     */
    private String lastName;
    /**
     * 名
     */
    private String firstName;
    /**
     * 客人类型：Adults 成人，Children 儿童
     */
    private String type;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
