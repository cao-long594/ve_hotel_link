package cn.vetech.center.hotel.link.util.orderbook;

import cn.vetech.center.hotel.link.api.orderbook.dto.BookContact;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookCustomer;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookOrderRoom;
import cn.vetech.center.hotel.link.api.orderbook.vo.LinkHotelOrderBookVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchNightlyRate;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;
import cn.vetech.center.hotel.link.bean.PersonName;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.enums.CurrencyEnum;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.util.HanyuPinyinHelper;
import cn.vetech.center.hotel.link.util.VeStringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author chengwanshan
 * @since 2022/3/30 11:34
 */
public class OrderBookCommonUtils {

    /**
     * 客人姓名，转拼音
     *
     * @param name 客人姓名
     * @return PersonName
     */
    public static Optional<PersonName> handlePersonNameToHanyuPinyin(String name) {
        Optional<PersonName> personNameOptional = handlePersonNameOriginal(name);
        if (!personNameOptional.isPresent()) {
            return Optional.empty();
        }
        PersonName personName = personNameOptional.get();
        personName.setFirstName(HanyuPinyinHelper.toHanyuPinyin(personName.getFirstName()));
        personName.setLastName(HanyuPinyinHelper.toHanyuPinyin(personName.getLastName()));
        return Optional.of(personName);
    }

    /**
     * 客人姓名，不转拼音
     *
     * @param name 客人姓名
     * @return PersonName
     */
    public static Optional<PersonName> handlePersonNameOriginal(String name) {
        name = StringUtils.trim(name);
        if (StringUtils.isBlank(name)) {
            return Optional.empty();
        }
        // 姓
        String lastName = "";
        // 名
        String firstName = "";
        if (name.contains("@")) {
            lastName = StringUtils.substringBefore(name, "@");
            firstName = StringUtils.substringAfter(name, "@");
        } else if (name.contains("/")) {
            lastName = StringUtils.substringBefore(name, "/");
            firstName = StringUtils.substringAfter(name, "/");
        } else if (name.contains(" ")) {
            lastName = StringUtils.substringBefore(name, " ");
            firstName = StringUtils.substringAfter(name, " ");
        } else {
            if (VeStringUtil.containChina(name)) {
                lastName = name.substring(0, 1);
                firstName = name.substring(1);
            } else {
                // 名字是单个单词等，只传名
                firstName = name;
            }
        }
        PersonName personName = new PersonName();
        personName.setLastName(lastName);
        personName.setFirstName(firstName);
        return Optional.of(personName);
    }

    /**
     * 客人姓名，转拼音
     *
     * @param name 客人姓名
     * @return PersonName
     */
    public static Optional<PersonName> handlePersonNameByGngj(String gngj, String name, String firstName, String lastName) {
        if (StringUtils.equals(GnGjTypeEnum.GJ.getCode(), gngj)) {
            if (StringUtils.isNotBlank(firstName) && StringUtils.isNotBlank(lastName)) {
                PersonName personName = new PersonName();
                personName.setFirstName(firstName);
                personName.setLastName(lastName);
                return Optional.of(personName);
            }
            return OrderBookCommonUtils.handlePersonNameToHanyuPinyin(name);
        } else {
            if (StringUtils.isNotBlank(firstName) && StringUtils.isNotBlank(lastName)) {
                PersonName personName = new PersonName();
                personName.setFirstName(firstName);
                personName.setLastName(lastName);
                return Optional.of(personName);
            }
            return OrderBookCommonUtils.handlePersonNameOriginal(name);
        }
    }

    /**
     * 客人姓名转换
     *
     * @param bookCustomer 客人信息
     * @return PersonName
     */
    public static Optional<PersonName> handlePersonNameByGngj(String gngj, BookCustomer bookCustomer) {
        return handlePersonNameByGngj(gngj, bookCustomer.getName(), bookCustomer.getFirstname(), bookCustomer.getLastname());
    }

