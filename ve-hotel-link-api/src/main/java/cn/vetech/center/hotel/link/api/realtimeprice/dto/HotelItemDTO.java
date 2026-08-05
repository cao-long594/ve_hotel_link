package cn.vetech.center.hotel.link.api.realtimeprice.dto;

import cn.vetech.center.hotel.link.api.hotelgetjdlb.vo.HotelGysdx;

import java.util.List;

/**
 * @author vetech
 * @since 2023/10/31
 */
public class HotelItemDTO {
    /**
     * 酒店id
     */
    private String jdid;
    /**
     * 房源商的酒店ID
     */
    private String hotelid;
    /**
     * 酒店星级
     */
    private String xj;
    /**
     * 酒店推荐星级
     */
    private String tjxj;
    /**
     * 酒店品牌
     */
    private String jdpp;
    /**
     * 酒店标签
     */
    private List<String> hotelTagList;
    /**
     * 酒店主题
     */
    private List<String> hotelThemeList;
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
     * 最低价 num
     */
    private String zdj;
    /**
     * 是否胜意房源映射（即gyslist节点信息是否是取自胜意cps）
     */
    private boolean veSupplyMappingFlag;
    /**
     * 供应商映射关系list
     */
    private List<HotelGysdx> gyslist;
    /**
     * 版本md5
     */
    private String versionMd5;

    public String getJdid() {
        return jdid;
    }

    public void setJdid(String jdid) {
        this.jdid = jdid;
    }

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
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

    public String getJdpp() {
        return jdpp;
    }

    public void setJdpp(String jdpp) {
        this.jdpp = jdpp;
    }

    public List<String> getHotelTagList() {
        return hotelTagList;
    }

    public void setHotelTagList(List<String> hotelTagList) {
        this.h