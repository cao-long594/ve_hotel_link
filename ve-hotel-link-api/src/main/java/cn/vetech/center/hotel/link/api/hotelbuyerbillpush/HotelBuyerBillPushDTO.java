package cn.vetech.center.hotel.link.api.hotelbuyerbillpush;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * 酒店采购商账单明细推送服务请求对象
 *
 * @author 7761
 */
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelBuyerBillPushDTO extends LinkHotelDTO {
    /**
     * 供应商编号
     */
    private String gysBh;
    /**
     * 订单类型，03001正常单，03002退单
     */
    private String ddlx;
    /**
     * 总公司
     */
    private String zgs;
    /**
     * 企业编号
     */
    private String qybh;
    /**
     * 因公因私，1因公，2因私
     */
    private String clyy;
    /**
     * 订单来源
     */
    private String ddly;
    /**
     * 费控单号，正常单或退单单号
     */
    private String ywdh;
    /**
     * 费控单号，正常单单号
     */
    private String oldywdh;
    /**
     * 供应订单编号
     */
    private String gyddbh;
    /**
     * 供应商发票类型,0普票，1专票
     */
    private String gysfplx;
    /**
     * 入住日期
     */
    private Date cfdatetime;
    /**
     * 离店日期
     */
    private Date dddatetime;
    /**
     * 是否超标，0否，1是
     */
    private String sfwb;
    /**
     * 预订时间
     */
    private Date dpdatetime;
    /**
     * 业务发生时间（数据更新时间）
     */
    private Date ywfssj;
    /**
     * 协议价格类型，0或空为非协议，1为接口协议，2为单体协议，3直连集团协议
     */
    private String xyjglx;
    /**
     * 接口协议类型，xyjglx=1时，12为接口托管单体，13为接口托管集团
     */
    private String jkxylx;

    /**
     * 费控系统客户状态枚举
     */
    private String khddzt;

    /**
     * 费控系统客户状态说明
     */
    private String khddztsm;

    /**
     * 费控系统供应商状态枚举
     */
    private String gyddzt;

    /**
     * 费控系统供应商状态说明
     */
    private String gyddztsm;

    /**
     * 订单平台 charge
     */
    private String pt;
    /**
     * 房间集合
     */
    @XmlElementWrapper(name = "roomList")
    @XmlElement(name = "room")
    private List<Room> roomList;
    /**
     * 客户支付状态
     */
    private String khzfzt;