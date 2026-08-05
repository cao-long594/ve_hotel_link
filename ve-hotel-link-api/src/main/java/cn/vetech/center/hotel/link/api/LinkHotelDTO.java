package cn.vetech.center.hotel.link.api;

import cn.vetech.charge.cloud.modules.utils.mapper.JsonMapper;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 酒店link请求参数父类
 *
 * @author gaojin
 */
public class LinkHotelDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 房源商编号 31200801 仅仅只是在查询报价时可以为空
     * 在订单交互业务 有且必定只有一个房源商编号
     */
    @ApiModelProperty(value = "房源商编号 31200801 仅仅只是在查询报价时可以为空 在订单交互业务 有且必定只有一个房源商编号", dataType = "string")
    private String fybh;
    /**
     * 房源商账号配置信息
     * CPS客户端不需要传入，只需要传入bh
     * LINK客户端需要根据bh实例化，传入给房源商
     */
    @ApiModelProperty(value = "房源商账号配置信息，客户端不需要传入，只需要传入bh。LINK客户端需要根据bh实例化，传入给房源商", dataType = "string")
    private Map<String, String> supplier;

    /**
     * cps供应商账号信息,该配置会配置以下信息
     * 是否走独立ES sfzes:0-否 1-是  独立es地址:esurl  独立es秘钥:essign
     * 商户代号：SHDH  账号:ZH  CPS秘钥:MY  公司id:COMPID   cps地址:IP
     */
    @ApiModelProperty(value = "房源商账号配置信息，客户端不需要传入，只需要传入bh。LINK客户端需要根据bh实例化，传入给房源商", dataType = "string")
    private Map<String, String> cpsPlatformSuppler;

    /**
     * 默认ccps供应商账号信息,该配置会配置以下信息
     * 是否走独立ES sfzes:0-否 1-是  独立es地址:esurl  独立es秘钥:essign
     * 商户代号：SHDH  账号:ZH  CPS秘钥:MY  公司id:COMPID   cps地址:IP
     * 优先取cpsPlatformSuppler 再取从base获取的供应商，如果不行再从defaultCpsPlatformSuppler获取
     */
    @ApiModelProperty(value = "房源商账号配置信息，客户端不需要传入，只需要传入bh。LINK客户端需要根据bh实例化，传入给房源商", dataType = "string")
    private Map<String, String> defaultCpsPlatformSuppler;


    @ApiModelProperty(value = "供应商编号数组", dataType = "List")
    private List<String> merchantCodeList;

    /**
     * 房源商酒店ID,如果hotelId有值,此字段有值
     */
    @ApiModelProperty(value = "房源商酒店ID", dataType = "string")
    private String hotelId;
    /**
     * 房源商房型ID,销售房型编号,查询报价时为RoomTypeId
     */
    @ApiModelProperty(value = "房源商房型ID,销售房型编号,查询报价时为RoomTypeId", dataType = "string")
    private String roomId;
    /**
     * 房源商产品编号,价格计划ID
     */
    @ApiModelProperty(value = "房源商产品编号,价格计划ID", dataType = "string")
    private String ratePlanId;
    /**
     * 房源商订单号
     */
    @ApiModelProperty(value = "房源商订单号", dataType = "string")
    private String orderId;
    /**
     * 转单前的原始订单号
     */
    @ApiModelProperty(value = "原始订单号", dataType = "string")
    private String ysOrderId;
    /**
     * 胜意酒店ID,可为空
     */
    @ApiModelProperty(value = "胜意酒店ID,可为空", dataType = "string")
    private String localHotelId;
    /**
     * 胜意酒店名称,可为空
     */
    @ApiModelProperty(value = "胜意酒店名称,可为空", dataType = "string")
    private String localHotelName;
    /**
     * 胜意房型ID:可为空
     */
    @ApiModelProperty(value = "胜意房型ID:可为空", dataType = "string")
    private String localRoomId;
    /**
     * 胜意订单ID
     */
    @ApiModelProperty(value = "胜意订单ID", dataType = "string")
    private String localOrderId;
    /**
     * 商户编号
     */
    @ApiModelProperty(value = "商户编号", dataType = "string")
    private String businessNo;
    /**
     * CMBCHINA MENGNIU
     */
    @ApiModelProperty(value = "value",dataType = "String")
    private String compid;
    /**
     * 商户简称
     */
    @ApiModelProperty(value = "商户简称", dataType = "string")
    private String businessName;
    /**
     * 操作人编号
     */
    @ApiModelProperty(value = "操作人编号", dataType = "string")
    private String userId;
    /**
     * 操作机器IP
     */
    @ApiModelProperty(value = "操作机器IP", dataType = "string")
    private String ip;
    /**
     * 成人数
     */
    @ApiModelProperty(value = "成人数", dataType = "string")
    private String adult;
    /**
     * 儿童数
     */
    @ApiModelProperty(value = "儿童数", dataType = "string")
    private String child = "0";
    /**
     * 儿童年龄
     */
    @ApiModelProperty(value = "儿童年龄", dataType = "string")
    private String age = "";
    /**
     * 国内国际 0:国际；1：国内
     */
    @ApiModelProperty(value = "国内国际 0国际 1国内", dataType = "string")
    private String gngj;
    /**
     * 是否港澳台，1是，其他不是
     */
    @ApiModelProperty(value = "是否港澳台，1是，其他不是", dataType = "string")
    private String sfgat;

    /**
     * 入住人国籍（好巧道旅需要)
     */
    @ApiModelProperty(value = "入住人国籍", dataType = "string")
    private String rzrgj = "CN";
    /**
     * 入住人国籍名称
     */
    @ApiModelProperty
    private String rzrgjmc;
    /**
     * 房间数
     */
    @ApiModelProperty(value = "房间数", dataType = "integer")
    private Integer fjs;

    //废弃  目前只有cpslink使用 改用fylx 处理多账号逻辑
    /**
     * 供应商账号名称
     * 将报价返回的供应商账号fymc字段存入这个字段中，方便后面下单，同步，取消等操作
     * zhmc如果为空就默认CPS账号
     * 注意：房源商工程里面取账号信息只能从dto中Supplier字段获取！！！
     * 这个字段的作用是确定采购用哪一个房源商账号下单
     * 费控对应商户编号 shbh
     */
    @ApiModelProperty(value = "供应商账号名称", dataType = "string")
    private String zhmc;


    /*********************************相比cps 新增字段***************************************/
    /**
     * 会员id 费控使用
     */
    private String hyid;
    /**
     * 常旅客id 费控使用
     */
    private String clkid;
    /**
     * 常旅客部门id 费控使用
     */
    private String clkdeptid;
    /**
     * 请求来源平台 cps charge(费控) cloud(差旅云)
     */
    @ApiModelProperty(value = "请求来源平台 cps charge cloud", dataType = "string")
    private String pt;
    /**
     * 多接口日志唯一表示
     */
    @ApiModelProperty(value = "多接口日志唯一表示", dataType = "string")
    private String uuidKey;
    /**
     * 出行类型：1因公出行 2因私出行
     */
    private String ygys;
    /**
     * 酒店名称 cps下单用
     */
    private String hotelName;
    /**
     * 操作时间
     */
    private String operateTime;
    /**
     * 系统id
     */
    private String systemId;
    /**
     * 签名
     */
    private String sign;
    /**
     * 服务名
     */
    private String service;
    /**
     * 后续请求url，如：asms1w直连link，需通过此字段传入asms1w供应商配置获取的请求url,
     */
    private String followReqUrl;
    /*********************************相比cps 新增字段***************************************/

    /**
     * 添加供应商下单标记字段
     */
    private String gysxdbj;

    /**
     * 语种  zhcn:简体中文;zhtw:繁体中文;enus:英文;dede:德文;frfr:德文;jajp:日文;kokr:韩文;ruru:俄文;enmy:马来语（英）;
     */
    private String veLanguage;
    /**
     * 预订人法人公司
     */
    private String ydrfrgs;
    /**
     * 唯一设备id
     */
    private String deviceUniqueId;
    /**
     * 拓展字段 各房源，应用各自的业务需要传的字段，用json的方式传，在各自房源中解析
     */
    private String extendedInfo;

    /**
     * cps响应的请求id 这个作为入参传入到 查询报价接口 或者下单接口
     */
    private String cpsPreRepTraceid;
    /**
     * 用户会员id
     */
    private String uid;
    /**
     * 客户请求中的User-Agent标头字符串。
     * 如果您正在构建应用程序，则用户代理值应为｛app name｝/｛app version｝。
     */
    private String userAgent;
    /**
     * 预订用户当前所在IP地址
     */
    private String ydrIp;
    /**
     * 业务类型
     * 0或空：例如 查询报价、验价、下单、支付等，需要保证账号是开启状态
     * 1:例如 查询订单详情、取消订单、申请退房等，订单已经生成，后续流程不需要考虑账号是否为开启状态
     */
    private String serviceType;
    /**
     * 成本中心id
     */
    private String cbzxid;
    /**
     * 成本中心编号
     */
    private String cbzxbh;

    public String getCbzxid() {
        return cbzxid;
    }

    public void setCbzxid(String cbzxid) {
        this.cbzxid = cbzxid;
    }

    public String getCbzxbh() {
        return cbzxbh;
    }

    public void setCbzxbh(String cbzxbh) {
        this.cbzxbh = cbzxbh;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getYdrIp() {
        return ydrIp;
    }

    public void setYdrIp(String ydrIp) {
        this.ydrIp = ydrIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDeviceUniqueId() {
        return deviceUniqueId;
    }

    public void setDeviceUniqueId(String deviceUniqueId) {
        this.deviceUniqueId = deviceUniqueId;
    }

    public String getYdrfrgs() {
        return ydrfrgs;
    }

    public void setYdrfrgs(String ydrfrgs) {
        this.ydrfrgs = ydrfrgs;
    }

    public String getVeLanguage() {
        return veLanguage;
    }

    public void setVeLanguage(String veLanguage) {
        this.veLanguage = veLanguage;
    }

    public String getGysxdbj() {
        return gysxdbj;
    }

    public void setGysxdbj(String gysxdbj) {
        this.gysxdbj = gysxdbj;
    }

    public String getZhmc() {
        return zhmc;
    }

    public void setZhmc(String zhmc) {
        this.zhmc = zhmc;
    }

    public Integer getFjs() {
        return fjs;
    }

    public void setFjs(Integer fjs) {
        this.fjs = fjs;
    }

    public String getRzrgj() {
        return rzrgj;
    }

    public void setRzrgj(String rzrgj) {
        this.rzrgj = rzrgj;
    }

    public String getRzrgjmc() {
        return rzrgjmc;
    }

    public void setRzrgjmc(String rzrgjmc) {
        this.rzrgjmc = rzrgjmc;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    @XmlTransient
    public Map<String, String> getSupplier() {
        return supplier;
    }

    public void setSupplier(Map<String, String> supplier) {
        this.supplier = supplier;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(String ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getYsOrderId() {
        return ysOrderId;
    }

    public void setYsOrderId(String ysOrderId) {
        this.ysOrderId = ysOrderId;
    }

    public String getLocalHotelId() {
        return localHotelId;
    }

    public void setLocalHotelId(String localHotelId) {
        this.localHotelId = localHotelId;
    }

    public String getLocalHotelName() {
        return localHotelName;
    }

    public void setLocalHotelName(String localHotelName) {
        this.localHotelName = localHotelName;
    }

    public String getLocalRoomId() {
        return localRoomId;
    }

    public void setLocalRoomId(String localRoomId) {
        this.localRoomId = localRoomId;
    }

    public String getLocalOrderId() {
        return localOrderId;
    }

    public void setLocalOrderId(String localOrderId) {
        this.localOrderId = localOrderId;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGngj() {
        return gngj;
    }

    public void setGngj(String gngj) {
        this.gngj = gngj;
    }

    public String getSfgat() {
        return sfgat;
    }

    public void setSfgat(String sfgat) {
        this.sfgat = sfgat;
    }

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getUuidKey() {
        return uuidKey;
    }

    public void setUuidKey(String uuidKey) {
        this.uuidKey = uuidKey;
    }

    public String getHyid() {
        return hyid;
    }

    public void setHyid(String hyid) {
        this.hyid = hyid;
    }

    public String getClkid() {
        return clkid;
    }

    public void setClkid(String clkid) {
        this.clkid = clkid;
    }

    public String getClkdeptid() {
        return clkdeptid;
    }

    public void setClkdeptid(String clkdeptid) {
        this.clkdeptid = clkdeptid;
    }

    public String getYgys() {
        return ygys;
    }

    public void setYgys(String ygys) {
        this.ygys = ygys;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getFollowReqUrl() {
        return followReqUrl;
    }

    public void setFollowReqUrl(String followReqUrl) {
        this.followReqUrl = followReqUrl;
    }

    public Map<String, String> getCpsPlatformSuppler() {
        return cpsPlatformSuppler;
    }

    public void setCpsPlatformSuppler(Map<String, String> cpsPlatformSuppler) {
        this.cpsPlatformSuppler = cpsPlatformSuppler;
    }

    public String getExtendedInfo() {
        return extendedInfo;
    }

    public void setExtendedInfo(String extendedInfo) {
        this.extendedInfo = extendedInfo;
    }

    public String getCpsPreRepTraceid() {
        return cpsPreRepTraceid;
    }

    public void setCpsPreRepTraceid(String cpsPreRepTraceid) {
        this.cpsPreRepTraceid = cpsPreRepTraceid;
    }

    public String getCommonLogDdbh(){
        return StringUtils.EMPTY;
    }

    public String getCommonLogYwdh(){
        return StringUtils.EMPTY;
    }

    public List<String> getMerchantCodeList() {
        return merchantCodeList;
    }

    public void setMerchantCodeList(List<String> merchantCodeList) {
        this.merchantCodeList = merchantCodeList;
    }


    public Map<String, String> getDefaultCpsPlatformSuppler() {
        return defaultCpsPlatformSuppler;
    }

    public void setDefaultCpsPlatformSuppler(Map<String, String> defaultCpsPlatformSuppler) {
        this.defaultCpsPlatformSuppler = defaultCpsPlatformSuppler;
    }

    /**
     * @return json
     */
    public String toJson() {
        try {
            return JsonMapper.nonEmptyMapper().toJson(this);
        }catch (Exception e){}
        return "转换json失败";
    }
}