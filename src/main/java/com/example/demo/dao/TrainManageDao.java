package com.example.demo.dao;

import com.example.demo.pojo.Train;
import com.example.demo.pojo.TrainInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrainManageDao extends JpaRepository<TrainInfo,Integer> {

    @Query(value = "select t from TrainInfo t")
    List<TrainInfo> findOptions();


    @Query(value = "select t from Train t")
    List<Train> findPullDownMenu();

//    @Query(value = "select * from train_info where status = ?1 and start_date = ?2 and train_type_name = ?3", nativeQuery = true)
    List<TrainInfo> findByStatusAndStartDate(String statusOption, String trainData);

//    TrainInfo insertTrainfo(String subjects, String period, String trainTypeName, String date, String empname);
//    AndTrainTypeName

}
