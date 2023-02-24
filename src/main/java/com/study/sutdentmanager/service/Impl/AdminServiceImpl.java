package com.study.sutdentmanager.service.Impl;

import com.study.sutdentmanager.dao.AdminDao;
import com.study.sutdentmanager.pojo.Admin;
import com.study.sutdentmanager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin findByAdmin(Admin admin) {
        return adminDao.findByAdmin(admin);
    }

    @Override
    public int editPwdByAdmin(Admin admin) {
        return adminDao.editPwdByAdmin(admin);
    }
}
