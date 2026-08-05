package cn.vetech.center.hotel.link.supply.service.config.asms.request;

/**
 * 供应商配置request
 *
 * @author luqs
 * @version v1.0
 **/
public class SupplierConfigQryRequest {
    /**
     * 总公司编号
     */
    private String zgs;
    /**
     * 企业编号
     */
    private String qybh;
    /**
     * 部门id
     */
    private String bmid;
    /**
     * 员工id
     */
    private String ygid;
    /**
     * 产品编号
     */
    private String cpbh;
    /**
     * 供应商编号，即房源简称
     */
    private String gysbh;

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

    public String getBmid() {
        return bmid;
    }

    public void setBmid(String bmid) {
        this.bmid = bmid;
    }

    public String getYgid() {
        return ygid;
    }

    public void setYgid(String ygid) {
        this.ygid = ygid;
    }

    public String getCpbh() {
        return cpbh;
    }

    public void setCpbh(String cpbh) {
        this.cpbh = cpbh;
    }

    public String getGysbh() {
        return gysbh;
    }

    public void setGysbh(String gysbh) {
        this.gysbh = gysbh;
    }
}
