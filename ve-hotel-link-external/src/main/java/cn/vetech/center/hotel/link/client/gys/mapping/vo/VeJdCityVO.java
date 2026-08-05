package cn.vetech.center.hotel.link.client.gys.mapping.vo;

import java.io.Serializable;

/**
 * @author huchaochao
 * 2020/11/14 16:00
 * @Title: JdCityVO
 * @Description: 酒店城市vo
 */
public class VeJdCityVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;

    /**
     * 对应类型，1省份,2城市，3.行政区
     */
    private String dylx;

    /**
     * 本地对应编号
     */
    private String localdy;

    /**
     * 本地对应名称
     */
    private String localmc;

    /**
     * 外部对应的编号
     */
    private String wbdycode;

    /**
     * 外部对应的名称
     */
    private String wbdymc;

    /**
     *
     */
    private String cjDatetime;

    /**
     *
     */
    private String xgDatetime;

    /**
     * 本地上级编号，对应类型（dylx）为3行政区 存储行政区的上级城市编号
     */
    private String bdsjbh;

    /**
     * 本地上级名称，对应类型（dylx）为3行政区 存储行政区的上级城市名称
     */
    private String bdsjmc;

    /**
     * 外部上级编号，对应类型（dylx）为3行政区 存储行政区的上级城市编号
     */
    private String wbsjbh;

    /**
     * 外部上级名称，对应类型（dylx）为3行政区 存储行政区的上级城市名称
     */
    private String wbsjmc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDylx() {
        return dylx;
    }

    public void setDylx(String dylx) {
        this.dylx = dylx;
    }

    public String getLocaldy() {
        return localdy;
    }

    public void setLocaldy(String localdy) {
        this.localdy = localdy;
    }

    public String getLocalmc() {
        return localmc;
    }

    public void setLocalmc(String localmc) {
        this.localmc = localmc;
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

    public void setWbdy