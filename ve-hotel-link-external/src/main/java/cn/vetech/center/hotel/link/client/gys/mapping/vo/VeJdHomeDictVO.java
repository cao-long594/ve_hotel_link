package cn.vetech.center.hotel.link.client.gys.mapping.vo;

import java.io.Serializable;

/**
 * @author huchaochao
 * 2020/11/14 16:00
 * @Title: JdHomeDictVO
 * @Description: 供应商数据字典VO
 */
public class VeJdHomeDictVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    private String bh;

    /**
     * 1 酒店 2民宿
     */
    private Integer ywlx;

    /**
     * 类型
     */
    private String lx;

    /**
     * 上级编号
     */
    private String sjbh;

    /**
     * 层级
     */
    private Integer cj;

    /**
     * 名称
     */
    private String mc;

    /**
     * 描述
     */
    private String ms;

    /**
     * 房源商字典编号
     */
    private String wbbh;

    /**
     * 房源商字典名称
     */
    private String wbmc;

    /**
     * 房源商字典描述
     */
    private String wbms;

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public Integer getYwlx() {
        return ywlx;
    }

    public void setYwlx(Integer ywlx) {
        this.ywlx = ywlx;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getSjbh() {
        return sjbh;
    }

    public void setSjbh(String sjbh) {
        this.sjbh = sjbh;
    }

    public Integer getCj() {
        return cj;
    }

    public void setCj(Integer cj) {
        this.cj = cj;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getWbbh() {
        return wbbh;
    }

    public void setWbbh(String wbbh) {
        this.wbbh = wbbh;
    }

    public String getWbmc() {
        return wbmc;
    