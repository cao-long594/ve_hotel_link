package cn.vetech.center.hotel.link.elong.ratesearch.response;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2021/12/14 10:27
 */
public class ElongSearchMeals {
    /**
     * 餐食文案描述  总餐食描述
     */
    private String mealCopyWriting;
    /**
     * 每日餐食表格
     * 包含多个DayMeal，见DayMeal节点；
     * 具体场景demo可见：http://open.elong.com/faq/detail?id=312&plt=2
     */
    private List<ElongSearchDayMeal> dayMealTable;
    /**
     * 是否存在餐食表格
     * 为true代表取“dayMealTable”餐食表格字段，查看每天的餐食情况
     */
    private Boolean hasMealTable;

    public String getMealCopyWriting() {
        return mealCopyWriting;
    }

    public void setMealCopyWriting(String mealCopyWriting) {
        this.mealCopyWriting = mealCopyWriting;
    }

    public List<ElongSearchDayMeal> getDayMealTable() {
        return dayMealTable;
    }

    public void setDayMealTable(List<ElongSearchDayMeal> dayMealTable) {
        this.dayMealTable = dayMealTable;
    }

    public Boolean getHasMealTable() {
        return hasMealTable;
    }

    public void setHasMealTable(Boolean hasMealTable) {
        this.hasMealTable = hasMealTable;
    }
}
