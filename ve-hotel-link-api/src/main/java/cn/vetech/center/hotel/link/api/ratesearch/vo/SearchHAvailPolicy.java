package cn.vetech.center.hotel.link.api.ratesearch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 酒店特殊提示
 *
 * @author SongJun 8963
 */
public class SearchHAvailPolicy implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 提示编号
     */
    @ApiModelProperty(value = "提示编号", dataType = "string")
    private String id;
    /**
     * 提示内容
     */
    @ApiModelProperty(value = "提示内容", dataType = "string")
    private String availPolicyText;
    /**
     * 有效开始时间
     */
    @ApiModelProperty(value = "有效开始时间", dataType = "string")
    private String availPolicyStart;
    /**
     * 有效结束时间
     */
    @ApiModelProperty(value = "有效结束时间", dataType = "string")
    private String availPolicyEnd;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", dataType = "string")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvailPolicyText() {
        return availPolicyText;
    }

    public void setAvailPolicyText(String availPolicyText) {
        this.availPolicyText = availPolicyText;
    }

    public String getAvailPolicyStart() {
        return availPolicyStart;
    }

    public void setAvailPolicyStart(String availPolicyStart) {
        this.availPolicyStart = availPolicyStart;
    }

    public String getAvailPolicyEnd() {
        return availPolicyEnd;
    }

    public void setAvailPolicyEnd(String availPolicyEnd) {
        this.availPolicyEnd = availPolicyEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
