package cn.vetech.center.hotel.link.api.orderlist.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.PageVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2021/7/5 19:48
 */
public class LinkHotelOrderListVO extends LinkHotelVO {
    /**
     * 分页对象
     */
    @ApiModelProperty(value = "分页返回参数", dataType = "PageVO")
    private PageVO pageVO = new PageVO();
    /**
     * 符合订单总数
     */
    private String count;
    /**
     * 订单集合
     */
    private List<Order> orders;

    public PageVO getPageVO() {
        return pageVO;
    }

    public void setPageVO(PageVO pageVO) {
        this.pageVO = pageVO;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
