package cn.vetech.center.hotel.link.api.ratesearch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 房型
 *
 * @author gaojin
 */
public class SearchRoom implements Serializable {
    /**
     * 该房型来源,房源商ID
     */
    @ApiModelProperty(value = "该房型来源,房源商ID", dataType = "string")
    private String bh;
    /**
     * 房源商名称 艺龙
     */
    @ApiModelProperty(value = "房源商编号 艺龙", dataType = "string")
    private String fymc;

    /**
     * 房源商英文名称
     */
    @ApiModelProperty(value = "房源商英文名称", dataType = "string")
    private String fyjc;
    /**
     * 房源商酒店ID
     */
    @ApiModelProperty(value = "房源商酒店ID", dataType = "string")
    private String hotelId;
    /**
     * 房源商房型ID
     */
    @ApiModelProperty(value = "房源商房型ID", dataType = "string")
    private String roomId;
    /**
     * 本地房型ID
     */
    @ApiModelProperty(value = "本地房型ID", dataType = "string")
    private String localRoomId;
    /**
     * 房型名称
     */
    @ApiModelProperty(value = "房型名称", dataType = "string")
    private String roomName;
    /**
     * 产品信息,价格计划
     */
    @ApiModelProperty(value = "产品信息,价格计划", dataType = "string")
    private List<SearchRatePlan> ratePlans;
    /**
     * 前台现付最低价 这个房型,首日,所有接口,对应的所有价格计划中 最低的价格
     */
    @ApiModelProperty(value = "前台现付最低价这个房型,首日,所有接口,对应的所有价格计划中最低的价格", dataType = "string")
    private double minPrice;
    /**
     * 门市价
     */
    @ApiModelProperty(value = "门市价", dataType = "string")
    private double listPrice;
    /**
     * 房型描述
     */
    @ApiModelProperty(value = "房型描述", dataType = "string")
    private String description;
    /**
     * 楼层
     */
    @ApiModelProperty(value = "楼层", dataType = "string")
    private String floor;
    /**
     * 面积
     */
    @ApiModelProperty(value = "面积", dataType = "string")
    private String roomArea;
    /**
     * 宽带
     */
    @ApiModelProperty(value = "宽带", dataType = "string")
    private String broadNet;
    /**
     * 床型
     */
    @ApiModelProperty(value = "床型", dataType = "string")
    private String bedType;
    /**
     * 床型描述
     */
    @ApiModelProperty(value = "床型描述", dataType = "string")
    private String bedDesc;
    /**
     * 房间最大入住人数
     */
    @ApiModelProperty(value = "房间最大入住人数", dataType = "string")
    private String capacity;

    /**
     * 是否cps前台展示过滤
     */
    @ApiModelProperty(value = "是否cps前台展示", dataType = "string")
    private String show = "0";
    /**
     * 是否显示(过滤/显示) 1 显示
     */
    @ApiModelProperty(value = "是否显示(过滤/显示)" ,dataType = "string")
    private String showFilterRp;
    /**
     * 房型原名称
     */
    private String oriName;
    /**
     * 房型格式化名
     */
    private String fmtName;
    /**
     * 预订特别提示
     */
    private String ydtbts;

    /**
     * 房型设施
     */
    private String fxss;
    /**
     * 允许加床 0不可加床；1可以加床
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String yxjc;
    /**
     * 加床数量
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String jcsl;
    /**
     * 加床尺寸
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String jccc;
    /**
     * 宽带 0 无法上网
     * 1无线上网
     * 2有线宽带
     * 3无线WIFI和有线宽带
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String wifi;
    /**
     * 窗户类型 0朝向走廊
     * 1装饰性假窗
     * 2天窗
     * 3窗户交小
     * 4窗外是墙体
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String chlx;
    /**
     * 是否有窗 0 无窗
     * 1有窗
     * 2部分有窗
     */
    @ApiModelProperty(value = "是否有窗",dataType = "string")
    private String ch;
    /**
     * 是否无烟 0不可吸烟
     * 1可以吸烟
     */
    @ApiModelProperty(value = "是否无烟",dataType = "string")
    private String sfwy;
    /**
     * 入住人国籍 0仅内宾1可接待外宾
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String rzrgj;
    /**
     * 加床费用
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String jcfy;
    /**
     * 房间数量
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String fjsl;
    /**
     * 儿童推荐入住人数
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String ettjrzrs;
    /**
     * 儿童最大年龄
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String etzdnl;
    /**
     * 儿童最小年龄
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String etzxnl;
    /**
     * 房型首图地址
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String stdz;
    /**
     * 房型是否映射
     */
    @ApiModelProperty(value = "",dataType = "string")
    private String fxsfys;
    /**
     * 房型图片list
     */
    @ApiModelProperty(value = "房型图片list",dataType = "string")
    private List<String> tps;

    /******************************相比cps新增字段**********************************/
    /**
     * 供应商平台 费控使用
     */
    private String gyspt;
    /**
     * 房型图片
     */
    @ApiModelProperty(value = "房型图片", dataType = "string")
    private String fxtp;
    /**
     *
     */
    private List<SearchTp> searchTps;

    /******************************相比cps新增字段**********************************/