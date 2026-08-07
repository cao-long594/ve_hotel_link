package cn.vetech.center.hotel.link.util;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookCustomer;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookOrderRoom;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.Objects;

/**
 * @author chengwanshan
 * @since 2021/12/9 10:15
 */
public class HotelUtils {
    /**
     * 处理成人数
     *
     * @param orderRooms orderRooms
     * @return int
     */
    public static int convertAdultNum(List<BookOrderRoom> orderRooms) {
        // 成人数必须大于0，默认1，（请求参数中有时adult=0，造成下单失败，这里给默认值1）
        int adultNum = 0;
        // 优先取入住人节点
        if (CollectionUtils.isNotEmpty(orderRooms)) {
            for (BookOrderRoom bookOrderRoom : orderRooms) {
                List<BookCustomer> customers = bookOrderRoom.getCustomers();
                if (CollectionUtils.isNotEmpty(customers)) {
                    adultNum += customers.size();
                }
            }
        }
        return adultNum;
    }

    /**
     * 校验酒店公共请求参数
     *
     * @param config 配置信息
     * @param dto    标准请求DTO
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> valCommonData(Object config, LinkHotelDTO dto) {
        if (Objects.isNull(config)) {
            return ImmutablePair.of(false, "获取配置为空");
        }
        if (Objects.isNull(dto)) {
            return ImmutablePair.of(false, "请求dto为空");
        }
        return ImmutablePair.of(true, "请求参数校验通过");
    }

}
