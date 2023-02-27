package com.study.sutdentmanager.dao;

import com.study.sutdentmanager.pojo.SelectedCourse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SelectedCourseDao {
    List<SelectedCourse> queryList(Map<String,Object> paramMap);

    Integer queryCount(Map<String,Object> paramMap);

    List<SelectedCourse> isStudentId(int studentId);
}
