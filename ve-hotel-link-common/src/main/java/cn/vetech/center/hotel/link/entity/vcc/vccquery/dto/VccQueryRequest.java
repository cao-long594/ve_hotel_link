package cn.vetech.center.hotel.link.entity.vcc.vccquery.dto;

import cn.vetech.center.hotel.link.entity.vcc.VccBaseRequest;

/**
 * @author xiaotengyu
 * @since 2021/9/27 14:05
 */
public class VccQueryRequest extends VccBaseRequest {
    /**
     * 商户编号   String(10) 是
     */
    private String merchantNo;
    /**
     * 产品编码       String(20) 是 固 定 值  VCC20CARD
     */
    private String productCode;
    /**
     * 商户请求号  String(50) 否 和cardOrderNo 至少填一个
     */
    private String merTradeNo;
    /**
     * 卡唯一识别号     String(30) 否 和 merTradeNo至少填一个
     */
    private String cardOrderNo;


    /**
     * 查询开始日期
     */
    private String startDate;

    /**
     * 查询结束日期
     */
    private String endDate;
    /**
     * 分页页码
     * 默认 1
     */
    private Integer start;
    /**
     * 查询记录数
     * 默认 100
     * 最大 100
     */
    private Integer limit;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String getMerchantNo() {
        return merchantNo;
    }

    @Override
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    @Override
    public String getProductCode() {
        return productCode;
    }

    @Override
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getMerTradeNo() {
        return merTradeNo;
    }

    public void setMerTradeNo(String merTradeNo) {
        this.merTradeNo = merTradeNo;
    }

    public String getCardOrderNo() {
        return cardOrderNo;
    }

    public void setCardOrderNo(String cardOrderNo) {
        this.cardOrderNo = cardOrderNo;
    }

    public String getSta