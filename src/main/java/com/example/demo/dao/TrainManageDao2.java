//package com.example.demo.dao;
//
//import com.example.demo.pojo.TrainInfo;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//@Repository
//public interface TrainManageDao2 extends JpaRepository<TrainInfo,Integer> {
//    @Query(value = "select * from train_info  where status = ? and start_date = ? and train_type_name = ?", nativeQuery = true)
//    List<TrainInfo> findByStatusAndStartDateAndTrainTypeName(String statusOption, String trainData, String lessonOption);
//}
