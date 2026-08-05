package cn.vetech.center.hotel.link.enums;

/**
 * 价格预订类型  为空或0 指标准预订流程 、1：查价格需要传入房间人数（例如好巧）这种类型需要弹出房间人数输入框实时拉取接口价格
 * @author xiaotengyu
 * @since 2022-12-30 15:04
 */
public enum HotelJgydlxEnum {

    /**
     * 指标准预订流程
     */
    GENERAL("0"),
    /**
     * 特殊预定：查价格需要传入房间人数（例如好巧）这种类型需要弹出房间人数输入框实时拉取接口价格
     */
    SPECIAL("1")
    ;
    /**
     * 值
     */
    private final String val;

    private HotelJgydlxEnum(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }


}
