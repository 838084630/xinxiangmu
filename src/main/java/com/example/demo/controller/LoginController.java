package com.example.demo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.example.demo.pojo.Admin;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {
    String hutoolCode = "";
    @Autowired
    private LoginService loginService;

    @PostMapping("/adminLogin")
    public Map<String, Object> checkAdmin(@RequestParam(value = "adminName")String adminName ,
                                          @RequestParam(value = "adminPass")String adminPass ,
                                          @RequestParam(value = "code")String code ,
                                          HttpSession session
                                          ){
//        String sessionCheckCode = (String) session.getAttribute("code");
        HashMap<String, Object> map = new HashMap<>();
//        System.out.println("code:"+code);
//        System.out.println("hutoolCode:"+hutoolCode);
//        System.out.println("sessionCheckCode:"+sessionCheckCode);
        if (code!=null && hutoolCode.equals(code)){
            //登录成功，返回json的提示。
            Admin result = loginService.findByAdminNameAndAdminPass(adminName, adminPass);
            if (result==null){
                map.put("code","400");
            }else {
                session.setAttribute("username",adminName);
                session.setAttribute("password",adminPass);

                map.put("name",result.getAdminName());
                map.put("pass",result.getAdminPass());
                map.put("level",result.getLevel());


                map.put("code","200");
            }

        }else {
            map.put("code","401");
        }
       return map;
    }

    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //HuTool定义图形验证码的长和宽,验证码的位数，干扰线的条数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36,4,10);
        //将验证码放入session
        session.setAttribute("code",lineCaptcha.getCode());
        hutoolCode = lineCaptcha.getCode();
//        System.out.println("图形验证码的sessionCode:"+session.getAttribute("code"));
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
