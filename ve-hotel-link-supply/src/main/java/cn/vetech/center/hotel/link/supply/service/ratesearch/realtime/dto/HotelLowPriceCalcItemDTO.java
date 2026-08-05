package cn.vetech.center.hotel.link.supply.service.ratesearch.realtime.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 酒店最低价计算
 *
 * @author luqs
 * @version v1.0
 */
public class HotelLowPriceCalcItemDTO implements Serializable {
    private static final long serialVersionUID = -7506780269395266617L;
    /**
     * 酒店id
     */
    @NotBlank(message = "酒店id不可为空")
    private String hotelId;
    /**
     * 城市id
     */
    private String cityId;
    /**
     * 星级1：一星，2：二星，3：三星，4：四星，5：五星；6：六星；7：七星；0或空：其他（无星级）
     */
    private String starRating;
    /**
     * 品牌编号
     */
    private String brandId;
    /**
     * 酒店价格
     */
    private BigDecimal hotelPrice;
    /**
     * 酒店映射
     */
    @Valid
    @NotEmpty
    private List<HotelMappingItemDTO> hotelMappingList;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public BigDecimal getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(BigDecimal hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public List<HotelMappingItemDTO> getHotelMappingList() {
        return hotelMappingList;
    }

 public void setHotelPrice(BigDecimal hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public List<HotelMappingItemDTO> getHotelMappingList() {
        return hotelMappingList;
    }

    public void setHotelMappingList(List<HotelMappingItemDTO> hotelMappingList) {
        this.hotelMappingList = hotelMappingList;
    }
}
