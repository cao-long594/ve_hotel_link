package cn.vetech.center.hotel.link.elong.common;

import cn.vetech.center.hotel.link.enums.HotelImageTypeEnum;

import java.util.stream.Stream;

/**
 * @author xiaotengyu
 * @since 2022-11-17 16:50
 */
public class ElongImageEnum {

    /**
     * <p>
     * 艺龙图片与标准图片映射关系枚举
     * </p>
     * @author wangkai
     * @since 2020/11/24
     */
    public enum ElongImageTypeMapEnum {

        /**
         * 餐厅
         */
        CT_MAP(HotelImageTypeEnum.CT, "1", "餐厅"),
        /**
         * 休闲室
         */
        XXS_MAP(HotelImageTypeEnum.XXS, "2", "休闲室"),
        /**
         * 会议室
         */
        HYS_MAP(HotelImageTypeEnum.HYS, "3", "会议室"),
        /**
         * 服务
         */
        FW_MAP(HotelImageTypeEnum.FW, "4", "服务"),
        /**
         * 酒店外观
         */
        JDWG_MAP(HotelImageTypeEnum.JDWG, "5", "酒店外观"),
        /**
         * 大堂/接待台
         */
        DTJDT_MAP(HotelImageTypeEnum.DTJDT, "6", "大堂/接待台"),
        /**
         * 酒店介绍
         */
        JDJS_MAP(HotelImageTypeEnum.JDJS, "7", "酒店介绍"),
        /**
         * 房型图
         */
        FST_MAP(HotelImageTypeEnum.FST, "8", "房型图/客房 (Guest Room) "),
        /**
         * 背景图
         */
        BJT_MAP(HotelImageTypeEnum.BJT, "9", "背景图"),
        /**
         * 其他
         */
        QT_MAP(HotelImageTypeEnum.QT, "10", "其他");


        /**
         *
         */
        private final HotelImageTypeEnum typeEnum;

        /**
         *
         */
        private final String elongImageType;


        /**
         *
         */
        private final String elongDesc;


        private ElongImageTypeMapEnum(HotelImageTypeEnum typeEnum, String elongImageType, String elongDesc) {
            this.typeEnum = typeEnum;
            this.elongImageType = elongImageType;
            this.elongDesc = elongDesc;
        }

       /**
         * 图片转换将艺龙图片类型转成本地图片类型
         * @param elongImageType elongImageType
         * @return java.lang.String
         */
        public static String convertType(String elongImageType) {
            return Stream.of(ElongImageTypeMapEnum.values()).filter(elongImageTypeMapEnum -> elongImageTypeMapEnum.getElongImageType().equals(elongImageType)).findFirst().orElse(QT_MAP).getTypeEnum().getType();
        }

        public HotelImageTypeEnum getTypeEnum() {
            return typeEnum;
        }



        public String getElongImageType() {
            return elongImageType;
        }



        public String getElongDesc() {
            return elongDesc;
        }


    }

    public enum ElongImageSizeEnum
    {
        /**
         * 350_350
         */
        S1("1"),
        /**
         *640_960
         */
        S7("7"),
        /**
         *1080_800
         */
        S8("8"),
        /**
         *1140_640
         */
        S9("9"),
        /**
         *800_600
         */
        S10("10"),
        /**
         *960_640
         */
        S11("11"),
        /**
         *375_200
         */
        S12("12")
                ;
        /**
         * 值
         */
        private final String val;

        private ElongImageSizeEnum(String val) {
            this.val = val;
        }

        public String getVal() {
            return val;
        }


    }

}