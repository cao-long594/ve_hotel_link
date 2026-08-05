package cn.vetech.center.hotel.link.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author chengwanshan
 * @since 2021/7/19 17:16
 */
public enum CountryEnum {
    /**
     * “ GB/T 2659-2000”的“CN”适用于整个 中华人民共和国辖区（包括 中国大陆、 香港、 澳门）。而“ISO 3166-1”和“ CNS 12842”的“CN”则仅适用于中国大陆，不含 港澳地区。
     */
    CN("中国内地", "China", "CN", "CHN", "CNY", "zh-cn"),
    /**
     *
     */
    HK("中国香港", "Hong Kong", "HK", "HKG", "HKD", ""),
    /**
     *
     */
    MO("中国澳门", "Macao", "MO", "MAC", "MOP", ""),
    /**
     * 所用英文名称系依据 国际标准化组织之称呼 [1] [2]所示。台湾地区的国际政治地位可参见 未被国际普遍承认的国家列表、 台海现状以及 旧金山条约。
     */
    TW("中国台湾", "Taiwan", "TW", "TWN", "TWD", ""),
    /**
     *
     */
    US("美国", "United States of America (USA)", "US", "USA", "USD", ""),
    /**
     *
     */
    AD("安道尔", "Andorra", "AD", "AND", "", ""),
    /**
     *
     */
    AE("阿联酋", "United Arab Emirates", "AE", "ARE", "", ""),
    /**
     *
     */
    AF("阿富汗", "Afghanistan", "AF", "AFG", "", ""),
    /**
     *
     */
    AG("安提瓜和巴布达", "Antigua & Barbuda", "AG", "ATG", "", ""),
    /**
     *
     */
    AI("安圭拉", "Anguilla", "AI", "AIA", "", ""),
    /**
     *
     */
    AL("阿尔巴尼亚", "Albania", "AL", "ALB", "", ""),
    /**
     *
     */
    AM("亚美尼亚", "Armenia", "AM", "ARM", "", ""),
    /**
     *
     */
    AO("安哥拉", "Angola", "AO", "AGO", "", ""),
    /**
     *
     */
    AQ("南极洲", "Antarctica", "AQ", "ATA", "", ""),
    /**
     *
     */
    AR("阿根廷", "Argentina", "AR", "ARG", "", ""),
    /**
     *
     */
    AS("美属萨摩亚", "American Samoa", "AS", "ASM", "", ""),
    /**
     *
     */
    AT("奥地利", "Austria", "AT", "AUT", "", ""),
    /**
     *
     */
    AU("澳大利亚", "Australia", "AU", "AUS", "", ""),
    /**
     *
     */
    AW("阿鲁巴", "Aruba", "AW", "ABW", "", ""),
    /**
     *
     */
    AX("奥兰群岛", "?aland Island", "AX", "ALA", "", ""),
    /**
     *
     */
    AZ("阿塞拜疆", "Azerbaijan", "AZ", "AZE", "", ""),
    /**
     *
     */
    BA("波黑", "Bosnia & Herzegovina", "BA", "BIH", "", ""),
    /**
     *
     */
    BB("巴巴多斯", "Barbados", "BB", "BRB", "", ""),
    /**
     *
     */
    BD("孟加拉", "Bangladesh", "BD", "BGD", "", ""),
    /**
     *
     */
    BE("比利时", "Belgium", "BE", "BEL", "", ""),
    /**
     *
     */
    BF("布基纳法索", "Burkina", "BF", "BFA", "", ""),
    /**
     *
     */
    BG("保加利亚", "Bulgaria", "BG", "BGR", "", ""),
    /**
     *
     */
    BH("巴林", "Bahrain", "BH", "BHR", "", ""),
    /**
     *
     */
    BI("布隆迪", "Burundi", "BI", "BDI", "", ""),
    /**
     *
     */
    BJ("贝宁", "Benin", "BJ", "BEN", "", ""),
    /**
     *
     */
    BL("圣巴泰勒米岛", "Saint Barthélemy", "BL", "BLM", "", ""),
    /**
     *
     */
    BM("百慕大", "Bermuda", "BM", "BMU", "", ""),
    /**
     *
     */
    BN("文莱", "Brunei", "BN", "BRN", "", ""),
    /**
     *
     */
    BO("玻利维亚", "Bolivia", "BO", "BOL", "", ""),
    /**
     *
     */
    BQ("荷兰加勒比区", "Caribbean Netherlands", "BQ", "BES", "", ""),
    /**
     *
     */
    BR("巴西", "Brazil", "BR", "BRA", "", ""),
    /**
     *
     */
    BS("巴哈马", "The Bahamas", "BS", "BHS", "", ""),
    /**
     *
     */
    BT("不丹", "Bhutan", "BT", "BTN", "", ""),
    /**
     *
     */
    BV("布韦岛", "Bouvet Island", "BV", "BVT", "", ""),
    /**
     *
     */
    BW("博茨瓦纳", "Botswana", "BW", "BWA", "", ""),
    /**
     *
     */
    BY("白俄罗斯", "Belarus", "BY", "BLR", "", ""),
    /**
     *
     */
    BZ("伯利兹", "Belize", "BZ", "BLZ", "", ""),
    /**
     *
     */
    CA("加拿大", "Canada", "CA", "CAN", "", ""),
    /**
     *
     */
    CC("科科斯群岛", "Cocos (Keeling) Islands", "CC", "CCK", "", ""),
    /**
     *
     */
    CF("中非", "Central African Republic", "CF", "CAF", "", ""),
    /**
     *
     */
    CH("瑞士", "Switzerland", "CH", "CHE", "", ""),
    /**
     *
     */
    CL("智利", "Chile", "CL", "CHL", "", ""),
    /**
     *
     */
    CM("喀麦隆", "Cameroon", "CM", "CMR", "", ""),
    /**
     *
     */
    CO("哥伦比亚", "Colombia", "CO", "COL", "", ""),
    /**
     *
     */
    CR("哥斯达黎加", "Costa Rica", "CR", "CRI", "", ""),
    /**
     *
     */
    CU("古巴", "Cuba", "CU", "CUB", "", ""),
    /**
     *
     */
    CV("佛得角", "Cape Verde", "CV", "CPV", "", ""),
    /**
     *
     */
    CX("圣诞岛", "Christmas Island", "CX", "CXR", "", ""),
    /**
     *
     */
    CY("塞浦路斯", "Cyprus", "CY", "CYP", "", ""),
    /**
     *
     */
    CZ("捷克", "Czech Republic", "CZ", "CZE", "", ""),
    /**
     *
     */
    DE("德国", "Germany", "DE", "DEU", "", ""),
    /**
     *
     */
    DJ("吉布提", "Djibouti", "DJ", "DJI", "", ""),
    /**
     *
     */
    DK("丹麦", "Denmark", "DK", "DNK", "", ""),
    /**
     *
     */
    DM("多米尼克", "Dominica", "DM", "DMA", "", ""),
    /**
     *
     */
    DO("多米尼加", "Dominican Republic", "DO", "DOM", "", ""),
    /**
     *
     */
    DZ("阿尔及利亚", "Algeria", "DZ", "DZA", "", ""),
    /**
     *
     */
    EC("厄瓜多尔", "Ecuador", "EC", "ECU", "", ""),
    /**
     *
     */
    EE("爱沙尼亚", "Estonia", "EE", "EST", "", ""),
    /**
     *
     */
    EG("埃及", "Egypt", "EG", "EGY", "", ""),
    /**
     *
     */
    EH("西撒哈拉", "Western Sahara", "EH", "ESH", "", ""),
    /**
     *
     */
    ER("厄立特里亚", "Eritrea", "ER", "ERI", "", ""),
    /**
     *
     */
    ES("西班牙", "Spain", "ES", "ESP", "", ""),
    /**
     *
     */
    FI("芬兰", "Finland", "FI", "FIN", "", ""),
    /**
     *
     */
    FJ("斐济群岛", "Fiji", "FJ", "FJI", "", ""),
    /**
     *
     */
    FK("马尔维纳斯群岛（ 福克兰）", "Falkland Islands", "FK", "FLK", "", ""),
    /**
     *
     */
    FM("密克罗尼西亚联邦", "Federated States of Micronesia", "FM", "FSM", "", ""),
    /**
     *
     */
    FO("法罗群岛", "Faroe Islands", "FO", "FRO", "", ""),
    /**
     *
     */
    FR("法国", "France", "FR", "FRA", "", ""),
    /**
     *
     */
    GA("加蓬", "Gabon", "GA", "GAB", "", ""),
    /**
     *
     */
    GD("格林纳达", "Grenada", "GD", "GRD", "", ""),
    /**
     *
     */
    GE("格鲁吉亚", "Georgia", "GE", "GEO", "", ""),
    /**
     *
     */
    GF("法属圭亚那", "French Guiana", "GF", "GUF", "", ""),
    /**
     *
     */
    GH("加纳", "Ghana", "GH", "GHA", "", ""),
    /**
     *
     */
    GI("直布罗陀", "Gibraltar", "GI", "GIB", "", ""),
    /**
     *
     */
    GL("格陵兰", "Greenland", "GL", "GRL", "", ""),
    /**
     *
     */
    GN("几内亚", "Guinea", "GN", "GIN", "", ""),
    /**
     *
     */
    GP("瓜德罗普", "Guadeloupe", "GP", "GLP", "", ""),
    /**
     *
     */
    GQ("赤道几内亚", "Equatorial Guinea", "GQ", "GNQ", "", ""),
    /**
     *
     */
    GR("希腊", "Greece", "GR", "GRC", "", ""),
    /**
     *
     */
    GS("南乔治亚岛和南桑威奇群岛", "South Georgia and the South Sandwich Islands", "GS", "SGS", "", ""),
    /**
     *
     */
    GT("危地马拉", "Guatemala", "GT", "GTM", "", ""),
    /**
     *
     */
    GU("关岛", "Guam", "GU", "GUM", "", ""),
    /**
     *
     */
    GW("几内亚比绍", "Guinea-Bissau", "GW", "GNB", "", ""),
    /**
     *
     */
    GY("圭亚那", "Guyana", "GY", "GUY", "", ""),
    /**
     *
     */
    HM("赫德岛和麦克唐纳群岛", "Heard Island and McDonald Islands", "HM", "HMD", "", ""),
    /**
     *
     */
    HN("洪都拉斯", "Honduras", "HN", "HND", "", ""),
    /**
     *
     */
    HR("克罗地亚", "Croatia", "HR", "HRV", "", ""),
    /**
     *
     */
    HT("海地", "Haiti", "HT", "HTI", "", ""),
    /**
     *
     */
    HU("匈牙利", "Hungary", "HU", "HUN", "", ""),
    /**
     *
     */
    ID("印尼", "Indonesia", "ID", "IDN", "", ""),
    /**
     *
     */
    IE("爱尔兰", "Ireland", "IE", "IRL", "", ""),
    /**
     *
     */
    IL("以色列", "Israel", "IL", "ISR", "", ""),
    /**
     *
     */
    IM("马恩岛", "Isle of Man", "IM", "IMN", "", ""),
    /**
     *
     */
    IN("印度", "India", "IN", "IND", "", ""),
    /**
     *
     */
    IO("英属印度洋领地", "British Indian Ocean Territory", "IO", "IOT", "", ""),
    /**
     *
     */
    IQ("伊拉克", "Iraq", "IQ", "IRQ", "", ""),
    /**
     *
     */
    IR("伊朗", "Iran", "IR", "IRN", "", ""),
    /**
     *
     */
    IS("冰岛", "Iceland", "IS", "ISL", "", ""),
    /**
     *
     */
    IT("意大利", "Italy", "IT", "ITA", "", ""),
    /**
     *
     */
    JE("泽西岛", "Jersey", "JE", "JEY", "", ""),
    /**
     *
     */
    JM("牙买加", "Jamaica", "JM", "JAM", "", ""),
    /**
     *
     */
    JO("约旦", "Jordan", "JO", "JOR", "", ""),
    /**
     *
     */
    JP("日本", "Japan", "JP", "JPN", "", ""),
    /**
     *
     */
    KH("柬埔寨", "Cambodia", "KH", "KHM", "", ""),
    /**
     *
     */
    KI("基里巴斯", "Kiribati", "KI", "KIR", "", ""),
    /**
     *
     */
    KM("科摩罗", "The Comoros", "KM", "COM", "", ""),
    /**
     *
     */
    KW("科威特", "Kuwait", "KW", "KWT", "", ""),
    /**
     *
     */
    KY("开曼群岛", "Cayman Islands", "KY", "CYM", "", ""),
    /**
     *
     */
    LB("黎巴嫩", "Lebanon", "LB", "LBN", "", ""),
    /**
     *
     */
    LI("列支敦士登", "Liechtenstein", "LI", "LIE", "", ""),
    /**
     *
     */
    LK("斯里兰卡", "Sri Lanka", "LK", "LKA", "", ""),
    /**
     *
     */
    LR("利比里亚", "Liberia", "LR", "LBR", "", ""),
    /**
     *
     */
    LS("莱索托", "Lesotho", "LS", "LSO", "", ""),
    /**
     *
     */
    LT("立陶宛", "Lithuania", "LT", "LTU", "", ""),
    /**
     *
     */
    LU("卢森堡", "Luxembourg", "LU", "LUX", "", ""),
    /**
     *
     */
    LV("拉脱维亚", "Latvia", "LV", "LVA", "", ""),
    /**
     *
     */
    LY("利比亚", "Libya", "LY", "LBY", "", ""),
    /**
     *
     */
    MA("摩洛哥", "Morocco", "MA", "MAR", "", ""),
    /**
     *
     */
    MC("摩纳哥", "Monaco", "MC", "MCO", "", ""),
    /**
     *
     */
    MD("摩尔多瓦", "Moldova", "MD", "MDA", "", ""),
    /**
     *
     */
    ME("黑山", "Montenegro", "ME", "MNE", "", ""),
    /**
     *
     */
    MF("法属圣马丁", "Saint Martin (France)", "MF", "MAF", "", ""),
    /**
     *
     */
    MG("马达加斯加", "Madagascar", "MG", "MDG", "", ""),
    /**
     *
     */
    MH("马绍尔群岛", "Marshall islands", "MH", "MHL", "", ""),
    /**
     *
     */
    MK("马其顿", "Republic of Macedonia (FYROM)", "MK", "MKD", "", ""),
    /**
     *
     */
    ML("马里", "Mali", "ML", "MLI", "", ""),
    /**
     *
     */
    MM("缅甸", "Myanmar (Burma)", "MM", "MMR", "", ""),
    /**
     *
     */
    MQ("马提尼克", "Martinique", "MQ", "MTQ", "", ""),
    /**
     *
     */
    MR("毛里塔尼亚", "Mauritania", "MR", "MRT", "", ""),
    /**
     *
     */
    MS("蒙塞拉特岛", "Montserrat", "MS", "MSR", "", ""),
    /**
     *
     */
    MT("马耳他", "Malta", "MT", "MLT", "", ""),
    /**
     *
     */
    MV("马尔代夫", "Maldives", "MV", "MDV", "", ""),
    /**
     *
     */
    MW("马拉维", "Malawi", "MW", "MWI", "", ""),
    /**
     *
     */
    MX("墨西哥", "Mexico", "MX", "MEX", "", ""),
    /**
     *
     */
    MY("马来西亚", "Malaysia", "MY", "MYS", "", ""),
    /**
     *
     */
    NA("纳米比亚", "Namibia", "NA", "NAM", "", ""),
    /**
     *
     */
    NE("尼日尔", "Niger", "NE", "NER", "", ""),
    /**
     *
     */
    NF("诺福克岛", "Norfolk Island", "NF", "NFK", "", ""),
    /**
     *
     */
    NG("尼日利亚", "Nigeria", "NG", "NGA", "", ""),
    /**
     *
     */
    NI("尼加拉瓜", "Nicaragua", "NI", "NIC", "", ""),
    /**
     *
     */
    NL("荷兰", "Netherlands", "NL", "NLD", "", ""),
    /**
     *
     */
    NO("挪威", "Norway", "NO", "NOR", "", ""),
    /**
     *
     */
    NP("尼泊尔", "Nepal", "NP", "NPL", "", ""),
    /**
     *
     */
    NR("瑙鲁", "Nauru", "NR", "NRU", "", ""),
    /**
     *
     */
    OM("阿曼", "Oman", "OM", "OMN", "", ""),
    /**
     *
     */
    PA("巴拿马", "Panama", "PA", "PAN", "", ""),
    /**
     *
     */
    PE("秘鲁", "Peru", "PE", "PER", "", ""),
    /**
     *
     */
    PF("法属波利尼西亚", "French polynesia", "PF", "PYF", "", ""),
    /**
     *
     */
    PG("巴布亚新几内亚", "Papua New Guinea", "PG", "PNG", "", ""),
    /**
     *
     */
    PH("菲律宾", "The Philippines", "PH", "PHL", "", ""),
    /**
     *
     */
    PK("巴基斯坦", "Pakistan", "PK", "PAK", "", ""),
    /**
     *
     */
    PL("波兰", "Poland", "PL", "POL", "", ""),
    /**
     *
     */
    PN("皮特凯恩群岛", "Pitcairn Islands", "PN", "PCN", "", ""),
    /**
     *
     */
    PR("波多黎各", "Puerto Rico", "PR", "PRI", "", ""),
    /**
     *
     */
    PS("巴勒斯坦", "Palestinian territories", "PS", "PSE", "", ""),
    /**
     *
     */
    PW("帕劳", "Palau", "PW", "PLW", "", ""),
    /**
     *
     */
    PY("巴拉圭", "Paraguay", "PY", "PRY", "", ""),
    /**
     *
     */
    QA("卡塔尔", "Qatar", "QA", "QAT", "", ""),
    /**
     *
     */
    RE("留尼汪", "Réunion", "RE", "REU", "", ""),
    /**
     *
     */
    RO("罗马尼亚", "Romania", "RO", "ROU", "", ""),
    /**
     *
     */
    RS("塞尔维亚", "Serbia", "RS", "SRB", "", ""),
    /**
     *
     */
    RU("俄罗斯", "Russian Federation", "RU", "RUS", "", ""),
    /**
     *
     */
    RW("卢旺达", "Rwanda", "RW", "RWA", "", ""),
    /**
     *
     */
    SB("所罗门群岛", "Solomon Islands", "SB", "SLB", "", ""),
    /**
     *
     */
    SC("塞舌尔", "Seychelles", "SC", "SYC", "", ""),
    /**
     *
     */
    SD("苏丹", "Sudan", "SD", "SDN", "", ""),
    /**
     *
     */
    SE("瑞典", "Sweden", "SE", "SWE", "", ""),
    /**
     *
     */
    SG("新加坡", "Singapore", "SG", "SGP", "", ""),
    /**
     *
     */
    SI("斯洛文尼亚", "Slovenia", "SI", "SVN", "", ""),
    /**
     *
     */
    SJ("斯瓦尔巴群岛和 扬马延岛", "Template:Country data SJM Svalbard", "SJ", "SJM", "", ""),
    /**
     *
     */
    SK("斯洛伐克", "Slovakia", "SK", "SVK", "", ""),
    /**
     *
     */
    SL("塞拉利昂", "Sierra Leone", "SL", "SLE", "", ""),
    /**
     *
     */
    SM("圣马力诺", "San Marino", "SM", "SMR", "", ""),
    /**
     *
     */
    SN("塞内加尔", "Senegal", "SN", "SEN", "", ""),
    /**
     *
     */
    SO("索马里", "Somalia", "SO", "SOM", "", ""),
    /**
     *
     */
    SR("苏里南", "Suriname", "SR", "SUR", "", ""),
    /**
     *
     */
    SS("南苏丹", "South Sudan", "SS", "SSD", "", ""),
    /**
     *
     */
    ST("圣多美和普林西比", "Sao Tome & Principe", "ST", "STP", "", ""),
    /**
     *
     */
    SV("萨尔瓦多", "El Salvador", "SV", "SLV", "", ""),
    /**
     *
     */
    SY("叙利亚", "Syria", "SY", "SYR", "", ""),
    /**
     *
     */
    SZ("斯威士兰", "Swaziland", "SZ", "SWZ", "", ""),
    /**
     *
     */
    TC("特克斯和凯科斯群岛", "Turks & Caicos Islands", "TC", "TCA", "", ""),
    /**
     *
     */
    TD("乍得", "Chad", "TD", "TCD", "", ""),
    /**
     *
     */
    TG("多哥", "Togo", "TG", "TGO", "", ""),
    /**
     *
     */
    TH("泰国", "Thailand", "TH", "THA", "", ""),
    /**
     *
     */
    TK("托克劳", "Tokelau", "TK", "TKL", "", ""),
    /**
     *
     */
    TL("东帝汶", "Timor-Leste (East Timor)", "TL", "TLS", "", ""),
    /**
     *
     */
    TN("突尼斯", "Tunisia", "TN", "TUN", "", ""),
    /**
     *
     */
    TO("汤加", "Tonga", "TO", "TON", "", ""),
    /**
     *
     */
    TR("土耳其", "Turkey", "TR", "TUR", "", ""),
    /**
     *
     */
    TV("图瓦卢", "Tuvalu", "TV", "TUV", "", ""),
    /**
     *
     */
    TZ("坦桑尼亚", "Tanzania", "TZ", "TZA", "", ""),
    /**
     *
     */
    UA("乌克兰", "Ukraine", "UA", "UKR", "", ""),
    /**
     *
     */
    UG("乌干达", "Uganda", "UG", "UGA", "", ""),
    /**
     *
     */
    UY("乌拉圭", "Uruguay", "UY", "URY", "", ""),
    /**
     *
     */
    VA("梵蒂冈", "Vatican City (The Holy See)", "VA", "VAT", "", ""),
    /**
     *
     */
    VE("委内瑞拉", "Venezuela", "VE", "VEN", "", ""),
    /**
     *
     */
    VG("英属维尔京群岛", "British Virgin Islands", "VG", "VGB", "", ""),
    /**
     *
     */
    VI("美属维尔京群岛", "United States Virgin Islands", "VI", "VIR", "", ""),
    /**
     *
     */
    VN("越南", "Vietnam", "VN", "VNM", "", ""),
    /**
     *
     */
    WF("瓦利斯和富图纳", "Wallis and Futuna", "WF", "WLF", "", ""),
    /**
     *
     */
    WS("萨摩亚", "Samoa", "WS", "WSM", "", ""),
    /**
     *
     */
    YE("也门", "Yemen", "YE", "YEM", "", ""),
    /**
     *
     */
    YT("马约特", "Mayotte", "YT", "MYT", "", ""),
    /**
     *
     */
    ZA("南非", "South Africa", "ZA", "ZAF", "", ""),
    /**
     *
     */
    ZM("赞比亚", "Zambia", "ZM", "ZMB", "", ""),
    /**
     *
     */
    ZW("津巴布韦", "Zimbabwe", "ZW", "ZWE", "", ""),
    /**
     * 中国大陆主要使用“刚果（布）”一词，意指“首都为 布拉柴维尔的 刚果（共和国）”，而“刚果”一词亦普遍为民间所用。
     */
    CG("刚果（布）", "Republic of the Congo", "CG", "COG", "", ""),
    /**
     * 中国大陆主要使用“刚果（金）”一词，意指“首都为 金沙萨的 刚果（共和国）”，而“民主刚果”一词亦普遍为民间所用。
     */
    CD("刚果（金）", "Democratic Republic of the Congo", "CD", "COD", "", ""),
    /**
     * 中国大陆和 台湾均曾将之普遍译作“ 莫三比给”
     */
    MZ("莫桑比克", "Mozambique", "MZ", "MOZ", "", ""),
    /**
     * 中国大陆曾将之普遍译作“ 格恩西岛”
     */
    GG("根西岛", "Guernsey", "GG", "GGY", "", ""),
    /**
     * 亦有部份人士使用“ 刚比亚”一词于 港澳地区
     */
    GM("冈比亚", "Gambia", "GM", "GMB", "", ""),
    /**
     * 亦有部份人士使用“ 北玛利安娜群岛”一词于 港澳地区
     */
    MP("北马里亚纳群岛", "Northern Mariana Islands", "MP", "MNP", "", ""),
    /**
     * 亦有部份人士使用“ 埃塞俄比亚”一词于 台湾
     */
    ET("埃塞俄比亚", "Ethiopia", "ET", "ETH", "", ""),
    /**
     * 亦有部份人士使用“ 新喀尔多尼亚”一词于 港澳地区
     */
    NC("新喀里多尼亚", "New Caledonia", "NC", "NCL", "", ""),
    /**
     * 亦有部份人士使用“ 瓦努阿图”一词于 港澳地区
     */
    VU("瓦努阿图", "Vanuatu", "VU", "VUT", "", ""),
    /**
     * 台湾亦普遍采用“ 法属南方及南极陆地”一词于其它场合（如 MSN台湾）
     */
    TF("法属南部领地", "French Southern Territories", "TF", "ATF", "", ""),
    /**
     * 台湾亦普遍采用“ 纽威岛”（ CNS 12842译名）一词于其它场合（如 MSN台湾）
     */
    NU("纽埃", "Niue", "NU", "NIU", "", ""),
    /**
     * 台湾亦普遍采用“ 美国外岛”一词于其它场合（如 MSN台湾）
     */
    UM("美国本土外小岛屿", "United States Minor Outlying Islands", "UM", "UMI", "", ""),
    /**
     * 台湾和 香港亦普遍采用“ 科克群岛”（ CNS 12842译名）一词于其它场合
     */
    CK("库克群岛", "Cook Islands", "CK", "COK", "", ""),
    /**
     * 台湾和 香港亦普遍采用“ 联合王国”一词于其它场合
     */
    GB("英国", "Great Britain (United Kingdom; England)", "GB", "GBR", "", ""),
    /**
     * 台湾和 香港均将之简称为“ 特立尼达”
     */
    TT("特立尼达和多巴哥", "Trinidad & Tobago", "TT", "TTO", "", ""),
    /**
     * 台湾将之简称为“ 圣文森”
     */
    VC("圣文森特和格林纳丁斯", "St. Vincent & the Grenadines", "VC", "VCT", "", ""),
    /**
     * 新加坡与 马来西亚均将之译作“ 新西兰”。 香港亦普遍采用“新西兰”一词于其它场合
     */
    NZ("新西兰", "New Zealand", "NZ", "NZL", "", ""),
    /**
     * 新加坡与 马来西亚均将之译作“ 沙特阿拉伯”。 香港亦普遍采用“沙特阿拉伯”一词于其它场合
     */
    SA("沙特阿拉伯", "Saudi Arabia", "SA", "SAU", "", ""),
    /**
     * 新加坡与 马来西亚均将之译作“老挝”
     */
    LA("老挝", "Laos", "LA", "LAO", "", ""),
    /**
     * 澳门习惯称之为“ 北朝鲜”
     */
    KP("朝鲜 北朝鲜", "North Korea", "KP", "PRK", "", ""),
    /**
     * 澳门习惯称之为“ 韩国”
     */
    KR("韩国 南朝鲜", "South Korea", "KR", "KOR", "", ""),
    /**
     * 澳门民间亦普遍称之为葡国
     */
    PT("葡萄牙", "Portugal", "PT", "PRT", "", ""),
    /**
     * 香港习惯略去“斯坦”后缀，有必要会用全称
     */
    KG("吉尔吉斯斯坦", "Kyrgyzstan", "KG", "KGZ", "", ""),
    /**
     * 香港习惯略去“斯坦”后缀，有必要会用全称
     */
    KZ("哈萨克斯坦", "Kazakhstan", "KZ", "KAZ", "", ""),
    /**
     * 香港习惯略去“斯坦”后缀，有必要会用全称
     */
    TJ("塔吉克斯坦", "Tajikistan", "TJ", "TJK", "", ""),
    /**
     * 香港习惯略去“斯坦”后缀，有必要会用全称
     */
    TM("土库曼斯坦", "Turkmenistan", "TM", "TKM", "", ""),
    /**
     * 香港习惯略去“斯坦”后缀，有必要会用全称
     */
    UZ("乌兹别克斯坦", "Uzbekistan", "UZ", "UZB", "", ""),
    /**
     * 香港亦普遍采用“ 圣克里斯托佛岛及尼维斯岛”一词于其它场合（如 香港邮政的邮政指南附录表）。亦有部份人士使用“ 圣基茨和尼维斯”一词于 港澳地区
     */
    KN("圣基茨和尼维斯", "St. Kitts & Nevis", "KN", "KNA", "", ""),
    /**
     * 香港亦普遍采用“ 圣皮埃兰和密克隆群岛”一词于其它场合（如 香港邮政的邮政指南附录表）
     */
    PM("圣皮埃尔和密克隆", "Saint-Pierre and Miquelon", "PM", "SPM", "", ""),
    /**
     * 香港亦普遍采用“ 圣赫勒拿岛”一词于其它场合（如 香港邮政的邮政指南附录表）。亦有部份人士使用“ 圣海伦娜岛”一词于 港澳地区
     */
    SH("圣赫勒拿", "St. Helena & Dependencies", "SH", "SHN", "", ""),
    /**
     * 香港亦普遍采用“ 圣路西亚”一词于其它场合
     */
    LC("圣卢西亚", "St. Lucia", "LC", "LCA", "", ""),
    /**
     * 香港亦普遍采用“ 毛里求斯”一词于其它场合
     */
    MU("毛里求斯", "Mauritius", "MU", "MUS", "", ""),
    /**
     * 香港亦普遍采用“科特迪瓦”一词于其它场合（如 香港邮政的邮政指南附录表）
     */
    CI("科特迪瓦", "C?te d'Ivoire", "CI", "CIV", "", ""),
    /**
     * 香港亦普遍采用“ 肯尼亚”一词于其它场合
     */
    KE("肯尼亚", "Kenya", "KE", "KEN", "", ""),
    /**
     * 香港亦普遍采用“ 蒙古”一词于其它场合
     */
    MN("蒙古国 蒙古", "Mongolia", "MN", "MNG", "", ""),
    ;

