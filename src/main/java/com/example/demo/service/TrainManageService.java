package com.example.demo.service;


import com.example.demo.pojo.Train;
import com.example.demo.pojo.TrainInfo;

import java.util.Date;
import java.util.List;

public interface TrainManageService {


    List<TrainInfo> findOptions();
    List<Train> findPullDownMenu();

    List<TrainInfo> searchByConditions(String statusOption,String trainData,String lessonOption);

    TrainInfo saveTrainInfo(TrainInfo trainInfo);

    List<Train> findTrainTypeByName(String trainTypeName);
}
