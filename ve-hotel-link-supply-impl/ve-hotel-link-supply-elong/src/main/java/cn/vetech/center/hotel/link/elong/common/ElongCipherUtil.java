package cn.vetech.center.hotel.link.elong.common;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author xiaotengyu
 * @since 2023-06-21 14:58
 */
public class ElongCipherUtil {
    public static final String CHARSET = "UTF-8";
    public static final String KEY_DES = "DES";
    static final String CIPHER_DES = "DES/CBC/PKCS5Padding";
    private static final String HEX_CHARS = "0123456789abcdef";

    /**
     * DES对称加密
     *
     * @param content 加密内容
     * @param password 对称加密的key
     * @return  加密后结果
     * @throws Exception 异常
     */
    public static String desEncrypt(String content, String password) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(password.getBytes(CHARSET), KEY_DES);
        Cipher cipher = Cipher.getInstance(CIPHER_DES);
        byte[] byteContent = content.getBytes(CHARSET);
        IvParameterSpec iv = new IvParameterSpec(password.getBytes(CHARSET));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] result = cipher.doFinal(byteContent);
        return toHexString(result);
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder buffer = new StringBuilder();
        for (byte aByte : bytes) {
            buffer.append(HEX_CHARS.charAt(aByte >>> 4 & 0x0F));
            buffer.append(HEX_CHARS.charAt(aByte & 0x0F));
        }
        return buffer.toString();
    }
}