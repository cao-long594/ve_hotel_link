package cn.vetech.center.hotel.link.enums;/**
 * Created by vetech on 2019/2/21.
 */

import org.apache.commons.lang.StringUtils;

/**
 * 〈酒店字典类型枚举〉
 *
 * @author chengzhibing
 * @since 2019/3/29
 */
public enum HotelImageTypeEnum {
    /**** .*/
    ZT("0", "主页展示图"),
    /**** .*/
    CT("1", "餐厅"),
    /**** .*/
    XXS("2", "休闲室"),
    /**** .*/
    HYS("3", "会议室"),
    /**** .*/
    FW("4", "服务"),
    /**** .*/
    JDWG("5", "酒店外观"),
    /**** .*/
    DTJDT("6", "大堂/接待台"),
    /**** .*/
    JDJS("7", "酒店介绍"),
    /**** .*/
    FST("8", "房型图"),
    /**** .*/
    BJT("9", "背景图"),
    /**** .*/
    QT("10", "其他");

    /**
     * 类型编号
     */
    private final String type;
    /**
     * 类型名称
     */
    private final String name;

    private HotelImageTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }



    public String getName() {
        return name;
    }



    /**
     * 获取类型名称
     *
     * @param type 类型
     * @return 类型名称
     */
    public static String getName(String type) {
        if (StringUtils.isEmpty(type)) {
            return type;
        }
        for (HotelImageTypeEnum b : HotelImageTypeEnum.values()) {
            if (b.type.equals(type)) {
                return b.name;
            }
        }
        return null;
    }
}
