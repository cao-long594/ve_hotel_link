package cn.vetech.center.hotel.link.api.enums;

/**
 * @author chengwanshan
 * @since 2025/1/21 15:03
 */
public enum FileTypeEnum {
    /**
     * PDF
     */
    PDF("pdf", "pdf"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private FileTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
