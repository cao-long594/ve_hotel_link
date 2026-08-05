package cn.vetech.center.hotel.link.supply.service.hotel.charge.vo;


import cn.vetech.charge.cloud.modules.utils.mapper.XmlMapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * @author gaojin
 */
public class HotelJdb {
    /**
     * 酒店id
     */
    private String jdid;
    /**
     * 房源商的酒店ID
     */
    private String hotelid;
    /**
     * 酒店实际来源1:中航信2:代理人3:ASMS4:艺龙5:携程6捷旅7假日
     */
    private String jdgygx;
    /**
     * 酒店资料维护的供应商id
     */
    private String jdcsly;
    /**
     * 状态 3：已上架 4：已下架 6已删除
     */
    private String zt;
    /**
     * 酒店查询次数
     */
    private String jdcxcs;
    /**
     * 酒店中文名称
     */
    private String jdzwmc;
    /**
     * 酒店英文名称
     */
    private String jdywmc;
    /**
     * 酒店星级
     */
    private String xj;
    /**
     * 酒店推荐星级
     */
    private String tjxj;
    /**
     * 是否推荐星级0否1是
     */
    private String istjxj;
    /**
     * 酒店品牌
     */
    private String jdpp;
    /**
     * 酒店地址
     */
    private String jddz;
    /**
     * 所在国家
     */
    private String szgj;
    /**
     * 所在省份
     */
    private String szsf;
    /**
     * 所在城市
     */
    private String szcs;
    /**
     * 行政区
     */
    private String xzq;
    /**
     * 商业区
     */
    private String syq;
    /**
     * 所在城市名称
     */
    private String szcsmc;
    /**
     * 所在行政区名称
     */
    private String xzqmc;
    /**
     * 所在商圈名称
     */
    private String syqmc;
    /**
     * 固定电话
     */
    private String gddh;
    /**
     * 传真号码
     */
    private String czhm;
    /**
     * 酒店主图
     */
    private String jdzy;
    /**
     * 最低价 num
     */
    private String zdj;
    /**
     * 艺龙房型对比 num
     */
    private String elfxbl;
    /**
     * 酒店开业时间
     */
    private String kysj;
    /**
     * 装修时间
     */
private String zxsj;
    /**
     * 酒店基础设施列表集合
     */
    private String jdjcsslbjh;
    /**
     * 酒店休闲设施列表集合
     */
    private String jdxxsslbjh;
    /**
     * 房型设施列表集合
     */
    private String fxsslbjh;
    /**
     * 酒店服务列表集合
     */
    private String jdfwlbjh;
    /**
     * 特别提示
     */
    private String tbts;
    /**
     * 酒店简介
     */
    private String jdjj;
    /**
     * 酒店主题
     */
    private String jdztlb;
    /**
     * 国内国际,1国内/2港澳/3国际
     */
    private String gngjlb;
    /**
     * 精选推广,0:否1:是
     */
    private String jxtg;
    /**
     * 推荐推广,0:否1:是
     */
    private String tjtg;
    /**
     * 热门推广,0:否1:是
     */
    private String rmtg;
    /**
     * 促销推广,0:否1:是
     */
    private String cxtg;
    /**
     * 主推推广,0:否1:是
     */
    private String zttg;
    /**
     * 周边交通
     */
    private String zbjt;
    /**
     * 百度经度
     */
    private String bdjd;
    /**
     * 百度纬度
     */
    private String bdwd;
    /**
     * google经度
     */
    private String ggjd;
    /**
     * google纬度
     */
    private String ggwd;
    /**
     *
     */
    private String jl;
    /**
     * 同城投诉率对比
     */
    private String tctsldb;
    /**
     * 同城成功率对比
     */
    private String tccgldb;
    /**
     * 同城确认率对比
     */
    private String tcqrldb;
    /**
     * 同城总评分对比
     */
    private String tczpfdb;
    /**
     * 投诉率
     */
    private String pftsl;
    /**
     * 成功率
     */
    private String pfcgl;
    /**
     * 确认率
     */
    private String pfqrl;
    /**
     * 总评分
     */
    private String pfzpf;
    /**
     *
     */
    private String bd;
    /**
     *
     */
    private String gg;
     /**
     * 酒店数量
     */
    private Integer jdsl;
    /**
     * 可售酒店数量
     */
    private Integer ksjdsl;
    /**
     * 可售计划数量
     */
    private Integer ksjhsl;
    /**
     * 平均售价
     */
    private Double pjsj;
    /**
     * 现付价格计划数量
     */
    private Integer xfjgjhsl;
    /**
     * 现付平均价
     */
    private Double xfpjj;
    /**
     * 预付价格计划数量
     */
    private Integer yfjgjhsl;
    /**
     * 预付平均价
     */
    private Double yfpjj;
    /**
     * 酒店品牌名称
     */
    private String jdppmc;

