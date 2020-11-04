package com.xiaoyu.service.impl;

import com.xiaoyu.dao.StudentDao;
import com.xiaoyu.domain.Student;
import com.xiaoyu.service.StudentService;
import com.xiaoyu.util.SqlSessionUtil;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    //                                                         取StudentDao dao层的反射对象
    private StudentDao studentDao = SqlSessionUtil.getSession().getMapper(StudentDao.class);

    @Override
    public Student getById(String id) {
        Student s = studentDao.getById(id);
        return s;
    }

    @Override
    public void save(Student s) {
        studentDao.save(s);
    }

    @Override
    public List<Student> getAll() {
        List<Student> slis = studentDao.getAll();
        return slis;
    }
}
