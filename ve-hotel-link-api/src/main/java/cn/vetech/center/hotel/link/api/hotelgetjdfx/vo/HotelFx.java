package cn.vetech.center.hotel.link.api.hotelgetjdfx.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * @author gaojin
 */
public class HotelFx {
    /**
     * 主键ID
     */
    private String fxid;
    /**
     * 酒店ID
     */
    private String jdid;
    /**
     * 房型名称；去空格，多个连续空格只保留一个
     */
    private String fxmc;
    /**
     * 格式化房型名称，（注：费控会基于此字段进行名称匹配合并，谨慎赋值！！！）
     */
    private String roomNameFmt;
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
     *
     */
    private List<RoomMap> fxMaps;
    /**
     * 允许加床
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
     * 是否有窗
     */
    private String ch;
    /**
     * 是否无烟
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
    /**
     * 房源编号
     */
    private String fybh;

    @XmlElementWrapper(name = "fxMaps")
    @XmlElement(name = "fxMap")
    public List<RoomMap> getFxMaps() {
        return fxMaps;
    }
