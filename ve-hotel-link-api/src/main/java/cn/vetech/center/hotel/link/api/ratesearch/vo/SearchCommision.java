package cn.vetech.center.hotel.link.api.ratesearch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 佣金
 *
 * @author Songjun 8963
 */
public class SearchCommision implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 返佣类型：NUL无返佣,PCT百分比,FIX固定返佣
     */
    @ApiModelProperty(value = "返佣类型：NUL无返佣,PCT百分比,FIX固定返佣", dataType = "string")
    private String commissionType;
    /**
     * 返佣的百分比,如果是固定返佣,反算返佣率
     */
    @ApiModelProperty(value = "返佣的百分比,如果是固定返佣,反算返佣率", dataType = "string")
    private String percent;
    /**
     * 固定的佣金值
     */
    @ApiModelProperty(value = "固定的佣金值", dataType = "string")
    private String fix;
    /**
     * 税金
     */
    @ApiModelProperty(value = "税金", dataType = "string")
    private String tax;

    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

}
