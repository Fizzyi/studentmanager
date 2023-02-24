package com.study.sutdentmanager.dao;


import com.study.sutdentmanager.pojo.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao {
    Teacher findByTeacher(Teacher teacher);

}
