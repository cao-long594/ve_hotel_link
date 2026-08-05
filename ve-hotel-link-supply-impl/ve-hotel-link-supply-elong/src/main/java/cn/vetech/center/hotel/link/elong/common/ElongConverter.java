package cn.vetech.center.hotel.link.elong.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lipeng on 2018/7/24 15:00
 * @comment
 */
public class ElongConverter {

    /**
     * 合并艺龙rp rate inv
     * @param rpResult rpResult
     * @param rateResult rateResult
     * @param invResult invResult
     * @return JSONArray
     */
    public static JSONArray merge(JSONObject rpResult, JSONObject rateResult, JSONObject invResult){
        //根据艺龙文档 如果酒店没有rp  那么这个酒店是不可用的
        JSONArray hotels=rpResult.getJSONArray("Hotels");
        if(ListUtil.isEmpty(hotels)){
            return null;
        }
        JSONArray rps=new JSONArray();
        //按酒店id分组 rates invs数据
        Map<String, JSONArray> ratesMap=groupRateByHotelId(rateResult);
        Map<String, JSONArray> invsMap=groupInvByHotelId(invResult);
        hotels.forEach(temp->{
            JSONObject hotel= (JSONObject) temp;
            String hotelId=hotel.getString("HotelID");
            //获取该酒店对应的价格 房态
            JSONArray rates=ratesMap.get(hotelId);
            JSONArray invs=invsMap.get(hotelId);
            //合并单个酒店的rp rates invs
            JSONObject result=merge(hotel,rates,invs);
            if(result==null){
                return;
            }
            result.put("hotelId",hotelId);
            rps.add(result);
        });
        return rps;
    }

    /**
     * 单个酒店合并 hotel rates invs
     * @param hotel hotel
     * @param rates rates
     * @param invs invs
     * @return JSONObject
     */
    public static JSONObject merge(JSONObject hotel, JSONArray rates, JSONArray invs){
        JSONArray ratePlans=hotel.getJSONArray("RatePlans");
        if(ListUtil.isEmpty(ratePlans)){
            return null;
        }
//        rates=merge(rates,invs);
        //按roomId分组ratePlans
        Map<String, JSONArray> ratePlansMap=groupRatePlans(ratePlans);
        //按HotelCode+"_"+RoomTypeId+"_"+RatePlanId分组rates
        Map<String, JSONArray> ratesMap=groupRates(rates);
        //按HotelCode+"_"+RoomTypeId+"_"+Date分组invs
        Map<String, JSONObject> invsMap=groupInvs(invs);

        JSONObject result=new JSONObject();
        JSONArray rooms=new JSONArray();
        result.put("rooms",rooms);
        ratePlansMap.entrySet().forEach(entry->{
            String roomTypeId=entry.getKey();
            JSONObject room=new JSONObject();
            room.put("roomTypeId",roomTypeId);
            rooms.add(room);
            JSONArray roomRatePlans=entry.getValue();
            room.put("ratePlans",mergeRoomRatePlans(roomTypeId,roomRatePlans,ratesMap,invsMap));
        });
        return result;
    }

    /**
     * 合并单个房型的价格计划  价格  库存
     * @param roomTypeId roomTypeId
     * @param roomRatePlans roomRatePlans
     * @param ratesMap ratesMap
     * @param invsMap invsMap
     * @return result
     */
    public static JSONArray mergeRoomRatePlans(String roomTypeId, JSONArray roomRatePlans, Map<String, JSONArray> ratesMap, Map<String, JSONObject> invsMap){
        if(ListUtil.isEmpty(roomRatePlans)){
            return null;
        }
        JSONArray result=new JSONArray();
        roomRatePlans.forEach(roomRatePlanTemp->{
            JSONObject roomRatePlan= (JSONObject) roomRatePlanTemp;
            result.add(roomRatePlan);
            String hotelCode=roomRatePlan.getString("HotelCode");
            String ratePlanId=roomRatePlan.getString("RatePlanId");
            String rateKey=String.join("_",hotelCode,roomTypeId,ratePlanId);
            JSONArray roomRates=ratesMap.get(rateKey);
            if(ListUtil.isEmpty(roomRates)){
                roomRatePlan.put("rates",new JSONArray());
                return;
            }
            roomRatePlan.put("rates",merge(roomRates,invsMap));
        });
        return result;
    }


