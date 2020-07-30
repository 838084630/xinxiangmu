package com.example.demo.controller;


import com.example.demo.pojo.TrainInfo;
import com.example.demo.service.TrainManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TrainManageController {
    @Autowired
    private TrainManageService trainManageService;
//    @RequestMapping("/trainManage")
//    public String goTrainManage(){
//        return "trainManage";
//    }

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

    @RequestMapping("/searchByConditions")
    public String searchByConditions(String statusOption,String trainData,String lessonOption,Model model){
        List<TrainInfo> trainInfoList = trainManageService.searchByConditions(statusOption, trainData, lessonOption);
        for (int i = 0; i < trainInfoList.size(); i++) {
            if(!trainInfoList.get(i).getTrainTypeName().equals(lessonOption)){
                trainInfoList.remove(i);
            }
            System.out.println("BBBBBBBBBBBBB"+trainInfoList.get(i));
        }
       model.addAttribute("trainInfoList",trainInfoList);
       return "trainManage";

    }

//    下拉框1
    @RequestMapping("/xx")
    @ResponseBody
    public List<TrainInfo> options(){
        List<TrainInfo> trainInfoList = trainManageService.findOptions();

        return trainInfoList;
    }
//    下拉框2
    @RequestMapping("/xx2")
    @ResponseBody
    public List<TrainInfo> options2(){
        List<TrainInfo> trainInfoList = trainManageService.findOptions();

        return trainInfoList;
    }

//    新增课程
@RequestMapping("/addLesson")
@ResponseBody
public Map addLesson(String subjects, String departmentName, String empname, String period, String trainTypeName){

    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

////    查培训主表根据培训类名获取类名id，便于创建新的公告，先创建实体类
//    trainManageService.queryTrainType(trainTypeName);
//    get方法获取id
    TrainInfo trainInfo = new TrainInfo();
    trainInfo.setSubjects(subjects);
    trainInfo.setPeriod(period);
    trainInfo.setUpdateDate(date);
    trainInfo.setTrainTypeName(trainTypeName);
    trainInfo.setDirector(empname);

//    trainInfo.setTrainType("");
//    trainInfo.setDirector("");
//    trainInfo.setGoal("");
//    trainInfo.setDescription("");
//    trainInfo.setStatus("");
//    trainInfo.setCreateId(0);
//    trainInfo.setUpdateId(0);
//    trainInfo.setTrainId(2);

    TrainInfo result = trainManageService.saveTrainInfo(trainInfo);
    HashMap<String, Object> map = new HashMap<>();
            return (Map) map.put("code","200");

}

}
