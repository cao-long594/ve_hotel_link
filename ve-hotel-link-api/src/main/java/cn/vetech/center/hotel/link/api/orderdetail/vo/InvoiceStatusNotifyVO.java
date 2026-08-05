package cn.vetech.center.hotel.link.api.orderdetail.vo;

import java.math.BigDecimal;

/**
 * 发票状态改变通知request
 * @author meiqiangbo
 * @version 2018/8/26
 */
public class InvoiceStatusNotifyVO {

    /**
     * 采购发票id
     */
    private String cgfpid;

    /**
     * 订单编号
     */
    private String ddbh;

    /**
     * 产品编号
     */
    private String cpbh;

    /**
     * 发票状态
     */
    private String fpzt;

    /**
     * 发票地址
     */
    private String fpwj;

    /**
     * cps发票记录id
     */
    private String cpsfpid;

    /**
     * 开票时间
     */
    private String kpsj;

    /**
     * 发票代码
     */
    private String invoiceCode;
    /**
     * 发票号码
     */
    private String invoiceNo;

    /**
     * 发票图片地址
     */
    private String fptpdz;

    /**
     * 客户订单号
     */
    private String khddh;

    /**
     * 操作类型,0接口开票,1手工开票,2服务单代开
     */
    private String czlx;

    /**
     * 发票金额
     */
    private String fpje;

    /**
     * 纳税人识别号
     */
    private String nsrsbh;

    /**
     * 申请时间
     */
    private String sqsj;

    /**
     * 发票抬头
     */
    private String fptt;

    /**
     * 注册地址，专票必填
     */
    private String zcdz;

    /**
     * 注册电话，专票必填
     */
    private String zcdh;

    /**
     * 开户银行，专票必填
     */
    private String khyh;

    /**
     * 银行账号，专票必填
     */
    private String yhzh;

    /**
     * 发票备注：例如入住日期：2018-8-21 离店日期：2018-8-23 1间房/3间夜
     */
    private String fpbz;

    /**
     * 发票内容：例如旅游服务-代订房费
     */
    private String fpnr;

    /**
     * 发票类型：1增值税专票、2增值税普票
     */
    private String fplx;

    /**
     * 单位：例如：酒店业务单位 默认 间夜（各业务线自己赋值）
     */
    private String fpdw;

    /**
     * 数量，指发票上的货物数量，例如3间夜（各业务线自己赋值）
     */
    private Long fphwsl;

    /**
     * 税率：酒店页面3%（各业务线自己赋值）
     */
    private BigDecimal fpsl;

    /**
     * 规格型号：可空（各业务线自己赋值）
     */
    private String fpggxh;

    /**
     * 入住人姓名
     */
    private String rzrxm;
    /**
     * 常旅客id
     */
    private String clkid;
    /**
     * 发票类别
     */
    private String fplb;
    /**
     * 开票方式
     */
    private String kpfs;
    /**
     * 员工id
     */
    private String ygid;
    /**
     * 加密文件字符串
     */
    private String encodeFileStr;