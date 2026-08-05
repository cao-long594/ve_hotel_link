package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author xiaotengyu
 * @since 2024-05-10 17:26
 */
public class ElongInterBedGroup {

    /**
     * 床型信息id	String	Y	国际特有字段
     */
    @JsonProperty("BedGroupId")
    private String bedGroupId;
    /**
     * 床型信息描述	String	Y	国际特有字段
     */
    @JsonProperty("BedGroupDesc")
    private String bedGroupDesc;
    /**
     * 床类型	BedType[]	Y	参考BedType节点 国际特有字段
     */
    @JsonProperty("BedTypes")
    private List<ElongInterBedGroupBedType> bedTypes;

    public String getBedGroupId() {
        return bedGroupId;
    }

    public void setBedGroupId(String bedGroupId) {
        this.bedGroupId = bedGroupId;
    }

    public String getBedGroupDesc() {
        return bedGroupDesc;
    }

    public void setBedGroupDesc(String bedGroupDesc) {
        this.bedGroupDesc = bedGroupDesc;
    }

    public List<ElongInterBedGroupBedType> getBedTypes() {
        return bedTypes;
    }

    public void setBedTypes(List<ElongInterBedGroupBedType> bedTypes) {
        this.bedTypes = bedTypes;
    }
}
