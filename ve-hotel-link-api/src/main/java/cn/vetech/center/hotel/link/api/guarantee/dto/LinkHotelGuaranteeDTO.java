package cn.vetech.center.hotel.link.api.guarantee.dto;

import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 担保校验
 *
 * @author gaojin
 */
public class LinkHotelGuaranteeDTO extends LinkHotelRateSearchDTO {

    /**
     * 最早到店时间，yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(value = "最早到店时间，yyyy-MM-dd HH:mm:ss", dataType = "string")
    private String earliestArrivalTime;
    /**
     * 最晚到店时间，yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(value = "最晚到店时间，yyyy-MM-dd HH:mm:ss", dataType = "string")
    private String latestArrivalTime;
    /**
     * 房间数量
     */
    @ApiModelProperty(value = "房间数量", dataType = "string")
    private String numberOfRooms;
    /**
     * 总价
     */
    @ApiModelProperty(value = "总价", dataType = "string")
    private String totalPrice;

    /**************************相比cps新增字段**********************/
    /**
     * 间夜数
     */
    private String jys;

    /**************************相比cps新增字段**********************/

    public String getEarliestArrivalTime() {
        return earliestArrivalTime;
    }

    public void setEarliestArrivalTime(String earliestArrivalTime) {
        this.earliestArrivalTime = earliestArrivalTime;
    }

    public String getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setLatestArrivalTime(String latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getJys() {
        return jys;
    }

    public void setJys(String jys) {
     