    /**
     * 合并价格 和 库存
     * @param rates rates
     * @param invsMap invsMap
     * @return JSONArray
     */
    public static JSONArray merge(JSONArray rates, Map<String, JSONObject> invsMap){
        if(ListUtil.isEmpty(rates)){
            return rates;
        }

        rates.forEach(rateTemp->{
            JSONObject rate= (JSONObject) rateTemp;
            String hotelCode=rate.getString("HotelCode");
            String roomTypeId=rate.getString("RoomTypeId");
            JSONArray rateInvs=new JSONArray();
            rate.put("Inventories",rateInvs);
            String startDate= VeDate.getNextDay(rate.get("StartDate")+"","0");
            String endDate=VeDate.getNextDay(rate.get("EndDate")+"","0");
            int day=VeDate.getTwoDay(endDate,startDate);
//            if(day==0){
//                day=1;
//            }
            for (int i = 0; i <=day; i++) {
                String date= VeDate.getNextDay(startDate,i+"");
                String key=String.join("_",hotelCode,roomTypeId,date);
                JSONObject inv=invsMap.get(key);
                if(inv==null){
                    continue;
                }
                rateInvs.add(inv);
            }
        });
        return rates;
    }

    /**
     * 按房型分组
     * @param ratePlans ratePlans
     * @return map
     */
    public static Map<String, JSONArray> groupRatePlans(JSONArray ratePlans){
        Map<String, JSONArray> map=new HashMap<>();
        if(ListUtil.isEmpty(ratePlans)){
            return map;
        }
        //使用所有房型的ratePlan
        JSONArray roomAllRatePlan=new JSONArray();
        ratePlans.forEach(ratePlanTemp->{
            JSONObject ratePlan= (JSONObject) ratePlanTemp;
            String roomTypeIds= (String) ratePlan.get("RoomTypeIds");
            if("all".equalsIgnoreCase(roomTypeIds)){
                //解决引用类型问题
                roomAllRatePlan.add(JSONObject.parseObject(ratePlan.toJSONString()));
            }
        });
        ratePlans.forEach(ratePlanTemp->{
            JSONObject ratePlan= (JSONObject) ratePlanTemp;
            String roomTypeIds= (String) ratePlan.get("RoomTypeIds");
            if("all".equalsIgnoreCase(roomTypeIds)){
                return;
            }
            for (String roomTypeId : roomTypeIds.split(",")) {
                JSONArray roomRatePlans=map.get(roomTypeId);
                if(roomRatePlans==null){
                    roomRatePlans=new JSONArray();
                    roomRatePlans.addAll(roomAllRatePlan);
                    map.put(roomTypeId,roomRatePlans);
                }
                //解决引用类型问题
                roomRatePlans.add(JSONObject.parseObject(ratePlan.toJSONString()));
            }
        });
        return map;
    }

    /**
     * 价格按HotelCode+"_"+RoomTypeId+"_"+RatePlanId分组
     * @param rates rates
     * @return map
     */
    public static Map<String, JSONArray> groupRates(JSONArray rates){
        Map<String, JSONArray> map=new HashMap<>();
        if(ListUtil.isEmpty(rates)){
            return map;
        }
        rates.forEach(rateTemp->{
            JSONObject rate= (JSONObject) rateTemp;
            String hotelCode=rate.getString("HotelCode");
            String ratePlanId=rate.getString("RateplanId");
            String roomTypeId=rate.getString("RoomTypeId");
            String key=String.join("_",hotelCode,roomTypeId,ratePlanId);
            JSONArray roomRates=map.get(key);
            if(roomRates==null){
                roomRates=new JSONArray();
                map.put(key,roomRates);
            }
            roomRates.add(rate);
        });
        return map;
    }
    /**
     * 按HotelCode+"_"+RoomTypeId+"_"+Date分组库存
     * @param invs invs
     * @return invs
     */
    public static Map<String, JSONObject> groupInvs(JSONArray invs){
        Map<String, JSONObject> invsMap=new HashMap<>();
        if(ListUtil.isEmpty(invs)){
            return invsMap;
        }
        invs.forEach(invTemp->{
            JSONObject inv= (JSONObject) invTemp;
            String hotelCode=inv.getString("HotelCode");
            String roomTypeId=inv.getString("RoomTypeId");
            String date=VeDate.getNextDay(inv.get("Date")+"","0");
            String key=String.join("_",hotelCode,roomTypeId, date);
            invsMap.put(key,inv);
        });
        return invsMap;
    }



