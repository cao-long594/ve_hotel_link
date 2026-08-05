package cn.vetech.center.hotel.link.api.ratesearch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 增值服务
 *
 * @author SongJun 8963
 */
public class SearchValueAdd implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 增值服务编号
     */
    @ApiModelProperty(value = "增值服务编号", dataType = "string")
    private String valueAddId;
    /**
     * 业务代码,01-早餐 02-午餐 03-晚餐 04-宽带上网 05-服务费 06-政府税 99-特殊早餐(可能存在不同日期段的多个记录,优先级高于01普通早餐)
     */
    @ApiModelProperty(value = "业务代码,01-早餐 02-午餐 03-晚餐 04-宽带上网 05-服务费 06-政府税 99-特殊早餐(可能存在不同日期段的多个记录,优先级高于01普通早餐)", dataType = "string")
    private String typeCode;
    /**
     * 附加服务描述,代理不想解析的话,可以直接显示该描述
     */
    @ApiModelProperty(value = "附加服务描述,代理不想解析的话,可以直接显示该描述", dataType = "string")
    private String description;
    /**
     * 是否包含在房费中,false-不包含 true-包含
     */
    @ApiModelProperty(value = "是否包含在房费中,false-不包含 true-包含", dataType = "string")
    private String isInclude;
    /**
     * 包含的份数
     */
    @ApiModelProperty(value = "包含的份数", dataType = "string")
    private String amount;
    /**
     * 货币代码
     */
    @ApiModelProperty(value = "货币代码", dataType = "string")
    private String currencyCode;
    /**
     * 单价默认选项,Money-金额,Percent-比例,None-无效
     */
    @ApiModelProperty(value = "单价默认选项,Money-金额,Percent-比例,None-无效", dataType = "string")
    private String priceOption;
    /**
     * 单价,视PriceOption表示金额或比例, 比例值保存的百分数,不是最终的小数, 例如 20%, 则该字段保存为20
     */
    @ApiModelProperty(value = "单价,视PriceOption表示金额或比例, 比例值保存的百分数,不是最终的小数, 例如 20%, 则该字段保存为20", dataType = "string")
    private String price;
    /**
     * 是否单加,目前只有早餐服务该字段有意义
     */
    @ApiModelProperty(value = "是否单加,目前只有早餐服务该字段有意义", dataType = "string")
    private String isExtAdd;
    /**
     * 单加单价默认选项,Money-金额,Percent-比例
     */
    @ApiModelProperty(value = "单加单价默认选项,Money-金额,Percent-比例", dataType = "string")
    private String extOption;
    /**
     * 单加单价,视 extOption 不同表示金额或比例值, 比例值保存的百分数,不是最终的小数, 例如 20%, 则该字段保存为20
     */
    @ApiModelProperty(value = "单加单价,视 extOption 不同表示金额或比例值, 比例值保存的百分数,不是最终的小数, 例如 20%, 则该字段保存为20", dataType = "string")
    private String extPrice;
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期", dataType = "string")
    private String startDate;
    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期", dataType = "string")
    private String endDate;
    /**
     * 周有效设置,仅对特殊早餐有效,表示在指定的日期范围内符合星期设置的条件下,该产品的早晨服务使用本条信息,特殊早晨优先于通常的早晨设置,EndDate是包含该天
     */
    @ApiModelProperty(value = "周有效设置,仅对特殊早餐有效,表示在指定的日期范围内符合星期设置的条件下,该产品的早晨服务使用本条信息,特殊早晨优先于通常的早晨设置,EndDate是包含该天", dataType = "string")
    private String weekSet;