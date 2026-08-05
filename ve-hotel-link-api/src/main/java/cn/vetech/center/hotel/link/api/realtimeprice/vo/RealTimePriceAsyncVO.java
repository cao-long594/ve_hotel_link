package cn.vetech.center.hotel.link.api.realtimeprice.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步实时报价
 *
 * @author vetech
 * @since 2023/10/17
 */
public class RealTimePriceAsyncVO extends LinkHotelVO {
    /**
     * 缓存key
     */
    private String cacheKey;
    /**
     * 价格完成的本地酒店id
     */
    private Set<String> priceCompletedHotelIdList;
    /**
     * 价格未完成的本地酒店id
     */
    private Set<String> priceNotCompletedHotelIdList;
    /**
     * 本地酒店id
     */
    private Set<String> hotelIdList;
    /**
     * 酒店价格数据
     */
    private List<HotelRealTimePriceAsyncVO> hotelPriceAsyncList = Collections.emptyList();
    /**
     * 加载次数
     */
    private AtomicInteger loadCount = new AtomicInteger(0);

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public Set<String> getPriceCompletedHotelIdList() {
        return priceCompletedHotelIdList;
    }

    public void setPriceCompletedHotelIdList(Set<String> priceCompletedHotelIdList) {
        this.priceCompletedHotelIdList = priceCompletedHotelIdList;
    }

    public Set<String> getPriceNotCompletedHotelIdList() {
        return priceNotCompletedHotelIdList;
    }

    public void setPriceNotCompletedHotelIdList(Set<String> priceNotCompletedHotelIdList) {
        this.priceNotCompletedHotelIdList = priceNotCompletedHotelIdList;
    }

    public Set<String> getHotelIdList() {
        return hotelIdList;
    }

    public void setHotelIdList(Set<String> hotelIdList) {
        this.hotelIdList = hotelIdList;
    }

    public List<HotelRealTimePriceAsyncVO> getHotelPriceAsyncList() {
        return hotelPriceAsyncList;
    }

    public void setHotelPriceAsyncList(List<HotelRealTimePr