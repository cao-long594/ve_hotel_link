package cn.vetech.center.hotel.link.api.hotelgetjdfx.vo;

import java.util.List;

/**
 * @author gaojin
 */
public class RoomMap {
    /**
     * 房型id
     */
    private String fyid;
    /**
     * 房型名称
     */
    private String fxmc;
    /**
     * 房源商的酒店id
     */
    private String fyjdid;
    /**
     * 本地房型id
     */
    private String fxid;
    /**
     * 房源商的房型id
     */
    private String fyfxid;
    /**
     * 房源房型名称
     */
    private String fyfxmc;

    /**
     * 房型描述;文本描述
     */
    private String fxms;
    /**
     * 房型设施
     */
    private String fxss;
    /**
     * 床型;床型：1*1.8,2*1.5
     */
    private String cx;
    /**
     * 楼层;楼层：6-10
     */
    private String lc;
    /**
     * 面积
     */
    private String mj;
    /**
     * 备注,预订特别提示
     */
    private String ydtbts;
    /**
     * 入住最大数;推荐入住人数
     */
    private String tjrzrs;

    /**
     * 是否允许加床 0不可加床 1可加床
     */
    private String yxjc;
    /**
     * 加床数量
     */
    private String jcsl;
    /**
     * 加床尺寸
     */
    private String jccc;
    /**
     * 宽带
     */
    private String wifi;
    /**
     * 窗户类型
     */
    private String chlx;
    /**
     * 窗户 0无窗 1有窗 2部分有窗
     */
    private String ch;
    /**
     * 是否无烟 0不可 1可吸烟
     */
    private String sfwy;
    /**
     * 入住人国籍
     */
    private String rzrgj;

    /**
     * 加床费用
     */
    private String jcfy;

    /**
     * 房间数量
     */
    private String fjsl;

    /**
     * 儿童推荐入住人数
     */
    private String ettjrzrs;
    /**
     * 儿童最大年龄
     */
    private String etzdnl;
    /**
     * 儿童最小年龄
     */
    private String etzxnl;
    /**
     * 房型图片list
     */
    private List<String> images;

    public String getFxmc() {
        return fxmc;
    }

    public void setFxmc(String fxmc) {
        this.fxmc = fxmc;
    }

    public String getFyid() {
        return fyid;
    }

