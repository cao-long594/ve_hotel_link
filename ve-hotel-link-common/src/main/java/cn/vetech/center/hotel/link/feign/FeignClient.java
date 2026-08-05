package cn.vetech.center.hotel.link.feign;

import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import com.google.common.collect.Lists;
import feign.Feign;
import feign.Feign.Builder;
import feign.Logger.Level;
import feign.Request.Options;
import feign.RequestTemplate;
import feign.Retryer.Default;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

/**
 * @author wangkai
 * @since 2021/1/23
 */
public abstract class FeignClient {

    /**
     * 空集合
     */
    private static List<String> emptyList = Lists.newArrayList();


    /**
     * 请求链接配置 超时时间 链接时间
     */
    protected Options options = new Options();


    /**
     * 获取client
     *
     * @param url       url
     * @param kClass    kClass
     * @param headerMap headerMap
     * @param retryer   retryer
     * @param logLevel  logLevel
     * @param <K>       K
     * @return K
     */
    public <K> K getClient(String url, Class<K> kClass, Map<String, String> headerMap, Default retryer, Level logLevel) {
        Builder builder = Feign.builder();
        if (MapUtils.isNotEmpty(headerMap)) {
            builder.requestInterceptor(new BaseAuthInterceptor(headerMap));
        }
        if (retryer != null) {
            builder.retryer(retryer);
        }
        builder.options(getOptions()).logLevel(logLevel == null ? Level.NONE : logLevel);
        builder.decoder(getDecoder());
        builder.encoder(getEncoder());
        return builder.target(kClass, url);

    }

    /**
     * 获取编码
     *
     * @return feign.codec.Encoder
     */
    public Encoder getEncoder() {


            return new DefaultEncoder();
    }

    /**
     * 获取解码
     *
     * @return feign.codec.Decoder
     */
    public Decoder getDecoder() {
        return new Decoder.Default();
    }

    /**
     * 获取client
     *
     * @param url    url
     * @param kClass kClass
     * @param <K>    K
     * @return K
     */
    public <K> K getClient(String url, Class<K> kClass) {

        return getClient(url, kClass, null, null);
    }

    /**
     * 获取client
     *
     * @param url     url
     * @param kClass  kClass
     * @param retryer retryer
     * @param <K>     K
     * @return K
     */
    public <K> K getClient(String url, Class<K> kClass, Default retryer) {

        return getClient(url, kClass, null, retryer, null);
    }


    /**
     * 获取client
     *
     * @param url       url
     * @param kClass    kClass
     * @param headerMap headerMap
     * @param retryer   retryer
     * @param <K>       K
     * @return K
     */
    public <K> K getClient(String url, Class<K> kClass, Map<String, String> headerMap, Default retryer) {

        return getClient(url, kClass, headerMap, retryer, null);
    }

    /**
     * 获取client
     *
     * @param url       url
     * @param kClass    kClass
     * @param headerMap headerMap
     * @param <K>       K
     * @return K
     */
    public <K> K getClient(String url, Class<K> kClass, Map<String, String> headerMap) {

        return getClient(url, kClass, headerMap, null);
    }

    /**
     * 获取参数
     *
     * @return Options
     */
    public Options getOptions() {
        return options;
    }


    static class DefaultEncoder implements Encoder {

        /**
         * 编码
         *
         * @param object   object
         * @param bodyType bodyType
         * @param template template
         */
        @Override
        public void encode(Object object, Type bodyType, RequestTemplate template) {
            if (bodyType == String.class) {
                template.body(object.toString());
                return;
            }
            if (bodyType == byte[].class) {
                template.body((byte[]) object, null);
                return;
            }
            if (object != null && template.headers().getOrDefault("Content-Type", emptyList).contains("application/json;charset=utf-8")) {
                template.body(JacksonUtils.toJsonWithNonEmpty(object));
                return;
            }
            if (object != null && CollectionUtils.isEmpty(template.headers().get("Content-Type"))) {

                Map<String, String> map = null;
                if (object instanceof Map) {
                    map = (Map) object;
                } else {
                    map = BeanMapper.map(object, Map.class);
                }
                map.forEach(template::query);
                return;
            }
            if (object != null) {
                throw new EncodeException(
                        format("%s is not a type supported by this encoder.", object.getClass()));

            }
        }
    }
}
