package cn.vetech.center.hotel.link.api.ratesearch.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 酒店link查询报价响应信息类
 *
 * @author gaojin
 */
@XmlRootElement(name = "response")
public class LinkHotelRateSearchVO extends LinkHotelVO {
    private static final long serialVersionUID = 1L;
    /**
     * 标记价格是否完成
     */
    private boolean sfwc;
    /**
     * 完成的房源编号 标记版本
     */
    private String fybhs;
    /**
     * 入住时间
     */
    @ApiModelProperty(value = "入住时间", dataType = "string")
    private String checkInDate;
    /**
     * 离店时间
     */
    @ApiModelProperty(value = "离店时间", dataType = "string")
    private String checkOutDate;
    /**
     * 胜意酒店ID
     */
    @ApiModelProperty(value = "胜意酒店ID", dataType = "string")
    private String localHotelId;
    /**
     * 酒店名称
     */
    @ApiModelProperty(value = "酒店名称", dataType = "string")
    private String hotelName;
    /**
     * 最低价格
     */
    @ApiModelProperty(value = "最低价格", dataType = "string")
    private String lowRate;
    /**
     * 房间节点
     */
    @ApiModelProperty(value = "房间节点", dataType = "string")
    private List<SearchRoom> rooms;
    /**
     * 返回的有价格的供应商编号 便于CPS获取房型映射关系  以及后续某个供应商价格计划超时的缓存是否覆盖考虑
     */
    @ApiModelProperty(value = "有价格的房源编号", dataType = "string")
    private List<String> yjggys;
    /**
     * 1协议
     */
    private String sfxyjd;
    /**
     * 耗时
     */
    private Long firReqCostMillis;
    /**
     * 价格来源于缓存标识
     */
    private boolean fromCacheFlag;

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) 