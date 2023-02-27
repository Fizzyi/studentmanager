package com.study.sutdentmanager.service;

import com.study.sutdentmanager.pojo.SelectedCourse;
import com.study.sutdentmanager.util.PageBean;

import java.util.List;
import java.util.Map;

public interface SelectedCourseService {
    PageBean<SelectedCourse> queryPage(Map<String, Object> paramMap);

    int addSelectedCourse(SelectedCourse selectedCourse);

    int deleteSelectedCourse(Integer id);

    boolean isStudentId(int id);

    List<SelectedCourse> getAllBySid(int studentid);
}
