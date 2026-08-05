package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ElongSearchValueAdd {
    /**
     * 增值服务编号
     */
    @JsonProperty("ValueAddId")
    private String valueAddId;
    /**
     * 业务代码。01-早餐 02-午餐 03-晚餐 04-宽带上网 05-服务费 06-政府税
     * 99-特殊早餐(可能存在不同日期段的多个记录，优先级高于01普通早餐)
     */
    @JsonProperty("TypeCode")
    private String typeCode;
    /**
     * 附加服务描述，代理不想解析的话，可以直接显示该描述
     */
    @JsonProperty("Description")
    private String description;
    /**
     * 是否包含在房费中。false-不包含 true-包含
     */
    @JsonProperty("IsInclude")
    private String isInclude;
    /**
     * 包含的份数
     */
    @JsonProperty("Amount")
    private String amount;
    /**
     * 货币代码
     */
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    /**
     * 单价默认选项。Money-金额，Percent-比例，None-无效
     */
    @JsonProperty("PriceOption")
    private String priceOption;
    /**
     * 单价
     * 视PriceOption表示金额或比例
     * 比例值保存的百分数，不是最终的小数，例如 20%，则该字段保存为20
     */
    @JsonProperty("Price")
    private String price;
    /**
     * 是否单加。目前只有早餐服务该字段有意义
     */
    @JsonProperty("IsExtAdd")
    private String isExtAdd;
    /**
     * 单加单价默认选项。Money-金额，Percent-比例
     */
    @JsonProperty("ExtOption")
    private String extOption;
    /**
     * 单加单价。视 extOption 不同表示金额或比例值, 比例值保存的百分数，不是最终的小数
     * 例如 20%， 则该字段保存为20
     */
    @JsonProperty("ExtPrice")
    private String extPrice;
    /**
     * 开始日期
     */
    @JsonProperty("StartDate")
    private String startDate;
    /**
     * 结束日期
     */
    @JsonProperty("EndDate")
    private String endDate;
    /**
     * 周有效设置
     * 仅对特殊早餐有效,表示在指定的日期范围内符合星期设置的条件下
     * 该产品的早晨服务使用本条信息；特殊早晨优先于通常的早晨设置
     * EndDate是包含该天
     */
    @JsonProperty("WeekSet")
    private String weekSet;

    public String getValueAddId() {
        return valueAddId;
    }
