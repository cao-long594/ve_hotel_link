package cn.vetech.center.hotel.link.elong.orderbook;

import cn.vetech.center.hotel.link.api.orderbook.dto.BookContact;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookOrderRoom;
import cn.vetech.center.hotel.link.api.orderbook.dto.LinkHotelOrderBookDTO;
import cn.vetech.center.hotel.link.api.orderbook.vo.LinkHotelOrderBookVO;
import cn.vetech.center.hotel.link.bean.PersonName;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.elong.common.ElongConfig;
import cn.vetech.center.hotel.link.elong.common.ElongHttp;
import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import cn.vetech.center.hotel.link.elong.common.ElongService;
import cn.vetech.center.hotel.link.elong.constant.ElongCodeEnum;
import cn.vetech.center.hotel.link.elong.orderbook.request.ElongBookContact;
import cn.vetech.center.hotel.link.elong.orderbook.request.ElongBookCreditCard;
import cn.vetech.center.hotel.link.elong.orderbook.request.ElongBookCustomer;
import cn.vetech.center.hotel.link.elong.orderbook.request.ElongBookOrderRoom;
import cn.vetech.center.hotel.link.elong.orderbook.request.ElongOrderBookRequest;
import cn.vetech.center.hotel.link.elong.orderbook.response.ElongOrderBook;
import cn.vetech.center.hotel.link.elong.orderbook.response.ElongOrderBookResponse;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongExtend;
import cn.vetech.center.hotel.link.elong.ratesearch.response.NightPriceExtend;
import cn.vetech.center.hotel.link.elong.register.ElongRegisterService;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.enums.SalePriceControlTypeEnum;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.orderbook.OrderBookApiRes;
import cn.vetech.center.hotel.link.util.orderbook.OrderBookCommonUtils;
import cn.vetech.charge.base.CommonMagicNumber;
import cn.vetech.charge.cloud.modules.utils.collection.CollectionUtil;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author gaojin
 */
@Service
public class ElongOrderBookService extends ElongHttp implements ElongService {
    /**
     * 日志工具
     */
    private final Logger logger = LoggerFactory.getLogger(ElongOrderBookService.class);
    /**
     * 艺龙订单预订接口名
     */
    private final String method = "hotel.order.create";
    /**
     * 艺龙订单预订使用https
     */
    private final String http = "https";

    /**
     * 注册服务
     */
    @Autowired
    private ElongRegisterService elongRegisterService;

    @Override
    public ElongResponse execute(ElongRequest req) {
        String src = null;
        try {
            src = sendInvoke(method, req.toJson(), req.getConfig(), http, 0L);
        } catch (Exception e) {
            logger.error("艺龙下单异常;请求参数：{}", req.toJson(), e);
            return null;
        }
        logger.info("艺龙下单，请求：{}，返回：{}", req.toJson(), src);
        return JacksonUtils.parseNonEmpty(src, ElongOrderBookResponse.class);
    }

