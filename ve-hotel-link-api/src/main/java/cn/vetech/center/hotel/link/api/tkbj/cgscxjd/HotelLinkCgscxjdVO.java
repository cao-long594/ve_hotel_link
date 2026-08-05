package cn.vetech.center.hotel.link.api.tfxbj.cgscxjd;


import cn.vetech.center.hotel.link.api.LinkHotelVO;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author :ZhangYu
 * @Date: 2019/10/23
 * @Description:
 */
@XmlRootElement(name="response")
public class HotelLinkCgscxjdVO extends LinkHotelVO {
    /**
     * 询价单编号
     */
    private String xjdid;
    /**
     * 询价单状态：0.待报价
     1.已报价待确认
     2.已成交
     3.已取消
     4.未报价自动取消
     */
    private String xjdzt;

    public String getXjdid() {
        return xjdid;
    }

    public void setXjdid(String xjdid) {
        this.xjdid = xjdid;
    }

    public String getXjdzt() {
        return xjdzt;
    }

    public void setXjdzt(String xjdzt) {
        this.xjdzt = xjdzt;
    }
}
