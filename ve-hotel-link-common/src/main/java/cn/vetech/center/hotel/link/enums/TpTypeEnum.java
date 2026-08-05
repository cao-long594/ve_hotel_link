package cn.vetech.center.hotel.link.enums;

public enum TpTypeEnum {

    tp0("0","展示图"),
    tp1("1","餐厅"),
    tp2("2","休闲室"),
    tp3("3","会议室"),
    tp4("4","服务"),
    tp5("5","酒店外观"),
    tp6("6","大堂/接待台"),
    tp7("7","酒店介绍"),
    tp8("8","房型"),
    tp9("9","背景图"),
    tp10("10","其他")
    ;

    private TpTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private final String code;

    private final String name;

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }




}

