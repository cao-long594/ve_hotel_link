package cn.vetech.center.hotel.link.api.conferencehotel.list.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 会议酒店列表
 *
 * @author luqs
 * @version v1.0
 */
@XmlRootElement(name = "request")
public class ConferenceHotelListDTO extends LinkHotelDTO {
    private static final long serialVersionUID = -5743750163906329251L;
    /**
     * 酒店名称
     */
    @ApiModelProperty(value = "酒店中文名称", example = "")
    private String jdzwmc;
    /**
     * 所在城市编号
     */
    @ApiModelProperty(value = "所在城市编号", example = "10458")
    private String szcs;
    /**
     * 入住日期
     */
    @ApiModelProperty(value = "入住日期：yyyy-MM-dd", example = "2019-01-31")
    private String ksrq;
    /**
     * 离店日期
     */
    @ApiModelProperty(value = "离店日期：yyyy-MM-dd", example = "2019-02-01")
    private String jsrq;
    /**
     * 酒店品牌
     */
    @ApiModelProperty(value = "酒店品牌", example = "")
    private String jdpp;
    /**
     * 酒店集团编号
     */
    @ApiModelProperty(value = "酒店集团编号", example = "")
    private String jdjtbh;
    /**
     * 行政区
     */
    @ApiModelProperty(value = "行政区", example = "")
    private String xzq;
    /**
     * 星级,2、3、4、5,传2表示查询3星级以下酒店,多个用逗号隔开
     */
    @ApiModelProperty(value = "星级,2、3、4、5,传2表示查询3星级以下酒店,多个用逗号隔开", example = "")
    private String xj;
    /**
     * 百度经度
     */
    @ApiModelProperty(value = "百度经度", example = "")
    private String bdjd;
    /**
     * 百度纬度
     */
    @ApiModelProperty(value = "百度纬度", example = "")
    private String bdwd;
    /**
     * （经度，维度在一个字段传，用英文逗号或者斜线隔开，例如112.021,30.231） 经度 ， 维度
     * 计算距离百度经纬度
     * （应用场景：1、计算当前位置距酒店距离；2、计算某地标比如：某城市市中心距酒店距离；），
     * 这个字段跟bdjd、bdwd这2个字段肯定不会同时出现
     */
    private String jsjlbdjwd;
    /**
     * 距离目的点公里
     */
    @ApiModelProperty(value = "距离目的点公里,精确到3位小数", example = "")
    private double jl;
    /**
     * 客房价格范围始
     */
    @ApiModelProperty(value = "客房价格范围始", example = "")
    private String jgs;
    /**
     * 客房价格范围止
     */
    @ApiModelProperty(value = "客房价格范围止", example = "")
    private String jgz;
    /**
     * 酒店主题类别，多值逗号分隔
     */
    @ApiModelProperty(value = "酒店主题类别", example = "")
    private String jdztlb;
    /**
     * 酒店标签类别
     */
    @ApiModelProperty(value = "酒店标签类别", example = "")
    private String jdbqlb;
    /**
     * 查询类型
     */
    @ApiModelProperty(value = "查询类型", example = "")
    private String arrTypeId;
    /**
     * 类型  0商圈 1行政区  2县级市 3poi 4关键字  5:酒店品牌 6：酒店集团
     */
    @ApiModelProperty(value = "查询类型", example = "")
    private String arrType;
    /**
     * poi是酒店的时候查询  传jdid 把这个酒店排在前面
     */
    private String searchJdid;
    /**
     * 支付方式 0现付 1预付
     */
    @ApiModelProperty(value = "支付方式 0现付 1预付", example = "")
    private String payment;
    /**
     * 地级城市是否查询下级县市， 0/空代表是默认搜索下级县市；1代表否，不搜索下级城市
     */
    private String sfcxxjcs;
    /**
     * 适用人群，0、仅内宾  1、外宾适用（含内宾和港澳台） 2、港澳台客人适用（含内宾，除港澳台以外的客人不适用）3、香港客人适用（含内宾，除香港以外的客人不适用） 4、台湾客人适用（含内宾，除台湾以外的客人不适用）  5、澳门客人适用（含内宾，除澳门以外的客人不适用）
     */
    private String suitCrowd;
    /**
     * 酒店评分
     */
    private String hotelScore;
    /**
     * 会场容纳人数，如：0_100
     */
    private String venueCapacity;
    /**
     * 会场面积，如：100_200
     */
    private String venueArea;
    /**
     * 会场数量，如：2_5
     */
    private String venueNum;
    /**
     * 会场全天参考价，如：2000_5000
     */
    private String venuePrice;
    /**
     * 排序
     */
    @ApiModelProperty(value = "0默认排序,+1最低门市价升序,-2星级降序,-1门市价降序,+2星级升序,+3距离升序,+4评分升序,-4评分降序", example = "")
    private String sortType;
    /**
     * 这里的start赋值方式：(pageDTO.getCurrent() - 1) * pageDTO.getSize()
     */
    @ApiModelProperty(value = "分页起始页码,从0开始", example = "")
    private int start = 0;
    /**
     * 分页每页条数
     */
    @ApiModelProperty(value = "分页每页条数", example = "20")
    private int count = 20;