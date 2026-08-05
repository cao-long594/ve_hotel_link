package cn.vetech.center.hotel.link.client.gys.cps.dto;


import io.swagger.annotations.ApiModelProperty;

/**
 * @author lixuan
 */
public class VeCdsIHotelDetailDTO {
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
     * 语种  zhcn:简体中文;zhtw:繁体中文;enus:英文;dede:德文;frfr:德文;jajp:日文;kokr:韩文;ruru:俄文;enmy:马来语（英）;
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
