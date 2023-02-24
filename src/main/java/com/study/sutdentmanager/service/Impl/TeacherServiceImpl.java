package com.study.sutdentmanager.service.Impl;

import com.study.sutdentmanager.dao.TeacherDao;
import com.study.sutdentmanager.pojo.Teacher;
import com.study.sutdentmanager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher findByTeacher(Teacher teacher) {
        return teacherDao.findByTeacher(teacher);
    }
}
