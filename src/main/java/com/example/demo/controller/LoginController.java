package com.example.demo.controller;

import com.example.demo.pojo.Admin;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/adminLogin")
    public Map<String, Object> checkAdmin(@RequestParam(value = "adminName")String adminName ,
                                          @RequestParam(value = "adminPass")String adminPass ,
                                          HttpSession session
                                          ){
        System.out.println("前端传来的名字："+adminName);
        System.out.println("前端传来的密码："+adminPass);
        Admin result = loginService.findByAdminNameAndAdminPass(adminName, adminPass);
        session.setAttribute("username",adminName);
        session.setAttribute("password",adminPass);
        HashMap<String, Object> map = new HashMap<>();
        try {
            System.out.println("name:"+result.getAdminName());
            System.out.println("pass:"+result.getAdminPass());
            map.put("name",result.getAdminName());
            map.put("pass",result.getAdminPass());
            map.put("level",result.getLevel());
        }
        catch(Exception NullPointerException){
            map.put("code","400");
            return map;
        }
        map.put("code","200");
        return map;
    }
}
