package cn.vetech.center.hotel.link.util.ratesearch.pojo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author chengwanshan
 * @since 2022/3/29 15:17
 */
public class RateSearchDailyRoomRate {
    /**
     * 日期，必须
     */
    private String date;
    /**
     * 房价，必须
     */
    private String price;
    /**
     * 房量，为空不校验
     */
    private String roomNum;
    /**
     * 房态状态，为空或true:可用   false:不可用
     */
    private String status;
    /**
     * 含早餐份数
     */
    @ApiModelProperty(value = "含早餐份数", dataType = "string")
    private String freeMealCount;
    /**
     * 含餐份数描述，根据freeMeal转换
     */
    @ApiModelProperty(value = "含餐份数描述", dataType = "string")
    private String freeMealMs;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFreeMealCount() {
        return freeMealCount;
    }

    public void setFreeMealCount(String freeMealCount) {
        this.freeMealCount = freeMealCount;
    }

    public String getFreeMealMs() {
        return freeMealMs;
    }

    public void setFreeMealMs(String freeMealMs) {
        this.freeMealMs = freeMealMs;
    }
}
