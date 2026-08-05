package cn.vetech.center.hotel.link.api.ratesearch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 变更、取消规则
 *
 * @author SongJun  8963
 */
public class SearchPrepayRule implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 规则编码
     */
    @ApiModelProperty(value = "规则编码", dataType = "string")
    private String prepayRuleId;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", dataType = "string")
    private String description;
    /**
     * 简版的取消规则说明
     */
    @ApiModelProperty(value = "简版的取消规则说明", dataType = "string")
    private String qxgzsmcut;
    /**
     * 转换时间后的描述
     */
    @ApiModelProperty(value = "转换时间后的描述", dataType = "string")
    private String convertDescription;
    /**
     * 日期类型
     */
    @ApiModelProperty(value = "日期类型", dataType = "string")
    private String dateType;
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
     * 周有效设置
     */
    @ApiModelProperty(value = "周有效设置", dataType = "string")
    private String weekSet;
    /**
     * 变更规则,-1不允许变更取消,1在到店当日24点前Hour小时前按规则看是否可以免费变更取消(一般是不收罚金),在Hour和Hour2之间按规则存在罚金,Hour2之后不能变更取消,0在约定日期时间点(DateNum+Time)前可以免费变更取消
     */
    @ApiModelProperty(value = "变更规则", dataType = "string")
    private String changeRule;
    /**
     * 具体取消时间日期部分,用于PrepayNeedOneTime
     */
    @ApiModelProperty(value = "具体取消时间日期部分,用于PrepayNeedOneTime", dataType = "string")
    private String dateNum;
    /**
     * 具体取消时间小时部分,用于PrepayNeedOneTime,用于PrepayNeedOneTime
     */
    @ApiModelProperty(value = "具体取消时间小时部分,用于PrepayNeedOneTime,用于PrepayNeedOneTime", dataType = "string")
    private String time;
    /**
     * 在变更时间点前是否扣费,用于PrepayNeedSomeDay的Hour前扣款类型(一般不收罚金)
      */
    @ApiModelProperty(value = "在变更时间点前是否扣费,用于PrepayNeedSomeDay的Hour前扣款类型(一般不收罚金)", dataType = "string")
    private String deductFeesBefore;
    /**
     * 时间点前扣费的金额或比例,用于PrepayNeedSomeDay的Hour前扣款类型(一般不收罚金)
     */
    @ApiModelProperty(value = "时间点前扣费的金额或比例,用于PrepayNeedSomeDay的Hour前扣款类型(一般不收罚金)", dataType = "string")
    private String deductNumBefore;
    /**
     * 时间点前扣款类型,Money金额Percent比例FristNight首晚
     */
    @ApiModelProperty(value = "时间点前扣款类型,Money金额Percent比例FristNight首晚", dataType = "string")
    private String cashScaleFirstBefore;
    /**
     * 时间点后扣款类型,Money金额Percent比例FristNight首晚
     */
    @ApiModelProperty(value = "时间点后扣款类型,Money金额Percent比例FristNight首晚", dataType = "string")
    private String cashScaleFirstAfter;
    /**
     * 在变更时间点后是否扣费,用于PrepayNeedSomeDay的Hour到Hour2之间的扣款类型
     */
    @ApiModelProperty(value = "在变更时间点后是否扣费,用于PrepayNeedSomeDay的Hour到Hour2之间的扣款类型", dataType = "string")
    private String deductFeesAfter;
    /**
     * 时间点后扣费的金额或比例,用于PrepayNeedSomeDay的Hour到Hour2之间的扣款类型
     */
    @ApiModelProperty(value = "时间点后扣费的金额或比例,用于PrepayNeedSomeDay的Hour到Hour2之间的扣款类型", dataType = "string")
    private String deductNumAfter;
    /**
     * 第一阶段提前几小时,用于PrepayNeedSomeDay
     */
    @ApiModelProperty(value = "第一阶段提前几小时,用于PrepayNeedSomeDay", dataType = "string")
    private String hour;
    /**
     * 第二阶段提前几小时,用于PrepayNeedSomeDay 去哪儿添加的退款规则
     */
    @ApiModelProperty(value = "第二阶段提前几小时,用于PrepayNeedSomeDay 去哪儿添加的退款规则", dataType = "string")
    private String hour2;
    /**
     *
     */
    @ApiModelProperty(value = "", dataType = "string")
    private String canDeductDate;
    /**
     * FIRST_NIGHT_PRICE首晚  FULL_NIGHT_PRICE 全额
     */
    @ApiModelProperty(value = "FIRST_NIGHT_PRICE首晚  FULL_NIGHT_PRICE 全额", dataType = "string")
    private String deductBaseType;
    /**
     *
     */
    @ApiModelProperty(value = "", dataType = "string")
    private String deductPercent;