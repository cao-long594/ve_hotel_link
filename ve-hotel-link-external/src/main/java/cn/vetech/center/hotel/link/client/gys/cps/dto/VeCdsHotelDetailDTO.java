package cn.vetech.center.hotel.link.client.gys.cps.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author gaojin
 */
public class VeCdsHotelDetailDTO {
    /**
     * 酒店ID
     */
    @ApiModelProperty(value = "酒店ID", dataType = "string")
    private String hotelId;
    /**
     * 0表示基本信息 1房型信息 2图片信息
     * 3联系人信息 4周边信息5外部酒店信息
     * 多个逗号分隔 不传表示所有
     */
    @ApiModelProperty(value = "查询信息类别", dataType = "string")
    private String infoType;

    /**
     * 语种：
     * zhcn 中(简体)
     * zhtw 中文(繁体)
     * enus 英文
     * jajp 日本
     * kokr 韩文
     * ruru 俄文
     * dede 德文
     * frfr 法文
     * enmy 马来语
     */
    private String veLanguage;

    public String getVeLanguage() {
        return veLanguage;
    }

    public void setVeLanguage(String veLanguage) {
        this.veLanguage = veLanguage;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
}
