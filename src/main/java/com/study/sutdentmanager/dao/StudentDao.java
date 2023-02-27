package com.study.sutdentmanager.dao;

import com.study.sutdentmanager.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentDao {

    Student findByStudent(Student student);

    List<Student> queryList(Map<String,Object> paramMap);

    Integer queryCount(Map<String,Object> paramMap);

    Student findById(Integer id);

    int deleteStudent(List<Integer> ids);

    int addStudent(Student student);

    int editStudent(Student student);

    int isStudentByClazzId(Integer id);
}