    /**
     * @param res res
     * @return vo
     */
    public LinkHotelOrderBookVO converResponse(ElongOrderBookResponse res) {
        if (Objects.isNull(res)) {
            return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10003, "接口响应数据为空");
        }
        LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
        ElongOrderBook result = res.getResult();
        String code = res.getCode();
        //code是0表示成功，如果失败，code会返回具体错误信息
        if (!"0".equals(code)) {
            return OrderBookApiRes.failSupportInverseQuery(ElongCodeEnum.OrderBookEnum.getErrorCodeEnumByCode(code), code);
        }
        if (Objects.isNull(result)) {
            return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10003);
        }
        vo.setErrorMsg(result.getPaymentMessage());
        if (StringUtils.isBlank(result.getOrderId())) {
            return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10003, "订单ID为空");
        }
        //paymentmessage 不为空
        if (StringUtils.isNotBlank(result.getPaymentMessage()) && !StringUtils.equalsIgnoreCase("null", result.getPaymentMessage())) {
            return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10003, result.getPaymentMessage());
        }
        vo.setOrderId(result.getOrderId());
        vo.setCancelTime(result.getCancelTime());
        vo.setAmount(result.getGuaranteeAmount());
        vo.setCurrencyCode(result.getCurrencyCode());
        vo.setIsInstantConfirm(result.getIsInstantConfirm());
        vo.setPaymentDeadlineTime(result.getPaymentDeadlineTime());
        vo.setPaymentMessage(result.getPaymentMessage());
        return OrderBookApiRes.success(vo);
    }

    /**
     * @param dto dto
     * @return request
     */
    public ElongOrderBookRequest convertRequest(LinkHotelOrderBookDTO dto) {
        if (dto == null) {
            logger.error("艺龙订单预订，CPS请求参数对象为null");
            return null;
        }
        ElongConfig config = BeanMapper.map(dto.getSupplier(), ElongConfig.class);
        ElongOrderBookRequest req = new ElongOrderBookRequest();
        req.setConfig(config);
        req.setAffiliateConfirmationId(dto.getLocalOrderId());
        req.setHotelId(dto.getHotelId());
        req.setRoomTypeId(dto.getRoomId());
        String rateplanid = dto.getRatePlanId();
        if (StringUtils.isNotEmpty(rateplanid)) {
            if (rateplanid.contains("_")) {
                req.setRatePlanId(rateplanid.split("_")[0]);
            } else {
                req.setRatePlanId(dto.getRatePlanId());
            }
            if (rateplanid.contains("@") && rateplanid.split("@").length >= CommonMagicNumber.INT2) {
                //req.setLittleMajiaId(rateplanid.split("@")[CommonMagicNumber.INT1]);
                req.setGoodsUniqId(rateplanid.split("@")[CommonMagicNumber.INT1]);
                if (rateplanid.split("@")[CommonMagicNumber.INT0].contains("_")) {
                    req.setRatePlanId(rateplanid.split("@")[CommonMagicNumber.INT0].split("_")[0]);
                } else {
                    req.setRatePlanId(rateplanid.split("@")[CommonMagicNumber.INT0]);
                }
            }
        }
        req.setArrivalDate(StringUtils.substring(dto.getCheckInDate(), CommonMagicNumber.INT0, CommonMagicNumber.INT10));
        req.setDepartureDate(StringUtils.substring(dto.getCheckOutDate(), CommonMagicNumber.INT0, CommonMagicNumber.INT10));
        req.setCustomerType(dto.getCustomerType());
        String payment = dto.getPayment();
        if ("0".equals(payment)) {
            req.setPaymentType("SelfPay");
            req.setIsGuaranteeOrCharged("false");
        } else if ("1".equals(payment)) {
            req.setPaymentType("Prepay");
            req.setIsGuaranteeOrCharged("true");
        } else {
            logger.error("艺龙订单预订，付款类型错误！");
        }
        req.setNumberOfRooms(dto.getNumberOfRooms());
        //前端这个块传错了  先这样临时解决
        int numberOfCustomers = 0;
        if (ListUtil.isNotEmpty(dto.getOrderRooms())) {
            numberOfCustomers = dto.getOrderRooms().stream().filter(orderRoom -> ListUtil.isNotEmpty(orderRoom.getCustomers())).mapToInt(orderRoom -> orderRoom.getCustomers().size()).sum();
        }
        req.setNumberOfCustomers(String.valueOf(numberOfCustomers));
        String earliestArrivalTime = dto.getEarliestArrivalTime();
        String[] split = earliestArrivalTime.replaceAll("\\(|\\)|（|）|[\\u4e00-\\u9fa5]", "").split(" ");
        String hour = StringUtils.substring(split[1], CommonMagicNumber.INT1, CommonMagicNumber.INT2);
        String hh = StringUtils.substring(split[1], 0, CommonMagicNumber.INT1);
        if (Integer.parseInt(hh) == 0 && (Integer.parseInt(hour) >= 0 || Integer.parseInt(hour) <= CommonMagicNumber.INT6)) {
            req.setEarliestArrivalTime(VeDate.getNextDay(split[0], "-1") + " 23:59:00");
        } else {
            req.setEarliestArrivalTime(earliestArrivalTime);
        }
        req.setLatestArrivalTime(dto.getLatestArrivalTime());
        req.setCurrencyCode(StringUtils.isNoneBlank(dto.getCurrencyCode()) ? dto.getCurrencyCode() : "RMB");
        req.setTotalPrice(dto.getTotalPrice());
        req.setCustomerIPAddress(dto.getCustomerIPAddress());
        //不允许确认
        req.setConfirmationType("NotAllowedConfirm");
        req.setNoteToElong(dto.getNoteToSupply());
        req.setNoteToHotel(dto.getNoteToHotel());
        //公司月结开发票
        req.setIsNeedInvoice("false");
        //入住人信息
        List<BookOrderRoom> orderRooms = dto.getOrderRooms();
        List<ElongBookOrderRoom> bookOrderRooms = BeanMapper.mapList(orderRooms, BookOrderRoom.class, ElongBookOrderRoom.class);
        //420803197410263616
        bookOrderRooms.forEach(rzr -> {
            rzr.getCustomers().forEach(r -> {
                r.setCustomerIdType(StringUtils.defaultIfEmpty(r.getCustomerIdType(), r.getIdCardType()));
                r.setIdcard(StringUtils.defaultIfEmpty(r.getIdcard(), r.getCustomerIdNo()));
                if (StringUtils.isNotBlank(r.getCustomerIdType()) && !"IdentityCard".equalsIgnoreCase(r.getCustomerIdType())) {
                    // 不是身份证类型,就把身份证置空
                    r.setIdcard("");
                }
                //begen xiaotengyu 艺龙姓名英文，拼音处理；禁止使用“小姐”、“先生”、“女士”、名人姓名、污秽词语，中文姓名不得出现任何汉字外的字符,当客人输入拼音或英文姓名时，应给与明确的提示，在下方展示红色提示文案，类似：英文姓和名用“/”隔开。并且要做强校验，没有斜杠时，无法提交订单。
                String name = dealName(r.getName());
                r.setName(name);
                //end
            });
        });
        req.setOrderRooms(bookOrderRooms);
        //订单联系人信息
        ElongBookContact contact = new ElongBookContact();
        BookContact bookContact = dto.getContact();
        if (null != bookContact) {
            contact.setName(bookContact.getName());
            contact.setMobile(bookContact.getMobile());
            contact.setMobileAreaCode(bookContact.getMobileGjdm());
        } else {
            contact.setName(config.getLxrxm());
            contact.setMobile(config.getLxrdh());
        }
        if (StringUtils.isNotBlank(config.getLxryx())) {
            contact.setEmail(config.getLxryx());
        }
        contact.setGender("Unknown");
        req.setContact(contact);
        //判断处理担保信用卡信息
        String type = dto.getGuaranteeType();
        String price = dto.getGuaranteePrice();
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(price)) {
            ElongBookCreditCard card = new ElongBookCreditCard();
            String cardEnc = cardEnc(0L, config.getXykkh(), config.getAppKey());
            card.setNumber(cardEnc);
            String[] yxq = config.getXykyxrq().split("/");
            card.setExpirationYear(yxq[1]);
            card.setExpirationMonth(yxq[0]);
            card.setHolderName(config.getXykckrxm());
            String zjlx = config.getXykckrzjlx();
            if ("0".equals(zjlx)) {
                card.setIdType("IdentityCard");
            } else if ("1".equals(zjlx)) {
                card.setIdType("Passport");
            } else {
                card.setIdType("Other");
            }
            card.setCvv(config.getXykcvv());
            card.setIdNo(config.getXykckrzjh());
            req.setCreditCard(card);
        }
        //处理扩展字段 销售价处理
        dealExtend(req, dto, config);
        //处理会员价
        buildOpenId(config, req, dto);
        //处理国际字段
        dealGjName(dto, req);

        return req;
    }

    /**
     * 处理名称
     *
     * @param name name
     * @return name
     */
    private String dealName(String name) {
        if (StringUtils.isEmpty(name)) {
            return name;
        }
        String[] s = StringUtils.split(name, " ");
        if (s == null || s.length != NumConstant.NUM_2) {
            return name;
        }
        String join = StringUtils.join(s, "/");
        return join;
    }

    /**
     * 处理限价
     *
     * @param req    艺龙请求对象
     * @param dto    本地请求对象
     * @param config config
     */
    private void dealExtend(ElongOrderBookRequest req, LinkHotelOrderBookDTO dto, ElongConfig config) {
        try {
            //20220614 customerPrice默认传成本价 如果此价格是限制价格产品则传艺龙售卖价格
            req.setCustomerPrice(dto.getTotalPrice());
            String gysxdbj = dto.getGysxdbj();
            if (StringUtils.isBlank(gysxdbj)) {
                return;
            }
            ElongExtend extend = JacksonUtils.parseNonNull(gysxdbj, ElongExtend.class);
            if (Objects.isNull(extend)) {
                return;
            }
            String littlemajiaid = extend.getLittlemajiaid();
            if (StringUtils.isNotBlank(littlemajiaid)) {
                String s = littlemajiaid.replaceAll("\\{ve\\}", "&");
                req.setLittleMajiaId(s);
            }
            List<NightPriceExtend> priceExtends = extend.getPriceExtends();
            if (!StringUtils.equalsIgnoreCase(config.getLpKzxsjlx(), SalePriceControlTypeEnum.EQUAL.getCode())
                    && !StringUtils.equalsIgnoreCase(config.getLpKzxsjlx(), SalePriceControlTypeEnum.LTPRICE.getCode())
                    && CollectionUtil.isNotEmpty(priceExtends) && Objects.nonNull(extend.getPriceLimitedType())) {
                int size = dto.getNightlyRates().size();
                int priceSize = priceExtends.size();
                if (size == priceSize) {
                    double sum = priceExtends.stream().mapToDouble(p -> NumberUtils.toDouble(p.getMember())).sum();
                    logger.info("艺龙下单限制销售价：{}", sum);
                    int numberOfRooms = NumberUtils.toInt(dto.getNumberOfRooms(), NumConstant.NUM_1);
                    req.setCustomerPrice(String.valueOf(sum * numberOfRooms));
                } else {
                    logger.error("elong:限价的每日价格和价格计划每日价格天数不同");
                }
            }
            //控制销售价处理
            if (StringUtils.equalsIgnoreCase(config.getLpKzxsjlx(), SalePriceControlTypeEnum.LTPRICE.getCode())
                    && CollectionUtil.isNotEmpty(priceExtends)) {
                int size = dto.getNightlyRates().size();
                int priceSize = priceExtends.size();
                req.setCustomerPrice(req.getTotalPrice());
                if (size == priceSize) {
                    double sum = priceExtends.stream().mapToDouble(p -> NumberUtils.toDouble(p.getCost())).sum();
                    logger.info("艺龙下单限制销售价产品，成本价：{}", sum);
                    req.setTotalPrice(String.valueOf(sum));
                } else {
                    logger.error("elong:限价的每日价格和价格计划每日价格天数不同");
                }
            }
            if (checkGngj(GnGjTypeEnum.judgeGjHotel(dto.getLocalHotelId()).getCode(), extend.getSfgat(), dto.getSfgat())) {
                if (StringUtils.isNotBlank(extend.getSupplierId())) {
                    req.setSupplierId(extend.getSupplierId());
                }
                if (StringUtils.isNotBlank(extend.getHotelCode())) {
                    req.setHotelCode(extend.getHotelCode());
                }
                if (StringUtils.isNotBlank(extend.getShopperProductId())) {
                    req.setShopperProductId(extend.getShopperProductId());
                }
                if (StringUtils.isNotBlank(extend.getSubSupplierId())) {
                    req.setSubSupplierId(extend.getSubSupplierId());
                }
                req.setCurrencyCode(StringUtils.defaultIfBlank(extend.getCurrencyCode(), "RMB"));
                req.setNumberOfAdults(NumberUtils.toInt(dto.getAdult(), NumConstant.NUM_1));
                // 港澳台酒店处理
                if (StringUtils.equalsAny("1", extend.getSfgat(), dto.getSfgat())) {
                    req.setNumberOfAdults(convertGatExt(req.getNumberOfAdults(), extend));
                }
            }

        } catch (Exception ex) {
            logger.error("elong:下单处理限价异常；orderbookdto:{}"
                    , JacksonUtils.toJsonWithDefault(dto)
                    , ex);
        }
    }

    /**
     * 处理会员价
     *
     * @param req    艺龙请求对象
     * @param config 配置
     * @param dto    本地请求对象
     */
    private void buildOpenId(ElongConfig config, ElongOrderBookRequest req, LinkHotelOrderBookDTO dto) {
        String openId = elongRegisterService.getOpenId(config, dto);
        if (StringUtils.isBlank(openId)) {
            return;
        }
        req.setOpenId(openId);
    }

    /**
     * 处理国际firstname， lastname
     *
     * @param dto     请求参数
     * @param request 艺龙请求参数
     */
    private void dealGjName(LinkHotelOrderBookDTO dto, ElongOrderBookRequest request) {
        ElongExtend extend = JacksonUtils.parseNonNull(dto.getGysxdbj(), ElongExtend.class);
        if (Objects.isNull(extend)) {
            return;
        }
        if (!checkGngj(GnGjTypeEnum.judgeGjHotel(dto.getLocalHotelId()).getCode(), extend.getSfgat(), dto.getSfgat())) {
            return;
        }
        //联系人
        ElongBookContact contact = request.getContact();
        Optional<PersonName> personName = OrderBookCommonUtils.handlePersonNameToHanyuPinyin(contact.getName());
        personName.ifPresent(person -> {
            contact.setFirstName(person.getFirstName());
            contact.setLastName(person.getLastName());
        });
        request.setContact(contact);
        //入住人
        List<ElongBookOrderRoom> orderRooms = request.getOrderRooms();
        for (ElongBookOrderRoom orderRoom : orderRooms) {
            List<ElongBookCustomer> customers = orderRoom.getCustomers();
            if (CollectionUtils.isEmpty(customers)) {
                continue;
            }
            for (ElongBookCustomer customer : customers) {
                Optional<PersonName> customerName = OrderBookCommonUtils.handlePersonNameToHanyuPinyin(customer.getName());
                customerName.ifPresent(name -> {
                    customer.setFirstName(name.getFirstName());
                    customer.setLastName(name.getLastName());
                });
            }
        }
    }

}