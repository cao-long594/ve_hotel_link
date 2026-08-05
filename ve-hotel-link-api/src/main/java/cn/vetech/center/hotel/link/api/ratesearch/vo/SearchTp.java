package cn.vetech.center.hotel.link.api.ratesearch.vo;

public class SearchTp {

    /**
     *图片类型
     */
    private String tplx;
    /**
     *房型id
     */
    private String fxid;
    /**
     *图片地址
     */
    private String tpdz;
    /**
     *图片规格 1-350px-jpg 2-70px-jpg 3-120px-jpg 4-70px-png 5-120px-png 6-640px-png
     */
    private String tpdx;
    /**
     *是否水印
     */
    private String sfsy;

    public String getTplx() {
        return tplx;
    }

    public void setTplx(String tplx) {
        this.tplx = tplx;
    }

    public String getFxid() {
        return fxid;
    }

    public void setFxid(String fxid) {
        this.fxid = fxid;
    }

    public String getTpdz() {
        return tpdz;
    }

    public void setTpdz(String tpdz) {
        this.tpdz = tpdz;
    }

    public String getTpdx() {
        return tpdx;
    }

    public void setTpdx(String tpdx) {
        this.tpdx = tpdx;
    }

    public String getSfsy() {
        return sfsy;
    }

    public void setSfsy(String sfsy) {
        this.sfsy = sfsy;
    }
}
