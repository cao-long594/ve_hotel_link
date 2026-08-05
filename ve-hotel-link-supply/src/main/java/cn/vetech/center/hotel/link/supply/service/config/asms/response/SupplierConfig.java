package cn.vetech.center.hotel.link.supply.service.config.asms.response;

import java.util.List;

/**
 * 供应商配置
 *
 * @author luqs
 * @version v1.0
 **/
public class SupplierConfig {
    /**
     * 总公司编号
     */
    private String zgs;
    /**
     * 企业编号
     */
    private String qybh;
    /**
     * cps配置不需要 费控和企业差旅取供应商平台(pt)
     */
    private String pt;
    /**
     * 房源编号
     */
    private String fybh;
    /**
     * 房源名称
     */
    private String fymc;
    /**
     * 供应商编号，cps配置取fyjc(房源简称)  费控和企业差旅取bh（商户编号）
     */
    private String gysbh;
    /**
     * 状态 1：开启 0：关闭
     */
    private String zt;
    /**
     * 下单状态 1：开启 0：关闭
     */
    private String xdzt;
    /**
     * 属性值
     */
    private List<SupplyParam> paramList;

    public String getZgs() {
        return zgs;
    }

    public void setZgs(String zgs) {
        this.zgs = zgs;
    }

    public String getQybh() {
        return qybh;
    }

    public void setQybh(String qybh) {
        this.qybh = qybh;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getGysbh() {
        return gysbh;
    }

    public void setGysbh(String gysbh) {
        this.gysbh = gysbh;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getXdzt() {
        return xdzt;
    }

    public void setXdzt(String xdzt) {
        this.xdzt = xdzt;
    }

    public List<SupplyParam> getParamList() {
        return paramList;
    }

    public void setParamList(List<SupplyParam> paramList) {
        this.paramList = paramList;
    }
}
