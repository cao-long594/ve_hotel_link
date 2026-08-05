package cn.vetech.center.hotel.link.client.gys.cps.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author lixuan
 */
public class VeCdsIHotelDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 房源商映射
     */
    @ApiModelProperty(value = "房源商映射", dataType = "list")
    private List<VeHotelMapVO> maps;
    /**
     * 酒店ID
     */
    @ApiModelProperty(value = "酒店ID", dataType = "string")
    private String jdid;
    /**
     * 数字，3：已上架；4：已下架；6已删除，只有已审核已上架且有价格计划的酒店，ASMS查询时，才可搜到，缺一不可
     */
    @ApiModelProperty(value = "酒店状态", dataType = "string")
    private String zt;
    /**
     * 中文，有的酒店同名，一般括号里面加地址用以区分，所以可有（），不能有乱码
     */
    @ApiModelProperty(value = "酒店中文", dataType = "string")
    private String zwmc;
    /**
     * 英文，数字，可以有英文逗号，不能有乱码
     */
    @ApiModelProperty(value = "酒店英文", dataType = "string")
    private String ywmc;
    /**
     * 数字0,1,2,3,4,5，0表示无
     */
    @ApiModelProperty(value = "星级", dataType = "string")
    private String xj;
    /**
     * 推荐星级  数字0,1,2,3,4,5，0表示无
     */
    @ApiModelProperty(value = "推荐星级", dataType = "string")
    private String tjxj;
    /**
     * 数字，无品牌则存0，如：ID为93，表示城市便捷
     */
    @ApiModelProperty(value = "酒店品牌", dataType = "string")
    private String pp;
    /**
     * 品牌名称
     */
    @ApiModelProperty(value = "品牌名称", dataType = "string")
    private String ppmc;
    /**
     * 地址，为空则异常，需要手工处理，不能有乱码
     */
    @ApiModelProperty(value = "中文地址", dataType = "string")
    private String zwdz;
    /**
     * 酒店英文地址
     */
    @ApiModelProperty(value = "英文地址", dataType = "string")
    private String ywdz;
    /**
     * 酒店类别(1国内/2港澳台/3国际,默认3)
     */
    @ApiModelProperty(value = "酒店类别", dataType = "string")
    private String gngjlb;
    /**
     * 所在国家，数字
     */
    @ApiModelProperty(value = "所在国家", dataType = "string")
