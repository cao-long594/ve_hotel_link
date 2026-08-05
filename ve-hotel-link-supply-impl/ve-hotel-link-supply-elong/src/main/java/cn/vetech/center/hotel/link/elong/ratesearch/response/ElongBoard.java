package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author xiaotengyu
 * @since 2024-05-10 16:42
 */
public class ElongBoard {

    /**
     * 是否含早	Boolean	Y	国际特有字段
     */
    @JsonProperty("BreakfastIncluded")
    private String breakfastIncluded;
    /**
     * 是否半膳	Boolean	Y	国际特有字段
     */
    @JsonProperty("HalfboardIncluded")
    private String halfboardIncluded;
    /**
     * 是否全膳	Boolean	Y	国际特有字段
     */
    @JsonProperty("FullboardIncluded")
    private String fullboardIncluded;
    /**
     * 膳食明细	Element	Y	参考BoardDetail节点 国际特有字段
     */
    @JsonProperty("Boards")
    private List<ElongBoardDetail> boards;

    public String getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(String breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public String getHalfboardIncluded() {
        return halfboardIncluded;
    }

    public void setHalfboardIncluded(String halfboardIncluded) {
        this.halfboardIncluded = halfboardIncluded;
    }

    public String getFullboardIncluded() {
        return fullboardIncluded;
    }

    public void setFullboardIncluded(String fullboardIncluded) {
        this.fullboardIncluded = fullboardIncluded;
    }

    public List<ElongBoardDetail> getBoards() {
        return boards;
    }

    public void setBoards(List<ElongBoardDetail> boards) {
        this.boards = boards;
    }
}