    /**
     * 基础设施名称
     */
    private List<String> jcssmclb;
    /**
     * 休闲设施名称
     */
    private List<String> xxssmclb;
    /**
     * 房型设施名称
     */
    private List<String> fxssmclb;
    /**
     * 服务设施名称
     */
    private List<String> fwssmclb;
    /**
     * 酒店主题类别名称
     */
    private List<String> jdztmclb;
    /**
     * 是否协议
     */
    private String sfxy;
    /**
     * 是否自供自采 0：否   1：是
     */
    private String sfzgzc;
    /**
     * 是否自签
     */
    private String sfcpszq;
    /**
     * 会议室设施
     */
    private String hysss;
    /**
     * 会场标签
     */
    private String hcbq;
    /**
     * 会议室数量
     */
    private String hyssl;
    /**
     * 标间数量
     */
    private String bjsl;
      /**
     * 单人间数量
     */
    private String drjsl;
    /**
     * 会议室列表
     */
    private List<HotelHysdxVO> hyjdlist;

    /**
     * 供应商映射关系list
     */
    private List<HotelGysdxVO> gyslist;

    /**
     * 是否满房 1 满房 0 未满房
     */
    private String sfmf;
    /**
     * 品牌名称
     */
    private String ppmc;

    /**
     * 是否有餐厅：1有餐厅，2无餐厅 0或空代表不确定是否有餐厅
     */
    private String sfyct;

    /**
     * 客房数量
     */
    private String kfsl;


    /**==========【酒店信息元素补充项目】新增字段==========**/
    /**
     * 酒店楼层高度
     */
    private String jdlcgd;
    /**
     * 酒店附近路口
     */
    private String jdfjlk;
    /**
     * 酒店英文地址
     */
    private String jdywdz;
    /**
     * 酒店邮政编码
     */
    private String jdyzbm;
    /**
     * 酒店英文简介
     */
    private String jdywjj;
    /**
     * 酒店电子邮箱，多个逗号分隔
     */
    private String jddzyx;
    /**
     * 酒店网站地址
     */
    private String jdwzdz;
    /**
     * 是否接待外宾 1 仅内宾  2 内宾和外宾
     */
    private String sfjdwb;
    /**
     * 资质证件 URL图片地址
     */
    private String zzzjdz;
    /**
     * 酒店集团编号
     */
    private String jdjtbh;
    /**
     * 酒店集团名称
     */
    private String jdjtmc;
    /**
     * 酒店集团简介
     */
    private String jdjtjj;
    /**
     * 酒店可支付的方式，信用卡的名称，多个用英文逗号分隔，例如  万事达信用卡，银联卡等
     */
    private String jdkyzffs;
    /**
     * 是否是申请单酒店 1 是 空 不是
     */
    private String sfsqdjd;

    /**
     * 标签列表
     */
    private String jdbqlb;

    public String getJdbqlb() {
        return jdbqlb;
    }

    public void setJdbqlb(String jdbqlb) {
        this.jdbqlb = jdbqlb;
    }

    public String getJdlcgd() {
        return jdlcgd;
    }

    public void setJdlcgd(String jdlcgd) {
        this.jdlcgd = jdlcgd;
    }

    public String getJdfjlk() {
        return jdfjlk;
    }

    public void setJdfjlk(String jdfjlk) {
        this.jdfjlk = jdfjlk;
    }

  public String getJdywdz() {
        return jdywdz;
    }

    public void setJdywdz(String jdywdz) {
        this.jdywdz = jdywdz;
    }

    public String getJdyzbm() {
        return jdyzbm;
    }

    public void setJdyzbm(String jdyzbm) {
        this.jdyzbm = jdyzbm;
    }

    public String getJdywjj() {
        return jdywjj;
    }

    public void setJdywjj(String jdywjj) {
        this.jdywjj = jdywjj;
    }

    public String getJddzyx() {
        return jddzyx;
    }

    public void setJddzyx(String jddzyx) {
        this.jddzyx = jddzyx;
    }

    public String getJdwzdz() {
        return jdwzdz;
    }

    public void setJdwzdz(String jdwzdz) {
        this.jdwzdz = jdwzdz;
    }

    public String getSfjdwb() {
        return sfjdwb;
    }

    public void setSfjdwb(String sfjdwb) {
        this.sfjdwb = sfjdwb;
    }

