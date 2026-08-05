package cn.vetech.center.hotel.link.api.tdsq.dto;


/**
 * @author xingyanyan
 */
public class Tfxx{
    private static final long serialVersionUID = 5229058993056568513L;
    private String fjxh;           //房间序号

    private String rzr;           //入住人

    private String qxrzrq;           //取消入住日期

    public String getFjxh() {
        return fjxh;
    }

    public void setFjxh(String fjxh) {
        this.fjxh = fjxh;
    }

    public String getRzr() {
        return rzr;
    }

    public void setRzr(String rzr) {
        this.rzr = rzr;
    }

    public String getQxrzrq() {
        return qxrzrq;
    }

    public void setQxrzrq(String qxrzrq) {
        this.qxrzrq = qxrzrq;
    }
}
