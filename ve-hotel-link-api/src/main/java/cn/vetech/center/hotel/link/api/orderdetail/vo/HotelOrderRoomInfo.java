package cn.vetech.center.hotel.link.api.orderdetail.vo;

/**
 * @author chengwanshan
 * @since 2024/12/17 15:30
 */
public class HotelOrderRoomInfo {
    /**
     * 房间ID
     */
    private String roomId;
    /**
     * 房间订单ID
     */
    private String roomOrderId;
    /**
     * 房源商返回的房间订单原始状态
     */
    private String trueStatus;
    /**
     * 对用户展示的房间订单状态
     */
    private String showStatus;
    /**
     * 房间序号
     */
    private String fjxh;
    /**
     * 供应商返回的房间入住人英文姓
     */
    private String originalLastname;
    /**
     * 供应商返回的房间入住人英文名
     */
    private String originalFirstname;
    /**
     * 入住人姓名
     */
    private String gustName;

    public String getGustName() {
        return gustName;
    }

    public void setGustName(String gustName) {
        this.gustName = gustName;
    }

    public String getFjxh() {
        return fjxh;
    }

    public void setFjxh(String fjxh) {
        this.fjxh = fjxh;
    }

    public String getOriginalLastname() {
        return originalLastname;
    }

    public void setOriginalLastname(String originalLastname) {
        this.originalLastname = originalLastname;
    }

    public String getOriginalFirstname() {
        return originalFirstname;
    }

    public void setOriginalFirstname(String originalFirstname) {
        this.originalFirstname = originalFirstname;
    }

    public String getRoomOrderId() {
        return roomOrderId;
    }

    public void setRoomOrderId(String roomOrderId) {
        this.roomOrderId = roomOrderId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTrueStatus() {
        return trueStatus;
    }

    public void setTrueStatus(String trueStatus) {
        this.trueStatus = trueStatus;
    }

    public String getShowStatus() {
        return showStatus;
