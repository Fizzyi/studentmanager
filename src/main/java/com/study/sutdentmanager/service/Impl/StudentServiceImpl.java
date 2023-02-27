package com.study.sutdentmanager.service.Impl;

import com.study.sutdentmanager.dao.StudentDao;
import com.study.sutdentmanager.pojo.Student;
import com.study.sutdentmanager.service.StudentService;
import com.study.sutdentmanager.util.PageBean;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;


    @Override
    public PageBean<Student> queryPage(Map<String, Object> paramMap) {
        PageBean<Student> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));
        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<Student> datas = studentDao.queryList(paramMap);
        pageBean.setDatas(datas);
        Integer totalsize = studentDao.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public Student findByStudent(Student student) {
        return studentDao.findByStudent(student);
    }

    @Override
    public Student findById(int id) {
        return studentDao.findById(id);
    }

    @Override
    public int deleteStudent(List<Integer> ids) {
        return studentDao.deleteStudent(ids);
    }

    @Override
    public int addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public int editStudent(Student student) {
        return studentDao.editStudent(student);
    }

    @Override
    public Boolean isStudentByClazzId(Integer id) {
        int classStudentCount = studentDao.isStudentByClazzId(id);
        return classStudentCount > 0;
    }
}