private String szgj;
    /**
     * 所在国家中文名称
     */
    @ApiModelProperty(value = "所在国家中文名称", dataType = "string")
    private String szgjzwmc;
    /**
     * 所在国家英文名称
     */
    @ApiModelProperty(value = "所在国家英文名称", dataType = "string")
    private String szgjywmc;
    /**
     * 所在省份
     */
    @ApiModelProperty(value = "所在省份", dataType = "string")
    private String szsf;
    /**
     * 所在省份中文名称
     */
    @ApiModelProperty(value = "所在省份中文名称", dataType = "string")
    private String szsfzwmc;
    /**
     * 所在省份英文名称
     */
    @ApiModelProperty(value = "所在省份英文名称", dataType = "string")
    private String szsfywmc;
    /**
     * 所在城市
     */
    @ApiModelProperty(value = "所在城市", dataType = "string")
    private String szcs;
    /**
     * 所在城市中文名称
     */
    @ApiModelProperty(value = "所在城市中文名称", dataType = "string")
    private String szcszwmc;
    /**
     * 所在城市英文名称
     */
    @ApiModelProperty(value = "所在城市英文名称", dataType = "string")
    private String szcsywmc;
    /**
     * 商业区ID
     */
    @ApiModelProperty(value = "商业区ID", dataType = "string")
    private String syq;
    /**
     * 商业区中文名称
     */
    @ApiModelProperty(value = "商业区中文名称", dataType = "string")
    private String syqzwmc;
    /**
     * 商业区英文名称
     */
    @ApiModelProperty(value = "商业区英文名称", dataType = "string")
    private String syqywmc;
    /**
     * 行政区ID
     */
    @ApiModelProperty(value = "行政区ID", dataType = "string")
    private String xzq;
    /**
     * 行政区中文名称
     */
    @ApiModelProperty(value = "行政区中文名称", dataType = "string")
    private String xzqzwmc;
    /**
     * 行政区英文名称
     */
    @ApiModelProperty(value = "行政区英文名称", dataType = "string")
    private String xzqywmc;
    /**
     * 区号-号码，手机号码，区号连着号码，座机-分机，如：027-59599999
     */
    @ApiModelProperty(value = "电话号码", dataType = "string")
    private String gddh;
    /**
     * 传真号码
     */
    @ApiModelProperty(value = "传真号码", dataType = "string")
    private String czhm;
    /**
     * 酒店主图,url，如：http://pavo.elongstatic.com/i/API350_350/f5b08ed938d524efb4aa277fa797d89f.jpg
     */
    @ApiModelProperty(value = "酒店主图", dataType = "string")
    private String zy;
    /**
     * 开业时间,年-月，如2017-06，2017年6月， 异常： 1900-01，9999-12
     */
    @ApiModelProperty(value = "开业时间", dataType = "string")
    private String kysj;
    /**
     * 装修时间,年-月，如2017-06，2017年6月， 异常： 1900-01，9999-12，处理成无
     */
    @ApiModelProperty(value = "装修时间", dataType = "string")
    private String zxsj;
    /**
     * 特别提示，文字，不能有乱码
     */
    @ApiModelProperty(value = "特别提示", dataType = "string")
    private String tbts;
    /**
     * 酒店简介中文，不能有乱码
     */
    @ApiModelProperty(value = "酒店简介中文", dataType = "string")
    private String zwjj;
    /**
     * 酒店简介英文
     */
    @ApiModelProperty(value = "酒店简介英文", dataType = "string")
    private String ywjj;
    /**
     * 最低价   数字，0或无表示没有获取到最低价
     */
    @ApiModelProperty(value = "最低价", dataType = "string")
    private String zdj;
    /**
     * 基础设施列表，数字，用英文逗号隔开
     */
    @ApiModelProperty(value = "基础设施列表", dataType = "string")
    private List<String> jcsslb;
    /**
     * 房型设施列表，数字，用英文逗号隔开
     */
    @ApiModelProperty(value = "房型设施列表", dataType = "string")
    private List<String> fxsslb;
    /**
     * 服务设施列表，数字，用英文逗号隔开
     */
    @ApiModelProperty(value = "服务设施列表", dataType = "string")
    private List<String> fwsslb;
    /**
     * 休闲设施列表，数字，用英文逗号隔开
     */
    @ApiModelProperty(value = "休闲设施列表", dataType = "string")
    private List<String> xxsslb;
    /**
     * 酒店主题列表，数字，用英文逗号隔开
     */
    @ApiModelProperty(value = "酒店主题列表", dataType = "string")
    private List<String> jdztlb;
    /**
     * 城市推广——精选
     */
    @ApiModelProperty(value = "城市推广——精选", dataType = "string")
    private String jxtg;
    /**
     * 城市推广——推荐
     */
    @ApiModelProperty(value = "城市推广——推荐", dataType = "string")
    private String tjtg;
    /**
     * 城市推广——热门
     */
    @ApiModelProperty(value = "城市推广——热门", dataType = "string")
    private String rmtg;
    /**
     * 城市推广——促销
     */
    @ApiModelProperty(value = "城市推广——促销", dataType = "string")
    private String cxtg;
    /**
     * 城市推广——主推
     */
    @ApiModelProperty(value = "城市推广——主推", dataType = "string")
    private String zttg;
    /**
     * 百度坐标，维度,经度
     */
    @ApiModelProperty(value = "百度坐标", dataType = "string")
    private String bd;
    /**
     * 谷歌坐标，维度,经度
     */
    @ApiModelProperty(value = "谷歌坐标", dataType = "string")
    private String gg;
    /**
     * 用户投诉率
     */
    @ApiModelProperty(value = "用户投诉率", dataType = "string")
    private String pftsl;
    /**
     * 预订成功率
     */
    @ApiModelProperty(value = "预订成功率", dataType = "string")
    private String pfcgl;
    /**
     * 及时确认率
     */
    @ApiModelProperty(value = "及时确认率", dataType = "string")
    private String pfqrl;
    /**
     * 酒店服务总评分，满分为5分，可以带一位小数，如3.6分，当是整数时，前台页面显示3.0这样的数字
     */
    @ApiModelProperty(value = "酒店服务总评分", dataType = "string")
    private String pfzpf;
    /**
     * 邮箱地址,123@qq.com
     */
    @ApiModelProperty(value = "邮箱地址", dataType = "string")
    private String email;
    /**
     * 基础设施英文名称，多个用英文逗号分隔
     */
    @ApiModelProperty(value = "基础设施英文名称", dataType = "string")
    private List<String> jcssywm;
    /**
     * 基础设施中文名称，多个用英文逗号分隔
     */
    @ApiModelProperty(value = "基础设施中文名称", dataType = "string")
    private List<String> jcsszwm;
    /**
     * 房型设施英文名称，多个用英文逗号分隔
     */
    @ApiModelProperty(value = "房型设施英文名称", dataType = "string")
    private List<String> fxssywm;
    /**
     * 房型设施中文名称，多个用英文逗号分隔
     */
    @ApiModelProperty(value = "房型设施中文名称", dataType = "string")
    private List<String> fxsszwm;
    /**
     * 服务设施英文名称，多个用英文逗号分隔
     */
    @ApiModelProperty(value = "服务设施英文名称", dataType = "string")
    private List<String> fwssywm;
    /**
     * 服务设施中文名称，多个用中文逗号分隔
     */
    @ApiModelProperty(value = "服务设施中文名称", dataType = "string")
    private List<String> fwsszwm;
    /**
     * 休闲设施英文名称，多个用英文逗号分隔
     */
    @ApiModelProperty(value = "休闲设施英文名称", dataType = "string")
    private List<String> xxssywm;
    /**
     * 休闲设施中文名称，多个用英文逗号分隔
     */
    @ApiModelProperty(value = "休闲设施中文名称", dataType = "string")
    private List<String> xxsszwm;
    /**
     * 酒店主题英文名称，多个用英文逗号分隔
     */
    @ApiModelProperty(value = "酒店主题英文名称", dataType = "string")
    private List<String> jdztywm;
    /**
     * 酒店主题中文名称，多个用英文逗号分隔
     */
    @ApiModelProperty(value = "酒店主题中文名称", dataType = "string")
    private List<String> jdztzwm;
    /**
     * 儿童政策中文
     */
    @ApiModelProperty(value = "儿童政策中文", dataType = "string")
    private String etzczw;
    /**
     * 儿童政策英文
     */
    @ApiModelProperty(value = "儿童政策英文", dataType = "string")
    private String etzcyw;
    /**
     * 入住时间
     */
    @ApiModelProperty(value = "入住时间", dataType = "string")
    private String rzsj;
    /**
     * 退房时间
     */
    @ApiModelProperty(value = "退房时间", dataType = "string")
    private String tfsj;
