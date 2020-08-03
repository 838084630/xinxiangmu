package com.example.demo.service.impl;

import com.example.demo.dao.TrainDao;
import com.example.demo.dao.TrainManageDao;
import com.example.demo.pojo.Train;
import com.example.demo.pojo.TrainInfo;
import com.example.demo.service.TrainManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrainManageServiceImpl implements TrainManageService {

    @Autowired
    private TrainManageDao  trainManageDao;
    @Autowired
    private TrainDao trainDao;
//    @Autowired
//    private TrainManageDao2 trainManageDao2;

//    @Override
//    public List<TrainTypeModel> findOptions() {

//        List<TrainTypeModel> models = new ArrayList<>();
//
//        List subjects = trainManageDao.findTrainType();
//        subjects.listIterator().forEachRemaining(subject -> {
//            TrainTypeModel model = new TrainTypeModel();
//            model.setSubjects((String)subject);
//            models.add(model);
//        });

//        return models;
//    }

    @Override
    public List<TrainInfo> findOptions() {
        return trainManageDao.findOptions();
    }

//    下拉框
    public List<Train> findPullDownMenu() {
        return trainManageDao.findPullDownMenu();
    }
    @Override
    public List<TrainInfo> searchByConditions(String statusOption, String trainData, String lessonOption) {

        return trainManageDao.findByStatusAndStartDate(statusOption,trainData,lessonOption);
    }

    @Override
    public TrainInfo saveTrainInfo(TrainInfo trainInfo) {
        TrainInfo save = trainManageDao.save(trainInfo);
        return save;
    }

    @Override
    public List<Train> findTrainTypeByName(String trainTypeName) {
        return trainDao.findByTrainTypeName(trainTypeName);
    }


}
