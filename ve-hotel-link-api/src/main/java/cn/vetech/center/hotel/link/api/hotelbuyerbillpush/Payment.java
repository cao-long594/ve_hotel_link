package cn.vetech.center.hotel.link.api.hotelbuyerbillpush;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * 表示一个支付对象。
 * @author 7761
 */
@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment {
    /**
     * 支付类型，1月结，2在线，10福利积分，11福利积分卡，20福利额度，21福利额度卡
     */
    private String zflx;
    /**
     * 收银类型，1收款2退款
     */
    private String sylx;
    /**
     * 是否服务费，1为服务费
     */
    private String sffwf;
    /**
     * 支付金额
     */
    private BigDecimal zfje;
    /**
     * 支付方式
     */
    @XmlElement(name = "zf_fs")
    private String zfFs;
    /**
     * 支付方式名称
     */
    @XmlElement(name = "zf_fsmc")
    private String zfFsmc;
    /**
     * 支付科目
     */
    @XmlElement(name = "zf_km")
    private String zfKm;
    /**
     * 支付科目名称
     */
    @XmlElement(name = "zf_kmmc")
    private String zfKmmc;
    /**
     * 支付账号（福利账户支付卡号）
     */
    @XmlElement(name = "zf_zh")
    private String zfZh;

    public String getZflx() {
        return zflx;
    }

    public void setZflx(String zflx) {
        this.zflx = zflx;
    }

    public String getSylx() {
        return sylx;
    }

    public void setSylx(String sylx) {
        this.sylx = sylx;
    }

    public String getSffwf() {
        return sffwf;
    }

    public void setSffwf(String sffwf) {
        this.sffwf = sffwf;
    }

    public BigDecimal getZfje() {
        return zfje;
    }

    public void setZfje(BigDecimal zfje) {
        this.zfje = zfje;
    }

    public String getZfFs() {
        return zfFs;
    }

    public void setZfFs(String zfFs) {
        this.zfFs = zfFs;
    }

    public String getZfFsmc() {
        return zfFsmc;
    }

    public void setZfFsmc(String zfFsmc) 