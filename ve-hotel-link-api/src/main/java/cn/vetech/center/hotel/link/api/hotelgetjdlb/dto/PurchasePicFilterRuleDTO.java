package cn.vetech.center.hotel.link.api.hotelgetjdlb.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 采购价格过滤规则
 * <br>
 * <strong>注：此类中所有条件均为"and"关系，即满足所有字段条件，才算满足此规则！！！</strong>
 * <ul>
 *     <li>1.采购端（如：费控）过滤设置比较分散，并非一条条规范的规则，目前将所有的过滤条件归类到一条规则里，规则里的条件之间是“and”关系；</li>
 *     <li>2.采购端将每个过滤设置作为一条过滤规则，规则里非当前过滤设置的条件全部默认成空（即默认满足其余条件）；</li>
 * </ul>
 *
 * @author luqs
 * @version v1.0
 */
public class PurchasePriceFilterRuleDTO implements Serializable {
    private static final long serialVersionUID = 8019804203375776879L;
    /**
     * 酒店id
     */
    private String hotelId;
    /**
     * 支持的支付模式，0：仅预付；1：仅现付；2：现付+预付；4：仅预付+非担保；5：仅现付+非担保；
     */
    private String supportPayModel;
    /**
     * 支持的开票类型，0：服务商开票；1：酒店开票
     */
    private List<String> supportInvoicingTypeList;
    /**
     * 不支持的开票类型，0：服务商开票；1：酒店开票
     */
    private List<String> invoicingTypeList;
    /**
     * 发票类型，0：专票；1：普票
     */
    private List<String> invoiceTypeList;
    /**
     * 房型名称关键字
     */
    private List<String> roomNameKwList;
    /**
     * 窗户类型，枚举值同价格计划
     */
    private List<String> windowTypeList;
    /**
     * 只展示单体协议价格
     */
    private boolean onlySingleProtocol;
    /**
     * 是否过滤满房
     */
    private boolean filterFullRoom;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getSupportPayModel() {
        return supportPayModel;
    }

    public void setSupportPayModel(String supportPayModel) {
        this.supportPayModel = supportPayModel;
    }

    public List<String> getSupportInvoicingTypeList() {
        return supportInvoicingTypeList;
    }

    public void setSupportInvoicingTypeList(List<String> supportInvoicingTypeList) {
        this.supportInvoicingTypeList = supportInvoicingTypeList;
    }

    public List<String> getInvoicingTypeList() {
