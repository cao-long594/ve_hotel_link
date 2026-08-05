package cn.vetech.center.hotel.link.api.hotelgetcdsdata;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author lixuan
 * @since 2019/7/9.
 */
@XmlRootElement(name = "request")
public class HotelLinkGetCdsDataDTO extends LinkHotelDTO {

    /**
     * 请求的字符串，例如品牌模糊查询
     */
    private String reqData;
    /**
     * 所属集团
     */
    private String ssjt;

    /**
     * 查询的数据类别，默认或0查品牌 1查询集团
     */
    private String type="0";

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数", example = "10", dataType = "int")
    private Integer size ;
    /**
     * 当前多少页 从1开始
     */
    @ApiModelProperty(value = "当前多少页 从1开始", example = "1", dataType = "int")

    private Integer current ;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReqData() {
        return reqData;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }

    public String getSsjt() {
        return ssjt;
    }

    public void setSsjt(String ssjt) {
        this.ssjt = ssjt;
    }
}
