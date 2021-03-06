package com.example.demo.service.impl;

import com.example.demo.dao.LoginDao;
import com.example.demo.pojo.Admin;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public Admin findByAdminNameAndAdminPass(String adminName, String adminPass) {
        return loginDao.findByAdminNameAndAdminPass(adminName,adminPass);
    }
}
