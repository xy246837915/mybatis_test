package com.xiaoyu.test;

import com.xiaoyu.dao.StudentDao;
import com.xiaoyu.domain.Student;
import com.xiaoyu.util.SqlSessionUtil;

import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        StudentDao studentDao = SqlSessionUtil.getSession().getMapper(StudentDao.class);

        /*List<String> stringList = studentDao.select1();
        for (String s : stringList){
            System.out.println(s);
        }*/


       /* List<Student> students = studentDao.select2(33);
        for (Student s : students) {
            System.out.println(s);
        }*/

        /*测试resultType返回map类型
          select * from tab_student
          当执行了sql语句之后，通过查询得到结果 id，name,age,
          根据返回值类型，会为我们自动创建一个该类型的对象，由该对象把查询的结果保存下来
          Map<String,Object> map1 = new HashMap();
          map1.put("id","10001")
          map1.put("name","tt")
          map1.put("age","33")
          当查询出来了第二条纪录，根据返回值类型，会再创建一个该类型的对象，保存第二条纪录的值
          Map<String,Object> map2 = new HashMap();
          map2.put("id","10002")
          map2.put("name","gg")
          map2.put("age","33")
          ...
          ...
          多条记录封装成为了多个map对象
          系统会为我们自动创建出来一个list集合来保存这些对象
          List<Map<String,Object>> mList = new ArrayList();
          mList.add(map1);
          mList.add(map2);
          mList.add(map3);
          ......
         */
        /*List<Map<String,Object>>  mapList = studentDao.select3();
        for(Map<String,Object> map:mapList){
            Set<String> strings = map.keySet();
            for(String key : strings){
                System.out.println("key:" +key);
                System.out.println("value:" +map.get( key));
            }
            System.out.println("--------------------------------");
        }*/

        /*Student s = new Student();
        s.setId("1");
        s.setName("牛");
        List<Student> students = studentDao.select4();
        for(Student s1 : students){
            System.out.println(s1);
        }*/

        //5.测试动态sql foreach标签
        //查询编号为 1001  1002 1003 的学员
        String sttr[] = {"1001","1002","1003"};
        List<Student> students = studentDao.select5(sttr);
        for(Student s:students){
            System.out.println(s);
        }
    }
}
