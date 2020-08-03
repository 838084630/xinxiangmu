package com.example.demo.dao;

import com.example.demo.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    @Query(value = "select  * from student where train_id = ?1 and flg = ?2",nativeQuery = true)
    List<Student> findEmpIdByFlgAndTrainId(String trainId,Integer flg);
    @Query(value = "select * from student where emp_id = ?1", nativeQuery = true)
    List<Student> findTrainId(String empId1);

    @Query(value = "select * from student where train_id = ?1", nativeQuery = true)
    List<Student> findEmpIdByTrainId(Integer integer);
}
