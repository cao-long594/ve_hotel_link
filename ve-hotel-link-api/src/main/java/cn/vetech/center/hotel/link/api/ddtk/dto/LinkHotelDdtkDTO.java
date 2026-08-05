package cn.vetech.center.hotel.link.api.ddtk.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import java.util.List;

/**
 * @author lipeng Created by vetech on 2019/10/23.
 */
public class LinkHotelDdtkDTO extends LinkHotelDTO {

    /**
     * 	String	是	订单号，供应商订单号（r如果是退单，则会把关联正常单的外部供应商订单号传入）
     */
    private String orderId;
    /**
     * 	String		平台订单号商旅系统订单号
     */
    private String ddbh;
    /**
     * 	String	是	订单类型：1酒店正常单  2酒店退单
     */
    private String ddlx;
    /**
     * 平台退单对应的供应商的退单号
     */
    private String tkdh;
    /**
     * 	Decimal	是	退款金额
     */
    private String tkje;
    /**
     * 	String		退款方式:1公司月结2一网通3微信4银联5支付宝
     */
    private String tkfs;
    /**
     * 	String		退款流水号(20190909新增)
     */
    private String tklsh;
    /**
     * String	否   混合退款标识：
     * 空或0表示否，1表示是
     * 当订单存在多种支付方式退款时，此标识为1。
     * 混合退款时，各种支付方式退款金额以tkList为准。
     */
    private String hhtkbs;
    /**
     * 是	退款集合
     */
    private List<LinkHotelDdtkList> tkList;

    public String getHhtkbs() {
        return hhtkbs;
    }

    public void setHhtkbs(String hhtkbs) {
        this.hhtkbs = hhtkbs;
    }

    public List<LinkHotelDdtkList> getTkList() {
        return tkList;
    }

    public void setTkList(List<LinkHotelDdtkList> tkList) {
        this.tkList = tkList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }

    public String getDdlx() {
        return ddlx;
    }

    public void setDdlx(String ddlx) {
        this.ddlx = ddlx;
    }

    public String getTkje() {
        return tkje;
    }

    public void setTkje(String tkje) {
        this.tkje = tkje;
    }

    public String getTkdh() {
        return