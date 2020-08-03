package com.example.demo.controller;


import com.example.demo.dao.DepRepository;
import com.example.demo.dao.EmpRepository;
import com.example.demo.domain.EmpAndDep;
import com.example.demo.pojo.Dep;
import com.example.demo.pojo.Emp;
import com.example.demo.pojo.Train;
import com.example.demo.pojo.TrainInfo;
import com.example.demo.service.TrainManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class TrainManageController {
    @Autowired
    private TrainManageService trainManageService;
    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private DepRepository depRepository;



    @RequestMapping("/trainPage")
    public String showPage(){
        return "trainManage";
    }


    @RequestMapping("/trainOptions")
    @ResponseBody
    public List<TrainInfo> findOptions(Model model){
        List<TrainInfo> trainInfoList = trainManageService.findOptions();

//        model.addAttribute("trainInfoList",trainInfoList);
//        return "trainManage";
        return trainInfoList;
    }

//    @RequestMapping("/searchByConditions")
//    public String searchByConditions(String statusOption,String trainData,String lessonOption,Model model){
//        List<TrainInfo> trainInfoList = trainManageService.searchByConditions(statusOption, trainData, lessonOption);
//        for (int i = 0; i < trainInfoList.size(); i++) {
//            if(!trainInfoList.get(i).getTrainTypeName().equals(lessonOption)){
//                trainInfoList.remove(i);
//            }
//            System.out.println("BBBBBBBBBBBBB"+trainInfoList.get(i));
//        }
//        model.addAttribute("trainInfoList",trainInfoList);
//       return "trainManage::trainInfo";
//
//    }


//实验一下地址栏中取数据
@RequestMapping("/searchByConditions")
public String searchByConditions(@RequestParam("statusOption")String s,
                                 @RequestParam("trainData")String t,
                                 @RequestParam("lessonOption")String l,
                                 Model model){

    List<TrainInfo> trainInfoList = trainManageService.searchByConditions(s,t,l);
    for (int i = 0; i < trainInfoList.size(); i++) {
//        if(!trainInfoList.get(i).getTrainTypeName().equals(l)){
//            trainInfoList.remove(i);
//        }
        System.out.println("BBBBBBBBBBBBB"+trainInfoList.get(i));
    }
    model.addAttribute("trainInfoList",trainInfoList);
    return "trainManage";


}


//    授课类型下拉框1
    @RequestMapping("/xx")
    @ResponseBody
    public List<Train> options(){
        List<Train> trainInfoList = trainManageService.findPullDownMenu();

        return trainInfoList;
    }
//    授课类型下拉框2
    @RequestMapping("/xx2")
    @ResponseBody
    public List<Train> options2(){
        List<Train> trainInfoList = trainManageService.findPullDownMenu();

        return trainInfoList;
    }
//    授课类型下拉框3
    @RequestMapping("/xx3")
    @ResponseBody
    public List<Train> options3(){
        List<Train> trainInfoList = trainManageService.findPullDownMenu();

        return trainInfoList;
    }
//      部门的下拉框
    @RequestMapping("/depType")
    @ResponseBody
    public List<Dep> depType(){
        List<Dep> trainInfoList = depRepository.findAll();

        return trainInfoList;
    }


    //    新增课程
@RequestMapping("/addLesson")
@ResponseBody
public Object addLesson(String subjects, String departmentName, String empname, String period, String trainTypeName){

    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

//    先查询部门与姓名是否存在，若存在才能增加课程

    List<EmpAndDep> empAndDeps = empRepository.queryDepIdBydepName(departmentName, empname);
    System.out.println("查询所填部门及员工信息是否正确："+empAndDeps);
    HashMap<String, Object> map = new HashMap<>();
    if (empAndDeps!=null&&!empAndDeps.isEmpty()){
//            String empid = "";
//        for (int i = 0; i < empAndDeps.size(); i++) {
//             empid = empAndDeps.get(i).getEmp().getEmpId();
//        }
//
//        System.out.println("添加课程需要获取授课者的id"+empid);

        //    查培训主表根据培训类名获取类名id，便于创建新的公告，先创建实体类
//    trainManageService.queryTrainType(trainTypeName);
//    get方法获取id

//        通过授课类型查询授课类型的ID
        List<Train> trainTypeByName = trainManageService.findTrainTypeByName(trainTypeName);
        System.out.println("*********"+trainTypeByName);
        String trainType = "" ;
        for (int i = 0; i < trainTypeByName.size(); i++) {
            trainType = "";
            trainType=trainTypeByName.get(i).getTrainType();

        }

        TrainInfo trainInfo = new TrainInfo();
        trainInfo.setSubjects(subjects);
        trainInfo.setPeriod(period);
        trainInfo.setUpdateDate(date);
        trainInfo.setTrainTypeName(trainTypeName);
        trainInfo.setDirector(empname);

        trainInfo.setTrainType(trainType);
        trainInfo.setGoal("");
        trainInfo.setDescription("miaoshu");
        trainInfo.setStatus("");
        trainInfo.setCreateId(1);
        trainInfo.setUpdateId(1);
//        trainInfo.setTrainId(20);

        TrainInfo result = trainManageService.saveTrainInfo(trainInfo);

        return map.put("code","200");

    }else {
        return map.put("code","400");
    }


}

}
