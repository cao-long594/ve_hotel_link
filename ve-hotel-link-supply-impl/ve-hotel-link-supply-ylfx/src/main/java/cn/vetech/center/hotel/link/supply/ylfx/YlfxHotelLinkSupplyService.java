package cn.vetech.center.hotel.link.supply.ylfx;

import cn.vetech.center.hotel.link.api.orderbook.dto.LinkHotelOrderBookDTO;
import cn.vetech.center.hotel.link.api.orderbook.vo.LinkHotelOrderBookVO;
import cn.vetech.center.hotel.link.api.ordercancel.dto.LinkHotelOrderCancelDTO;
import cn.vetech.center.hotel.link.api.ordercancel.vo.LinkHotelOrderCancelVO;
import cn.vetech.center.hotel.link.api.orderdetail.dto.LinkHotelOrderDetailDTO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.base.util.SupplierConfigUtils;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.orderbook.YlfxOrderBookService;
import cn.vetech.center.hotel.link.supply.ylfx.ordercancel.YlfxOrderCancelService;
import cn.vetech.center.hotel.link.supply.ylfx.orderdetail.YlfxOrderDetailService;
import cn.vetech.center.hotel.link.supply.ylfx.ratesearch.YlfxRateSearchService;
import cn.vetech.center.hotel.link.supply.ylfx.validate.YlfxValidateService;
import cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.YlfxV2RateSearchService;
import cn.vetech.center.hotel.link.supply.ylfx.v2.validate.YlfxV2ValidateService;
import cn.vetech.center.hotel.log.annotation.CommonLog;
import cn.vetech.center.hotel.log.annotation.Log;
import cn.vetech.charge.cpfl.DdlxEnum;
import cn.vetech.charge.cpfl.JklxEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 易旅分销
 *
 * @author 6161
 * @date 2024/07/18
 */
@Service
public class YlfxHotelLinkSupplyService implements IHotelLinkSupplyService {
    /**
     * 查询报价
     */
   @Autowired
    private YlfxRateSearchService rateSearchService;
    /**
     * 下单前验价
     */
    @Autowired
    private YlfxValidateService validateService;
    /**
     * V2 查询报价
     */
    @Autowired
    private YlfxV2RateSearchService v2RateSearchService;
    /**
     * V2 下单前验价
     */
    @Autowired
    private YlfxV2ValidateService v2ValidateService;
    /**
     * 下单
     */
    @Autowired
    private YlfxOrderBookService bookService;
    /**
     * 订单详情
     */
    @Autowired
    private YlfxOrderDetailService detailService;
    /**
     * 订单详情
     */
    @Autowired
    private YlfxOrderCancelService cancelService;

    @CommonLog(jkzh = "查询报价", jkmc = "RateSearch", ddlx = DdlxEnum.DDLX0300_0, jklx = JklxEnum.CX_1001)
    @Log(name = "查询报价")
    @Override
    public LinkHotelRateSearchVO rateSearch(LinkHotelRateSearchDTO dto) throws SupplyConnectException {
        YlfxConfig config = SupplierConfigUtils.parse(dto.getSupplier(), YlfxConfig.class);
        if ("v2".equals(config.getApiVersion())) {
            return v2RateSearchService.rateSearch(dto, config);
        }
        return rateSearchService.rateSearch(dto, config);
    }

    @Log(name = "验价")
    @Override
    public LinkHotelValidateVO validate(LinkHotelValidateDTO dto) throws SupplyConnectException {
        YlfxConfig config = SupplierConfigUtils.parse(dto.getSupplier(), YlfxConfig.class);
        if ("v2".equals(config.getApiVersion())) {
            return v2ValidateService.validate(dto, config);
        }
        return validateService.validate(dto, config);
    }

    @Log(name = "下单")
    @Override
    public LinkHotelOrderBookVO orderBook(LinkHotelOrderBookDTO dto) throws SupplyConnectException {
        YlfxConfig config = SupplierConfigUtils.parse(dto.getSupplier(), YlfxConfig.class);
        return bookService.orderBook(dto, config);
    }

    @Log(name = "订单详情")
    @Override
    public LinkHotelOrderDetailVO orderDetail(LinkHotelOrderDetailDTO dto) throws SupplyConnectException {
        YlfxConfig config = SupplierConfigUtils.parse(dto.getSupplier(), YlfxConfig.class);
        return detailService.orderDetail(dto, config);
    }

    @Log(name = "取消订单")
    @Override
    public LinkHotelOrderCancelVO orderCancel(LinkHotelOrderCancelDTO dto) throws SupplyConnectException {
        YlfxConfig config = SupplierConfigUtils.parse(dto.getSupplier(), YlfxConfig.class);
        return cancelService.orderCancel(dto, config);
    }
}
