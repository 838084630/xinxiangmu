package com.example.demo.controller;

import com.example.demo.dao.EmpRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.TrainManageDao;
import com.example.demo.pojo.Emp;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Train;
import com.example.demo.pojo.TrainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor;
import javax.servlet.http.HttpSession;
import javax.swing.text.Keymap;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserPageController {

    @Autowired
    private TrainManageDao trainManageDao;
    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private StudentRepository studentRepository;

     TrainInfo trainInfo = new TrainInfo();

    @RequestMapping("/signUp")
    public String showPage() {
        return "signUp";

    }


    @RequestMapping("/searchTrain")
    @ResponseBody
    public TrainInfo searchList(String trainType, String trainId) {
        int i = Integer.parseInt(trainId);
        TrainInfo byTrainId = trainManageDao.findByTrainId(i);
        if (byTrainId.getStatus().equals("已发布")){
            trainInfo.setTrainId(byTrainId.getTrainId());
            trainInfo.setTrainTypeName(byTrainId.getTrainTypeName());
            trainInfo.setStartDate(byTrainId.getStartDate());
            trainInfo.setDirector(byTrainId.getDirector());
            return byTrainId;
        }

        return null;
    }


    @RequestMapping("/queryTrainInfo")
    @ResponseBody
    public List<TrainInfo> showList() {

        List<TrainInfo> trainInfoList = new ArrayList<>();
        trainInfoList.add(trainInfo);
//        if(trainInfoList!=null && !trainInfoList.isEmpty()){
//            return trainInfoList;
//        }else{
//            return null;
//        }
        return trainInfoList;
    }


    @RequestMapping("/changeFlg")
    @ResponseBody
    public Map<String,Object> changeFlg(String trainType, String trainId,String empName){
        int zhuangtai = 1;
        String updateId = "1";
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        HashMap<String, Object> map = new HashMap<>();

        Emp byEmpName = empRepository.findByEmpName(empName);
        String empId = byEmpName.getEmpId();
//        int student = studentRepository.findStudent(empId);

//        if (student==1){
//            Student student1 = studentRepository.findByEmpId(empId);
//            if (student1.getFlg()==1){
//              map.put("message","你已经参与该课程了，请勿重复提交");
//            }else {
//                student1.setFlg(1);
//                studentRepository.save(student1);
//                map.put("message","更新");
//            }
//
//        }else if (student==0){

//            int i = studentRepository.insertOne(trainId, zhuangtai, empId,updateId,date);
//            Student student2 = new Student();
//            student2.setTrainId(trainId);
//            student2.setFlg(zhuangtai);
//            student2.setEmpId(empId);
//            student2.setUpdateId(updateId);
//            student2.setUpdateDate(date);
//            studentRepository.save(student2);
        int i = studentRepository.addOne(empId, zhuangtai, trainId, updateId, date);
        if (i==1){

            map.put("message","新增成功");
        }else {

            map.put("message","新增失败");
        }
//        }
        return map;
    }
}
