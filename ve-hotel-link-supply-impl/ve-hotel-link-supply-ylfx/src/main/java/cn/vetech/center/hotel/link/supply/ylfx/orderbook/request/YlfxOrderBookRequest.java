package cn.vetech.center.hotel.link.supply.ylfx.orderbook.request;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseRequest;
import cn.vetech.center.hotel.link.supply.ylfx.validate.request.DailyPrice;

import java.util.List;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class YlfxOrderBookRequest extends YlfxBaseRequest {
    /**
     * 渠道端订单ID
     */
    private String cusOrderId;
    /**
     * 酒店ID
     */
    private String hotelId;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 每日房间数
     */
    private int roomCount;
    /**
     * 入住日期
     */
    private String checkinDate;
    /**
     * 离店日期
     */
    private String checkoutDate;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系人手机号
     */
    private String contactPhone;
    /**
     * 每日价格列表
     */
    private List<DailyPrice> dailyList;
    /**
     * 总价
     */
    private String totalPrice;
    /**
     * 开票类型,0. 平台开票，  1. 酒店开票
     */
    private Integer  invoiceMode;
    /**
     * 对客总售卖价格，酒店开票时必填
     */
    private String totalSellPrice;
    /**
     * 对客每日售卖价格，酒店开票时必填
     */
    private List<DailyPrice> dailySellList;
    /**
     * 给酒店备注
     */
    private String remark;
    /**
     * 入住人列表
     */
    private List<CheckinPerson> checkinPersons;

    public String getCusOrderId() {
        return cusOrderId;
    }

    public void setCusOrderId(String cusOrderId) {
        this.cusOrderId = cusOrderId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount