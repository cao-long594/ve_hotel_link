package cn.vetech.center.hotel.link.util.orderdetail;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.HotelOrderStatusEnum;
import cn.vetech.center.hotel.link.api.enums.PtEnum;
import cn.vetech.center.hotel.link.enums.ChargeForNoShowOfPrepaidEnum;
import cn.vetech.center.hotel.link.enums.HotelGysOrderStatusEnum;
import cn.vetech.center.hotel.link.enums.PaymentEnum;
import cn.vetech.center.hotel.link.util.orderdetail.model.OrderStatusHandlerModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * 供应商原始订单状态转换成标准的订单状态
 *
 * @author chengwanshan
 * @since 2021/4/6 10:29
 */
public class OrderDetailHandler {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetailHandler.class);

    private OrderDetailHandler() {
    }

    /**
     * 处理订单状态（预付订单NOSHOW收取费用）
     *
     * @param cpszt              本地订单状态
     * @param payment            支付方式
     * @param pt                 平台
     * @param gysOrderStatusEnum 映射状态
     * @return String
     */
    public static String getShowStatus(String cpszt, String payment, String pt, HotelGysOrderStatusEnum gysOrderStatusEnum) {
        return getShowStatus(cpszt, null, payment, pt, gysOrderStatusEnum);
    }

    /**
     * 处理订单状态（预付订单NOSHOW收取费用）
     *
     * @param cpszt              本地订单状态
     * @param gyszt              供应商订单状态
     * @param payment            支付方式
     * @param pt                 平台
     * @param gysOrderStatusEnum 映射状态
     * @return String
     */
    public static String getShowStatus(String cpszt, String gyszt, String payment, String pt, HotelGysOrderStatusEnum gysOrderStatusEnum) {
//        LOGGER.info("同步订单状态公共方法开始，本地订单状态【{}】，供应商原始订单状态【{}】，支付方式【{}】，平台【{}】", cpszt, gyszt, payment, pt);
        OrderStatusHandlerModel handlerModel = new OrderStatusHandlerModel();
        handlerModel.setPlatform(pt);
        handlerModel.setPtOrderStatus(cpszt);
        handlerModel.setPayment(payment);
        String convert2Status = OrderDetailHandler.convert2Status(handlerModel, gysOrderStatusEnum);
        if (StringUtils.isBlank(convert2Status)) {
            convert2Status = cpszt;
        }
//        LOGGER.info("同步订单状态公共方法结束，转换后订单状态【{}】", convert2Status);
        return convert2Status;
    }

    /**
     * 根据平台处理订单状态（取消状态：8C（cps平台）和 8E（费控））
     *
     * @param platform 平台
     * @return String
     */
    @Deprecated
    public static String convert2Status(String platform) {
        // cps平台
        if (StringUtils.equalsIgnoreCase(PtEnum.CPS.getValue(), platform)) {
            // "8C", "供应拒单（确认后）"
            return HotelOrderStatusEnum.SUPPLY_CANCEL_AFTER_CONFIRM.getCode();
        }
        // "8E", "平台拒单（确认后）"
        return HotelOrderStatusEnum.SYSTEM_CANCEL_AFTER_CONFIRM.getCode();
    }

    /**
     * 根据平台处理订单状态（取消状态：8C（cps平台）和 8E（费控））
     * 根据平台处理订单状态（取消状态：7A（cps平台）和 7D（费控））
     *
     * @param platform      平台
     * @param ptOrderStatus 平台状态
     * @return String
     */
    public static String convert2Status(String platform, String ptOrderStatus) {
        // 平台状态（"7", "已支付 待确认"）
        if (HotelOrderStatusEnum.PAY_BEFORE_CONFIRM.getCode().equals(ptOrderStatus)) {
            // cps平台
            if (StringUtils.equalsIgnoreCase(PtEnum.CPS.getValue(), platform)) {
                // "7A", "供应拒单（确认前）"
                return HotelOrderStatusEnum.SUPPLY_CANCEL_BEFORE_CONFIRM.getCode();
            }
            // "7D", "平台拒单（确认前）"
            return HotelOrderStatusEnum.SYSTEM_CANCEL_BEFORE_CONFIRM.getCode();
        }

        // 平台状态（"8", "已支付已确认"）
        if (HotelOrderStatusEnum.PAY_AFTER_CONFIRM.getCode().equals(ptOrderStatus)) {
            // cps平台
            if (StringUtils.equalsIgnoreCase(PtEnum.CPS.getValue(), platform)) {
                // "8C", "供应拒单（确认后）"
                return HotelOrderStatusEnum.SUPPLY_CANCEL_AFTER_CONFIRM.getCode();
            }
            // "8E", "平台拒单（确认后）"
            return HotelOrderStatusEnum.SYSTEM_CANCEL_AFTER_CONFIRM.getCode();
        }
        return ptOrderStatus;
    }

    /**
     * 根据平台处理订单状态
     *
     * @param handlerModel       订单信息
     * @param gysOrderStatusEnum 供应商订单状态
     * @return String
     */
    private static String convert2Status(OrderStatusHandlerModel handlerModel, HotelGysOrderStatusEnum gysOrderStatusEnum) {
        if (handlerModel == null) {
            return null;
        }
        // 平台
        String platform = handlerModel.getPlatform();
        // 支付方式
        String payment = handlerModel.getPayment();
        // 本地订单状态
        String ptOrderStatus = handlerModel.getPtOrderStatus();
        // 预付订单，NOSHOW情况下，是否收费
        String isCharge = handlerModel.getIsChargeForNowshow();
        if (gysOrderStatusEnum == null) {
            return ptOrderStatus;
        }
        // 供应商订单状态
        String gysOrderStatus = gysOrderStatusEnum.getCode();
//        LOGGER.info("同步订单状态公共方法，转换后供应商订单状态（标准）【{}】", gysOrderStatusEnum.getName());

        // 供应商没有离店状态的，自动离店后，防止手动同步状态回退到 "已确认" 或 "已入住"
        if (StringUtils.equals(HotelOrderStatusEnum.OUT.getCode(), ptOrderStatus)) {
            // "5", "已离店"
            return HotelOrderStatusEnum.OUT.getCode();
        }

        // 现付
        if (StringUtils.equals(PaymentEnum.CASH.getCode(), payment)) {
            return convertCash(gysOrderStatus, ptOrderStatus, platform);
        }

        // 预付
        if (StringUtils.equals(PaymentEnum.PREPAID.getCode(), payment)) {
            return convertPrepaid(gysOrderStatus, ptOrderStatus, platform, isCharge);
        }
        return ptOrderStatus;
    }

    /**
     * 处理现付订单状态
     *
     * @param gysOrderStatus 供应商订单状态
     * @param ptOrderStatus  平台订单状态
     * @return String
     */
    private static String convertCash(String gysOrderStatus, String ptOrderStatus, String platform) {
        // 供应商状态（待确认）
        if (StringUtils.equals(HotelGysOrderStatusEnum.BEFORE_CONFIRM.getCode(), gysOrderStatus)) {
            // "1", "已预订，未确认"
            return HotelOrderStatusEnum.BOOK_NOT_CONFIRM.getCode();
        }

        // 供应商状态（已确认）
        if (StringUtils.equals(HotelGysOrderStatusEnum.AFTER_CONFIRM.getCode(), gysOrderStatus)) {
            // "2", "已确认"
            return HotelOrderStatusEnum.BOOK_CONFIRM.getCode();
        }

        // 供应商状态（已取消）
        if (StringUtils.equals(HotelGysOrderStatusEnum.CANCEL.getCode(), gysOrderStatus)) {
            // 平台状态（"1", "已预订，未确认"）
            if (StringUtils.equals(HotelOrderStatusEnum.BOOK_NOT_CONFIRM.getCode(), ptOrderStatus)) {
                // cps平台
                if (StringUtils.equalsIgnoreCase(PtEnum.CPS.getValue(), platform)) {
                    // "1C", "供应拒单（确认前）"
                    return HotelOrderStatusEnum.SUPPLY_CANCEL_NOT_CONFIRM.getCode();
                }
                // "1B", "平台拒单（确认前）"
                return HotelOrderStatusEnum.SYSTEM_CANCEL_NOT_CONFIRM.getCode();
            }
            // 平台状态（"2", "已确认"）
            if (StringUtils.equals(HotelOrderStatusEnum.BOOK_CONFIRM.getCode(), ptOrderStatus)) {
                // cps平台
                if (StringUtils.equalsIgnoreCase(PtEnum.CPS.getValue(), platform)) {
                    // "2C", "供应拒单（确认后）"
                    return HotelOrderStatusEnum.SUPPLY_CANCEL_CONFIRM.getCode();
                }
                // "2B", "平台拒单（确认后）"
                return HotelOrderStatusEnum.SYSTEM_CANCEL_CONFIRM.getCode();
            }
        }

        // 供应商状态（NOSHOW）
        if (StringUtils.equals(HotelGysOrderStatusEnum.NOSHOW.getCode(), gysOrderStatus)) {
            // "4", "NOSHOW"
            return HotelOrderStatusEnum.NOSHOW.getCode();
        }

        // 供应商状态（已入住）
        if (StringUtils.equals(HotelGysOrderStatusEnum.LIVE.getCode(), gysOrderStatus)) {
            // "3", "已入住"
            return HotelOrderStatusEnum.LIVE.getCode();
        }

        // 供应商状态（已离店）
        if (StringUtils.equals(HotelGysOrderStatusEnum.OUT.getCode(), gysOrderStatus)) {
            // "5", "已离店"
            return HotelOrderStatusEnum.OUT.getCode();
        }

        // 供应商状态（取消中），暂不修改订单状态
        return ptOrderStatus;
    }

    /**
     * 处理预付订单状态
     *
     * @param gysOrderStatus 供应商订单状态
     * @param ptOrderStatus  平台订单状态
     * @param platform       平台
     * @param isCharge       预付订单，NOSHOW情况下，是否收费
     * @return String
     */
    private static String convertPrepaid(String gysOrderStatus, String ptOrderStatus, String platform, String isCharge) {
        // 供应商状态（待确认）
        if (StringUtils.equals(HotelGysOrderStatusEnum.BEFORE_CONFIRM.getCode(), gysOrderStatus)) {
            // "7", "已支付 待确认"
            return HotelOrderStatusEnum.PAY_BEFORE_CONFIRM.getCode();
        }

        // 供应商状态（已确认）
        if (StringUtils.equals(HotelGysOrderStatusEnum.AFTER_CONFIRM.getCode(), gysOrderStatus)) {
            // "8", "已支付已确认"
            return HotelOrderStatusEnum.PAY_AFTER_CONFIRM.getCode();
        }

        // 供应商状态（已取消）
        if (StringUtils.equals(HotelGysOrderStatusEnum.CANCEL.getCode(), gysOrderStatus)) {
            // 平台状态（"6", "已预订未支付"）
            if (HotelOrderStatusEnum.BOOK_NOT_PAY.getCode().equals(ptOrderStatus)) {
                // "6A", "采购取消（支付前）"
                return HotelOrderStatusEnum.CUSTOMER_CANCEL_NOT_PAY.getCode();
            }

            // 平台状态（"7", "已支付 待确认"）
            if (HotelOrderStatusEnum.PAY_BEFORE_CONFIRM.getCode().equals(ptOrderStatus)) {
                // cps平台
                if (StringUtils.equalsIgnoreCase(PtEnum.CPS.getValue(), platform)) {
                    // "7A", "供应拒单（确认前）"
                    return HotelOrderStatusEnum.SUPPLY_CANCEL_BEFORE_CONFIRM.getCode();
                }
                // "7D", "平台拒单（确认前）"
                return HotelOrderStatusEnum.SYSTEM_CANCEL_BEFORE_CONFIRM.getCode();
            }

            // 平台状态（"8", "已支付已确认"）、（"3", "已入住"）
            if (HotelOrderStatusEnum.PAY_AFTER_CONFIRM.getCode().equals(ptOrderStatus) || HotelOrderStatusEnum.LIVE.getCode().equals(ptOrderStatus)) {
                // cps平台
                if (StringUtils.equalsIgnoreCase(PtEnum.CPS.getValue(), platform)) {
                    // "8C", "供应拒单（确认后）"
                    return HotelOrderStatusEnum.SUPPLY_CANCEL_AFTER_CONFIRM.getCode();
                }
                // "8E", "平台拒单（确认后）"
                return HotelOrderStatusEnum.SYSTEM_CANCEL_AFTER_CONFIRM.getCode();
            }
        }

        // 供应商状态（NOSHOW）
        if (StringUtils.equals(HotelGysOrderStatusEnum.NOSHOW.getCode(), gysOrderStatus)) {
            // 预付没有NOSHOW，如果供应商不收取费用，则对应("已取消")
            if (StringUtils.equals(ChargeForNoShowOfPrepaidEnum.NO_CHARGE.getCode(), isCharge)) {
                // "8E", "平台拒单（确认后）"
                return HotelOrderStatusEnum.SYSTEM_CANCEL_AFTER_CONFIRM.getCode();
            }
            // 如果供应商收取费用，则对应（"8", "已支付已确认"）
            return HotelOrderStatusEnum.PAY_AFTER_CONFIRM.getCode();
        }

        // 供应商状态（已入住）
        if (StringUtils.equals(HotelGysOrderStatusEnum.LIVE.getCode(), gysOrderStatus)) {
            // "3", "已入住"
            return HotelOrderStatusEnum.LIVE.getCode();
        }

        // 供应商状态（已离店）
        if (StringUtils.equals(HotelGysOrderStatusEnum.OUT.getCode(), gysOrderStatus)) {
            // "5", "已离店"
            return HotelOrderStatusEnum.OUT.getCode();
        }

        // 供应商状态（取消中），暂不修改订单状态
        return ptOrderStatus;
    }

    /**
     * 处理真实状态描述
     *
     * @param trueStatus   供应商返回真实状态
     * @param trueStatusMs 供应商返回真实状态描述
     * @return String
     */
    public static String convertTrueStatusMs(String trueStatus, String trueStatusMs) {
        if (StringUtils.isNotBlank(trueStatus)) {
            return trueStatus + SymbolConstant.LEFT_SLASH + StringUtils.defaultString(trueStatusMs, "");
        }
        return StringUtils.defaultString(trueStatusMs, "");
    }

    /**
     * @param separator separator
     * @param objects   objects
     * @return String
     */
    public static String joinWith(String separator, Object... objects) {
        if (objects == null) {
            return null;
        } else {
            String sanitizedSeparator = StringUtils.defaultString(separator, "");
            StringBuilder result = new StringBuilder();
            Iterator iterator = Arrays.asList(objects).iterator();

            while (iterator.hasNext()) {
                String value = Objects.toString(iterator.next(), "");
                if (StringUtils.isNotBlank(value)) {
                    result.append(value);
                    if (iterator.hasNext()) {
                        result.append(sanitizedSeparator);
                    }
                }
            }
            return result.toString();
        }
    } 