package cn.vetech.center.hotel.link.api.hotelbuyerbillpush;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * 成本对象
 * @author 7761
 */
@XmlRootElement(name = "cost")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cost {
    /**
     * 月结金额
     */
    private BigDecimal yjje;
    /**
     * 员工自付金额
     */
    private BigDecimal ygzfje;
    /**
     * 福利积分支付金额
     */
    private BigDecimal fljfzfje;
    /**
     * 福利额度支付金额
     */
    private BigDecimal fledzfje;
    /**
     * 分摊费用金额
     */
    private BigDecimal fyje;
    /**
     * 分摊销售价
     */
    private BigDecimal xsj;
    /**
     * 分摊比例
     */
    private BigDecimal ftbl;
    /**
     * 内部订单ID*
     */
    private String nbddid;
    /**
     * 内部订单单号*
     */
    private String nbddh;
    /**
     * 内部订单名称*
     */
    private String nbddmc;
    /**
     * 利润中心ID*
     */
    private String lrzxid;
    /**
     * 利润中心编号*
     */
    private String lrzxbh;
    /**
     * 利润中心名称*
     */
    private String lrzxmc;
    /**
     * WBS编号*
     */
    private String wbsbh;
    /**
     * WBS名称*
     */
    private String wbsmc;
    /**
     * WBS外部编号*
     */
    private String wbswbbh;
    /**
     * 项目单号*
     */
    private String xmdh;
    /**
     * 项目名称*
     */
    private String xmmc;
    /**
     * 成本中心单号
     */
    private String cbzxdh;
    /**
     * 成本中心名称
     */
    private String cbzxmc;
    /**
     * 法人公司代码
     */
    private String frgsdm;
    /**
     * 法人公司名称
     */
    private String frgsmc;
    /**
     * 核算数据类别1*
     */
    private String hssjlb1;
    /**
     * 核算数据类别2*
     */
    private String hssjlb2;
    /**
     * 核算数据类别3*
     */
    private String hssjlb3;
    /**
     * 核算数据类别4*
     */
    private String hssjlb4;
    /**
     * 核算数据类别5*
     */
    private String hssjlb5;
    /**
     * 核算数据ID1*
     */
    private String hssjid1;
    /**
     * 核算数据ID2*
     */
    private String hssjid2;
    /**
     * 核算数据ID3*
     */
    private String hssjid3;
    /**
     * 核算数据ID4*
     */
    private String hssjid4;
    /**
     * 核算数据ID5*
     */
    private String hssjid5;
    /**
     * 核算数据编号1*
     */
    private String hssjbh1;
    /**
     * 核算数据编号2*
     */
    private String hssjbh2;
    /**
     * 核算数据编号3*
     */
    private String hssjbh3;
    /**
     * 核算数据编号4*
     */
    private String hssjbh4;
    /**
     * 核算数据编号5*
     */
    private String hssjbh5;
    /**
     * 核算数据名称1*
     */
    private String hssjmc1;
    /**
     * 核算数据名称2*
     */
    private String hssjmc2;
    /**
     * 核算数据名称3*
     */
    private String hssjmc3;
    /**
     * 核算数据名称4*
     */
    private String hssjmc4;
    /**
     * 核算数据名称5*
     */
    private String hssjmc5;