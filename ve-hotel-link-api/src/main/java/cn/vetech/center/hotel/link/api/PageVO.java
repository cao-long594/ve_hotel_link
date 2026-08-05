package cn.vetech.center.hotel.link.api;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 分页基本信息
 * </p>
 *
 * @author wangkai
 * @since 2020/10/16
 */
public class PageVO {

    /**
     * 其他条件，根据供应商实际情况而定
     */
    @ApiModelProperty(value = "其他条件，根据供应商实际情况而定", dataType = "String")
    private String condition;
    /**
     * 是否是最后一次
     */
    @ApiModelProperty(value = "是否是最后一页", dataType = "Boolean")
    private Boolean isLast = true;
    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数", dataType = "Integer")
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public PageVO() {
    }

    public PageVO(String condition, Boolean isLast) {
        this.condition = condition;
        this.isLast = isLast;
    }

    public PageVO(Boolean isLast) {
        this.isLast = isLast;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }
}
