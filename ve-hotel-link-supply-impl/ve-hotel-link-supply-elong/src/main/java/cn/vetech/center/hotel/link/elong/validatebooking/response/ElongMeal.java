package cn.vetech.center.hotel.link.elong.validatebooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2025/10/15 14:46
 */
public class ElongMeal {
    /**
     * 餐食类型	String(2)	N
     */
    @JsonProperty("Type")
    private String type;
    /**
     * 是否包含在房费中	Boolean	N
     */
    @JsonProperty("IsInclude")
    private String isInclude;
    /**
     * 早餐份数	Int	N
     */
    @JsonProperty("NumberOfBreakfast")
    private String numberOfBreakfast;
    /**
     * 午餐份数	Int	N
     */
    @JsonProperty("NumberOfLunch")
    private String numberOfLunch;
    /**
     * 晚餐份数	Int	N
     */
    @JsonProperty("NumberOfDinner")
    private String numberOfDinner;
    /**
     * 餐食种类数量	Int	N
     */
    @JsonProperty("NumberOfTypeMeal")
    private String numberOfTypeMeal;
    /**
     * 可选餐食种类数量	Int	N
     */
    @JsonProperty("NumberOfOptionalMeal")
    private String numberOfOptionalMeal;
    /**
     * 可选餐食类型	String	Y
     */
    @JsonProperty("optionalMeals")
    private String optionalMeals;
    /**
     * 描述	String(500)	N
     */
    @JsonProperty("Description")
    private String description;
    /**
     * 早餐描述	String	Y
     */
    @JsonProperty("DescribeOfBreakfast")
    private String describeOfBreakfast;
    /**
     * 午餐描述	String	Y
     */
    @JsonProperty("DescribeOfLunch")
    private String describeOfLunch;
    /**
     * 晚餐描述	String	Y
     */
    @JsonProperty("DescribeOfDinner")
    private String describeOfDinner;
    /**
     * 开始日期	Date	Y
     */
    @JsonProperty("StartDate")
    private String startDate;
    /**
     * 结束日期	Date	Y
     */
    @JsonProperty("EndDate")
    private String endDate;
    /**
     * 周有效设置	String(20)	Y
     */
    @JsonProperty("WeekSet")
    private String weekSet;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type =