//    /**
//     * 艺龙酒店ID
//     */
//    private String elong;
//    /**
//     * 携程酒店ID
//     */
//    private String xcw;
//    /**
//     * 好巧酒店ID
//     */
//    private String hqw;
//    /**
//     * 道旅网酒店ID
//     */
//    private String szdl;
//    /**
//     * 深捷旅酒店ID
//     */
//    private String jltour;
    /**
     * 周边交通
     */
    @ApiModelProperty(value = "周边交通", dataType = "string")
    private String zbjt;
    /**
     * 地标查询时和地标店的距离
     */
    @ApiModelProperty(value = "地标查询时和地标店的距离", dataType = "string")
    private String distance;

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

    public List<VeHotelMapVO> getMaps() {
        return maps;
    }

    public void setMaps(List<VeHotelMapVO> maps) {
        this.maps = maps;
    }

    public String getJdid() {
        return jdid;
    }

    public void setJdid(String jdid) {
        this.jdid = jdid;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }

    public String getYwmc() {
        return ywmc;
    }

    public void setYwmc(String ywmc) {
        this.ywmc = ywmc;
    }

    public String getXj() {
        return xj;
    }

    public void setXj(String xj) {
        this.xj = xj;
    }

    public String getTjxj() {
        return tjxj;
    }

    public void setTjxj(String tjxj) {
        this.tjxj = tjxj;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getZwdz() {
        return zwdz;
    }

    public void setZwdz(String zwdz) {
        this.zwdz = zwdz;
    }

    public String getYwdz() {
        return ywdz;
    }

    public void setYwdz(String ywdz) {
        this.ywdz = ywdz;
    }

    public String getGngjlb() {
        return gngjlb;
    }

    