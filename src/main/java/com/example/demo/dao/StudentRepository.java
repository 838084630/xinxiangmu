package com.example.demo.dao;

import com.example.demo.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    @Query(value = "select  * from student where train_id = ?1 and flg = ?2",nativeQuery = true)
    List<Student> findEmpIdByFlgAndTrainId(String trainId,Integer flg);
    @Query(value = "select * from student where emp_id = ?1", nativeQuery = true)
    List<Student> findTrainId(String empId1);

    @Query(value = "select * from student where train_id = ?1", nativeQuery = true)
    List<Student> findEmpIdByTrainId(Integer integer);

    @Query(value = "select * from student where train_id =?1 and emp_id = ?2", nativeQuery = true)
    Student findOneResult(String trainId,String empId);

    @Query(value = "select count(*) from student where emp_id = ?1", nativeQuery = true)
    int findStudent(String empId);

    Student findByEmpId(String empId);
    @Query(value = "select count(*) from student where train_id = ?1", nativeQuery = true)
    int findByTrainId(int trainId);

    @Transactional
    @Modifying
    @Query(value = "insert into student(emp_id,flg,train_id,update_id,update_date) values(?1,?2,?3,?4,?5)",nativeQuery = true)
    int addOne(String empId, int zhuangtai, String trainId, String updateId, String date);

//    @Query(value = "insert into student values(?1,?2,?3,?4,?5)",nativeQuery = true)
//    int insertOne(String empId, int zhuangtai,String trainId,String updateId,String date);
}
