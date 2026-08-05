package cn.vetech.center.hotel.link.elong.common;

/**
 * @author xiaotengyu
 * @since 2024-12-16 10:11
 */
public enum ElongBoardTypeEnum {

    /**
     * 早餐
     */
    T1("1","早餐"),
    /**
     * 午餐
     */
    T2("2","午餐"),
    /**
     * 晚餐
     */
    T3("3","晚餐"),
    /**
     * 未知餐型
     */
    T0("0","未知餐型"),

    ;

    /**
     * 值
     */
    private String val;
    /**
     * 备注
     */
    private String desc;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ElongBoardTypeEnum(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }
}
