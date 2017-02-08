package main;

import java.lang.reflect.InvocationHandler;  
import java.lang.reflect.Method;  
  
/** 
 * 时间统计处理机，用于统计各方法耗时 
 * @author RyQ 
 * 
 */  
public class TimeCountProxyHandle implements InvocationHandler {  
  
    private Object proxied;  
    public TimeCountProxyHandle(Object obj) {  
        proxied = obj;  
    }  
  
    @Override  
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  
        long begin = System.currentTimeMillis();  
        Object result = method.invoke(proxied, args);  
        long end = System.currentTimeMillis();
        System.out.println(method.getName() + "1000次耗时:" + (end - begin) + "ms");  
        return result;  
    }  
} 