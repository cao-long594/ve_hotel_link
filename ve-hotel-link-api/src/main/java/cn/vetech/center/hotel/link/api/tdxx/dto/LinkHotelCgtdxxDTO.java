package cn.vetech.center.hotel.link.api.tdxx.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author xingyanyan
 */
@XmlRootElement(name = "request")
public class LinkHotelCgtdxxDTO extends LinkHotelDTO {
    /**
     * cps退单编号
     */
    private String cpstdbh;
    /**
     * gyshbh
     */
    private String gyshbh;
    /**
     * asmstdh
     */
    private String asmstdh;
    /**
     * cpsddbh
     */
    private String cpsddbh;
    /**
     * 申请类型
     * 1 超时整单取消
     * 2 提前离店
     */
    private String applyType;

    public String getCpstdbh() {
        return cpstdbh;
    }

    public void setCpstdbh(String cpstdbh) {
        this.cpstdbh = cpstdbh;
    }

    public String getGyshbh() {
        return gyshbh;
    }

    public void setGyshbh(String gyshbh) {
        this.gyshbh = gyshbh;
    }

    public String getAsmstdh() {
        return asmstdh;
    }

    public void setAsmstdh(String asmstdh) {
        this.asmstdh = asmstdh;
    }

    public String getCpsddbh() {
        return cpsddbh;
    }

    public void setCpsddbh(String cpsddbh) {
        this.cpsddbh = cpsddbh;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
}
