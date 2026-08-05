package cn.vetech.center.hotel.link.api.ratesearch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 每日价格明细
 *
 * @author SongJun  8963
 */
public class SearchNightlyRate implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当天日期
     */
    @ApiModelProperty(value = "当天日期", dataType = "string")
    private String date;
    /**
     * 税后价,-1表示不能销售
     */
    @ApiModelProperty(value = "税后价,-1表示不能销售", dataType = "string")
    private String priceAfterTax;
    /**
     * 税后价（原始币种）
     */
    private FeeInfo originPriceAfterTax;
    /**
     * 税前价
     */
    @ApiModelProperty(value = "税前价", dataType = "string")
    private String priceBeforTax;
    /**
     * 税前价（原始币种）
     */
    private FeeInfo originPriceBeforTax;
    /**
     * kzclxsj控制差旅销售价  可空
     */
    @ApiModelProperty(value = "控制差旅销售价  可空", dataType = "string")
    private String kzclxsj;
    /**
     * 控制分销销售价  可空
     */
    @ApiModelProperty(value = "控制分销销售价  可空", dataType = "string")
    private String kzfxxsj;
    /**
     * 控制直销销售价  可空
     */
    @ApiModelProperty(value = "控制直销销售价  可空", dataType = "string")
    private String kzzxxsj;
    /**
     * 门市价
     * <br/><strong>注：采用挂牌价"gpj"替代！！！</strong>
     */
    @ApiModelProperty(value = "门市价", dataType = "string")
    @Deprecated
    private String msj;
    /**
     * 税费
     */
    @ApiModelProperty(value = "税费", dataType = "string")
    private String tax;
    /**
     * 库存状态,true表示当天库存可用
     */
    @ApiModelProperty(value = "库存状态,true表示当天库存可用", dataType = "string")
    private String status;
    /**
     * 自签价格真实底价 预付会写这个值
     */
    @ApiModelProperty(value = "自签价格真实底价", dataType = "bigdecimal")
    private BigDecimal zqjgdj;
    /**
     * 优惠金额（目前仅用于前端展示）
     */
   @ApiModelProperty(value = "优惠金额", dataType = "string")
    private BigDecimal discountAmount;
    /**
     * 结算币种-房费
     */
    private BigDecimal jsbzFf;