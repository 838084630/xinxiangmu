package com.example.demo.controller;

import com.example.demo.dao.EmpRepository;
import com.example.demo.domain.EmpAndDep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class EmpController {
    @Autowired
    private EmpRepository empRepository;

    @RequestMapping("/empNum")
    public String findEmpAndDep(Model model){
        List<EmpAndDep> empAndDep = empRepository.findEmpAndDep();
        System.out.println(empAndDep);
        model.addAttribute("empAndDep",empAndDep);
        return "trainManage";
    }
}
