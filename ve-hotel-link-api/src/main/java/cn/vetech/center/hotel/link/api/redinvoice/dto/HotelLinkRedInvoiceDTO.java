package cn.vetech.center.hotel.link.api.redinvoice.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2025/3/25 13:47
 */
public class HotelLinkRedInvoiceDTO extends LinkHotelDTO {


    private List<RedInvoiceInfo> redInvoiceList;

    public List<RedInvoiceInfo> getRedInvoiceList() {
        return redInvoiceList;
    }

    public void setRedInvoiceList(List<RedInvoiceInfo> redInvoiceList) {
        this.redInvoiceList = redInvoiceList;
    }
}
