package com.xiaoyu.dao;

import com.xiaoyu.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    public Student getById(String id);
    public void save(Student s);
    List<Student> getAll();

    List<String> select1();

    List<Student> select2(int i);

    List<Map<String, Object>> select3();

    List<Student> select4();


    List<Student> select5(String[] sttr);
}
