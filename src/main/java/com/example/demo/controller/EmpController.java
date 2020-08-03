package com.example.demo.controller;

import com.example.demo.dao.EmpRepository;
import com.example.demo.domain.EmpAndDep;
import com.example.demo.pojo.EmpList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import javax.jws.WebParam;
import java.util.ArrayList;
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

//用bootstap加载部门人员表格
    @RequestMapping("/empList")
    @ResponseBody
    public List<EmpList> findEmpList(){

        List<EmpAndDep> empAndDep = empRepository.findEmpAndDep();
        System.out.println(empAndDep);
        ArrayList<EmpList> empLists = new ArrayList<>();
        for (int i = 0; i < empAndDep.size(); i++) {
            EmpList empList = new EmpList();
            String name = empAndDep.get(i).getEmp().getEmpName();
            String dep = empAndDep.get(i).getDep().getDepName();
            String role = empAndDep.get(i).getEmp().getRole();
            empList.setEmpName(name);
            empList.setDep(dep);
            empList.setRole(role);
            empLists.add(empList);
        }
        return empLists;


    }
}
