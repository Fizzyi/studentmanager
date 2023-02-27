package com.study.sutdentmanager.service;

import com.study.sutdentmanager.pojo.Clazz;
import com.study.sutdentmanager.util.PageBean;

import java.util.List;
import java.util.Map;

public interface ClazzService {



    PageBean<Clazz> queryPage(Map<String,Object> paramMap);

    int addClazz(Clazz clazz);

    int deleteClazz(List<Integer> ids);
}
