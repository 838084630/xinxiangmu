package com.example.demo.dao;

import com.example.demo.pojo.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainDao extends JpaRepository<Train,String> {
    @Query(value = "select * from train where train_type_name = ?1",nativeQuery = true)
    List<Train> findByTrainTypeName(String trainTypeName);

    @Query(value = "select * from train where train_type = ?1",nativeQuery = true)
    Train findOne(String trainTypeName);

}
