package com.study.sutdentmanager.dao;

import com.study.sutdentmanager.pojo.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {
    Admin findByAdmin(Admin admin);

    int editPwdByAdmin(Admin admin);
}
