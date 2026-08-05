package cn.vetech.center.hotel.link.elong.validatebooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2025/10/15 15:47
 */
public class ElongPrepayResult {
    /**
     * 取消规则	String	N
     */
    @JsonProperty("CancelDescription")
    private String cancelDescription;
    /**
     * 取消类型	int	N	1：免费取消；2：收费取消，3：限时取消，4：不可取消
     */
    @JsonProperty("CancelType")
    private String cancelType;
    /**
     * 是否使用阶梯担保规则	boolean	Y	（废弃），此字段无意义，与LadderParseList节点无关，可以忽略
     */
    @JsonProperty("LadderVouch")
    private String ladderVouch;
    /**
     * 取消规则明细	LadderParse[]	N	参考LadderParse节点
     */
    @JsonProperty("LadderParseList")
    private List<ElongLadderParse> ladderParseList;
    /**
     * 取消规则标签	String	Y	如果规则是任意取消和不可取消的没有这个字段和对应值, 限时取消和付费取消则会返回该字段
     */
    @JsonProperty("CancelTag")
    private String cancelTag;

    public String getCancelDescription() {
        return cancelDescription;
    }

    public void setCancelDescription(String cancelDescription) {
        this.cancelDescription = cancelDescription;
    }

    public String getCancelType() {
        return cancelType;
    }

    public void setCancelType(String cancelType) {
        this.cancelType = cancelType;
    }

    public String getLadderVouch() {
        return ladderVouch;
    }

    public void setLadderVouch(String ladderVouch) {
        this.ladderVouch = ladderVouch;
    }

    public List<ElongLadderParse> getLadderParseList() {
        return ladderParseList;
    }

    public void setLadderParseList(List<ElongLadderParse> ladderParseList) {
        this.ladderParseList = ladderParseList;
    }

    public String getCancelTag() {
        return cancelTag;
    }

    public void setCancelTag(String cancelTag) {
        this.cancelTag = cancelTag;
    }
}
