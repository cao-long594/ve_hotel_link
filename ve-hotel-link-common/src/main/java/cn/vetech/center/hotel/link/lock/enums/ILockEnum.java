package cn.vetech.center.hotel.link.lock.enums;

/**
 * lock枚举
 *
 * @author luqs
 * @version v1.0
 */
public interface ILockEnum {

    /**
     * 获取lock key
     *
     * @return String
     */
    String getLockKeyPrefix();

    /**
     * 获取等待时长（ms）
     *
     * @return long
     */
    int getWaitTime();

    /**
     * 获取lock占用时长（s）
     *
     * @return long
     */
    int getLeaseTime();
}
