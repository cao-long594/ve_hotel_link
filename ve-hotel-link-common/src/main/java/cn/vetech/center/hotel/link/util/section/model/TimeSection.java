package cn.vetech.center.hotel.link.util.section.model;

/**
 * 时间分段
 *
 * @author luqs
 * @version v1.0
 **/
public class TimeSection {
    /**
     * 起始时间
     */
    private String start;
    /**
     * 截止时间
     */
    private String end;
    /**
     * 其他条件，根据供应商实际情况而定
     */
    private String condition;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
