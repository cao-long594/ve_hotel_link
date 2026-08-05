package cn.vetech.center.hotel.link.api;

import cn.vetech.charge.cloud.modules.utils.mapper.JsonMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 酒店link响应信息父类
 *
 * @author gaojin
 */
public class LinkHotelVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final int SUCCESS = 1;
    /**
     * 失败
     */
    public static final int FAIL = -1;
    /**
     * 酒店系统对房源商 请求Request
     */
    @ApiModelProperty(value = "酒店系统对房源商 请求Request", dataType = "string")
    @JsonIgnore
    protected String hotelRequest;
    /**
     * 酒店系统对房源商 返回Response
     */
    @ApiModelProperty(value = "酒店系统对房源商 返回Response", dataType = "string")
    @JsonIgnore
    protected String hotelResponse;
    /**
     * cps 对酒店系统 请求Request
     */
    @ApiModelProperty(value = "cps 对酒店系统 请求Request", dataType = "string")
    protected String cpsRequset;
    /**
     * cps 对酒店系统化 返回Response
     */
    @ApiModelProperty(value = "cps 对酒店系统化 返回Response", dataType = "string")
    protected String cpsResponse;
    /**
     * 状态:1成功，-1失败
     */
    @ApiModelProperty(value = "状态:1成功，-1失败", dataType = "string")
    private Integer status = SUCCESS;
    /**
     * 失败错误结果信息
     */
    @ApiModelProperty(value = "失败错误结果信息", dataType = "string")
    private String errorMsg;
    /**
     * 失败错误代码
     */
    @ApiModelProperty(value = "失败错误代码", dataType = "string")
    private String errorCode;
    /**
     *
     */
    @ApiModelProperty(value = "供应商失败错误代码", dataType = "string")
    private String gysErrorCode;
    /**
     * 失败错误结果信息
     */
    @ApiModelProperty(value = "供应商失败错误结果信息", dataType = "string")
    private String gysErrorMsg;
    /**
     * 注意：错误信息，cps退款接口用
     */
    @ApiModelProperty(value = "失败错误结果信息", dataType = "string")
    private String errorMessage;
    /**
     * cps响应的请求id 这个作为入参传入到 查询报价接口 或者下单接口
     */
    private String cpsPreRepTraceid;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getHotelRequest() {
        return hotelRequest;
    }

    public void setHotelRequest(String hotelRequest) {
        this.hotelRequest = hotelRequest;
    }

    public String getHotelResponse() {
        return hotelResponse;
    }

    public void setHotelResponse(String hotelResponse) {
        this.hotelResponse = hotelResponse;
    }

    public String getCpsRequset() {
        return cpsRequset;
    }

    public void setCpsRequset(String cpsRequset) {
        this.cpsRequset = cpsRequset;
    }

    public String getCpsResponse() {
        return cpsResponse;
    }

    public void setCpsResponse(String cpsResponse) {
        this.cpsResponse = cpsResponse;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getCommonLogDdbh(){
        return StringUtils.EMPTY;
    }

    public String getCommonLogYwdh(){
        return StringUtils.EMPTY;
    }

    /**
     * @return json
     */
    public String toJson() {
        try {
            return JsonMapper.nonEmptyMapper().toJson(this);
        } catch (Exception e) {
        }
        return "转换json失败";
    }

    public String getGysErrorCode() {
        return gysErrorCode;
    }

    public void setGysErrorCode(String gysErrorCode) {
        this.gysErrorCode = gysErrorCode;
    }

    public String getGysErrorMsg() {
        return gysErrorMsg;
    }

    public void setGysErrorMsg(String gysErrorMsg) {
        this.gysErrorMsg = gysErrorMsg;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCpsPreRepTraceid() {
        return cpsPreRepTraceid;
    }

    public void setCpsPreRepTraceid(String cpsPreRepTraceid) {
        this.cpsPreRepTraceid = cpsPreRepTraceid;
    }
}