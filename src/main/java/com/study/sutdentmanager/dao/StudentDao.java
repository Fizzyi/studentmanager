package com.study.sutdentmanager.dao;

import com.study.sutdentmanager.pojo.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao {

    Student findByStudent(Student student);
}
