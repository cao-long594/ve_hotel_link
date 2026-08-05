package cn.vetech.center.hotel.link.elong.notice.request;

/**
 * @author chengwanshan
 * @since 2021/7/14 17:06
 */
public class ElongNoticeOrderDetailRequest {
    /**
     * 推送类型 Enum	N
     * Inventory：库存增量
     * Rate：价格增量
     * Order：订单增量
     * State：状态增量
     * Data：酒店增量
     * HotelDetail：酒店详情增量
     * 说明：用来标示是哪种类型数据,该接口始终为：Order
     */
    private String type;
    /**
     * 数据	String	N	加密后的数据，需要解密，解密方式见“加解密方式”，密钥为appkey后8位，数据可能有多条,为Order的集合，见Order
     */
    private String data;
    /**
     * 唯一标示	String	N	出现问题，方便定位到哪条数据
     */
    private String guid;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
