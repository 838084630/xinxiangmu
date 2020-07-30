package com.example.demo.service;

import com.example.demo.pojo.Admin;

public interface LoginService {

    Admin findByAdminNameAndAdminPass(String adminName, String adminPass);
}
