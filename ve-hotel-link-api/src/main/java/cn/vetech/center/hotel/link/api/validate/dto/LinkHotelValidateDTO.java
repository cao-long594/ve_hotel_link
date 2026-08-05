package cn.vetech.center.hotel.link.api.validate.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookContact;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookNightlyRate;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookOrderRoom;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookOrderRoomExt;
import cn.vetech.center.hotel.link.api.ratesearch.dto.UserVipExtInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.HotelBedInfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 下单前数据校验
 *
 * @author gaojin
 */
public class LinkHotelValidateDTO extends LinkHotelDTO {
    /**
     * 入住日期：yyyy-mm-dd
     */
    @ApiModelProperty(value = "入住日期：yyyy-mm-dd", dataType = "string")
    private String checkInDate;
    /**
     * 离店日期：yyyy-mm-dd
     */
    @ApiModelProperty(value = "离店日期：yyyy-mm-dd", dataType = "string")
    private String checkOutDate;
    /**
     * 支付方式：0 现付 1 预付
     */
    @ApiModelProperty(value = "支付方式：0 现付 1 预付", dataType = "string")
    private String payment;
    /**
     * 房间数量
     */
    @ApiModelProperty(value = "房间数量", dataType = "string")
    private String numberOfRooms;
    /**
     * 最早到店时间,yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(value = "最早到店时间,yyyy-MM-dd HH:mm:ss", dataType = "string")
    private String earliestArrivalTime;
    /**
     * 最晚到店时间,yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(value = "最晚到店时间,yyyy-MM-dd HH:mm:ss", dataType = "string")
    private String latestArrivalTime;
    /**
     * 总价
     */
    @ApiModelProperty(value = "总价", dataType = "string")
    private String totalPrice;

    /**********************相比cps新增字段*******************************/
    /**
     * 联系人信息
     */
    @ApiModelProperty(value = "联系人信息", dataType = "string")
    private BookContact contact;
    /**
     * 入住人信息
     */
    @ApiModelProperty(value = "入住人信息", dataType = "string")
    private List<BookOrderRoom> orderRooms;
    /**
     * 每日价格
     */
    @ApiModelProperty(value = "每日价格", dataType = "string")
    private List<BookNightlyRate> nightlyRates;

    /*************************START**以下是京东方宿舍专用字段********************************/
    /**
     * 入住现地名称 例如B7
     */
    private String payPlace;
    /**
     * 入住现地代码 例如 107
     */
    private String payPlaceId;
    /**
     * 姓名
     */
    private String employeeName;
    /**
     * 是否BOE员工 0-否/1-是
     */
    private String boeFlag;
    /**
     * 工号
     */
    private String employeeId;
    /**
     * 职级
     */
    private String bandId;
    /**
     * 性别 0-男/1-女
     */
    private String sex;
    /**
     * 差旅审批单号
     */
    private String approvalNo;
    /*************************END**以上是京东方宿舍专用字段********************************/

    /**********************相比cps新增字段*******************************/

    /**
     * 加密CPS数据 add by xiaotengyu 费控 国际酒店验单需要知道原始价格 添加cpsencryptData
     */
    private String cpsencryptData;

    /**
     * 预订用户当前所在IP地址【必填】
     */
    private String ydrIp;
    /**
     * 预订用户当前所在百度纬度【非必填】
     */
    private String ydrLat;
    /**
     * 预订用户当前所在百度经度【非必填】
     */
    private String ydrLng;
    /**
     * 预订用户手机号【必填】
     */
    private String ydrPhoneNumber;
    /**
     * 预订终端枚举 Android、iPhone、PC\Touch
     */
    private String ydrYdzd;

    /**
     * 扩展字段，存json格式字符串 供应商下单标记扩展字段
     */
    private String gysxdbj;
    /**
     * 下单使用，会员价下单信息
     */
    private UserVipExtInfo userVipExtInfo;

    /**
     * 加密cps摘要 费控--->cps
     */
    private String cpsMd5Summary;
    /**
     * 员工工号
     */
    private String yggh;
    /**
     * 客人信息
     */
    private List<BookOrderRoomExt> orderRoomsExt;
    /**
     * 床型信息
     */
    private HotelBedInfo bedInfo;
    /**
     * 货币类型
     * 请和获取价格的地方保持一致
    */
    @ApiModelProperty(value = "货币类型,请和获取价格的地方保持一致", dataType = "string")
    private String currencyCode;