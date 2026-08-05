package cn.vetech.center.hotel.link.elong.common;

import cn.vetech.center.hotel.link.api.enums.FreeMealEnum;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.SuffixTypeEnum;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.constant.HotelLinkConstant;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.elong.orderdetail.response.ElongOrderDetail;
import cn.vetech.center.hotel.link.elong.orderdetail.response.ElongRefundDetail;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongExtend;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchDayMeal;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchLadderParse;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.http.HttpService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.VeStringUtil;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchCommonUtils;
import cn.vetech.charge.base.CommonMagicNumber;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import cn.vetech.charge.cloud.modules.utils.security.MD5Tool;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

;

/**
 * 艺龙接口请求工具
 *
 * @author gaojin
 */
public class ElongHttp {
    /**
     * 日志工具
     */
    private static final Logger logger = LoggerFactory.getLogger(ElongHttp.class);
    /**
     *
     */
    public static final int IF = 0x0F;
    /**
     * /**
     */
    public static final String FYBH = FyEnum.ELONG.getFybh();
    /**
     *
     */
    public static final String FYEN = FyEnum.ELONG.getFyen();
    /**
     *
     */
    public static final String CHARSET = "UTF-8";
    /**
     *
     */
    private static HttpService httpService;

    /**
     * @param httpService 1
     */
    @Autowired
    public void setHttpService(HttpService httpService) {
        ElongHttp.httpService = httpService;
    }

    /**
     * @return config
     */
    public ElongConfig getConfig(Map<String, String> source) {
        return BeanMapper.map(source, ElongConfig.class);
    }

    /**
     * 接口调用入口
     *
     * @param data   业务参数(json)，发送时使用的url编码
     * @param method 需要调用的接口名称
     * @param config 供应商的接口配置
     * @param http   http/https
     * @param epoch  时间戳，非特殊需要时默认传0L
     * @return 响应数据
     */
    public static String sendInvoke(String method, String data, ElongConfig config, String http, long epoch) throws Exception {
        if (StringUtils.isBlank(method) || StringUtils.isBlank(data) || config == null) {
            return null;
        }
        String server = config.getUrl();
        if (!StringUtils.startsWithIgnoreCase(server, "http")) {
            http = StringUtils.isBlank(http) ? "http" : http;
            server = http + "://" + config.getUrl();
        }


        Map<String, String> reqMap = getReqMap(method, data, config, epoch);
        return httpService.doGet(server, reqMap);
    }

    /**
     * @param data   业务参数(json)，发送时使用的url编码
     * @param method 需要调用的接口名称
     * @param config 供应商的接口配置
     * @param epoch  时间戳，非特殊需要时默认传0L
     * @return 请求参数
     */
    public static Map<String, String> getReqMap(String method, String data, ElongConfig config, long epoch) {
        if (StringUtils.isBlank(method) || StringUtils.isBlank(data) || config == null) {
            return null;
        }
        if (epoch == 0L) {
            epoch = System.currentTimeMillis() / Long.valueOf("1000");
        }
        data = "{\"Version\":\"" + config.getVersion() + "\",\"Local\":\"" + config.getLocal() + "\",\"Request\":" + data + "}";
        String sig = MD5Tool.MD5Encode(epoch + MD5Tool.MD5Encode(data + config.getAppKey()) + config.getSecretKey());
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("format", "json");
        reqMap.put("method", method);
        reqMap.put("user", config.getUser());
        reqMap.put("timestamp", String.valueOf(epoch));
        reqMap.put("signature", sig);
        reqMap.put("data", data);
        return reqMap;
    }

    /**
     * 信用卡号加密 信用卡卡号Des加密
     * 加密内容=当前时间戳+#+信用卡号，key为appkey的后8位
     *
     * @param epoch  时间戳
     * @param card   信用卡卡号
     * @param appKey appKey
     * @return 加密后的字符串
     */
    public static String cardEnc(long epoch, String card, String appKey) {
        if (StringUtils.isBlank(card) || StringUtils.isBlank(appKey)) {
            return null;
        }
        int len = appKey.length();
        if (len < CommonMagicNumber.INT8) {
            return null;
        }
        String res = null;
        if (epoch == 0L) {
            epoch = System.currentTimeMillis() / Long.valueOf("1000");
        }
        String source = epoch + "#" + card;
        String key = appKey.substring(len - CommonMagicNumber.INT8);
        try {
            res = desEncrypt(source, key);
        } catch (Exception e) {
            logger.error("艺龙信用卡号加密异常", e);
        }
        return res;
    }

    /**
     *
     */
    private static final String HEX_CHARS = "0123456789abcdef";

