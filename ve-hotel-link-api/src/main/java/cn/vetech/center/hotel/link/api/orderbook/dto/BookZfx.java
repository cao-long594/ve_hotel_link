package cn.vetech.center.hotel.link.api.orderbook.dto;

import cn.vetech.center.hotel.link.api.ratesearch.vo.FeeInfo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author pengyefei
 * @since 2020/11/7
 */
public class BookZfxx implements Serializable {
    /**
     * 支付金额
     */
    private BigDecimal zfje;
    /**
     * 支付金额（原始币种）
     */
    private FeeInfo originPaymentPrice;
    /**
     * 支付方式
     * 1公司月结 2网通 3微信 4银联 5支付宝,  6：在线支付， 7：华夏银行扫码支付，  8：华夏银行快捷支付
     */
    private String zffs;
    /**
     * 支付账户
     */
    private String zfzh;
    /**
     * 支付流水号
     */
    private String zflsh;
    /**
     * 支付宝、微信的支付流水号
     */
    private String yszflsh;
    /**
     * 金额类型，1:服务费
     */
    private String zfjelx;
    /**
     * 支付金额（指定币种）
     */
    private FeeInfo customPaymentPrice;

    public FeeInfo getCustomPaymentPrice() {
        return customPaymentPrice;
    }

    public void setCustomPaymentPrice(FeeInfo customPaymentPrice) {
        this.customPaymentPrice = customPaymentPrice;
    }

    public FeeInfo getOriginPaymentPrice() {
        return originPaymentPrice;
    }

    public void setOriginPaymentPrice(FeeInfo originPaymentPrice) {
        this.originPaymentPrice = originPaymentPrice;
    }

    public String getYszflsh() {
        return yszflsh;
    }

    public void setYszflsh(String yszflsh) {
        this.yszflsh = yszflsh;
    }

    public BigDecimal getZfje() {
        return zfje;
    }

    public void setZfje(BigDecimal zfje) {
        this.zfje = zfje;
    }

    public String getZffs() {
        return zffs;
    }

    public void setZffs(String zffs) {
        this.zffs = zffs;
    }

    public String getZfzh() {
        return zfzh;
    }

    public void setZfzh(String zfzh) {
        this.zfzh = zfzh;
    }

    public String getZflsh() {
        return zflsh;
    }

    public void setZflsh(String z