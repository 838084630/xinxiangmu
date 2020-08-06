package com.example.demo.controller;


import com.example.demo.dao.*;
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

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TrainManageController {
    @Autowired
    private TrainManageService trainManageService;
    @Autowired
    private TrainManageDao trainManageDao;
    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private DepRepository depRepository;
    @Autowired
    private TrainDao trainDao;
    @Autowired
    private StudentRepository studentRepository;


    @RequestMapping("/trainPage")
    public String showPage() {
        return "trainManage";
    }


    @RequestMapping("/trainOptions")
    @ResponseBody
    public List<TrainInfo> findOptions(Model model) {
        List<TrainInfo> trainInfoList = trainManageService.findOptions();

//        model.addAttribute("trainInfoList",trainInfoList);
//        return "trainManage";
        return trainInfoList;
    }



    //培训检索
    @RequestMapping("/searchByConditions")
    public String searchByConditions(@RequestParam("statusOption") String s,
                                     @RequestParam("trainData") String t,
                                     @RequestParam("lessonOption") String l,
                                     Model model) {

//        先查询出报名此培训的所有人，更新trainInfo中的人数

        List<TrainInfo> trainInfoList = trainManageService.searchByConditions(s, t, l);
        for (int i = 0; i < trainInfoList.size(); i++) {
            int trainId = trainInfoList.get(i).getTrainId();
            int number = studentRepository.findByTrainId(trainId);
            trainInfoList.get(i).setJoinNumtrue(number);
        }
        model.addAttribute("trainInfoList", trainInfoList);
        return "trainManage";


    }


    //    授课类型下拉框1
    @RequestMapping("/xx")
    @ResponseBody
    public List<Train> options() {
        List<Train> trainInfoList = trainManageService.findPullDownMenu();

        return trainInfoList;
    }

    //    授课类型下拉框2
    @RequestMapping("/xx2")
    @ResponseBody
    public List<Train> options2() {
        List<Train> trainInfoList = trainManageService.findPullDownMenu();

        return trainInfoList;
    }

    //    授课类型下拉框3
    @RequestMapping("/xx3")
    @ResponseBody
    public List<Train> options3() {
        List<Train> trainInfoList = trainManageService.findPullDownMenu();

        return trainInfoList;
    }
    //    授课类型下拉框4
    @RequestMapping("/xx4")
    @ResponseBody
    public List<Train> options4() {
        List<Train> trainInfoList = trainManageService.findPullDownMenu();

        return trainInfoList;
    }

    //      部门的下拉框
    @RequestMapping("/depType")
    @ResponseBody
    public List<Dep> depType() {
        List<Dep> trainInfoList = depRepository.findAll();

        return trainInfoList;
    }


    //    新增课程
    @RequestMapping("/addLesson")
    @ResponseBody
    public Object addLesson(String subjects, String departmentName, String empname, String period, String trainTypeName) {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

//    先查询部门与姓名是否存在，若存在才能增加课程

        List<EmpAndDep> empAndDeps = empRepository.queryDepIdBydepName(departmentName, empname);
        System.out.println("查询所填部门及员工信息是否正确：" + empAndDeps);
        HashMap<String, Object> map = new HashMap<>();
        if (empAndDeps != null && !empAndDeps.isEmpty()) {
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
            String trainType = "";
            for (int i = 0; i < trainTypeByName.size(); i++) {
                trainType = "";
                trainType = trainTypeByName.get(i).getTrainType();

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

            return map.put("code", "200");

        } else {
            return map.put("code", "400");
        }


    }
    @RequestMapping("/updateLesson")
    @ResponseBody
    @Transactional
    public Map<String,Object> updateLesson(String goal,String discription,String substance,String trainId,String trainType){
        System.out.println("课程更新："+goal);
        System.out.println("课程更新："+discription);
        System.out.println("课程更新："+substance);
        System.out.println("："+trainId);
        System.out.println("课程名字ID："+trainType);
        HashMap<String, Object> map = new HashMap<>();
        int i = Integer.parseInt(trainId);
        try{
            TrainInfo trainInfo = trainManageDao.findByTrainId(i);
            trainInfo.setGoal(goal);
            trainInfo.setDescription(discription);
            trainManageDao.save(trainInfo);
            Train one = trainDao.findOne(trainType);
            one.setTrainDescription(substance);
            trainDao.save(one);
            map.put("code","200");
        }catch (Exception e){
            map.put("code","400");
        }
       return map;
    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public String changeStatus(String trainId,String status){
        int i = Integer.parseInt(trainId);
        System.out.println("XXXXX"+trainId);
        System.out.println("XXXXX"+status);
        TrainInfo byTrainId = trainManageDao.findByTrainId(i);
        if (status.equals("培训发布")){
            status = "已发布";
        }else if (status.equals("培训确定")){
            status = "已确定";
        }else if (status.equals("培训完了")){
            status = "已完结";
        }
        byTrainId.setStatus(status);
        trainManageDao.save(byTrainId);
        return "OK";
    }
}
