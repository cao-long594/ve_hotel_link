package cn.vetech.center.hotel.link.api.orderbook.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 发票信息
 *
 * @author gaojin
 */
public class BookInvoice implements Serializable {
    private static final long serialVersionUID = 1L;

    /*************************对接泰坦云开票接口，新增字段********************************/
    /**
     *
     * 订单号
     */
    @ApiModelProperty(value = "订单号", dataType = "string")
    private String orderNo;
    /**
     *
     * 配送方式： 1.电子邮箱 2.邮寄到付 3.免费邮寄
     */
    @ApiModelProperty(value = "配送方式  1.电子邮箱 2.邮寄到付 3.免费邮寄", dataType = "string")
    private String psfs;
    /**
     * 发票抬头
     */
    @ApiModelProperty(value = "发票抬头", dataType = "string")
    private String psFptt;
    /**
     * 纳税人识别号
     */
    @ApiModelProperty(value = "纳税人识别号", dataType = "string")
    private String psNsrsbh;
    /**
     * 发票类型：1增值税专票、2增值税普票
     */
    @ApiModelProperty(value = "发票类型：1增值税专票、2增值税普票", dataType = "string")
    private String psFplx;
    /**
     * 收件人姓名
     */
    @ApiModelProperty(value = "收件人姓名", dataType = "string")
    private String psUserName;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", dataType = "string")
    private String psEmail;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", dataType = "string")
    private String psPhone;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", dataType = "string")
    private String psAddress;
    /**
     * 注册地址
     */
    @ApiModelProperty(value = "注册地址", dataType = "string")
    private String companyAddress;
    /**
     * 注册电话
     */
    @ApiModelProperty(value = "注册电话", dataType = "string")
    private String companyPhone;
    /**
     * 开户银行
     */
    @ApiModelProperty(value = "开户银行", dataType = "string")
    private String companyBankName;
    /**
     * 开户银行卡号
     */
    @ApiModelProperty(value = "开户银行卡号", dataType = "string")
    private String companyBankNo;
    /**
     * 发票金额
     */
    @ApiModelProperty(value = "发票金额", dataType = "string")
    private String psFpje;
    /**
     * 发票类别：例如: 1.纸质、2.电子
     */
    @ApiModelProperty(value = "发票类别：例如: 1.纸质、2.电子", dataType = "string")
    private String psFplb;
    /*************************对接泰坦云开票接口，新增字段********************************/

    /***********************对接标准接口，新增字段begin ********************************/
    /**
     * 是否发送邮件 0否 1是
     */
    private String sffsyj;
    /**
     * 开票类型 0个人开票 1企业开票
     */
    private String kplx;

    public String getSffsyj() {
        return sffsyj;
    }

    /*********************对接标准接口，end ********************************/
