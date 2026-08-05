package cn.vetech.center.hotel.link.component;

import cn.vetech.charge.cloud.cache.api.IVeCacheManage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 对房源商的token的统一管理
 * <p>
 * key说明 可以参考
 *
 * @author houya
 * cn.vetech.center.hotel.link.hzwnew.common.HzwNewUtil#getToken
 * <p>
 * String auth = StringUtils.trimToEmpty(config.getClientId()) + ":" + StringUtils.trimToEmpty(config.getSecret());
 * String key = MD5Tool.MD5Encode(auth);
 */
@Service
public class TokenCacheService {

    /**
     * 缓存
     */
    @Resource(name = "rawUserDefinedRedisManage")
    private IVeCacheManage iVeCacheManage;

    /**
     * token缓存名字
     */
    private static final String CACHENAME = "link-hotel-token";


    /**
     * 设置缓存
     *
     * @param fybh      房源商编号
     * @param key       用来缓存的key  应该是 这个配置的 账号 密码等组合的md5后的东西, 可以用获取token的请求参数
     * @param token     生成的token
     * @param expiresIn 缓存时长 秒钟
     */
    public void putToken(String fybh, String key, String token, int expiresIn) {
        if (StringUtils.isBlank(token)) {
            return;
        }
        iVeCacheManage.put(CACHENAME + ":" + fybh, key, token, expiresIn);
    }

    /**
     * 获取缓存中的token
     *
     * @param fybh 房源商编号
     * @param key  用来缓存的key  应该是 这个配置的 账号 密码等组合的md5后的东西, 可以用获取token的请求参数
     * @return token
     */
    public String getToken(String fybh, String key) {
        String token = (String) iVeCacheManage.get(CACHENAME + ":" + fybh, key);
        return token;
    }

    /**
     * 获取缓存中的token的剩余时间
     *
     * @param fybh 房源商编号
     * @param key  用来缓存的key  应该是 这个配置的 账号 密码等组合的md5后的东西, 可以用获取token的请求参数
     * @return 剩余时间
     */
    public long getExpire(String fybh, String key) {
        Long expire = iVeCacheManage.getExpire(iVeCacheManage.genKey(CACHENAME + ":" + fybh, key));
        return expire == null ? -1 : expire;
    }
/**
     * 删掉缓存中的token
     *
     * @param fybh 房源商编号
     * @param key  用来缓存的key  应该是 这个配置的 账号 密码等组合的md5后的东西, 可以用获取token的请求参数
     * @return token
     */
    public void cleanToken(String fybh, String key) {
        iVeCacheManage.remove(CACHENAME + ":" + fybh, key);
    }
}
