package cn.vetech.center.hotel.link.supply.ylfx.v2.validate.response;

/**
 * 易旅分销 V2 产品可订校验数据
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2ValidateData {
    /**
     * 校验后的房型与报价
     */
    private YlfxV2ValidateRoom room;

    public YlfxV2ValidateRoom getRoom() {
        return room;
    }

    public void setRoom(YlfxV2ValidateRoom room) {
        this.room = room;
    }
}
