package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2021/12/14 18:01
 */
public class ElongSearchPicture {
    /**
     * 礼包套餐图片顺序
     */
    @JsonProperty("ImgIndex")
    private String imgIndex;
    /**
     * 礼包套餐图片链接
     */
    @JsonProperty("ImgUrl")
    private String imgUrl;

    public String getImgIndex() {
        return imgIndex;
    }

    public void setImgIndex(String imgIndex) {
        this.imgIndex = imgIndex;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
