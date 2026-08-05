package cn.vetech.center.hotel.link.ratelimt;

/**
 * lua脚本
 *
 * @author wangkai
 * @since 2021/3/17
 */
public class LuaScript {


    /**
     * 返回1 标识成功
     * consumeCount 当前需要消费的个数
     * capacity 单位时间最大个数
     * time 单位时间、
     * alreadyConsumerCount 已使用个数
     */
    public static final String RATE_LIMIT = "local key = KEYS[1]\n"
            + "local consumeCount = tonumber(ARGV[1])\n"
            + "local capacity = tonumber(ARGV[2])\n"
            + "local time = tonumber(ARGV[3])\n"
            + "local alreadyConsumerCount = tonumber(redis.call('get', key) or 0)\n"
            + "if(alreadyConsumerCount+consumeCount>capacity) then\n"
            + "    return 0\n"
            + "end\n"
            + "local val=redis.call('INCRBY',key,consumeCount)\n"
            + "if(alreadyConsumerCount==0) then\n"
            + "    redis.call('expire',key,time)\n"
            + "end\n"
            + "return 1";

}