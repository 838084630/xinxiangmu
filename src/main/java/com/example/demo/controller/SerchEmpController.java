package com.example.demo.controller;

import com.example.demo.dao.DepRepository;
import com.example.demo.dao.EmpRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.TrainManageDao;
import com.example.demo.domain.EmpAndDep;
import com.example.demo.pojo.Dep;
import com.example.demo.pojo.Emp;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.TrainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SerchEmpController {
//    这个controller是为了第四个页面，根据部门和未参加的授课类型查询员工的信息

    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TrainManageDao trainManageDao;
    @Autowired
    private DepRepository depRepository;

    ArrayList<Emp> resultList = new ArrayList<>();

    @RequestMapping("/searchEmpByDep")
    public List<Emp> findByCondition(String depOption, String lessonOption) {
//        emp1是该部门下所有员工,emp2是参加该课程的所有员工，emp3是未参加该课程的该部门的所有员工
        ArrayList<String> emp1 = new ArrayList<>();
        ArrayList<String> emp2 = new ArrayList<>();
        ArrayList<String> emp3 = new ArrayList<>();

//        首先根据部门名字查到学员的id；
//        List<EmpAndDep> empId = empRepository.findEmpId(depOption);
        Dep depId = depRepository.findDepIdByDepName(depOption);
        String depId1 = depId.getDepId();
        List<Emp> empId = empRepository.findEmpIdByDepId(depId);
//        System.out.println("XXXXXX"+empId);
        for (int i = 0; i < empId.size(); i++) {

            emp1.add(empId.get(i).getEmpId());
        }
        List<TrainInfo> trainIdList = trainManageDao.findTrainIdByType(lessonOption);
//        System.out.println("YYYYYY"+trainIdList);
        for (int i = 0; i < trainIdList.size(); i++) {
            int trainId = trainIdList.get(i).getTrainId();
//            String s = String.valueOf(trainId);
            List<Student> result2 = studentRepository.findEmpIdByTrainId(trainId);

//            这是参加了该授课类型的人员ID；
            for (int j = 0; j < result2.size(); j++) {
                emp2.add(result2.get(j).getEmpId());
            }
        }
//        System.out.println("QQQQQQQQQQQQQQQQQQQ"+emp1);
//        System.out.println("QQQQQQQQQQQQQQQQQQQ"+emp2);
        for (int i = 0; i < emp1.size(); i++) {
            for (int j = 0; j < emp2.size(); j++) {
                if (emp1.get(i).equals(emp2.get(j))) {
                    emp3.add(emp1.get(i));
                }
            }
        }
        System.out.println("该部门参加该课程的人员ID" + emp3);

//这是查到的去掉参加的人的所有人员表，需要再根据部门去掉多余的人员
        List<Emp> AllEmp = empRepository.findByEmpIdNotIn(emp3);
//根据传来的部门，查询出最后的结果
        System.out.println("奇怪了" + depId1);
        ArrayList<Emp> list1 = new ArrayList<>();
        for (int i = 0; i < AllEmp.size(); i++) {
            if (AllEmp.get(i).getDepId().equals(depId1)) {
              list1.add(AllEmp.get(i));
            }

        }
        resultList.clear();
        resultList.addAll(list1);

        return AllEmp;
    }


    @RequestMapping("/showUncommitedEmp")
    public List<Emp> showUncommitedEmp() {
//        System.out.println("表格数据砸出木来呢？"+resultList);
        return resultList;
    }

}
