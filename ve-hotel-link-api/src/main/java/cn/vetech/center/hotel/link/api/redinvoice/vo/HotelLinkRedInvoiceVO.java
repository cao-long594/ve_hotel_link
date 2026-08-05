package cn.vetech.center.hotel.link.api.redinvoice.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2025/3/25 13:47
 */
public class HotelLinkRedInvoiceVO extends LinkHotelVO {

    /**
     * 红冲发票list
     */
    private List<RedInvoiceDetail> redInvoiceVOList;

    public List<RedInvoiceDetail> getRedInvoiceVOList() {
        return redInvoiceVOList;
    }

    public void setRedInvoiceVOList(List<RedInvoiceDetail> redInvoiceVOList) {
        this.redInvoiceVOList = redInvoiceVOList;
    }
}
