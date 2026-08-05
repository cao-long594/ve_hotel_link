package cn.vetech.center.hotel.link.elong.orderbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


/**
 * 发票信息
 *
 * @author gaojin
 */
public class ElongBookInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 发票类型
     * Paper-纸质发票，Electronic-电子发票
     */
    @JsonProperty("InvoiceType")
    private String invoiceType;
    /**
     * 抬头类型
     * Personally-个人，Enterprise- 企业，Government-政府机关行政单位，默认为企业；
     * Personally时，不需填写抬头Title（抬头默认为个人）和纳税人识别号/统一社会信用代码ITIN
     * Enterprise时，必须填写抬头Title和纳税人识别号/统一社会信用代码ITIN
     * Government时，必须填写抬头Title
     */
    @JsonProperty("TitleType")
    private String titleType;
    /**
     * 抬头
     */
    @JsonProperty("Title")
    private String title;
    /**
     * 纳税人识别号/统一社会信用代码
     * 字符长度是15、18或20位，是数字和字母的组合或纯数字
     */
    @JsonProperty("ITIN")
    private String itin;
    /**
     * 发票内容
     * 代订房费或代订住宿费
     */
    @JsonProperty("ItemName")
    private String itemName;
    /**
     * 金额
     */
    @JsonProperty("Amount")
    private String amount;
    /**
     * 收件人
     */
    @JsonProperty("Recipient")
    private ElongBookRecipient recipient;
    /**
     * 是否添加发票备注
     * true-在发票备注栏中添加酒店预订信息（酒店名称、入住日期、离店日期、房间数）
     * false-不添加，默认值
     */
    @JsonProperty("IsNeedRelationOrder")
    private ElongBookRecipient isNeedRelationOrder;

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItin() {
        retu