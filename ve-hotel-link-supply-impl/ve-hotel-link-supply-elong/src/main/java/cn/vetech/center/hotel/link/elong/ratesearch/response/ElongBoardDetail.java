package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaotengyu
 * @since 2024-05-10 16:43
 */
public class ElongBoardDetail {

    /**
     * 描述	String	Y	国际特有字段
     */
    @JsonProperty("Description")
    private String description;
    /**
     * 膳食数量	Int	Y	国际特有字段
     */
    @JsonProperty("Count")
    private String count;
    /**
     * 膳食类型	Int	Y	膳食类型 1：早餐；2：午餐；3：晚餐；0：未知餐型；国际特有字段
     */
    @JsonProperty("type")
    private String type;
    /**
     * 膳食描述	String	Y	国际特有字段
     */
    @JsonProperty("BoardDesc")
    private String boardDesc;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc) {
        this.boardDesc = boardDesc;
    }
}
