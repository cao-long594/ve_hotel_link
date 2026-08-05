package cn.vetech.center.hotel.aspcet.aspcet;



import cn.vetech.center.hotel.aspcet.bean.MethodBean;
import cn.vetech.center.hotel.aspcet.bean.MethodBeanConverter;
import cn.vetech.center.hotel.aspcet.service.MethodBeanService;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public abstract class MethodAspcet implements MethodBeanService {

    /**
     * spring aspect pointcut
     */
//    @Pointcut("@annotation(cn.vetech.center.hotel.log.annotation.CommonLog)")
    public abstract void pointCut();
    /**
     * @param point point
     * @return s
     * @throws Throwable Throwable
     */
    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodBean methodBean= MethodBeanConverter.convert(point);
        methodBean.setBegin(new Date());
        try {
            before(methodBean);
            Object result=around(methodBean,point);
            methodBean.setEnd(new Date());
            methodBean.setResult(result);
            afterReturn(methodBean);
            return result;
        } catch (Throwable e) {
            methodBean.setEnd(new Date());
            methodBean.setThrowable(e);
            afterThrow(methodBean);
            throw e;
        } finally {
            after(methodBean);
        }
    }

    public static List<Map<String,String>> convertMap(List<String> lines,Map<String,String> colMap){
        List lineMaps= lines.stream().map(line->{
            Map<String,String> map=new HashMap<>();
            String[] clos=line.split(",");
            for (int i = 0; i < clos.length; i++) {
                String key=colMap.get(i+"");
                String value=clos[i].replaceAll("\"","");
                map.put(key,value);
            }
            return map;
        }).collect(Collectors.toList());
        return lineMaps;
    }

    public static Map<String,String> ltCol(){
        Map<String,String> map=new HashMap<>();
        map.put("0","jdid");
        map.put("1","zwmc");
        map.put("2","szcs");
        map.put("3","elong");
        map.put("4","jltour");
        map.put("5","yaduo");
        map.put("6","hzw");
        map.put("7","jinjiang");
        map.put("8","tty");
        return map;
    }

    public static Map<String,String> ltFy(){
        Map<String,String> map=new HashMap<>();
        map.put("elong","fy1");
        map.put("jltour","fy5");
        map.put("yaduo","fy13");
        map.put("hzw","fy14");
        map.put("jinjiang","fy15");
        map.put("tty","fy18");
        return map;
    }

    public static Map<String,String> syFy(){
        Map<String,String> map=new HashMap<>();
        map.put("elong","fy1");
        map.put("jltour","fy5");
        map.put("yaduo","fy15");
        map.put("hzw","fy21");
        map.put("jinjiang","fy30");
        map.put("tty","fy18");
        return map;
    }

    public static Map<String,String> syCol(){
        Map<String,String> map=new HashMap<>();
        map.put("0","jdid");
        map.put("1","zwmc");
        map.put("2","szcs");
        map.put("3","elong");
        map.put("4","jltour");
        map.put("5","yaduo");
        map.put("6","tty");
        map.put("7","hzw");
        map.put("8","jinjiang");
        return map;
    }

}
