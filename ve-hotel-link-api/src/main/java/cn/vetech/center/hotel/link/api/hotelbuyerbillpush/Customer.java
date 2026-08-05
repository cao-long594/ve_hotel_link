package cn.vetech.center.hotel.link.api.hotelbuyerbillpush;

import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 入住人
 * @author 7761
 */
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    /**
     * 入住人姓名
     */
    private String rzrxm;
    /**
     * 入住人ID
     */
    private String rzrid;
    /**
     * 入住人工号
     */
    private String rzrgh;
    /**
     * 保障级别
     */
    private String fwbzjb;
    /**
     * 保障级别名称
     */
    private String fwbzjbmc;
    /**
     * 部门ID
     */
    private String bmid;
    /**
     * 部门编号
     */
    private String bmbh;
    /**
     * 部门名称
     */
    private String bmmc;
    /**
     * 申请单号
     */
    private String ccsqd;
    /**
     * 入住日期（用于拼房变更）
     */
    private Date rzrq;
    /**
     * 离店日期（用于拼房变更）
     */
    private Date ldrq;
    /**
     * 拼房标记 ，1为拼房
     */
    private String pfbj;
    /**
     * 分摊费用金额
     */
    private BigDecimal fyje;
    /**
     * 分摊销售价
     */
    private BigDecimal xsj;
    /**
     * 个人差旅标准价
     */
    private BigDecimal grclbzj;
    /**
     * 房间差旅标准价
     */
    private BigDecimal clbzj;
    /**
     * 房间分摊差旅标准价
     */
    private BigDecimal ftclbzj;
    /**
     * 是否违背
     */
    private String sfwb;
    /**
     * 超标金额
     */
    private BigDecimal cbje;
    /**
     * 节约归己金额
     */
    private BigDecimal jygjje;
    /**
     * 退单手续费
     */
    private BigDecimal tpf;
    /**
     * 服务商服务费
     */
    @XmlElement(name = "fws_fwf")
    private BigDecimal fwsFwf;
    /**
     * 服务商服务费类型，5按供应商
  @XmlElement(name = "fws_fwflx")
    private String fwsFwflx;
    /**
     * 业务类型ID
     */
    private String ywlxid;
    /**
     * 业务类型名称
     */
    private String ywlxmc;
    /**
     * 服务商编号
     */
    private String fwfsbh;
    /**
     * 服务商名称
     */
    private String fwfsmc;

    /**
     * 房间序号
     */
    @ApiModelProperty(value = "序号", dataType = "string")
    private String fjxh;
    /**
     * 支付信息List，上级节点Room
     */
    @XmlElementWrapper(name="paymentList")
    @XmlElement(name="payment")
    private List<Payment> paymentList;
    /**
     * 成本对象List，上级节点Room
     */
    @XmlElementWrapper(name="costList")
    @XmlElement(name="cost")
    private List<Cost> costList;