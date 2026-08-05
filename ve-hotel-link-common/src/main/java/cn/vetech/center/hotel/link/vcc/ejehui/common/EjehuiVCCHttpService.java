package cn.vetech.center.hotel.link.vcc.ejiehui.common;

import cn.vetech.center.hotel.link.http.HttpClientUtilExt;
import cn.vetech.center.hotel.link.http.HttpService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.vcc.ejiehui.enums.EjiehuiVccGeneralEnum;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;

/**
 * @author chengwanshan
 * @since 2025/4/17 11:12
 */
@Service
public class EjiehuiVCCHttpService {
    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(EjiehuiVCCHttpService.class);

    /**
     * httpservice
     */
    @Autowired
    private HttpService httpService = new HttpService();

    /**
     * @param responseT   responseT
     * @param request     request
     * @param config      config
     * @param apiSiteEnum apiSiteEnum
     * @param <T>         t
     * @param <R>         R
     * @return s
     */
    public <T, R> Optional<T> sendInvokeApi(Class<T> responseT
            , R request
            , EjiehuiVccConfig config
            , EjiehuiVccGeneralEnum.ApiSiteEnum apiSiteEnum) {
        EjiehuiVCCParam<R> param = new EjiehuiVCCParam<>();
        param.setMethod(apiSiteEnum.getCode());
        param.setTimestamp(String.valueOf(System.currentTimeMillis() / Long.parseLong("1000")));
        param.setV("3.0");
        param.setData(request);
        EjiehuiVCCBaseRequest baseRequest = new EjiehuiVCCBaseRequest();
        baseRequest.setAppKey(config.getAppKey());
        String requestJson = JacksonUtils.toJsonWithNonEmpty(param);
        String result = StringUtils.EMPTY;
        try {
            baseRequest.setParam(encryptDES(requestJson, config.getAppSecret()));
            result = httpService.doPostBody(config.getBaseUrl(), JacksonUtils.toJsonWithNonEmpty(baseRequest), HttpClientUtilExt.headMapJson());
            if (StringUtils.isEmpty(result)) {
                logger.warn("Ejiehui：{}请求供应商返回为空；请求参数:{}", apiSiteEnum.getName(), requestJson);
                return Optional.empty();
            }
            EjiehuiVCCBaseResponse baseResponse = JacksonUtils.parseNonEmpty(result, EjiehuiVCCBaseResponse.class);
            if (Objects.isNull(baseResponse)) {
                logger.warn("Ejiehui：{}请求供应商返回参数转换异常；请求参数:{}， 返回参数：{}", apiSiteEnum.getName(), requestJson, result);
                return Optional.empty();
            }
            String code = baseResponse.getCode();
            String msg = baseResponse.getMsg();
            String data = baseResponse.getData();
            if (!EjiehuiVccGeneralEnum.BaseCodeEnum.CODE_000000.getCode().equals(code)) {
                logger.warn("Ejiehui：{}请求供应商返回失败；请求参数:{}， 返回参数：{}", apiSiteEnum.getName(), requestJson, result);
                return Optional.empty();
            }
            T response = JacksonUtils.parseNonEmpty(data, responseT);
            if (Objects.isNull(response)) {
                logger.warn("Ejiehui：{}请求供应商返回业务参数为空；请求参数:{}，返回参数：{}", apiSiteEnum.getName(), requestJson, result);
                return Optional.empty();
            }
            logger.info("Ejiehui:{}，请求参数【{}】", apiSiteEnum.getName(), requestJson);
            return Optional.of(response);
        } catch (Exception ex) {
            logger.error("Ejiehui：{}异常；requestData:{};response:{}", apiSiteEnum.getName(), requestJson, result, ex);
        }
        return Optional.empty();
    }
    /**
     * 加密
     *
     * @param data=业务参数请求
     * @param key=app_secret
     * @return String
     */
    public static String encryptDES(String data, String key) {
        try {
            // 生成一个可信任的随机数源 ,   SHA1PRNG: 仅指定算法名称
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            // 从原始密钥数据创建 DESKeySpec 对象
            DESKeySpec deskey = new DESKeySpec(key.getBytes("UTF-8"));
            //创建一个密匙工厂，然后用它把 DESKeySpec 转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(deskey);
            //Cipher 对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用 密 匙 初 始 化 Cipher 对 象 ,
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return Base64.encodeBase64String(cipher.doFinal(data.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     /**
     * 解密
     *
     * @param data data
     * @param key  key
     * @return String
     */
    public static String decryptDES(byte[] data, String key) {
        try {
            // 算法要求有一个可信任的随机数源,   SHA1PRNG: 仅指定算法名称
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            // 创建一个 DESKeySpec 对象
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec 对象转换成 SecretKey 对象
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            // Cipher 对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化 Cipher 对象
            cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
            // 真正开始解密操作
            return new String(cipher.doFinal(data), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
