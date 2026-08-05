package cn.vetech.center.hotel.link.api.ratesearch.vo;

/**
 * @author chengwanshan
 * @since 2024/12/5 19:28
 */
public class HotelPriceTag {
    /**
     * 标签标识，例如 "platform_member_vip", 可根据这个唯一标识做差异化标签展示
     * 参考枚举  HotelPriceTagEnum
     */
    private String tagCode;
    /**
     * 标签名称，例如 "平台会员价"
     */
    private String tagName;

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
