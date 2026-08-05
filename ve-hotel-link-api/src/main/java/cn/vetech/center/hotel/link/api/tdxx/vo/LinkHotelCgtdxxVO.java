package cn.vetech.center.hotel.link.api.tdxx.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookZfxx;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * @author xingyanyan
 */
@XmlRootElement(name = "response")
public class LinkHotelCgtdxxVO extends LinkHotelVO {
    private String tdbh;    //退单编号
    /**
     * 平台退款单状态
     * T0 待审核;T1 已审核待退款;T2 已退款;T3 取消
     */
    @JsonProperty("ddzt")
    private String pttdzt;
    private String ddbh;    //原订单号
    private String szcs;    //所在城市
    private String cszwmc;  //城市名称
    private String jdid;    //酒店ID
    private String jdmc;    //酒店名称
    private String fxid;    //房型ID
    private String fxmc;    //房型名称
    private String cgshh;   //采购商编号
    private String sqr;     //申请人
    private Date sqsj;      //申请时间，日期格式：yyyy-MM-dd HH:mm:ss
    private String sqbz;    //申请备注
    private String qxgz;    //取消规则描述
    private String qxgzjsfs;//取消规则计算方式
    private int tjys;       //退间夜数
    private Double tjyff;   //退间夜房费
    private Date ptshsj;  //平台审核时间
    private String ptshsm;  //平台审核说明
    private Double ytje;   //应退金额   平台审核应退总金额
    private String tklsh;   //退款流水号
    private String tkfs;    //退款方式
    /**
     * 退款方式名称
     */
    private String tkfsmc;
    private String tkzh;    //退款账号
    private Date tksj;      //退款时间，日期格式：yyyy-MM-dd HH:mm:ss
    private Double tkje;    //实退金额
    private List<Tfxx> tfxxs;//退房信息
    /**
     *  V（1）是否符合取消规则枚举：1符合，2不符合，3其他
     */
    private String fhqxgz;
    /**
     * V（100）是否符合取消规则说明文本：符合、不符合、其他说明文本
     */
    private String fhqxgzsm;
    /**
     * 退款明细
     */
    private List<RefundPaymentInfo> refundPaymentInfoList;
    /**
     * 支付集合
     */
    private List<BookZfxx> zfList;
    /**
     * 退款操作类型，默认空/0=退单通知(退间夜退款)，1=仅退款通知(仅退款不退间夜)
  */
    private String tkczlx;
    /**
     * 关联供应商退款单号，tkczlx=1时必填，用于退单补退场景校验
     */
    private String oldtkdh;

    public String getTkczlx() {
        return tkczlx;
    }

    public void setTkczlx(String tkczlx) {
        this.tkczlx = tkczlx;
    }

    public String getOldtkdh() {
        return oldtkdh;
    }
