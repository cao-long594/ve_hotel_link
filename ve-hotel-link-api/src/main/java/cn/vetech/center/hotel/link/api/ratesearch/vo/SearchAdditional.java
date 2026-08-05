package cn.vetech.center.hotel.link.api.ratesearch.vo;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 附加费
 *
 * @author Songjun 8963
 */
public class SearchAdditional implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 附加费代码
     */
    @ApiModelProperty(value = "附加费代码", dataType = "string")
    private String feeCode;
    /**
     * 附加费名称，如：服务费、城市税 等
     */
    @ApiModelProperty(value = "附加费名称", dataType = "string")
    private String feeName;
    /**
     * 收费频率，如：每次预订、每人每晚、每间客房每晚、每间客房每间预订 等
     * 参考枚举ChargeFrequenceEnum
     */
    @ApiModelProperty(value = "收费频率", dataType = "string")
    private String chargeFrequence;
    /**
     * 价格
     */
    @ApiModelProperty(value = "价格", dataType = "string")
    private String amount;
    /**
     * 币种，参考枚举CurrencyEnum
     */
    @ApiModelProperty(value = "币种", dataType = "string")
    private String currency;
    /**
     * 收费单元
     */
    @ApiModelProperty(value = "收费单元", dataType = "string")
    private String chargeUnit;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", dataType = "string")
    private String comments;
    /**
     * 描述，cps展示的描述
     */
    @ApiModelProperty(value = "描述", dataType = "string")
    private String cpsComments;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", dataType = "string")
    private String count;
    /**
     * 类型，参考枚举AdditionalChargeEnum
     * Excluded： 创建预订时不收取附加费；顾客将直接在酒店支付附加费
     * MANDATORY：创建预订时要收取附加费；已计算到每日房费 和 房费总价中
     */
    private String charge;

    public String getCpsComments() {
        return cpsComments;
    }

    public void setCpsComments(String cpsComments) {
        this.cpsComments = cpsComments;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public Strin