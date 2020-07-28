package com.example.demo.controller;

import com.example.demo.pojo.Manager;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/adminLogin")
    public int checkAdmin(String managerUsername ,Integer managerPassword){
        return loginService.findByAdminname(managerUsername, managerPassword);

    }
}
