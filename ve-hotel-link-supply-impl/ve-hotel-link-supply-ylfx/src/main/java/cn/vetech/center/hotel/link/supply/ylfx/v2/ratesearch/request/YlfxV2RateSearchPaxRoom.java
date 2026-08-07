package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.request;

import java.util.List;

/**
 * 易旅分销 V2 入住人房间信息
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2RateSearchPaxRoom {
    /**
     * 房间序号，从 1 开始
     */
    private Integer roomIndex;
    /**
     * 成人数
     */
    private Integer adults;
    /**
     * 儿童数
     */
    private Integer children;
    /**
     * 儿童年龄列表
     */
    private List<Integer> childrenAges;

    public Integer getRoomIndex() {
        return roomIndex;
    }

    public void setRoomIndex(Integer roomIndex) {
        this.roomIndex = roomIndex;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public List<Integer> getChildrenAges() {
        return childrenAges;
    }

    public void setChildrenAges(List<Integer> childrenAges) {
        this.childrenAges = childrenAges;
    }
}
