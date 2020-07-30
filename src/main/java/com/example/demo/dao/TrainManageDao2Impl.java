//package com.example.demo.dao;
//
//import com.example.demo.pojo.TrainInfo;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.Resource;
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import java.util.List;
//
//@Repository
//public class TrainManageDao2Impl {
//    @Resource
//    private EntityManager entityManager;
//
//    public List<TrainInfo> searchByConditions(String statusOption, String trainData, String lessonOption){
//        String sql = "select * from train_info where status = ? and start_date = ? and train_type_name = ?";
//        Query query = this.entityManager.createNativeQuery(sql, TrainInfo.class);
//        query.setParameter(statusOption,1);
//        query.setParameter(trainData,2);
//        query.setParameter(lessonOption,3);
//        List resultList = query.getResultList();
//        return resultList;
//
//    }
//}
