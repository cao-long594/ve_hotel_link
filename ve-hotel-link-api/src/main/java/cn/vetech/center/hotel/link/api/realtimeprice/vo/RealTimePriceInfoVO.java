package cn.vetech.center.hotel.link.api.realtimeprice.vo;

import cn.vetech.center.hotel.link.api.enums.PriceStatusEnum;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;

import java.util.Collections;
import java.util.List;

/**
 * @author vetech
 * @since 2023/10/17
 */
public class RealTimePriceInfoVO {

    /**
     * 房源商编号
     */
    private String supplyNo;
    /**
     * 供应商编号
     */
    private String supplyMerchNo;
    /**
     * 房源酒店id
     */
    private String supplyHotelId;
    /**
     * 1正常  2 价格未开启缓存 3 超时未返回 4执行异常
     */
    private String priceStatus;
    /**
     * 本地酒店id
     */
    private String localHotelId;
    /**
     * 价格数据
     */
    private List<SearchRoom> searchRoomList = Collections.emptyList();

    public RealTimePriceInfoVO() {
    }

    public RealTimePriceInfoVO(String supplyNo, String supplyMerchNo, String supplyHotelId, PriceStatusEnum statusEnum, String localHotelId, List<SearchRoom> searchRoomList) {
        this.supplyNo = supplyNo;
        this.supplyMerchNo = supplyMerchNo;
        this.supplyHotelId = supplyHotelId;
        this.priceStatus = statusEnum.getCode();
        this.localHotelId = localHotelId;
        this.searchRoomList = searchRoomList;
    }

    public RealTimePriceInfoVO(String supplyNo, String supplyMerchNo, String supplyHotelId, String localHotelId, PriceStatusEnum statusEnum) {
        this.supplyNo = supplyNo;
        this.supplyMerchNo = supplyMerchNo;
        this.supplyHotelId = supplyHotelId;
        this.priceStatus = statusEnum.getCode();
        this.localHotelId = localHotelId;
    }

    public String getSupplyNo() {
        return supplyNo;
    }

    public void setSupplyNo(String supplyNo) {
        this.supplyNo = supplyNo;
    }

    public String getSupplyMerchNo() {
        return supplyMerchNo;
    }

    public void setSupplyMerchNo(String supplyMerchNo) {
        this.supplyMerchNo = supplyMer