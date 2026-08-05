package cn.vetech.center.hotel.aspcet.bean;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author vetech
 */
public class MethodBean {
    /**
     * 拦截的类
     */
    private Class clazz;
    /**
     * 执行的方法
     */
    private Method method;
    /**
     * 开始时间
     */
    private Date begin;
    /**
     * 结束时间
     */
    private Date end;
    /**
     * 入参  key 参数名  value 参数值
     */
    private Map<String,Object> params;
    /**
     * 返回
     */
    private Object result;
    /**
     * 异常信息
     */
    private Throwable throwable;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