    /**
     * 按酒店分组价格  因为拉取的时候是多个酒店id拉取的
     * @param rateResult rateResult
     * @return map
     */
    public static Map<String, JSONArray> groupRateByHotelId(JSONObject rateResult){
        Map<String, JSONArray> map=new HashMap<>();
        if(rateResult==null){
            return map;
        }
        JSONArray rates=rateResult.getJSONArray("Rates");
        if(ListUtil.isEmpty(rates)){
            return map;
        }
        rates.forEach(temp->{
            JSONObject jRate= (JSONObject) temp;
            String hotelId=jRate.getString("HotelID");
            JSONArray rateA=map.get(hotelId);
            if(rateA==null){
                rateA=new JSONArray();
                map.put(hotelId,rateA);
            }
            rateA.add(jRate);
        });
        return map;
    }

    /**
     * 按酒店分组库存  因为拉取的时候是多个酒店id拉取的
     * @param invResult invResult
     * @return map
     */
    public static Map<String, JSONArray> groupInvByHotelId(JSONObject invResult){
        Map<String, JSONArray> map=new HashMap<>();
        if(invResult==null){
            return map;
        }
        JSONArray invs=invResult.getJSONArray("Inventories");
        if(ListUtil.isEmpty(invs)){
            return map;
        }
        invs.forEach(temp->{
            JSONObject jInv= (JSONObject) temp;
            String hotelId=jInv.getString("HotelID");
            JSONArray invA=map.get(hotelId);
            if(invA==null){
                invA=new JSONArray();
                map.put(hotelId,invA);
            }
            invA.add(jInv);
        });
        return map;
    }


    //增量合并

    /**
     * 本地新艺龙结构 与 elong rp 合并  用于增量
     * @param lrp 本地新艺龙结构
     * @param erp elong rp
     * @return 合并后的结果
     */
    public static JSONObject mergeRp(JSONObject lrp, JSONObject erp){
        //本地新艺龙结构是按房型存储
        JSONArray eratePlans=erp.getJSONArray("RatePlans");
        if(ListUtil.isEmpty(eratePlans)){
            return null;
        }
        JSONArray rooms=lrp.getJSONArray("rooms");
        if(ListUtil.isEmpty(rooms)){
            return null;
        }
        JSONObject newRp=new JSONObject();
        JSONArray newRooms=new JSONArray();
        newRp.put("rooms",newRooms);
        //elong新结构  按roomsId_hotelCode_ratePlanId分组
        Map<String, JSONObject> lratePlansMap=groupRatePlansNewRp(rooms);
        //elong的结构按房型分组
        Map<String, JSONArray> eratePlansMap=groupRatePlans(eratePlans);
        //用新拉取elong的rp去找 老rp 价格房态数据
        eratePlansMap.forEach((roomTypeId,eroomRatePlans)->{
            if(ListUtil.isEmpty(eroomRatePlans)){
                return;
            }
            eroomRatePlans.forEach(eroomRatePlanTemp->{
                JSONObject eroomRatePlan= (JSONObject) eroomRatePlanTemp;
                String hotelCode=eroomRatePlan.getString("HotelCode");
                String ratePlanId=eroomRatePlan.getString("RatePlanId");
                String key=String.join("_",roomTypeId,hotelCode,ratePlanId);
                JSONObject lratePlan=lratePlansMap.get(key);
                if(lratePlan==null){
                    return;
                }
                eroomRatePlan.put("rates",lratePlan.get("rates"));
            });
            JSONObject room=new JSONObject();
            room.put("roomTypeId",roomTypeId);
            room.put("ratePlans",eroomRatePlans);
            newRooms.add(room);
        });
        return newRp;
    }

