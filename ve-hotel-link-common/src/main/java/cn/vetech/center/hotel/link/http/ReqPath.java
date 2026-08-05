package cn.vetech.center.hotel.link.http;

/**
 * <p>
 * 请求路径
 * </p>
 *
 * @author wangkai
 * @since  2020/10/21
 */
public interface ReqPath {

    /**
     * 请求地址
     */
    String path();

    /**
     * 地址描述
     */
    String desc();
}
