package com.xiaoyu.test;

import com.xiaoyu.domain.Student;
import com.xiaoyu.service.StudentService;
import com.xiaoyu.service.impl.StudentServiceImpl;
import com.xiaoyu.util.ServiceFactory;

import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        //传递目标对象参数，获得代理类对象。自动生成.class代理对象类，这个类里边的save方法其实就是invoke();
        StudentService ss = (StudentService) ServiceFactory.getService(new StudentServiceImpl());
        /*Student student = new Student();
        student.setId("1005");
        student.setName("帅虎");
        student.setAge(33);
        //ss  是代理对象，
        ss.save(student);//所以这里执行的是动态代理的invoke()方法*/

        //Student s = ss.getById("1003");
        //System.out.println(s);

        //查询所有记录
       List<Student> slist = ss.getAll();
        for (Student s : slist){
            System.out.println(s);
        }



    }
}
