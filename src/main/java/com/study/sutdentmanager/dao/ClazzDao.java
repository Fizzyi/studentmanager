package com.study.sutdentmanager.dao;

import com.study.sutdentmanager.pojo.Clazz;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClazzDao {

    List<Clazz> queryList(Map<String,Object> paramMap);

    Integer queryCount(Map<String,Object> paraMap);

    int addClazz(Clazz clazz);

    int deleteClazz(List<Integer> ids);
}
