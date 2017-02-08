package main;

import java.lang.reflect.Proxy;

public class Main {
	public static void main(String[] args){
		
		
		Interface Interface= new Setup_Key();
		
		// 动态代理，统计各个方法耗时  
        Interface identProxy = (Interface) Proxy.newProxyInstance(  
                Setup_Key.class.getClassLoader(),  
                new Class[] { Interface.class }, new TimeCountProxyHandle(Interface));  
        
        identProxy.Ad();  
        identProxy.Ne(); 
        identProxy.Mu(); 
        identProxy.In();
        identProxy.Ex();
        identProxy.Add();
        identProxy.Neg();
        identProxy.PM();
        identProxy.Mul();
        identProxy.Inv();
        identProxy.Exp();
        identProxy.P();
	}
}
