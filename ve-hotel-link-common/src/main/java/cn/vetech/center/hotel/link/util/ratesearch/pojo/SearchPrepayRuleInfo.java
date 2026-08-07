package cn.vetech.center.hotel.link.util.ratesearch.pojo;

import cn.vetech.center.hotel.link.api.enums.SuffixTypeEnum;

/**
 * @author chengwanshan
 * @since 2024/9/26 18:01
 */
public class SearchPrepayRuleInfo {
    /**
     * 取消规则
     */
    private SuffixTypeEnum suffixTypeEnum;
    /**
     * 最晚取消时间
     */
    private String latestCancelTime;
    /**
     * 最晚取消时间时区，latestCancelTime对应的时区，北京时区：UTC+08:00
     * 国际酒店latestCancelTime有值时，latestCancelTimeZone必传
     * 1、供应商接口明确返回时区的
     * 2、供应商只告知是酒店本地时区，接口并未输出的，传酒店城市所在时区，dto中有传
     */
    private String latestCancelTimeZone;
    /**
     * 取消规则描述，注意，这里给了描述，就优先使用，没有给描述公共方法自动拼接
     */
    private String desc;
    /**
     * 取消规则描述desc中的时区类型，参考CancelRuleDescTimeZoneTypeEnum，1：酒店当地时间，2：北京时间，3：带时区的时间
     * 国际酒店，desc有值时，descTimeZoneType必传，desc为空时不传
     */
    private String descTimeZoneType;
    /**
     * 国内国际，参考GnGjTypeEnum，不传默认国内
     * 国际酒店必传
     */
    private String gngj;
    /**
     * 酒店当前时区
     */
    private String hotelLocalTimeZone;

    public String getHotelLocalTimeZone() {
        return hotelLocalTimeZone;
    }

    public void setHotelLocalTimeZone(String hotelLocalTimeZone) {
        this.hotelLocalTimeZone = hotelLocalTimeZone;
    }

    public String getDescTimeZoneType() {
        return descTimeZoneType;
    }

    public void setDescTimeZoneType(String descTimeZoneType) {
        this.descTimeZoneType = descTimeZoneType;
    }

    public SuffixTypeEnum getSuffixTypeEnum() {
        return suffixTypeEnum;
    }

    public void setSuffixTypeEnum(SuffixTypeEnum suffixTypeEnum) {
        this.suffixTypeEnum = suffixTypeEnum;
    }

    public String getLatestCancelTime() {
        return latestCancelTime;
    }

    public void setLatestCancelTime(String latestCancelTime) {
        this.latestCancelTime = latestCancelTime;
    }

    public String getLatestCancelTimeZone() {
        return latestCancelTimeZone;
 