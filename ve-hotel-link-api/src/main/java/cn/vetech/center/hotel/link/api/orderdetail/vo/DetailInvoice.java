package cn.vetech.center.hotel.link.api.orderdetail.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 发票信息详情
 *
 * @author gaojin
 */
public class DetailInvoice implements Serializable {
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
     * 配送方式  注意：此处注释仅供参考，如：1.电子邮箱 2.邮寄到付 3.免费邮寄
     */
    @ApiModelProperty(value = "配送方式  注意：此处注释仅供参考，如：1.电子邮箱 2.邮寄到付 3.免费邮寄", dataType = "string")
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
    /**
     * 发票 url
     */
    private List<String> invoiceUrlList;
    /*************************对接泰坦云开票接口，新增字段********************************/
    /**
     * 发票文件列表
     */
    private List<HotelOrderInvoiceInfo> invoiceInfoList;

    public List<HotelOrderInvoiceInfo> getInvoiceInfoList() {
        return invoiceInfoList;
    }

    public void setInvoiceInfoList(List<HotelOrderInvoiceInfo> invoiceInfoList) {
        this.invoiceInfoList = invoiceInfoList;
    }
