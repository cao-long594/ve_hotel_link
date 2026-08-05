package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.hotelbuyerbillpush.HotelBuyerBillPushDTO;
import cn.vetech.center.hotel.link.api.hotelbuyerbillpush.HotelBuyerBillPushVO;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 6010
 * @since 2023-05-05 13:56
 */
@Service
public class HotelBuyerBillPushService {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(HotelBuyerBillPushService.class);

    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configService;
    /**
     * 接口分发服务
     */
    @Autowired
    private SupplyDistributeService distributeService;

    /**
     * 酒店采购商账单明细推送服务
     *
     * @param dto 1
     * @return 1
     * @throws SupplyConnectException
     */
    public HotelBuyerBillPushVO hotelBuyerBillPush(@RequestBody HotelBuyerBillPushDTO dto) throws SupplyConnectException {
        //获取参数配置
        dto.setZhmc(dto.getGysBh());
        configService.setConfig(dto);


        //默认过滤参数
        Set<String> filterFields = Sets.newHashSet("nbddid", "nbddh", "nbddmc", "lrzxid", "lrzxbh", "lrzxmc", "wbsbh", "wbsmc", "wbswbbh",
                "xmdh", "xmmc", "hssjlb1", "hssjlb2", "hssjlb3", "hssjlb4", "hssjlb5", "hssjid1", "hssjid2", "hssjid3", "hssjid4", "hssjid5",
                   "hssjbh1", "hssjbh2", "hssjbh3", "hssjbh4", "hssjbh5", "hssjmc1", "hssjmc2", "hssjmc3", "hssjmc4", "hssjmc5");
        //设置的不过滤参数
        Map<String, String> supplier = dto.getSupplier();
        if (!supplier.isEmpty() && supplier.containsKey("bmzdsc")) {
            String bmzdsc = supplier.get("bmzdsc");
            if (StringUtils.isNotBlank(bmzdsc)) {
                String[] split = bmzdsc.split("\\/");
                for (int i = 0; i < split.length; i++) {
                    filterFields.remove(split[i]);
                }
            }
        }
        //过滤参数
        filterDto(dto, filterFields);

        //分发给供应商
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        return supplyService.hotelBuyerBillPush(dto);
    }

    /**
     * 过滤对象中的值
     *
     * @param o            1
     * @param filterFields 1
     */
    private <T> void filterDto(T o, Set<String> filterFields) {
        if (o == null || filterFields.isEmpty()) {
            return;
        }
        try {
            Class clazz = o.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                String name = fields[j].getName();
                fields[j].setAccessible(true);
                if (filterFields.contains(name)) {
                    fields[j].set(o, null);
                    continue;
                }
                if (fields[j].getType().equals(List.class)) {
                    List list = (List) fields[j].get(o);
                    if (list == null || list.size() == 0) {
                        continue;
                    }
                    list.forEach(l -> filterDto(l, filterFields));
                } else if (fields[j].getType().equals(Map.class)) {
                    Map map = (Map) fields[j].get(o);
                    if (map.isEmpty()) {
                        continue;
                    }
                    for (Object value : map.values()) {
                        filterDto(value, filterFields);
                    }
                }

            }
        } catch (Exception e) {
            logger.error("异常",e);
        }
    }
}