    /**
     * 中文名称（中国内地惯用名，港澳台地区叫法可能不一样）
     */
    private final String chineseName;
    /**
     * 英文名称（不准，不要使用，需要后期维护）
     */
    private final String englishName;
    /**
     * 二字码
     */
    private final String twoCode;
    /**
     * 三字码
     */
    private final String threeCode;
    /**
     * 币种
     */
    private final String currency;
    /**
     * 语言
     */
    private final String language;
    // 电话代码     如：中国  86    美国：1
    // 时差   如：中国：0  美国：-13

    private CountryEnum(String chineseName, String englishName, String twoCode, String threeCode, String currency, String language) {
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.twoCode = twoCode;
        this.threeCode = threeCode;
        this.currency = currency;
        this.language = language;
    }

    public String getChineseName() {
        return chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getTwoCode() {
        return twoCode;
    }

    public String getThreeCode() {
        return threeCode;
    }

    public String getCurrency() {
        return currency;
    }

    public String getLanguage() {
        return language;
    }

    /**
     * TWOCODEMAP
     */
    private static final Map<String, CountryEnum> TWOCODEMAP = new HashMap<>();
    /**
     * THREECODEMAP
     */
    private static final Map<String, CountryEnum> THREECODEMAP = new HashMap<>();

    static {
        CountryEnum[] countryEnums = CountryEnum.values();
        Arrays.asList(countryEnums).forEach(c -> {
            TWOCODEMAP.put(c.getTwoCode(), c);
            THREECODEMAP.put(c.getThreeCode(), c);
        });
    }

    /**
     * @param twoCode 国家代码（两位）
     * @return 1
     */
    public static CountryEnum instanceByTwoCode(String twoCode) {
        return TWOCODEMAP.get(twoCode);
    }

    /**
     * 获取国际代码（二位码）列表
     *
     * @return 国际代码（二位码）列表
     */
    public static List<String> getTwoCodeList() {
        return new ArrayList<>(TWOCODEMAP.keySet());
    }

    /**
     * 根据threeCode 获取country
     * @param threeCode code
     * @return country
     */
    public static CountryEnum getCountryByThreeCode(String threeCode) {
        CountryEnum countryEnum = THREECODEMAP.get(threeCode);
        if(Objects.isNull(countryEnum)){
            countryEnum = TWOCODEMAP.get(threeCode);
        }
        return countryEnum;
    }

}

