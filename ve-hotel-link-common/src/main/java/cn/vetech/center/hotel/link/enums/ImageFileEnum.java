package cn.vetech.center.hotel.link.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author xiaotengyu
 * @since 2021/3/18 20:54
 */
public class ImageFileEnum {

    /***
     * 是否有效
     */
    public enum ImageYXEnum {
        /***
         * 有效
         */
        YX("1", "有效"),
        /***
         * 无效
         */
        WX("0", " 无效");

        /**
         * code
         */
        private final String code;
        /***
         * desc
         */
        private final String desc;
        ImageYXEnum(String c, String d) {
            this.code = c;
            this.desc = d;
        }

        public String getCode() {
            return code;
        }



        public String getDesc() {
            return desc;
        }


    }

    /***
     * 下载状态
     */
    public enum ImageDownloadStatusEnum {

        /**
         * 未开始
         */
        W("0", "未开始"),
        /***
         * 成功
         */
        S("1", "成功"),
        /***
         * 弃用
         */
        F("2", "弃用");

        /**
         * code
         */
        private final String code;
        /***
         * desc
         */
        private final String desc;

        ImageDownloadStatusEnum(String c, String d) {
            this.code = c;
            this.desc = d;
        }

        public String getCode() {
            return code;
        }



        public String getDesc() {
            return desc;
        }



    }

    /**
     * 图片种类
     */
    public enum ImageKindEnum {

        /**
         * 酒店
         */
        JD("1", "酒店"),
        /**
         * 房型
         */
        FX("2", "房型");

        /**
         * code
         */
        private final String code;
        /***
         * desc
         */
        private final String desc;
        ImageKindEnum(String c, String d) {
