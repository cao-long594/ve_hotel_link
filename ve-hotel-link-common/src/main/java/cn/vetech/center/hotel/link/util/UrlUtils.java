package cn.vetech.center.hotel.link.util;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.enums.ImageFileEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * url工具类
 *
 * @author luqs
 * @version v1.0
 **/
public class UrlUtils {
    private UrlUtils() {
    }

    /**
     * url分隔符：/
     */
    private static final String URL_SEPARATOR = "/";

    /**
     * 补全url
     * <br/>如：https:api.vetech.com -> https:api.vetech.com/
     *
     * @param url url
     * @return String
     */
    public static String completeUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return url;
        }

        url = trim(url);
        if (url.endsWith(URL_SEPARATOR)) {
            return url;
        }
        return url + URL_SEPARATOR;
    }

    /**
     * 补全url
     * <br/>如：https:api.vetech.com -> https:api.vetech.com/
     *
     * @param url url
     * @param api api
     * @return String
     */
    public static String completeUrl(String url, String api) {
        api = StringUtils.defaultIfBlank(api, StringUtils.EMPTY);
        if (StringUtils.isBlank(url)) {
            return StringUtils.defaultIfBlank(url, StringUtils.EMPTY) + api;
        }

        url = trim(url);
        url = url.endsWith(URL_SEPARATOR) ? url : url + URL_SEPARATOR;
        api = api.startsWith(URL_SEPARATOR) ? api.replaceFirst(URL_SEPARATOR, StringUtils.EMPTY) : api;
        return url + api;
    }


    /**
     * 去除前尾空字符（空白符主要包括' '，'\t'，'\r'，'\n'等等）
     *
     * @param configValue 配置值
     * @return String
     */
    public static String trim(String configValue) {
        return StringUtils.trim(configValue);
    }

    /**
     * 处理图片url
     * @param fybh 房源编号
     * @param url 图片地址
     * @return 图片处理后地址
     */
    public static String getXcwOutWaterImageUrl(String fybh,String url){
        if(StringUtils.equalsIgnoreCase(FyEnum.XCW.getFybh(),fybh)){
            //如果是无水印地址；则不处理返回
            boolean nowaterUrl = jugeIsNowaterUrl(url);
            if(nowaterUrl){
                return url;
            }
            //1.判断图片适用的那种切割方式
            Optional<ImageFileEnum.XcwImageCutEnum> xcwImageCutEnum = ImageFileEnum.XcwImageCutEnum.containVal(url);
            return xcwImageCutEnum.map(cutEnum->{
                String regin = String.format("%s_[+]{0,1}(\\d+)_[+]{0,1}(\\d+)",cutEnum.name());
                String imageCutContent = getImageCutContent(url);
                //添加无水印编辑
                String nowaterImageCut = imageCutContent + SymbolConstant.XCW_IMAGE_NOWATER_SIGN;
                return url.replaceAll(regin,nowaterImageCut);
            }).orElse(url);
        }
        return url;
    }

    /**
     * 获取图片切割内容
     * @param url 图片地址
     * @return 切割内容
     */
    private static String getImageCutContent(String url){
        if(StringUtils.isBlank(url)){
            return StringUtils.EMPTY;
        }
        Pattern cutPattern = Pattern.compile("R_[+]{0,1}(\\d+)_[+]{0,1}(\\d+)");
        Matcher cutMatcher = cutPattern.matcher(url);
        if(!cutMatcher.find()){
            return "R_550_412";
        }
        return cutMatcher.group();
    }

    /**
     * 判断是否为无水印图片地址
     * @param url 图片地址
     * @return true：是无水印图片地址；false：是有水印图片地址
     */
    public static boolean jugeIsNowaterUrl(String url){
        if(StringUtils.isBlank(url)){
            return false;
        }

        Pattern pattern = Pattern.compile("R_[+]{0,1}(\\d+)_[+]{0,1}(\\d+)" + SymbolConstant.XCW_IMAGE_NOWATER_SIGN);
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }
}