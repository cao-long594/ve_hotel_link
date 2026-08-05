package cn.vetech.center.hotel.link.api.ddvalidate.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;

/**
 * @author : xiangmeng
 * @since : 9:03 2021/7/28
 */
public class LinkHotelDdValidateVO  extends LinkHotelVO {
    /**
     * 校验错误信息
     */
    private String jyErrorMsg;

    public String getJyErrorMsg() {
        return jyErrorMsg;
    }

    public void setJyErrorMsg(String jyErrorMsg) {
        this.jyErrorMsg = jyErrorMsg;
    }
}