    /**
     * DES对称加密
     *
     * @param content  content
     * @param password 对称加密的key
     * @return str
     * @throws Exception ex
     */
    public static String desEncrypt(String content, String password) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(password.getBytes(CHARSET), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        byte[] byteContent = content.getBytes(CHARSET);
        IvParameterSpec iv = new IvParameterSpec(password.getBytes(CHARSET));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] result = cipher.doFinal(byteContent);
        return toHexString(result);
    }

    /**
     * @param bytes byts
     * @return str
     */
    public static String toHexString(byte[] bytes) {
        StringBuilder buffer = new StringBuilder();
        for (byte aByte : bytes) {
            buffer.append(HEX_CHARS.charAt(aByte >>> CommonMagicNumber.INT4 & IF));
            buffer.append(HEX_CHARS.charAt(aByte & IF));
        }
        return buffer.toString();
    }

    /**
     * 将List转换成Map
     *
     * @param clazz List和Map中的泛型
     * @param list  需要转换的集合
     * @param key   生成Map时，由key字段对应的值，生成Map主键
     * @param <T>   泛型
     * @return Map
     */
    public static <T> Map<String, T> listToMap(Class<T> clazz, List<T> list, String key) {
        Map<String, T> map = new HashMap<>();
        if (clazz == null || list == null || key == null) {
            return map;
        }
        try {
            Method method = new PropertyDescriptor(key, clazz).getReadMethod();
            for (T o : list) {
                String value = (String) method.invoke(o);
                map.put(value, o);
            }
        } catch (Exception e) {
            logger.error("将List转换成Map异常", e);
        }
        return map;
    }

    /**
     * 计算处理罚金
     *
     * @param orderDetail 订单明细
     * @return 罚金
     */
    protected BigDecimal dealPenaltyAmount(ElongOrderDetail orderDetail) {
        ElongRefundDetail refundDetail = orderDetail.getRefundDetail();
        if (Objects.isNull(refundDetail)) {
            return BigDecimal.ZERO;
        }
        Double refundAmount = refundDetail.getRefundAmount();
        if (Objects.isNull(refundAmount)) {
            return BigDecimal.ZERO;
        }
        if (refundAmount.doubleValue() == 0d) {
            return BigDecimal.ZERO;
        }
        if (StringUtils.isBlank(orderDetail.getTotalPrice())) {
            return BigDecimal.ZERO;
        }
        String totalPrice = orderDetail.getTotalPrice();
        BigDecimal totalPriceDecimal = NumberUtils.createBigDecimal(totalPrice);
        BigDecimal penaltyAmount = totalPriceDecimal.subtract(BigDecimal.valueOf(refundAmount.doubleValue()));
        return penaltyAmount.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 计算处理退款金额
     *
     * @param orderDetail 订单明细
     * @return 退款金额
     */
    protected BigDecimal dealRefundAmount(ElongOrderDetail orderDetail) {
        ElongRefundDetail refundDetail = orderDetail.getRefundDetail();
        if (Objects.isNull(refundDetail)) {
            return BigDecimal.ZERO;
        }
        Double refundAmount = refundDetail.getRefundAmount();
        if (Objects.isNull(refundAmount)) {
            return BigDecimal.ZERO;
        }
        if (refundAmount.doubleValue() == 0d) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(refundAmount).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 处理早餐
     *
     * @param dayMealTable 餐食信息
     * @return 早餐数量
     */
    protected ImmutableTriple<FreeMealEnum, String, String> dealMealNum(List<ElongSearchDayMeal> dayMealTable, String rzrq) {
        // 每天的是否使用动态餐食是否相同
        if (ListUtil.isEmpty(dayMealTable)) {
            return ImmutableTriple.of(FreeMealEnum.NO, FreeMealEnum.NO.getAmount(), FreeMealEnum.NO.getDesc());
        }
        // 例如：入住日期是2025-05-14，离店日期是2025-05-17，早餐分别取2025-05-15到2025-05-17日期对应的早餐
        List<ElongSearchDayMeal> elongSearchDayMeals = dayMealTable.stream()
                .filter(meal -> !StringUtils.equals(meal.getDate(), rzrq))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(elongSearchDayMeals)) {
            return ImmutableTriple.of(FreeMealEnum.NO, FreeMealEnum.NO.getAmount(), FreeMealEnum.NO.getDesc());
        }
        // 处理早餐，按份数升序
        List<ElongSearchDayMeal> roomRates = elongSearchDayMeals.stream()
                .sorted(Comparator.comparing(ElongSearchDayMeal::getBreakfastShare))
                .collect(Collectors.toList());
        ElongSearchDayMeal dayMeal = roomRates.get(0);
        Boolean useDynamicMeal = dayMeal.getUseDynamicMeal();
        // 为true取dynamicMealDesc
        if (Objects.nonNull(useDynamicMeal) && useDynamicMeal) {
            // 每天餐食是否相同
            Map<String, List<ElongSearchDayMeal>> groupingByDynamicMealDescMap = elongSearchDayMeals.stream()
                    .collect(Collectors.groupingBy(ElongSearchDayMeal::getDynamicMealDesc));
            if (MapUtils.isEmpty(groupingByDynamicMealDescMap) || groupingByDynamicMealDescMap.size() != 1) {
                return ImmutableTriple.of(FreeMealEnum.NO, FreeMealEnum.NO.getAmount(), FreeMealEnum.NO.getDesc());
            }
            // 动态餐食描述
            String mealDesc = VeStringUtil.joinWithCommaIsNotBlank(dayMeal.getDynamicMealDesc(), dayMeal.getDayMealCopyWriting());
            return ImmutableTriple.of(FreeMealEnum.Q, null, mealDesc);
        } else {
            // 早餐数量
            Integer breakfastShare = dayMeal.getBreakfastShare();
            // 早餐描述
            String breakfastDesc = dayMeal.getBreakfastDesc();
            String fastNum = Objects.nonNull(breakfastShare) ? String.valueOf(breakfastShare) : "0";
            return ImmutableTriple.of(FreeMealEnum.getFreeMealByCode("B_" + fastNum), fastNum, breakfastDesc);
        }
    }

    /**
     * 赋值最晚取消时间
     *
     * @param plancps         cps
     * @param ladderParseList list
     */
    public void dealZwqxsj(SearchRatePlan plancps, List<ElongSearchLadderParse> ladderParseList, String gngj, String hotelLocalTimezone) {
        String curTime = VeDate.getStringDate();
        try {
            if (ListUtil.isEmpty(ladderParseList)) {
                plancps.setZwqxsj(VeDate.getStringDate());
                RateSearchCommonUtils.convertSearchPrepayRule(plancps, SuffixTypeEnum.NOT_CANCEL, null, null);
                return;
            }
            Optional<ElongSearchLadderParse> opLadderParse = ladderParseList.stream().filter(ladder -> {
                if (Objects.isNull(ladder.getAmount())) {
                    return false;
                }
                boolean boolCut = StringUtils.equalsIgnoreCase(ladder.getCutType(), "0");
                int compareTo = ladder.getAmount().compareTo(BigDecimal.ZERO);
                return compareTo == NumConstant.NUM_0 && boolCut;
            }).findFirst();
            opLadderParse.ifPresent(parse -> {
                String endTime = parse.getEndTime();
                if (StringUtils.isBlank(endTime)) {
                    plancps.setZwqxsj(VeDate.getStringDate());
                    return;
                }
                long longTime = org.apache.commons.lang3.math.NumberUtils.toLong(endTime);
                if (longTime == 0L) {
                    plancps.setZwqxsj(VeDate.getStringDate());
                    return;
                }
                Date endDateTime = new Date(longTime * NumConstant.NUM_1000);
                String zwqxsj = VeDate.dateToStrLong(endDateTime);
                plancps.setZwqxsj(zwqxsj);
            });
        } catch (Exception ex) {
            logger.error("艺龙赋值最晚取消时间异常:{}", JacksonUtils.toJsonWithDefault(ladderParseList), ex);
            plancps.setZwqxsj(VeDate.getStringDate());
        }
        if (StringUtils.isBlank(plancps.getZwqxsj())) {
            plancps.setZwqxsj(VeDate.getStringDate());
        }
        try {
            int twoSec = VeDate.getTwoSec(curTime, plancps.getZwqxsj());
            if (twoSec > NumConstant.NUM_0) {
                RateSearchCommonUtils.convertSearchPrepayRule(plancps, SuffixTypeEnum.NOT_CANCEL, null, null);
                return;
            }
        } catch (Exception ex) {
            logger.error("比较最晚取消时间异常:{}", plancps.getZwqxsj(), ex);
        }
        RateSearchCommonUtils.dealTimezonePrepayRule(plancps, gngj, HotelLinkConstant.BEIJIN_TIMEZONE, StringUtils.EMPTY, hotelLocalTimezone);
    }

    public boolean checkGngj(String dtoGngj, String extSfgat, String sfgat) {
        // 艺龙港澳台酒店走国际酒店账号查询，传参
        return StringUtils.equals(dtoGngj, GnGjTypeEnum.GJ.getCode()) || StringUtils.equalsAny("1", sfgat, extSfgat);
    }

    public Integer convertGatExt(Integer numberOfAdults, ElongExtend extend) {
        // 港澳台酒店预订，成人数需要跟查询报价传的成人数一致
        Integer numberOfAdultsExt = extend.getNumberOfAdults();
        if (Objects.nonNull(numberOfAdultsExt) && Objects.nonNull(numberOfAdults) && numberOfAdultsExt > numberOfAdults) {
            return numberOfAdultsExt;
        }
        return numberOfAdults;
    }

}