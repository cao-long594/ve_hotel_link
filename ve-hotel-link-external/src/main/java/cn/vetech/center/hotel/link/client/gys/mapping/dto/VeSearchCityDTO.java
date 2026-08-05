package cn.vetech.center.hotel.link.client.gys.mapping.dto;

import java.io.Serializable;

/**
 * @author huchaochao
 * 2020/11/2 21:52
 * @Title: SearchDTO
 * @Description: 城市查找DTO
 */
public class VeSearchCityDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 房源简称
     */
    private String fyjc;
    /**
     * 对应类型：1省份,2城市，3.行政区，4.PP品牌，5.设施，6.主题
     */
    private String dylx;
    /**
     * 外部对应
     */
    private String wbdycode;
    /**
     * 外部对应名称
     */
    private String wbdymc;
    /**
     * 外部上级编号
     */
    private String wbsjbh;

    public String getFyjc() {
        return fyjc;
    }

    public void setFyjc(String fyjc) {
        this.fyjc = fyjc;
    }

    public String getDylx() {
        return dylx;
    }

    public void setDylx(String dylx) {
        this.dylx = dylx;
    }

    public String getWbdycode() {
        return wbdycode;
    }

    public void setWbdycode(String wbdycode) {
        this.wbdycode = wbdycode;
    }

    public String getWbdymc() {
        return wbdymc;
    }

    public void setWbdymc(String wbdymc) {
        this.wbdymc = wbdymc;
    }

    public String getWbsjbh() {
        return wbsjbh;
    }

    public void setWbsjbh(String wbsjbh) {
        this.wbsjbh = wbsjbh;
    }
}
