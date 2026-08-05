package cn.vetech.center.hotel.link.elong.common;

import cn.vetech.charge.base.CommonMagicNumber;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author chengwanshan
 * @since 2021/7/14 18:05
 */
public class ElongSecurityUtil {
    /**
     * AES
     */
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * @param args args
     * @throws Exception e
     */
    public static void main(String[] args) throws Exception {
        String data = "/GweSdHWnCLQ+4sQwvyI4Ci/zX38tuJ03rwM3QOdhvGCpFW2R5bXEGqaiUb3I9/ZM23bmLMIaYie\r\n9mjLHOiD4yHY8OK4irgIwKzbEtB4r3mEBuhmAVnZ9vqqVUIM6BUinPlO+tTp3Rq+IMdOiH5UNjdg\r\nUD0YsE+Rpf6DX43YQClVnB6paVjgNXlKv1rk4RFwFvajhUBYXUkMqg3mPRY3q1B/A1UyMMfcf96t\r\nuT/ugV7aAwnER40yC5LH0jrzT8PrtxFL3QjKHqgrk2rj3VknOmn3h1Jz9ClMAMaCoMa3kvJj2PTj\r\nTcTVMQd5lOF9549ZENACckwqvDU0UWjA3Ekv/TEpz3BTXM1Z4FSAyBgO5P0EcZGe8cO3lhooBT0c\r\nxig9\r\n";
        String decrypt = decrypt(data, "34e63633");
    }

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     密钥
     * @return String
     * @throws Exception e
     */
    public static String encrypt(String content, String key) throws Exception {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        byte[] byteContent = content.getBytes("utf-8");
        // 初始化为加密模式的密码器
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
        // 加密
        byte[] result = cipher.doFinal(byteContent);
        // 通过Base64转码返回
        return Base64.encodeBase64String(result);
    }

    /**
     * AES 解密操作
     *
     * @param content 待解密内容
     * @param key     密钥
     * @return String
     * @throws Exception e
     */
    public static String decrypt(String content, String key) throws Exception {
        // 实例化
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        // 使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
        // 执行操作
        byte[] result = cipher.doFinal(Base64.decodeBase64(content));
        return new String(result, "utf-8");
    }

    /**
     * 生成加密秘钥
     *
     * @param key 密钥
     * @return SecretKeySpec
     * @throws Exception E
     */
    private static SecretKeySpec getSecretKey(final String key) throws Exception {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        // AES 要求密钥长度为 128
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(key.getBytes());
        kg.init(CommonMagicNumber.INT128, secureRandom);
        // 生成一个密钥
        SecretKey secretKey = kg.generateKey();
        // 转换为AES专用密钥
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);

    }
}