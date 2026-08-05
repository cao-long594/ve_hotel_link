package cn.vetech.center.hotel.link.api.tfxbj.cgscxjd;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author :ZhangYu
 * @since : 2019/10/23
 * @Description: 同步询价单
 */
@XmlRootElement(name="request")
public class HotelLinkCgscxjdDTO extends LinkHotelDTO {
    /**
     * 询价单id
     */
    private String asmsxjdid;
    /**
     * 询价单类型：0手工单 1在线预订
     */
    private String xjdlx;
    /**
     * 询价单标题
     */
    private String xjdbt;
    /**
     * 报价截止时间
     */
    private String bjjzsj;
    /**
     * 国内国际:0国际 1国内
     */
    private String gngj;
    /**
     * 国家编号
     */
    private String gjbh;
    /**
     * 酒店ID
     */
    private String jdid;
    /**
     * 酒店名称
     */
    private String jdmc;
    /**
     * 酒店所在城市ID
     */
    private String szcs;
    /**
     * 城市名称
     */
    private String szcsmc;
    /**
     * 酒店电话
     */
    private String jddh;
    /**
     * 酒店地址
     */
    private String jddz;
    /**
     * 联系人
     */
    private String lxr;
    /**
     * 联系人手机
     */
    private String lxrsj;
    /**
     * 联系人电话
     */
    private String lxrdh;
    /**
     * 联系人邮箱
     */
    private String lxryx;
    /**
     * 联系人传真
     */
    private String czhm;
    /**
     * 单位名称
     */
    private String dwmc;
    /**
     * 备注
     */
    private String bzbz;
    /**
     * 采购销售价总价，在线预订的询价单会有值
     */
    private String cgxszj;
    /**
     * 商户编号
     */
    private List<HotelFxxx> fxList;
    /**
     * 询价单类别
     */
    private String xjdlb;
    /**
     * 会议人数
     */
    private Integer hyrs;
    /**
     * 会议时间始
     */
    private String hysjs;
    /**
     * 会议时间止
     */
    private String hysjz;
    /**
     * 总预算金额
     */
    private Double zys;
    /**
     * 会议需求
     */
    private String hyxq;
    /**
     * 询价单附件
     */
    private String xjdfj;
    /**
     * 询价单附件
     */
    private List<HotelFj> fjList;
    /**
     * 是否需报价
     */
    private String sfxbj;
    /**
     * 活动类型
     */
    private String hdlx;