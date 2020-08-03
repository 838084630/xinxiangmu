package com.example.demo.controller;

import com.example.demo.dao.EmpRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.TrainManageDao;
import com.example.demo.domain.EmpAndDep;
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

    @RequestMapping("/searchEmpByDep")
    public List<EmpAndDep> findByCondition(String depOption,String lessonOption){
        ArrayList<Integer> students = new ArrayList<>();
        ArrayList<String> resultEmpId = new ArrayList<>();
//        首先根据部门名字查到学员的id；
        List<EmpAndDep> empId = empRepository.findEmpId(depOption);
        //        根据学员id查询train_id;
        for (int i = 0; i < empId.size(); i++) {
            String empId1 = empId.get(i).getEmp().getEmpId();
            List<Student> trainId = studentRepository.findTrainId(empId1);
            String trainId1 = trainId.get(i).getTrainId();
            List<TrainInfo> trainTypeName = trainManageDao.findTrainTypeName(trainId1);
            for (int j = 0; j < trainTypeName.size(); j++) {
                String trainTypeName1 = trainTypeName.get(j).getTrainTypeName();
                if (trainTypeName1.equals(lessonOption)){
                    int trainId2 = trainTypeName.get(j).getTrainId();
                    students.add(trainId2);
                }
            }

        }

        for (int i = 0; i < students.size(); i++) {
            Integer integer = students.get(i);
            List<Student> empIdByTrainId = studentRepository.findEmpIdByTrainId(integer);
            for (int j = 0; j < empIdByTrainId.size(); j++) {
                String empId1 = empIdByTrainId.get(j).getEmpId();
                    resultEmpId.add(empId1);
            }
        }

//        获取了报了此课程的该部门的人员id；


//        根据train_id查询train_type;
//        判断查到的train_type集合是否属于的集合
        return new ArrayList<>();
    }
}
