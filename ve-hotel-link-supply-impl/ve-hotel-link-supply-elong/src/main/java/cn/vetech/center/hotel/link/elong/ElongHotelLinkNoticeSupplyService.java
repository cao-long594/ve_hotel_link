package cn.vetech.center.hotel.link.elong;

import cn.vetech.center.hotel.link.api.notice.HotelNoticeCheckVO;
import cn.vetech.center.hotel.link.api.notice.HotelNoticeVO;
import cn.vetech.center.hotel.link.api.notice.LinkHotelNoticeDTO;
import cn.vetech.center.hotel.link.api.notice.LinkHotelOrderDetailNoticeDTO;
import cn.vetech.center.hotel.link.elong.notice.ElongNoticeOrderDetailService;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkNoticeSupplyService;
import cn.vetech.center.hotel.log.annotation.Log;
import cn.vetech.charge.cloud.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chengwanshan
 * @since 2021/7/16 12:24
 */
@Service
public class ElongHotelLinkNoticeSupplyService implements IHotelLinkNoticeSupplyService {
    /**
     * 订单详推送
     */
    @Autowired
    private ElongNoticeOrderDetailService noticeOrderDetailService;

    @Log(name="订单详通知-getOrderId")
    @Override
    public HotelNoticeVO getOrderId(LinkHotelNoticeDTO notice) throws SystemException {
        return noticeOrderDetailService.getOrderId(notice);
    }
    @Log(name="订单详通知-getOrderDetail")
    @Override
    public HotelNoticeCheckVO getOrderDetail(LinkHotelOrderDetailNoticeDTO detailNoticeDTO) throws SystemException {
        return noticeOrderDetailService.getOrderDetail(detailNoticeDTO);
    }
}
