package com.study.sutdentmanager.service.Impl;


import com.study.sutdentmanager.dao.ClazzDao;
import com.study.sutdentmanager.pojo.Clazz;
import com.study.sutdentmanager.service.ClazzService;
import com.study.sutdentmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    public ClazzDao clazzDao;

    @Override
    public PageBean<Clazz> queryPage(Map<String, Object> paramMap) {
        PageBean<Clazz> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));
        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<Clazz> datas = clazzDao.queryList(paramMap);
        pageBean.setDatas(datas);
        Integer totalsize = clazzDao.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int addClazz(Clazz clazz) {
        return clazzDao.addClazz(clazz);
    }

    @Override
    public int deleteClazz(List<Integer> ids) {
        return clazzDao.deleteClazz(ids);
    }
}