    /**
     * 处理下单联系人参数
     * 优先级
     * 1、config中配置的联系人信息
     * 2、下单请求参数中联系人
     * 3、入住人集合取第一个人
     *
     * @param contact    contact
     * @param orderRooms orderRooms
     * @return BookContact
     */
    public static BookContact convertBookContact(String name, String mobile, String email, BookContact contact, List<BookOrderRoom> orderRooms) {
        BookContact bookContact = new BookContact();
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(mobile)) {
            bookContact.setName(name);
            bookContact.setMobile(mobile);
            bookContact.setEmail(StringUtils.defaultString(email, String.format("%s@163.com", mobile)));
            return bookContact;
        } else if (Objects.nonNull(contact) && StringUtils.isNotBlank(contact.getName()) && StringUtils.isNotBlank(contact.getMobile())) {
            if (StringUtils.isEmpty(contact.getEmail())) {
                contact.setEmail(String.format("%s@163.com", contact.getMobile()));
            }
            return contact;
        } else if (CollectionUtils.isNotEmpty(orderRooms)) {
            BookOrderRoom bookOrderRoom = orderRooms.get(0);
            if (CollectionUtils.isNotEmpty(bookOrderRoom.getCustomers())) {
                BookCustomer bookCustomer = bookOrderRoom.getCustomers().get(0);
                bookContact.setName(bookCustomer.getName());
                bookContact.setMobile(bookCustomer.getMobile());
                bookContact.setEmail(String.format("%s@163.com", bookCustomer.getMobile()));
                return bookContact;
            }
        }
        return bookContact;
    }

    /**
     * 下单vo间夜价格赋值
     *
     * @param rooms 验价返回rooms
     * @param vo    下单vo
     */
    public static void setBookNightRates(List<SearchRoom> rooms, LinkHotelOrderBookVO vo) {
        List<SearchNightlyRate> bookNightRates = getBookNightRates(rooms);
        if (CollectionUtils.isEmpty(bookNightRates)) {
            return;
        }
        vo.setNightlyRates(bookNightRates);
    }

    /**
     * 下单vo间夜价格赋值
     *
     * @param rooms 验价返回rooms
     */
    public static List<SearchNightlyRate> getBookNightRates(List<SearchRoom> rooms) {
        if (CollectionUtils.isEmpty(rooms)) {
            return Collections.emptyList();
        }
        List<SearchRatePlan> ratePlans = rooms.get(0).getRatePlans();
        if (CollectionUtils.isEmpty(ratePlans)) {
            return Collections.emptyList();
        }
        List<SearchNightlyRate> nightlyRates = ratePlans.get(0).getNightlyRates();
        if (CollectionUtils.isEmpty(nightlyRates)) {
            return Collections.emptyList();
        }
        return nightlyRates;
    }

    /**
     * 处理入住人数
     *
     * @param adult      adult
     * @param orderRooms orderRooms
     * @return Integer
     */
    public static Integer handleAdult(String adult, List<BookOrderRoom> orderRooms) {
        if (NumberUtils.toInt(adult) > NumConstant.NUM_0) {
            return NumberUtils.toInt(adult);
        }
        if (CollectionUtils.isNotEmpty(orderRooms)) {
            int sum = orderRooms.stream().filter(room -> CollectionUtils.isNotEmpty(room.getCustomers())).mapToInt(room -> room.getCustomers().size()).sum();
            if (sum > NumConstant.NUM_0) {
                return sum;
            }
        }
        return NumConstant.NUM_1;
    }

    /**
     * RMB -> CNY
     *
     * @param currencyCode currencyCode
     * @return String
     */
    public static String convertCurrencyCode(String currencyCode) {
        if (StringUtils.isBlank(currencyCode)) {
            return null;
        }
        if (CurrencyEnum.RMB.getCurrency().equals(currencyCode)) {
            return CurrencyEnum.CNY.getCurrency();
        }
        return currencyCode;
    }
}