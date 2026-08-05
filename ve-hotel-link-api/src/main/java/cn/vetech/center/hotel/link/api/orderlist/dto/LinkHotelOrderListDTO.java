package cn.vetech.center.hotel.link.api.orderlist.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.PageDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2021/7/5 19:33
 */
public class LinkHotelOrderListDTO extends LinkHotelDTO {
    /**
     * 分页请求参数
     */
    @ApiModelProperty(value = "分页请求参数", dataType = "BasePage")
    private PageDTO pageDTO;
    /**
     * 下单日期始   格式yyyy-MM-dd
     */
    private String xdrqs;
    /**
     * 下单日期止   格式yyyy-MM-dd
     */
    private String xdrqz;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 页码 1开始
     */
    private String pageIndex;
    /**
     * 每页记录数（1-20） 默认10
     */
    private String pageSize;
    /**
     * 本地订单号集合
     */
    private List<String> localOrderIdList;
    /**
     * 供应订单号集合
     */
    private List<String> gysOrderIdList;

    public PageDTO getPageDTO() {
        return pageDTO;
    }

    public void setPageDTO(PageDTO pageDTO) {
        this.pageDTO = pageDTO;
    }

    public String getXdrqs() {
        return xdrqs;
    }

    public void setXdrqs(String xdrqs) {
        this.xdrqs = xdrqs;
    }

    public String getXdrqz() {
        return xdrqz;
    }

    public void setXdrqz(String xdrqz) {
        this.xdrqz = xdrqz;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getLocalOrderIdList() {
        return localOrderIdList;
    }
