package com.study.sutdentmanager.service;

import com.study.sutdentmanager.pojo.Student;
import com.study.sutdentmanager.util.PageBean;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Student findByStudent(Student student);

    PageBean<Student> queryPage(Map<String,Object> paramMap);

    Student findById(int id);

    int deleteStudent(List<Integer> ids);

    int addStudent(Student student);

    int editStudent(Student student);

    Boolean isStudentByClazzId(Integer id);
}
