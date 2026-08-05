package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ElongSearchHAvailPolicy {
    /**
     * 提示编号
     */
    @JsonProperty("Id")
    private String id;
    /**
     * 提示内容
     */
    @JsonProperty("AvailPolicyText")
    private String availPolicyText;
    /**
     * 有效开始时间
     */
    @JsonProperty("StartDate")
    private String availPolicyStart;
    /**
     * 有效结束时间
     */
    @JsonProperty("EndDate")
    private String availPolicyEnd;
    /**
     * 描述
     */
    @JsonProperty("Description")
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
