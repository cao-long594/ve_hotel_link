package cn.vetech.center.hotel.link.elong.common;

import cn.vetech.center.hotel.link.supply.base.config.BaseConfig;
import cn.vetech.center.hotel.link.util.JacksonUtils;

import java.io.Serializable;

/**
 * 艺龙账号配置信息
 *
 * @author gaojin
 */
public class ElongConfig extends BaseConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 账号
     */
    private String user;
    /**
     * 密码
     */
    private String appKey;
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * API，去掉http://，有的接口使用https://。
     */
    private String url;
    /**
     * 接口版本号，默认1.32
     */
    private String version = "1.32";
    /**
     * 语言，默认zh_CN
     */
    private String local = "zh_CN";
    /**
     * 现付返点
     */
    private String xffd;
    /**
     * 现付返现上限
     */
    private String xffxsx;
    /**
     * 预付返点
     */
    private String yffd;
    /**
     * 预付返现上限
     */
    private String yffxsx;
    /**
     * 信用卡卡号
     */
    private String xykkh;
    /**
     * 信用卡发卡银行
     */
    private String xykfkyh;
    /**
     * 信用卡CVV
     */
    private String xykcvv;
    /**
     * 信用卡有效期年月
     */
    private String xykyxrq;
    /**
     * 信用卡持卡人姓名
     */
    private String xykckrxm;
    /**
     * 信用卡持卡人证件号
     */
    private String xykckrzjh;
    /**
     * 信用卡持卡人证件类型，0身份证1护照
     */
    private String xykckrzjlx;
    /**
     * 信用卡持卡人手机号
     */
    private String xykckrsjh;

    /**
     * 静态数据地址
     */
    private String staticUrl;
    /**
     * 预付酒店开票控制销售价类型:  0和空 无控制 （默认），1大于等于，2等于，3不可销售
     */
    private String kzxsjlx;
    /**
     * 最低价抓取方式，1：最低价接口；2：报价接口
     */
    private String zdjzqfs;
    /**
     * 报价需要过滤的产品编号；例如途家供应商5047801
     */
    private String supplyId;

    /**
     * 限流时间窗口
     */
    private String rateTime;

    /**
     * 限流时容量
     */
    private String rateCapacity;
    /**
     * limit price 控制销售价类型(艺龙限价)
     */
    private String lpKzxsjlx;

    /**
     * 拉取城市国家类型； 默认0：所有城市、 1：国内 、2：国际
     */
    private String countryType;
    /**
     * 过滤产品类型， 1代表过滤预付酒店开票产品， 2代表过滤预付供应商开票产品
     */
    private String filterProductType;

    /**
     * 是否开启注册 1注册；0或空，不开启注册
     */
    private String switchRegister;
    /**
     * 是否获取缓存中 openId
     */
    private String cacheOpenId;
    /**
     * openid  缓存过期时间 单位秒
     */
    private String cacheExpireTime;
    /**
     * 支持发票类型  1 专票  2 普票
     */
    private String supportSpecialInvoice;
    /**
     * 是否开启会员价；1：开启会员价，0：不开启
     */
    private String sfkqhyj;

    /**
     * contentId
     */
    private String contentId;
    /**
     * 验价走新接口还是老接口，1:新接口data.booking接口，其他：老验价接口
     */
    private String validateType;
    /**
     * 输出会员ID信息
     */
    private String showVipExtInfo;

    public String getShowVipExtInfo() {
        return showVipExtInfo;
    }

    public void setShowVipExtInfo(String showVipExtInfo) {
        this.showVipExtInfo = showVipExtInfo;
    }

    public String getValidateType() {
        return validateType;
    }

    public void setValidateType(String validateType) {
        this.validateType = validateType;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getSfkqhyj() {
        return sfkqhyj;
    }

    public void setSfkqhyj(String sfkqhyj) {
        this.sfkqhyj = sfkqhyj;
    }

    public String getCacheExpireTime() {
        return cacheExpireTime;
    }

    public void setCacheExpireTime(String cacheExpireTime) {
        this.cacheExpireTime = cacheExpireTime;
    }

    public String getCacheOpenId() {
        return cacheOpenId;
    }

    public void setCacheOpenId(String cacheOpenId) {
        this.cacheOpenId = cacheOpe