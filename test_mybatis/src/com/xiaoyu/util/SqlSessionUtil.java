package com.xiaoyu.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {


    /*
       SqlSessionFactoryBuilder:SqlSessionFactory的建造者，
       通过该建造者对象调用建造方法，为我们创建一个新的SqlSessionFactory对象
       SqlSessionFactory的唯一作用就是为我们创建SQLSession对象
        */
    private static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    private static ThreadLocal<SqlSession> t = new ThreadLocal<>();

    //取得SqlSession对象
    public static SqlSession getSession(){
        SqlSession session = t.get();
        if(session == null){
            session = sqlSessionFactory.openSession();
            t.set(session);
        }
        return session;
    }

    //关闭对象
    public static void myClose(SqlSession session){
        if(session != null){
            session.close();
        }
        //这句必须加，非常容易忘记、     涉及到线程池的概念  从浏览器为服务器发出一个请求，服务器分配一根线程，
        //【tomcat服务器自带线程池】来处理浏览器发出的请求，其实这根线程用完没销毁，回到线程池中了
        //所以说每一次错做完成之后，线程回到线程池中了，连接(SQLSession)回到连接池中，所以说需要手动的将二者强制剥离开
        //剥离开之后相当于这根线程又是纯净的了
        t.remove();
    }
}
