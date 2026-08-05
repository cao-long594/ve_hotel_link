package cn.vetech.center.hotel.link.supply.service.config.asms.response;

import java.util.List;

/**
 * 供应商商户信息
 *
 * @author pengyefei
 * @since 2021/4/9
 */
public class SupplyMerchantInfo {
    /**
     * 编号
     */
    private String bh;
    /**
     * 总公司
     */
    private String zgs;
    /**
     * 商户名称
     */
    private String mc;
    /**
     * 显示商户名称
     */
    private String xsmc;
    /**
     * 状态 状态 0未审核 1启用 2停用
     */
    private String zt;
    /**
     * 商户类型 1服务商 2供应商 3供服一体
     */
    private String shlx;
    /**
     * 供应商配置
     */
    private List<SupplierConfig> configs;

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getZgs() {
        return zgs;
    }

    public void setZgs(String zgs) {
        this.zgs = zgs;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getXsmc() {
        return xsmc;
    }

    public void setXsmc(String xsmc) {
        this.xsmc = xsmc;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getShlx() {
        return shlx;
    }

    public void setShlx(String shlx) {
        this.shlx = shlx;
    }

    public List<SupplierConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<SupplierConfig> configs) {
        this.configs = configs;
    }
}
