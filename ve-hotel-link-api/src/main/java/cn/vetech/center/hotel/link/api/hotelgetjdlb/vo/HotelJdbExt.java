package cn.vetech.center.hotel.link.api.hotelgetjdlb.vo;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Date;
import java.util.List;

/**
 * @author xingyanyan
 * @since 2019/7/4 15:09
 */
public class HotelJdbExt extends HotelJdb {
    /**
     *
     */
    private String linenum;
    /**
     * 是否会议酒店 ，1是
     */
    private String sfhyjd;
    /**
     * 是否是宿舍
     */
    private String sfss;
    /**
     * 会议信息备注
     */
    private String hyxxbz;
    /**
     * 酒店类别1,与数据字典对应
     */
    private String jdlb1;
    /**
     * 酒店分类2,与数据字典对应
     */
    private String jdlb2;
    /**
     * 酒店分类3,与数据字典对应
     */
    private String jdlb3;
    /**
     * 审核人
     */
    private String shr;
    /**
     * 审核状态
     */
    private String shzt;
    /**
     * 审核时间:存审核时间,自动匹配进入的数据,存匹配进入的时间
     */
    private Date shsj;
    /**
     * 最后修改者
     */
    private String zhxgz;
    /**
     * 最后修改时间
     */
    private Date zhxgsj;
    /**
     * 新奥联通使用字段
     */
    private String sfxyjd;
    /**
     * 协议折扣，作为客户自己签的协议
     */
    private String xyzk;
    /**
     *
     */
    private String xyh;
    /**
     * 客户评分
     */
    private String customerScore;
    /**
     * 适用客户折扣，客户做为别人的适用客户，所享受的折扣
     */
    private List<String> sykhzkList;
    /**
     * 适用客户折扣对应的协议号
     */
    private List<String> sykhxyhList;
    /**
     *
     */
    private String xyid;
    /**
     * 发票类型
     */
    private String fplx;
    /**
     * 发票费率
     */
    private String fpfl;
    /**
     * 传真
     */
    private String fax;
    /**
     * 是否已签约   自签酒店用
     */
    private String sfqy;
    /**
     * 授权类型（1表示协议，2表示自有，3表示既是协议也是自有）
     */
    private String sqlx;
    /**
     * 是否含有wifi 0否 1是
     */
    private String isWifi;
    /**
     * 是否上网服务
     */
    private String isSwfw;
    /**
          * 是否接机服务
     */
    private String isJcfw;
    /**
     * 是否停车服务
     */
    private String isTcfw;
    /**
     * 是否餐厅服务
     */
    private String isCtfw;
    /**
     * 是否健身服务
     */
    private String isJsfw;
    /**
     * 是否过滤酒店 0不过滤  1过滤
     */
    private String sfgl;
    /**
     * 酒店主题名称
     */
    private List<String> jdztmc;
    /**
     * 房型设施
     */
    private List<String> fxss;
    /**
     * 服务列表
     */
    private List<String> fwlb;
    /**
     * 基础设施
     */
    private List<String> jcss;
    /**
     * 休闲设施
     */
    private List<String> xxss;
    /**
     * 客户自定义类型
     */
    private String customerType;
    /**
     * 导航信息
     */
    private List<NavigationInfoVO> navInfoList;