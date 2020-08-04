package com.example.demo.controller;

import com.example.demo.dao.EmpRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.domain.EmpAndDep;
import com.example.demo.pojo.Emp;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//        System.out.println("TRAINID:"+trainId);
        int flg = 1;
        List<Student> studentList = studentRepository.findEmpIdByFlgAndTrainId(trainId,flg);
        ArrayList<EmpList> empLists = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            String empId = studentList.get(i).getEmpId();
//            System.out.println("选择该培训的人员Id:"+empId);
            EmpAndDep empAndDep = empRepository.findEmpAndDep(empId);
//            System.out.println(empAndDep);
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

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String,Object> batchDelete(HttpServletRequest request) throws UnsupportedEncodingException {
        String queryString = request.getQueryString();
        String decode = URLDecoder.decode(queryString, "utf-8");
        System.out.println("decode:"+decode);
        String trainId = "";
        String empName = "";
        ArrayList<String> emps = new ArrayList<>();
        String[] strs = decode.split("&");

        for (int i = 0; i < strs.length; i++) {

            if (i<strs.length-1){

                empName=strs[i];

            }if (i==strs.length-1){

                trainId=strs[i];
            }
        }
        String[] oneName = empName.split(",");
        for (int i = 0; i < oneName.length; i++) {
            emps.add(oneName[i]);

        }
//        把trainIId=5的5截取出来
        String[] num = trainId.split("=");
        for (int i = 0; i < num.length; i++) {
            if (i==num.length-1){
               trainId = num[i];
            }
        }

//        拿到trainId,和一个empName集合 ,查出empId,再去student里修改flg为0；
        for (int i = 0; i <emps.size() ; i++) {
            String result = emps.get(i);
            Emp byEmpName = empRepository.findByEmpName(result);
            String empId = byEmpName.getEmpId();

            System.out.println("trainId:"+trainId);
            System.out.println("empId:"+empId);
            Student one = studentRepository.findOneResult(trainId,empId);

            System.out.println(one.getEmpId());
            one.setFlg(0);
            studentRepository.save(one);

        }

        return new HashMap<>();
    }
}