    /**
     * 本地新艺龙结构 与 elong rate 合并  用于增量
     * @param lrp 本地新艺龙结构
     * @param rates elong rate
     * @return 合并后的结果
     */
    public static JSONObject mergeRate(JSONObject lrp, JSONArray rates){
        JSONArray rooms=lrp.getJSONArray("rooms");
        if(ListUtil.isEmpty(rooms)){
            return null;
        }
        if(ListUtil.isEmpty(rates)){
            return lrp;
        }
        //elong新结构  按roomId_hotelCode_ratePlanId分组
        Map<String, JSONObject> lratePlansMap=groupRatePlansNewRp(rooms);
        rates.forEach((Object rateTemp) ->{
            JSONObject rate= (JSONObject) rateTemp;
            String roomTypeId=rate.getString("RoomTypeId");
            String hotelCode=rate.getString("HotelCode");
            String ratePlanId=rate.getString("RateplanId");
            boolean status=rate.getBooleanValue("Status");
            String startDate= VeDate.getNextDay(rate.get("StartDate")+"","0");
            String endDate=VeDate.getNextDay(rate.get("EndDate")+"","0");
            String key=String.join("_",roomTypeId,hotelCode,ratePlanId);
            JSONObject lratePlan=lratePlansMap.get(key);
            if(lratePlan==null){
                return;
            }
            //原来如果有相同价格 且新的可用覆盖  没有 新价格可用新增  如果新的价格不可用就删除以前的。
            JSONArray lrates=lratePlan.getJSONArray("rates");
            if(ListUtil.isEmpty(lrates)){
                if(!status){
                    return;
                }
                lrates=new JSONArray();
                lrates.add(rate);
                lratePlan.put("rates",lrates);
                return;
            }
            JSONArray remveRates=new JSONArray();
            final boolean[] flag = {true};
            lrates.forEach(lrateTemp->{
                JSONObject lrate= (JSONObject) lrateTemp;
                String lstartDate= VeDate.getNextDay(lrate.get("StartDate")+"","0");
                String lendDate=VeDate.getNextDay(lrate.get("EndDate")+"","0");
                if(lstartDate.equals(startDate)&&lendDate.equals(endDate)){
                    //匹配到对应得的价格 设为false不新增
                    flag[0] =false;
                    if(status){
//                        lrate=rate;这种写法有问题
                        rate.forEach((rateKey,rateValue)->{
                            lrate.put(rateKey,rateValue);
                        });
                    }else{
                        remveRates.add(lrate);
                    }
                }
            });
            //没有匹配到对应的价格 就新增
            if(flag[0]){
                lrates.add(rate);
            }
            if(ListUtil.isNotEmpty(remveRates)){
                lrates.removeAll(remveRates);
            }
        });
        return lrp;
    }

    /**
     * 本地新艺龙结构 与 elong inv 合并  用于增量
     * @param lrp 本地新艺龙结构
     * @param invs elong inv
     * @return 合并后的结果
     */
    public static JSONObject mergeInv(JSONObject lrp, JSONArray invs){
        JSONArray rooms=lrp.getJSONArray("rooms");
        if(ListUtil.isEmpty(rooms)){
            return null;
        }
        if(ListUtil.isEmpty(invs)){
            return lrp;
        }

        return null;
    }

    /**
     * 艺龙新结构  按roomsId_hotelCode_ratePlanId分组
     * @param rooms rooms
     * @return s
     */
    public static Map<String, JSONObject> groupRatePlansNewRp(JSONArray rooms){
        Map<String, JSONObject> map=new HashMap<>();
        if(ListUtil.isEmpty(rooms)){
            return map;
        }
        rooms.forEach(roomTemp->{
            JSONObject room= (JSONObject) roomTemp;
            String roomTypeId=room.getString("roomTypeId");
            JSONArray ratePlans=room.getJSONArray("ratePlans");
            if(ListUtil.isEmpty(ratePlans)){
                return;
            }
            ratePlans.forEach(ratePlanTemp->{
                JSONObject ratePlan= (JSONObject) ratePlanTemp;
                String hotelCode=ratePlan.getString("HotelCode");
                String ratePlanId=ratePlan.getString("RatePlanId");
                String key=String.join("_",roomTypeId,hotelCode,ratePlanId);
                map.put(key,ratePlan);
            });
        });
        return map;
    }

    //增量合并
}