package cn.vetech.center.hotel.link.api.orderbook.dto;

import java.math.BigDecimal;

/**
 * @author xiaotengyu
 * @since 2023-02-13 17:35
 */
public class BookOrderRoomExt {

    /**
     * 房间序号
     */
    private Integer fjxh;

    /**
     * 入住人序号
     */
    private Integer rzrxh;

    /**
     * 入住人姓名
     */
    private String rzr;

    /**
     * 入住人员工id
     */
    private String rzrygid;

    /**
     * 入住人手机号
     */
    private String rzrsj;

    /**
     * 入住人证件号码
     */
    private String customerIdNo;

    /**
     * 入住人证件类型
     */
    private String customerIdType;

    /**
     * 服务保障级别
     */
    private String fwbzjb;

    /**
     * 服务保障标准
     */
    private String fwbzbz;

    /**
     * 服务保障名称
     */
    private String fwbzmc;

    /**
     * 服务保障图片地址
     */
    private String fwbzurl;

    /**
     * 法人公司ID
     */
    private String frgsid;

    /**
     * 法人公司编号
     */
    private String frgsbh;

    /**
     * 法人公司名称，入住人所属法人公司名称
     */
    private String frgsmc;

    /**
     * 结算单位
     */
    private String jsdwid;

    /**
     * 结算单位名称
     */
    private String jsdwmc;

    /**
     * 成本中心ID
     */
    private String cbzxid;

    /**
     * 成本中心编号
     */
    private String cbzxbh;

    /**
     * 成本中心名称
     */
    private String cbzxmc;

    /**
     * 利润中心编号
     */
    private String lrzxbh;

    /**
     * 利润中心名称
     */
    private String lrzxmc;

    /**
     * 项目编号
     */
    private String xmbh;

    /**
     * 出差申请单号
     */
    private String sqdh;

    /**
     * 服务商服务费
     */
    private BigDecimal fwfFwf;

    /**
     * 房费分摊金额
     */
    private BigDecimal shareCost;
    /**
     * 入住人手机号国际编码
     */
    private String rzrsjGjbm;
    /**
     * 入住人国籍
     */
    private String rzrgj;
    /**
     * 入住人性别
     */
    private String rzrxb;
    /**
     * 英文姓
     */
     private String lastname;
    /**
     * 英文名
     */
    private String firstname;
    /**
     * 入住人部门id
     */
    private String rzrbmid;
    /**
     * 入住人部门名称
     */
    private String rzrbmmc;
    /**
     * 入住人工号
     */
    private String rzrgh;

    public String getRzrgh() {
        return rzrgh;
    }

    public void setRzrgh(String rzrgh) {
        this.rzrgh = rzrgh;
    }

    public String getRzrbmid() {
        return rzrbmid;
    }

    public void setRzrbmid(String rzrbmid) {
        this.rzrbmid = rzrbmid;
    }

    public String getRzrbmmc() {
        return rzrbmmc;
    }

    public void setRzrbmmc(String rzrbmmc) {
        this.rzrbmmc = rzrbmmc;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRzrxb() {
        return rzrxb;
    }

    public void setRzrxb(String rzrxb) {
        this.rzrxb = rzrxb;
    }

    public String getRzrsjGjbm() {
        return rzrsjGjbm;
    }

    public void setRzrsjGjbm(String rzrsjGjbm) {
        this.rzrsjGjbm = rzrsjGjbm;
    }

    public Integer getFjxh() {
        return fjxh;
    }

    public void setFjxh(Integer fjxh) {
        this.fjxh = fjxh;
    }

    public Integer getRzrxh() {
        return rzrxh;
    }

    public void setRzrxh(Integer rzrxh) {
        this.rzrxh = rzrxh;
    }

    public String getRzr() {
        return rzr;
    }

    public void setRzr(String rzr) {
        this.rzr = rzr;
    }

    public String getRzrygid() {
        return rzrygid;
    }

    public void setRzrygid(String rzrygid) {
        this.rzrygid = rzrygid;
    }

    public String getRzrsj() {
        return rzrsj;
