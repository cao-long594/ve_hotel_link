package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author gaojin
 */
public class ElongSearchRoom {
    /**
     * 展示房型编码。物理房型id
     */
    @JsonProperty("RoomId")
    private String roomId;
    /**
     * 房型名称
     */
    @JsonProperty("Name")
    private String name;
    /**
     * 产品信息
     */
    @JsonProperty("RatePlans")
    private List<ElongSearchRatePlan> ratePlans;
    /**
     * 备注
     */
    @JsonProperty("Description")
    private String description;
    /**
     * 图片地址。地址不是以http开头，则增加前
     */
    @JsonProperty("ImageUrl")
    private String imageUrl;
    /**
     * 可容纳人数
     */
    @JsonProperty("Capcity")
    private String capcity;
    /**
     * 楼层
     */
    @JsonProperty("Floor")
    private String floor;
    /**
     * 上网情况//0-无1-免费宽带2-收费宽带3-免费WIFI4-收费WIFI
     */
    @JsonProperty("Broadnet")
    private String broadnet;
    /**
     * 床型
     */
    @JsonProperty("BedType")
    private String bedType;
    /**
     * 床型描述
     */
    @JsonProperty("BedDesc")
    private String bedDesc;
    /**
     * 房间备注
     */
    @JsonProperty("Comments")
    private String comments;
    /**
     * 面积
     */
    @JsonProperty("Area")
    private String area;


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ElongSearchRatePlan> getRatePlans() {
        return ratePlans;
    }

    public void setRatePlans(List<ElongSearchRatePlan> ratePlans) {
        this.ratePlans = ratePlans;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        thi