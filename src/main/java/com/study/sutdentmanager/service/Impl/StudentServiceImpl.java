package com.study.sutdentmanager.service.Impl;

import com.study.sutdentmanager.dao.StudentDao;
import com.study.sutdentmanager.pojo.Student;
import com.study.sutdentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;


    @Override
    public Student findByStudent(Student student) {
        return studentDao.findByStudent(student);
    }
}
