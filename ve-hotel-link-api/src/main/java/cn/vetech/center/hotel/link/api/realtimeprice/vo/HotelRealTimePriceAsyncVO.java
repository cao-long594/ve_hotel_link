package cn.vetech.center.hotel.link.api.realtimeprice.vo;

import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;

import java.util.Collections;
import java.util.List;

/**
 * @author vetech
 * @since 2023/10/17
 */
public class HotelRealTimePriceAsyncVO {

    /**
     * 本地酒店id
     */
    private String localHotelId;
    /**
     * 价格是否加载完成
     */
    private boolean priceLoadCompletedFlag;
    /**
     * 版本md5
     */
    private String versionMd5;
    /**
     * 参与实时最低价供应商,如：[31200823_xcw_1,31200830_elong_2]
     */
    private List<String> partInSupplyMerchList;
    /**
     * 不参与实时最低价供应商
     */
    private List<String> notPartInSupplyMerchList;
    /**
     * 所有供应商
     */
    private List<String> allSupplyMerchList;
    /**
     * 价格数据
     */
    private List<SearchRoom> searchRoomList = Collections.emptyList();

    public String getLocalHotelId() {
        return localHotelId;
    }

    public void setLocalHotelId(String localHotelId) {
        this.localHotelId = localHotelId;
    }

    public boolean isPriceLoadCompletedFlag() {
        return priceLoadCompletedFlag;
    }

    public void setPriceLoadCompletedFlag(boolean priceLoadCompletedFlag) {
        this.priceLoadCompletedFlag = priceLoadCompletedFlag;
    }

    public String getVersionMd5() {
        return versionMd5;
    }

    public void setVersionMd5(String versionMd5) {
        this.versionMd5 = versionMd5;
    }

    public List<String> getPartInSupplyMerchList() {
        return partInSupplyMerchList;
    }

    public void setPartInSupplyMerchList(List<String> partInSupplyMerchList) {
        this.partInSupplyMerchList = partInSupplyMerchList;
    }

    public List<String> getNotPartInSupplyMerchList() {
        return notPartInSupplyMerchList;
    }

    public void setNotPartInSupplyMerchList(List<String> notPartInSupplyMerchList) {
        this.notPartInSupplyMerchList = notPartInSupplyMerch