package cn.vetech.center.hotel.link.supply.service.ratesearch.realtime.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.VipParamInfoToCpsAsmsDTO;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * cps价格查询
 *
 * @author luqs
 * @version v1.0
 */
@XmlRootElement(name = "request")
public class CpsPriceSearchRequest extends LinkHotelDTO {
    /**
     * 采购商编号
     */
    @NotBlank(message = "采购商编号不可为空")
    private String purchaseMerchNo;
    /**
     * 入住日期，格式：yyyy-MM-dd
     */
    private String checkInDate;
    /**
     * 离店日期，格式：yyyy-MM-dd
     */
    private String checkOutDate;
    /**
     * 查询渠道，如：0:直销  1：分销  2：差旅
     */
    private String qryChannel;
    /**
     * 支付类型，0：现付；1：预付
     */
    private String payType;
    /**
     * 酒店信息
     */
    @Valid
    @NotEmpty(message = "酒店信息不可为空")
    private List<HotelLowPriceCalcItemDTO> calcItemList;
    /**
     * vip信息
     */
    private VipParamInfoToCpsAsmsDTO vipParamInfoDTO;
    /**
     * 预订人保障级别，如：A、B、C、D、E
     */
    private String bookerProtectLevel;
    /**
     * 等待时长（ms）
     */
    private Integer waitTime;
    /**
     * 酒店本地时区
     */
    private String hotelLocalTimeZone;

    /**
     * 所在城市
     */
    private String szcs;

    public String getPurchaseMerchNo() {
        return purchaseMerchNo;
    }

    public void setPurchaseMerchNo(String purchaseMerchNo) {
        this.purchaseMerchNo = purchaseMerchNo;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

   public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getQryChannel() {
        return qryChannel;
    }

    public void setQryChannel(String qryChannel) {
        this.qryChannel = qryChannel;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @XmlElementWrapper(name = "calcItemList")
    @XmlElement(name = "calcItem")
    public List<HotelLowPriceCalcItemDTO> getCalcItemList() {
        return calcItemList;
    }

    public void setCalcItemList(List<HotelLowPriceCalcItemDTO> calcItemList) {
        this.calcItemList = calcItemList;
    }

    public VipParamInfoToCpsAsmsDTO getVipParamInfoDTO() {
        return vipParamInfoDTO;
    }

    public void setVipParamInfoDTO(VipParamInfoToCpsAsmsDTO vipParamInfoDTO) {
        this.vipParamInfoDTO = vipParamInfoDTO;
    }

    public String getBookerProtectLevel() {
        return bookerProtectLevel;
    }

    public void setBookerProtectLevel(String bookerProtectLevel) {
        this.bookerProtectLevel = bookerProtectLevel;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }

    public String getHotelLocalTimeZone() {
        return hotelLocalTimeZone;
    }

    public void setHotelLocalTimeZone(String hotelLocalTimeZone) {
        this.hotelLocalTimeZone = hotelLocalTimeZone;
    }

    public String getSzcs() {
        return szcs;
    }

    public void setSzcs(String szcs) {
        this.szcs = szcs;
    }
}
