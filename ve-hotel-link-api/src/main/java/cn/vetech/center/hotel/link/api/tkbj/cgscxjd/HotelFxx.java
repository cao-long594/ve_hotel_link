package cn.vetech.center.hotel.link.api.tfxbj.cgscxjd;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author wanggongchuang
 * @version 1.0
 */
public class HotelFxxx {
    /**
     * 产品类型：0房间、1会议室
     */
    private String cplx;
    /**
     * 房型id
     */
    private String fxid;
    /**
     * 房型名称
     */
    private String fxmc;
    /**
     * 房间数，正整数大于0
     */
    private String fjs;
    /**
     * 入住日期 2018-07-26
     */
    private String rzrq;
    /**
     * 离店日期  2018-07-26
     */
    private String ldrq;
    /**
     * 间夜数
     */
    private String jys;
    /**
     * 床型要求：大床、双床
     */
    private String cxsm;
    /**
     * 早餐要求：0无早，1单早，2双早
     */
    private String zc;
    /**
     * 其他房间特殊要求
     */
    private String qtyq;
    /**
     * 价格计划名称
     */
    private String jgjhmc;
    /**
     * 特殊预订说明
     */
    private String ysydsm;
    /**
     * 采购取消规则
     */
    private String cgqxgz;
    /**
     * 价格计划ID,在线预订的询价单会有值
     */
    private String jgjhid;
    /**
     * 采购销售价均价，每间夜单价，在线预订的询价单会有值
     */
    private String cgxsdj;
    /**
     * 采购销售价总价，在线预订的询价单会有值
     */
    private String cgxszj;

    /**
     * 入住人姓名
     */
    private String rzrxm;
    /**
     * 入住人ID
     */
    private String cxrygid;
    /**
     * 同住人姓名，多个英文逗号分隔
     */
    private String tzrxm;
    /**
     * 同住人ID，多个英文逗号分隔,没有ID 的是外部人员填写空
     */
    private String tzrygid;
    /**
     * 线下价格早餐0无早，1单早，2双早
     */
    private String zcslxxd;
    /**
     * 线下日均价，元
     */
    private Double mrjgxxd;
    /**
     * 线下单总金额，元
     */
    private Double ddzjxxd;
    /**
     * 线下价格来源，例如门店预订
     */
    private String jglyxxd;
    /**
     * 供应商名称
     */
    private String gysmc;

    public String getCplx() {
        return cplx;
    }

    public void setCplx(String cplx) {
        this.cplx = cplx;
    }

    public String getFxid() {
     