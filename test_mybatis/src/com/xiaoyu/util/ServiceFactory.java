package com.xiaoyu.util;

public class ServiceFactory {
    //传递目标对象，获得代理对象
    public static Object getService(Object service){
      return new TransactionInvocationHandler(service).geProxy();
    }

}
