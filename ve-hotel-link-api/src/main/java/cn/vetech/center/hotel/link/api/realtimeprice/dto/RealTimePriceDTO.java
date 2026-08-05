package cn.vetech.center.hotel.link.api.realtimeprice.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;

import java.util.List;
import java.util.Map;

/**
 * @author vetech
 * @since 2023/10/31
 */
public class RealTimePriceDTO extends LinkHotelDTO {

    /**
     * 等待时间(ms)
     */
    private Integer waitTime;
    /**
     * 酒店查询报价对象
     */
    private LinkHotelRateSearchDTO linkHotelRateSearchDTO;
    /**
     * 酒店映射
     */
    private List<HotelItemDTO> itemDTOList;
    /**
     * 供应商配置
     */
    List<Map<String, String>> supplierConfigs;

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }

    public List<HotelItemDTO> getItemDTOList() {
        return itemDTOList;
    }

    public void setItemDTOList(List<HotelItemDTO> itemDTOList) {
        this.itemDTOList = itemDTOList;
    }

    public LinkHotelRateSearchDTO getLinkHotelRateSearchDTO() {
        return linkHotelRateSearchDTO;
    }

    public void setLinkHotelRateSearchDTO(LinkHotelRateSearchDTO linkHotelRateSearchDTO) {
        this.linkHotelRateSearchDTO = linkHotelRateSearchDTO;
    }

    public List<Map<String, String>> getSupplierConfigs() {
        return supplierConfigs;
    }

    public void setSupplierConfigs(List<Map<String, String>> supplierConfigs) {
        this.supplierConfigs = supplierConfigs;
    }
}
