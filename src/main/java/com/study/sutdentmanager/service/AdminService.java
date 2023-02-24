package com.study.sutdentmanager.service;

import com.study.sutdentmanager.pojo.Admin;
import org.springframework.stereotype.Service;

public interface  AdminService {
    Admin findByAdmin(Admin admin);

    int editPwdByAdmin(Admin admin);
}
