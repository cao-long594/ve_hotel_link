package cn.vetech.center.hotel.link.api.orderbook.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 入住人信息
 *
 * @author gaojin
 */
public class BookCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 姓名，必填
     */
    @ApiModelProperty(value = "姓名，必填", dataType = "string")
    private String name;
    /**
     *
     */
    @ApiModelProperty(value = "姓名拼音", dataType = "string")
    private String pinyin;
    /**
     * 手机，必填
     */
    @ApiModelProperty(value = "手机，必填", dataType = "string")
    private String mobile;
    /**
     * 性别。Female 女，Maile 男, Unknown 保密
     * 枚举GenderTypeEunm
     */
    @ApiModelProperty(value = "性别。Female 女，Maile 男, Unknown 保密", dataType = "string")
    private String gender;
    /**
     * 国籍
     */
    @ApiModelProperty(value = "国籍", dataType = "string")
    private String nationality;
    /**
     * 身份证 ---- 小猪短租必传
     */
    @ApiModelProperty(value = "身份证", dataType = "string")
    private String idcard;

    /*************相比cps新增字段***********************/

    /**
     *
     */
    private String frgsmc;
    /**
     *
     */
    private String jsdwmc;
    /**
     *
     */
    private String frgsid;
    /**
     *
     */
    private String jsdwid;
    /**
     * 入住人证件类型   20200117新增
     * IdentityCard，身份证    Passport护照    Other 其它
     */
    private String customerIdType ;

    /**
     * 入住人证件类型   20200117新增
     * IdentityCard，身份证    Passport护照    Other 其它
     * 证件类型字段：cps房源和直连传入的字段不一样；cps传入的证件类型为idCardType；直连证件类型字段为：customerIdType
     */
    private String idCardType;

    /**
     *入住人证件号码
     */
    private String customerIdNo;

    /**
     * 保障级别ID
     */
    private String fwbzjb;
    /**
     * 保障级别名称
     */
    private String fwbzjbmc;


    /*************相比cps新增字段***********************/

/******************下单到同程所需字段*********************/
    /**
     * 成本中心編碼
     */
    private String costCenterCode;

    /**
     * 成本中心描述
     */
    private String costCenterChDesc;

    /**
     * 入住人ID，1w使用，用入住人查询部门
     */
    private String rzrid;
    /**
     * 分摊房费金额（同差旅云结算账单房费），单位：元
     */
    private String shareCost;

    /**
     *
     */
    private String frgsbh;

    /**
     * 申请单号
     */
    private String sqdh;
    /**
     * 员工工号
     */
    private String yggh;
    /**
     * 部门名称
     */
    private String bmmc;

    /**
     * 服务商服务费，预订下单记录
     */
    private String fwfFwf;
    /**
     * 成本中心编号
     */
    private String cbzxbh;
    /**
     * 利润中心编号
     */
    private String lrzxbh;
    /**
     * 利润中心名称
     */
    private String lrzxmc;
    /**
     * 项目编号
     */
    private String xmbh;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 入住人手机号国际编码
     */
    private String rzrsjGjbm;
    /**
     * 入住人国籍
     */
    private String rzrgj;
    /**
     * 英文姓
     */
    private String lastname;
    /**
     * 英文名
     */
    private String firstname;
    /**
     * 差旅标准金额
     */
    private String clbzj;
    /**
     * 出差事由
     */
    private String businessReason;
    /**
     * 预算号
     */
    private String budgetNumber;
    /**
     * 外部人员类型
     */
    private String externalPersonType;