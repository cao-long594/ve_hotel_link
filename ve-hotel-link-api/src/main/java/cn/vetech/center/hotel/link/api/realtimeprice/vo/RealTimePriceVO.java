package cn.vetech.center.hotel.link.api.realtimeprice.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;

import java.util.List;

/**
 * @author vetech
 * @since 2023/10/31
 */
public class RealTimePriceVO extends LinkHotelVO {

    /**
     * 实时计算最低价结果
     */
    private List<RealTimePriceInfoVO> realTimePriceInfoVOList;

    public List<RealTimePriceInfoVO> getRealTimePriceInfoVOList() {
        return realTimePriceInfoVOList;
    }

    public void setRealTimePriceInfoVOList(List<RealTimePriceInfoVO> realTimePriceInfoVOList) {
        this.realTimePriceInfoVOList = realTimePriceInfoVOList;
    }
}
