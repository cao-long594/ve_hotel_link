package cn.vetech.center.hotel.link.api.hotelgetjdlb.dto;


import java.util.List;

/**
 * @author vetech
 * @since 25/12/02
 */
public class HotelPreference {
    /**
     * 酒店
     */
    private List<String> orderedJdidList;

    /**
     *收藏
     */
    private List<String> collectJdidList;

    /**
     *浏览
     */
    private List<String> browseJdidList;

    /**
     *同事订过
     */
    private List<String> othersOrderedJdidList;

    public List<String> getOrderedJdidList() {
        return orderedJdidList;
    }

    public void setOrderedJdidList(List<String> orderedJdidList) {
        this.orderedJdidList = orderedJdidList;
    }

    public List<String> getCollectJdidList() {
        return collectJdidList;
    }

    public void setCollectJdidList(List<String> collectJdidList) {
        this.collectJdidList = collectJdidList;
    }

    public List<String> getBrowseJdidList() {
        return browseJdidList;
    }

    public void setBrowseJdidList(List<String> browseJdidList) {
        this.browseJdidList = browseJdidList;
    }

    public List<String> getOthersOrderedJdidList() {
        return othersOrderedJdidList;
    }

    public void setOthersOrderedJdidList(List<String> othersOrderedJdidList) {
        this.othersOrderedJdidList = othersOrderedJdidList;
    }
}
