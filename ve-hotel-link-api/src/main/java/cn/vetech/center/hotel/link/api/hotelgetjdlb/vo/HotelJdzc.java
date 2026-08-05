package cn.vetech.center.hotel.link.api.hotelgetjdlb.vo;

/**
 * 酒店政策，政策可能有多个，后台直接返回集合，前台页面进行if判断和展示
 * @author zhangheng
 * @version 2019-4-23
 */
public class HotelJdzc {
    /**
     * 政策编码
     * CheckInCheckOut 入住离店
     * Child 儿童
     * Meal 早餐
     * Pet 宠物
     *
     */
    private String code;

    /**
     * 名称
     */
    private String title;
    /**
     * 政策描述
     */
    private String text;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
