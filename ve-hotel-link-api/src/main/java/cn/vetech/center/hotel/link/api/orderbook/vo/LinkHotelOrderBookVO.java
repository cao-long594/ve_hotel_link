package cn.vetech.center.hotel.link.api.orderbook.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchNightlyRate;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 预订下单
 *
 * @author gaojin
 */
public class LinkHotelOrderBookVO extends LinkHotelVO {
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", dataType = "string")
    private String orderId;
    /**
     * 原始订单订单状态
     */
    @ApiModelProperty(value = "原始订单订单状态", dataType = "string")
    private String trueStatus;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态", dataType = "string")
    private String showStatus;
    /**
     * 最晚取消时间
     */
    @ApiModelProperty(value = "最晚取消时间", dataType = "string")
    private String cancelTime;
    /**
     * 担保金额
     * 如果此订单是担保订单,则在此列出担保金额,币种是人民币(如果提交订单时候的是港币,这里也会被换算成对应金额的人民币
     */
    @ApiModelProperty(value = "担保金额", dataType = "string")
    private String amount;
    /**
     * 预付金额
     */
    @ApiModelProperty(value = "预付金额", dataType = "string")
    private String orderPrice;
    /**
     * 支付链接
     */
    @ApiModelProperty(value = "支付链接", dataType = "string")
    private String urlStr;
    /**
     * 货币类型
     */
    @ApiModelProperty(value = "货币类型", dataType = "string")
    private String currencyCode;
    /**
     * 是否是即时确认
     */
    @ApiModelProperty(value = "是否是即时确认", dataType = "string")
    private String isInstantConfirm;
    /**
     * 支付最后期限
     */
    @ApiModelProperty(value = "支付最后期限", dataType = "string")
    private String paymentDeadlineTime;
    /**
     * 支付错误信息
     */
    @ApiModelProperty(value = "支付错误信息", dataType = "string")
    private String paymentMessage;

    /*******************相比cps新增字段********************/
    /*************************START**以下是京东方宿舍专用字段********************************/
    /**
     * 入住现地名称
     */
    private String payPlace;
    /**
     * 入住现地代码
     */
    private String payPlaceId;
    /**
     * 客房地理位置
     */
    private String area;
    /**
     * 客房楼房栋号
     */
    private String building;
    /**
     * 客房单元
     */
    private String unit;
    /**
     * 客房房间号
     */
    private String room;
    /**
     * 客房床位号
     */
    private String bunk;
    /**
     * 客房类型
     */
    private String roomType;
    /**
     * 入住须知
     */
    private String remark;
    /*************************END**以上是京东方宿舍专用字段********************************/
    /**
     * 开票状态码
     */
    private String invoiceRtnStatus;
    /**
     * 开票错误信息
     */
    private String invoiceRtnMsg;
    /**
     * 担保规则描述
     */
    private String dbgz;
    /**
     * 担保规则id
     */
    private String dbgzid;
    /*******************相比cps新增字段********************/
    /**
     * 供应商 支付卡号，暂时针对的是vcc支付 add by xiaotengyu 2021/09/28
     */
    private String gysdkzh;
    /**
     * 扩展字段，存json格式字符串
     */
    private String gysxdbj;
    /**
     * 下单失败原因，1:变价失败 2:满房失败
     */
    private String xdsbyy;
    /**
     * 支付流水号
     */
    private String payNo;
    /**
     * 传入供应商的实际姓名
     */
    private String gysjsxm;
    /**
     * 是否支持反查（通过本地订单编号查询供应商订单详情），1:支持，其他不支持
     */
    private String inverseQuery;

    /**
     * 多天价格
     */
    private List<SearchNightlyRate> nightlyRates;
    /**
     * 优惠分摊信息
     */
    private List<HotelOrderDiscountShareVO> discountShareList;