    public String getZzzjdz() {
        return zzzjdz;
    }

    public void setZzzjdz(String zzzjdz) {
        this.zzzjdz = zzzjdz;
    }

    public String getJdjtbh() {
        return jdjtbh;
    }

    public void setJdjtbh(String jdjtbh) {
        this.jdjtbh = jdjtbh;
    }

    public String getJdjtmc() {
        return jdjtmc;
    }

    public void setJdjtmc(String jdjtmc) {
        this.jdjtmc = jdjtmc;
    }

    public String getJdjtjj() {
        return jdjtjj;
    }

    public void setJdjtjj(String jdjtjj) {
        this.jdjtjj = jdjtjj;
    }

    public String getJdkyzffs() {
        return jdkyzffs;
    }

    public void setJdkyzffs(String jdkyzffs) {
        this.jdkyzffs = jdkyzffs;
    }
   /*==========【酒店信息元素补充项目】新增字段==========*/


    public String getKfsl() {
        return kfsl;
    }

    public void setKfsl(String kfsl) {
        this.kfsl = kfsl;
    }

    public String getSfmf() {
        return sfmf;
    }

    public void setSfmf(String sfmf) {
        this.sfmf = sfmf;
    }

    public List<HotelGysdxVO> getGyslist() {
        return gyslist;
    }

    public void setGyslist(List<HotelGysdxVO> gyslist) {
        this.gyslist = gyslist;
    }

    public String getHysss() {
        return hysss;
    }

    public void setHysss(String hysss) {
        this.hysss = hysss;
    }

    public String getHcbq() {
        return hcbq;
    }

    public void setHcbq(String hcbq) {
        this.hcbq = hcbq;
    }

    public String getHyssl() {
        return hyssl;
    }

    public void setHyssl(String hyssl) {
        this.hyssl = hyssl;
    }

    public String getBjsl() {
        return bjsl;
    }

    public void setBjsl(String bjsl) {
        this.bjsl = bjsl;
    }

    public String getDrjsl() {
        return drjsl;
    }

    public void setDrjsl(String drjsl) {
        this.drjsl = drjsl;
    }

    public List<HotelHysdxVO> getHyjdlist() {
        return hyjdlist;
    }

    public void setHyjdlist(List<HotelHysdxVO> hyjdlist) {
        this.hyjdlist = hyjdlist;
    }

    public String getSfxy() {
        return sfxy;
    }

    public void setSfxy(String sfxy) {
        this.sfxy = sfxy;
    }

    public String getSfzgzc() {
        return sfzgzc;
    }

    public void setSfzgzc(String sfzgzc) {
        this.sfzgzc = sfzgzc;
    }

    public String getSfcpszq() {
        return sfcpszq;
    }

    public void setSfcpszq(String sfcpszq) {
        this.sfcpszq = sfcpszq;
    }

    public List<String> getJcssmclb() {
        return jcssmclb;
    }

 public void setJcssmclb(List<String> jcssmclb) {
        this.jcssmclb = jcssmclb;
    }

    public List<String> getXxssmclb() {
        return xxssmclb;
    }

    public void setXxssmclb(List<String> xxssmclb) {
        this.xxssmclb = xxssmclb;
    }

    public List<String> getFxssmclb() {
        return fxssmclb;
    }

    public void setFxssmclb(List<String> fxssmclb) {
        this.fxssmclb = fxssmclb;
    }

    public List<String> getFwssmclb() {
        return fwssmclb;
    }

    public void setFwssmclb(List<String> fwssmclb) {
        this.fwssmclb = fwssmclb;
    }

    public List<String> getJdztmclb() {
        return jdztmclb;
    }

    public void setJdztmclb(List<String> jdztmclb) {
        this.jdztmclb = jdztmclb;
    }

    public String getJdid() {
        return jdid;
    }

    public void setJdid(String jdid) {
        this.jdid = jdid;
    }

    public String getJdgygx() {
        return jdgygx;
    }

    public void setJdgygx(String jdgygx) {
        this.jdgygx = jdgygx;
    }

    public String getJdcsly() {
        return jdcsly;
    }

