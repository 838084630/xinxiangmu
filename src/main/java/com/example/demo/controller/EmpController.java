package com.example.demo.controller;

import com.example.demo.dao.EmpRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.domain.EmpAndDep;
import com.example.demo.pojo.EmpList;
import com.example.demo.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import javax.jws.WebParam;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmpController {
    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private StudentRepository studentRepository;

//    @RequestMapping("/empNum")
//    public String findEmpAndDep(Model model){
//        List<EmpAndDep> empAndDep = empRepository.findEmpAndDep();
//        System.out.println(empAndDep);
//        model.addAttribute("empAndDep",empAndDep);
//        return "trainManage";
//    }

//用bootstap加载部门人员表格
    @RequestMapping("/empList")
    @ResponseBody
    public List<EmpList> findEmpList(HttpServletRequest request){
        String trainId = request.getQueryString();
        System.out.println("TRAINID:"+trainId);
        int flg = 1;
        List<Student> studentList = studentRepository.findEmpIdByFlgAndTrainId(trainId,flg);
        ArrayList<EmpList> empLists = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            String empId = studentList.get(i).getEmpId();
            System.out.println("选择该培训的人员Id:"+empId);
            EmpAndDep empAndDep = empRepository.findEmpAndDep(empId);
            System.out.println(empAndDep);
                EmpList empList = new EmpList();
                String name = empAndDep.getEmp().getEmpName();
                String dep = empAndDep.getDep().getDepName();
                String role = empAndDep.getEmp().getRole();
                empList.setEmpName(name);
                empList.setDep(dep);
                empList.setRole(role);
                empLists.add(empList);
        }

        return empLists;

    }
}
