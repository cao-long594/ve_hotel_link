package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaotengyu
 * @since 2024-05-10 17:29
 */
public class ElongInterBedGroupBedType {

    /**
     * 床类型id	String	Y	国际特有字段
     */
    @JsonProperty("BedTypeId")
    private String bedTypeId;
    /**
     * 床类型名称	String	Y	国际特有字段
     */
    @JsonProperty("BedTypeName")
    private String bedTypeName;
    /**
     * 床类型	String	Y	国际特有字段 大床,单人床,双层床,圆床,壁柜床,婴儿床,子母床,小床,胶囊床,沙发床,水床,通铺,折叠床,拼床,由酒店安排 等
     */
    @JsonProperty("BedType")
    private String bedType;
    /**
     * 床数	Int	Y	国际特有字段
     */
    @JsonProperty("Count")
    private String count;
    /**
     * 床大小	String	Y	国际特有字段
     */
    @JsonProperty("Size")
    private String size;

    public String getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(String bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
