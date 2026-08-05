package cn.vetech.center.hotel.link.supply.service.distribute.invoice;

import cn.vetech.center.hotel.link.api.orderbook.dto.LinkHotelOrderBookDTO;
import cn.vetech.center.hotel.link.supply.base.invoice.IInvoiceService;
import cn.vetech.charge.base.feign.invoice.dto.InvoiceInfoSearchDTO;
import cn.vetech.charge.base.feign.invoice.vo.InvoiceInfoSearchVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author vetech
 */
@Service
public class InvoiceDistributeService {
    /**
     *
     */
    private static final Logger LOGGER= LoggerFactory.getLogger(InvoiceDistributeService.class);

    @Autowired
    private Map<String, IInvoiceService> invoiceServiceMap;
    /**
     * 发票信息查询
     * @return 1
     */
    public InvoiceInfoSearchVO invoiceInfoSearch(LinkHotelOrderBookDTO dto){
        InvoiceInfoSearchDTO searchDTO=new InvoiceInfoSearchDTO();
        searchDTO.setZgs(dto.getCompid());
        searchDTO.setQybh(dto.getHyid());
        searchDTO.setYgid(dto.getClkid());
        searchDTO.setType("2");
        searchDTO.setFrgsdm(dto.getYdFrgs());
        String serviceName=dto.getPt()+IInvoiceService.NAME;
        IInvoiceService invoiceService=invoiceServiceMap.get(serviceName);
        if(invoiceService==null){
            LOGGER.info("{}发票服务未获取到",serviceName);
            return null;
        }
        InvoiceInfoSearchVO searchVO=invoiceService.invoiceInfoSearch(searchDTO);
        return searchVO;
    }

}
