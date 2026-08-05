package cn.vetech.center.hotel.link.api.orderdetail.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 间夜数据
 *
 * @author SongJun 8963
 */
public class DetailNightlyRate implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期", dataType = "string")
    private String date;
    /**
     * 会员卖价
     */
    @ApiModelProperty(value = "会员卖价", dataType = "string")
    private String member;
    /**
     * 结算价 仅预付
     */
    @ApiModelProperty(value = "结算价 仅预付", dataType = "string")
    private String cost;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }


}
