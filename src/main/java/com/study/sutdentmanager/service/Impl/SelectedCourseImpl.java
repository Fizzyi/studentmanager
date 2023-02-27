package com.study.sutdentmanager.service.Impl;

import com.study.sutdentmanager.dao.SelectedCourseDao;
import com.study.sutdentmanager.pojo.SelectedCourse;
import com.study.sutdentmanager.pojo.Student;
import com.study.sutdentmanager.service.SelectedCourseService;
import com.study.sutdentmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class SelectedCourseImpl implements SelectedCourseService {
    @Autowired
    private SelectedCourseDao selectedCourseDao;

    @Override
    public PageBean<SelectedCourse> queryPage(Map<String, Object> paramMap) {
        PageBean<SelectedCourse> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));
        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<SelectedCourse> datas = selectedCourseDao.queryList(paramMap);
        pageBean.setDatas(datas);
        Integer totalsize = selectedCourseDao.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return null;
    }

    @Override
    public int addSelectedCourse(SelectedCourse selectedCourse) {
        return 0;
    }

    @Override
    public int deleteSelectedCourse(Integer id) {
        return 0;
    }

    /**
     * 判断该学生是否有关联课程
     * @param id student_id
     * @return True/False
     */
    @Override
    public boolean isStudentId(int id) {
        List<SelectedCourse> selectedCoursesList = selectedCourseDao.isStudentId(id);
        // 判断一个list是否为空，可以直接 list.isEmpty()
        return selectedCoursesList.isEmpty();
    }

    @Override
    public List<SelectedCourse> getAllBySid(int studentid) {
        return null;
    }
}