    public void setJdcsly(String jdcsly) {
        this.jdcsly = jdcsly;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getJdcxcs() {
        return jdcxcs;
    }

    public void setJdcxcs(String jdcxcs) {
        this.jdcxcs = jdcxcs;
    }

    public String getJdzwmc() {
        return jdzwmc;
    }

    public void setJdzwmc(String jdzwmc) {
        this.jdzwmc = jdzwmc;
    }

    public String getJdywmc() {
        return jdywmc;
    }

    public void setJdywmc(String jdywmc) {
        this.jdywmc = jdywmc;
    }

    public String getXj() {
        return xj;
    }

    public void setXj(String xj) {
        this.xj = xj;
    }

    public String getJdpp() {
        return jdpp;
    }

 public void setJdpp(String jdpp) {
        this.jdpp = jdpp;
    }

    public String getJddz() {
        return jddz;
    }

    public void setJddz(String jddz) {
        this.jddz = jddz;
    }

    public String getSzgj() {
        return szgj;
    }

    public void setSzgj(String szgj) {
        this.szgj = szgj;
    }

    public String getSzsf() {
        return szsf;
    }

    public void setSzsf(String szsf) {
        this.szsf = szsf;
    }

    public String getSzcs() {
        return szcs;
    }

    public void setSzcs(String szcs) {
        this.szcs = szcs;
    }

    public String getXzq() {
        return xzq;
    }

    public void setXzq(String xzq) {
        this.xzq = xzq;
    }

    public String getSyq() {
        return syq;
    }

    public void setSyq(String syq) {
        this.syq = syq;
    }

    public String getSzcsmc() {
        return szcsmc;
    }

    public void setSzcsmc(String szcsmc) {
        this.szcsmc = szcsmc;
    }

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getSyqmc() {
        return syqmc;
    }

    public void setSyqmc(String syqmc) {
        this.syqmc = syqmc;
    }

    public String getGddh() {
        return gddh;
    }

    public void setGddh(String gddh) {
        this.gddh = gddh;
    }

    public String getCzhm() {
        return czhm;
    }

    public void setCzhm(String czhm) {
        this.czhm = czhm;
    }

    public String getJdzy() {
        return jdzy;
    }

    public void setJdzy(String jdzy) {
        this.jdzy = jdzy;
    }


    public String getZdj() {
        return zdj;
    }

    public void setZdj(String zdj) {
        this.zdj = zdj;
    }

    public String getElfxbl() {
        return elfxbl;
    }

    public void setElfxbl(String elfxbl) {
        this.elfxbl = elfxbl;
    }

    public String getKysj() {
        return kysj;
    }

    public void setKysj(String kysj) {
        this.kysj = kysj;
    }

    public String getZxsj() {
        return zxsj;
    }

    public void setZxsj(String zxsj) {
        this.zxsj = zxsj;
    }

    public String getJdjcsslbjh() {
        return jdjcsslbjh;
    }

    public void setJdjcsslbjh(String jdjcsslbjh) {
        this.jdjcsslbjh = jdjcsslbjh;
    }

    public String getJdxxsslbjh() {
        return jdxxsslbjh;
    }

    public void setJdxxsslbjh(String jdxxsslbjh) {
        this.jdxxsslbjh = jdxxsslbjh;
    }

    public String getFxsslbjh() {
        return fxsslbjh;
    }

    public void setFxsslbjh(String fxsslbjh) {
        this.fxsslbjh = fxsslbjh;
    }

    public String getJdfwlbjh() {
        return jdfwlbjh;
    }

    public void setJdfwlbjh(String jdfwlbjh) {
        this.jdfwlbjh = jdfwlbjh;
    }

    public String getTbts() {
        return tbts;
    }

    public void setTbts(String tbts) {
        this.tbts = tbts;
    }

    public String getJdjj() {
        return jdjj;
    }

    public void setJdjj(String jdjj) {
        this.jdjj = jdjj;
    }

    public String getJdztlb() {
        return jdztlb;
    }

    public void setJdztlb(String jdztlb) {
        this.jdztlb = jdztlb;
    }
public String getGngjlb() {
        return gngjlb;
    }

    public void setGngjlb(String gngjlb) {
        this.gngjlb = gngjlb;
    }

    public String getJxtg() {
        return jxtg;
    }

    public void setJxtg(String jxtg) {
        this.jxtg = jxtg;
    }

    public String getTjtg() {
        return tjtg;
    }

    public void setTjtg(String tjtg) {
        this.tjtg = tjtg;
    }

    public String getRmtg() {
        return rmtg;
    }

    public void setRmtg(String rmtg) {
        this.rmtg = rmtg;
    }

    public String getCxtg() {
        return cxtg;
    }

    public void setCxtg(String cxtg) {
        this.cxtg = cxtg;
    }

    public String getZttg() {
        return zttg;
    }

    public void setZttg(String zttg) {
        this.zttg = zttg;
    }

    public String getZbjt() {
        return zbjt;
    }

    public void setZbjt(String zbjt) {
        this.zbjt = zbjt;
    }

    public String getBdjd() {
        return bdjd;
    }

    public void setBdjd(String bdjd) {
        this.bdjd = bdjd;
    }

    public String getBdwd() {
        return bdwd;
    }

    public void setBdwd(String bdwd) {
        this.bdwd = bdwd;
    }

    public String getGgjd() {
        return ggjd;
    }

    public void setGgjd(String ggjd) {
        this.ggjd = ggjd;
    }

    public String getGgwd() {
        return ggwd;
    }

    public void setGgwd(String ggwd) {
        this.ggwd = ggwd;
    }

    public String getJl() {
        return jl;
    }

    public void setJl(String jl) {
        this.jl = jl;
    }

    public String getTctsldb() {
        return tctsldb;
    }

    public void setTctsldb(String tctsldb) {
        this.tctsldb = tctsldb;
    }

    public String getTccgldb() {
        return tccgldb;
    }

public void setTccgldb(String tccgldb) {
        this.tccgldb = tccgldb;
    }

    public String getTcqrldb() {
        return tcqrldb;
    }

    public void setTcqrldb(String tcqrldb) {
        this.tcqrldb = tcqrldb;
    }

    public String getTczpfdb() {
        return tczpfdb;
    }

    public void setTczpfdb(String tczpfdb) {
        this.tczpfdb = tczpfdb;
    }

    @XmlElement(name = "pf_tsl")
    public String getPftsl() {
        return pftsl;
    }

    public void setPftsl(String pftsl) {
        this.pftsl = pftsl;
    }

    @XmlElement(name = "pf_cgl")
    public String getPfcgl() {
        return pfcgl;
    }

    public void setPfcgl(String pfcgl) {
        this.pfcgl = pfcgl;
    }

    @XmlElement(name = "pf_qrl")
    public String getPfqrl() {
        return pfqrl;
    }

    public void setPfqrl(String pfqrl) {
        this.pfqrl = pfqrl;
    }

    @XmlElement(name = "pf_zpf")
    public String getPfzpf() {
        return pfzpf;
    }

    public void setPfzpf(String pfzpf) {
        this.pfzpf = pfzpf;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getTjxj() {
        return tjxj;
    }

    public void setTjxj(String tjxj) {
        this.tjxj = tjxj;
    }

    public String getIstjxj() {
        return istjxj;
    }

    public void setIstjxj(String istjxj) {
        this.istjxj = istjxj;
    }

    @XmlTransient
    public Integer getJdsl() {
        return jdsl;
    }
   public void setJdsl(Integer jdsl) {
        this.jdsl = jdsl;
    }

    @XmlTransient
    public Integer getKsjdsl() {
        return ksjdsl;
    }

    public void setKsjdsl(Integer ksjdsl) {
        this.ksjdsl = ksjdsl;
    }

    @XmlTransient
    public Integer getKsjhsl() {
        return ksjhsl;
    }

    public void setKsjhsl(Integer ksjhsl) {
        this.ksjhsl = ksjhsl;
    }

    @XmlTransient
    public Double getPjsj() {
        return pjsj;
    }

    public void setPjsj(Double pjsj) {
        this.pjsj = pjsj;
    }

    @XmlTransient
    public Integer getXfjgjhsl() {
        return xfjgjhsl;
    }

    public void setXfjgjhsl(Integer xfjgjhsl) {
        this.xfjgjhsl = xfjgjhsl;
    }

    @XmlTransient
    public Double getXfpjj() {
        return xfpjj;
    }

    public void setXfpjj(Double xfpjj) {
        this.xfpjj = xfpjj;
    }

    @XmlTransient
    public Integer getYfjgjhsl() {
        return yfjgjhsl;
    }

    public void setYfjgjhsl(Integer yfjgjhsl) {
        this.yfjgjhsl = yfjgjhsl;
    }

    @XmlTransient
    public Double getYfpjj() {
        return yfpjj;
    }

    public void setYfpjj(Double yfpjj) {
        this.yfpjj = yfpjj;
    }

    public String getJdppmc() {
        return jdppmc;
    }

    public void setJdppmc(String jdppmc) {
        this.jdppmc = jdppmc;
    }

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public String getPpmc() {
        return ppmc;
    }

    public void setPpmc(String ppmc) {
        this.ppmc = ppmc;
    }

    public String getSfyct() {
        return sfyct;
    }

    public void setSfyct(String sfyct) {
        this.sfyct = sfyct;
    }

    public String getSfsqdjd() {
        return sfsqdjd;
    }

    public void setSfsqdjd(String sfsqdjd) {
        this.sfsqdjd = sfsqdjd;