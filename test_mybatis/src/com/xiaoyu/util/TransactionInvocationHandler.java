package com.xiaoyu.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionInvocationHandler implements InvocationHandler {

    //目标对象   成员变量
    private Object target;
    //构造方法  无论是静态代理模式还是动态代理，都要写成员变量和构造方法
    public TransactionInvocationHandler(Object target){
        this.target = target;
    }

    //代理类的业务方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        SqlSession session = null;
        Object obj = null;

        try{
            //通过类名调用构造方法，也可以new对象调用，但是不建议，因为方法是静态的，new一个对象会在堆内存中只在制造一块垃圾
            session = SqlSessionUtil.getSession();

            //处理业务逻辑 业务层实现类的业务方法
            obj = method.invoke(target,args);//目标方法执行

            //处理业务逻辑完毕后提交事务
            session.commit();
        }catch (Exception e){
            session.rollback();//如果上边执行的代码有问题，在这里执行事务回滚
            e.printStackTrace();
        }finally{
            SqlSessionUtil.myClose(session);
        }
        return obj;
    }

    public Object geProxy(){
        //取得代理类的对象通过这一句话完成
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }
}
