package cn.vetech.center.hotel.link.entity;

import cn.vetech.center.hotel.link.util.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * 酒店图片对象
 * 需存储json格式
 *
 * @author xingyanyan on 2018/9/4.
 */
public class JdTp {
    public JdTp() {

    }

    public JdTp(String tpdz, String tplx, String tpcc) {
        this.tpdz = tpdz;
        this.tplx = tplx;
        this.tpcc = tpcc;
    }

    /**
     * 图片url地址
     */
    @JsonProperty("tpdz")
    private String tpdz;
    /**
     * 图片类型0-展示图；1-餐厅；2-休闲室；3-会议室 ；4-服务；5-酒店外观；
     * 6-大堂/接待台；7-酒店介绍；8-房型；9-背景图；10-其他
     */
    @JsonProperty("tplx")
    private String tplx;
    /**
     * 图片尺寸(传1或者2)  1：大图350*350以上；2:小图350*350及以下如70*70
     */
    @JsonProperty("tpcc")
    private String tpcc;

    /**
     * 本地地址
     */
    @JsonProperty("localdz")
    private String localdz;

    public String getTpdz() {
        return tpdz;
    }

    public void setTpdz(String tpdz) {
        this.tpdz = tpdz;
    }

    public String getTplx() {
        return tplx;
    }

    public void setTplx(String tplx) {
        this.tplx = tplx;
    }

    public String getTpcc() {
        return tpcc;
    }

    public void setTpcc(String tpcc) {
        this.tpcc = tpcc;
    }

    public String getLocaldz() {
        return localdz;
    }

    public void setLocaldz(String localdz) {
        this.localdz = localdz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JdTp jdTp = (JdTp) o;

        return StringUtils.equals(tpdz, jdTp.getTpdz());
    }

    @Override
    public int hashCode() {
        return tpdz != null ? tpdz.hashCode() : 0;
    }

    /**
     * @return json
     */
        public String toJson() {
        return JacksonUtils.toJsonWithNonEmpty(this);
    }
}