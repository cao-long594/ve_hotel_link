package cn.vetech.center.hotel.link.client.gys.mapping.dto;

import java.io.Serializable;

/**
 * @author huchaochao
 * 2020/11/3 9:30
 * @Title: SearchDictDTO
 * @Description: 外部供应商数据字典
 */
public class VeSearchDictDTO implements Serializable {
    /**
     * 房源简称
     */
    private String fyjc;
    /**
     * 编号
     */
    private String bh;
    /**
     * 1.酒店 2.民宿
     */
    private String ywlx;
    /**
     * 类型
     */
    private String lx;
    /**
     * 上级编号
     */
    private String sjbh;
    /**
     * 外部编号
     */
    private String wbbh;

    public String getFyjc() {
        return fyjc;
    }

    public void setFyjc(String fyjc) {
        this.fyjc = fyjc;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
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

    public String getWbbh() {
        return wbbh;
    }

    public void setWbbh(String wbbh) {
        this.wbbh = wbbh;
    }
}
