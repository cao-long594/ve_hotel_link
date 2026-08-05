package cn.vetech.center.hotel.link.client.gys.cps.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author gaojin
 */
public class VeCdsHotelDetailVO {
    /**
     * 酒店房源商映射
     */
    @ApiModelProperty(value = "酒店房源商映射", dataType = "list")
    private List<VeHotelMapVO> maps;
    /**
     * 酒店ID
     */
    @ApiModelProperty(value = "酒店ID", dataType = "string")
    private String jdid;
    /**
     * 酒店初始来源
     */
    @ApiModelProperty(value = "酒店初始来源", dataType = "string")
    private String csly;
    /**
     * 中文名称
     */
    @ApiModelProperty(value = "中文名称", dataType = "string")
    private String zwmc;
    /**
     * 英文名称
     */
    @ApiModelProperty(value = "英文名称", dataType = "string")
    private String ywmc;
    /**
     * 星级
     */
    @ApiModelProperty(value = "星级", dataType = "string")
    private String xj;
    /**
     * 酒店推荐星级
     */
    @ApiModelProperty(value = "推荐星级", dataType = "string")
    private String tjxj;
    /**
     * 百度坐标
     */
    @ApiModelProperty(value = "百度坐标", dataType = "string")
    private String bd;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", dataType = "string")
    private String dz;
    /**
     * 主页
     */
    @ApiModelProperty(value = "主页", dataType = "string")
    private String zy;
    /**
     * 固定电话
     */
    @ApiModelProperty(value = "固定电话", dataType = "string")
    private String gddh;
    /**
     * 传真号码
     */
    @ApiModelProperty(value = "传真号码", dataType = "string")
    private String czhm;
    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌", dataType = "string")
    private String pp;
    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌名称", dataType = "string")
    private String ppmc;
    /**
     * 最低价
     */
    @ApiModelProperty(value = "最低价", dataType = "string")
    private String zdj;
    /**
     * 所在城市ID
     */
    @ApiModelProperty(value = "所在城市ID", dataType = "string")
    private String szcs;
    /**
     * 酒店所在城市名称
     */
    @ApiModelProperty(value = "所在城市名称", dataType = "string")
    private String szcsmc;
    /**
     * 所在行政区ID
     */
    @ApiModelProperty(value = "所在行政区ID", dataType = "string")
    private String xzq;
    /**
     * 所在行政区名称
     */
    @ApiModelProperty(value = "所在行政区名称", dataType = "string")
    private String xzqmc;
    /**
     * 地标查询时和地标店的距离
     */
    @ApiModelProperty(value = "地标查询时和地标店的距离", dataType = "string")
    private String distance;
    /**
     * 评分投诉率
     */
    @ApiModelProperty(value = "评分投诉率", dataType = "string")
    private String pftsl;
    /**
     * 评分采购率
     */
    @ApiModelProperty(value = "评分采购率", dataType = "string")
    private String pfcgl;
    /**
     * 评分确认率
     */
    @ApiModelProperty(value = "评分确认率", dataType = "string")
    private String pfqrl;
    /**
     * 评分总评分
     */
    @ApiModelProperty(value = "评分总评分", dataType = "string")
    private String pfzpf;
    /**
     * 基础设施ID
     */
    @ApiModelProperty(value = "基础设施ID", dataType = "string")
    private List<String> jcsslb;
    /**
     * 休闲设施ID
     */
    @ApiModelProperty(value = "休闲设施ID", dataType = "string")
    private List<String> xxsslb;
    /**
     * 房型设施ID
     */
    @ApiModelProperty(value = "房型设施ID", dataType = "string")
    private List<String> fxsslb;
    /**
     * 服务设施ID
     */
    @ApiModelProperty(value = "服务设施ID", dataType = "string")
    private List<String> fwsslb;
    /**
     * 酒店主题类别ID
     */
    @ApiModelProperty(value = "酒店主题类别ID", dataType = "string")
    private List<String> jdztlb;

    /**
     * 酒店简介
     */
    @ApiModelProperty(value = "酒店简介", dataType = "string")
    private String jj;

    /**
     * 周边交通
     */
    @ApiModelProperty(value = "周边交通", dataType = "string")
    private String zbjt;

    /**
     * 特别提示
     */
    @ApiModelProperty(value = "特别提示", dataType = "string")
    private String tbts;

    /**
     * 基础设施名称
     */
    @ApiModelProperty(value = "基础设施名称", dataType = "string")
    private List<String> jcssmclb;
    /**
     * 休闲设施名称
     */
    @ApiModelProperty(value = "休闲设施名称", dataType = "string")
    private List<String> xxssmclb;
    /**
     * 房型设施名称
     */
    @ApiModelProperty(value = "房型设施名称", dataType = "string")
    private List<String> fxssmclb;
    /**
     * 服务设施名称
     */
    @ApiModelProperty(value = "服务设施名称", dataType = "string")
    private List<String> fwssmclb;
    /**
     * 酒店主题类别名称
     */
    @ApiModelProperty(value = "酒店主题类别名称", dataType = "string")
    private List<String> jdztmclb;

    /**
     * 精选推广
     */
    private String jxtg;
    /**
     * 推荐推广
     */
    private String tjtg;
    /**
     *
     */
    private String rmtg;
    /**
     *
     */
    private String cxtg;
    /**
     *
     */
    private String zttg;
    /**
     * 酒店状态
     */
    private String zt;
    /**
     * 是否有餐厅：1有餐厅，2无餐厅 0或空代表不确定是否有餐厅
     * 解析酒店基础设施获得
     */
    private String sfyct;
    /**
     * 协议酒店集合
     */
    private List<String> xyjdlist;

    /**
     * 装修时间
     */
    @ApiModelProperty(value = "装修时间", dataType = "string")
    private String zxsj;

    /**
     * 开业时间
     */
    @ApiModelProperty(value = "开业时间", dataType = "string")
    private String kysj;

    /**
     * 客房数量
     */
    @ApiModelProperty(value = "客房数量", dataType = "string")
    private String kfsl;

    /**
     * 标签列表
     */
    @ApiModelProperty(value = "标签列表", dataType = "string")
    private List<String> jdbqlb;

    /**
     * 自签酒店列表
     */
    private List<String> zqjdlist;

    /**
     * 协议类型  12：单体协议；13集团协议
     */
    private String protocolType;

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public List<String> getZqjdlist() {
        return zqjdlist;
    }

    public void setZqjdlist(List<String> zqjdlist) {
        this.zqjdlist = zqjdlist;
    }

    public List<String> getJdbqlb() {
        return jdbqlb;
    }

    public void setJdbqlb(List<String> jdbqlb) {
        this.jdbqlb = jdbqlb;
    }


    public String getZxsj() {
        return zxsj;
    }

    public void setZxsj(String zxsj) {
        this.zxsj = zxsj;
    }

    public String getKfsl() {
        return kfsl;
    }

    public void setKfsl(String kfsl) {
        this.kfsl = kfsl;
    }

    public String getKysj() {
        return kysj;
    }

    public void setKysj(String kysj) {
        this.kysj = kysj;
    }

    public List<String> getXyjdlist() {
        return xyjdlist;
    }

    public void setXyjdlist(List<String> xyjdlist) {
        this.xyjdlist = xyjdlist;
    }

    public String getJj() {
        return jj;
    }

    public void setJj(S
   