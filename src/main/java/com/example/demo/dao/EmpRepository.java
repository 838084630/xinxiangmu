package com.example.demo.dao;

import com.example.demo.domain.EmpAndDep;
import com.example.demo.pojo.Dep;
import com.example.demo.pojo.Emp;
import com.example.demo.pojo.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmpRepository extends JpaRepository<Emp,String> {
    @Query(value = "select new com.example.demo.domain.EmpAndDep(e,d) from Emp e,Dep d where e.depId=d.depId and e.empId = ?1")
     EmpAndDep findEmpAndDep(String empId);

    @Query(value = "select new com.example.demo.domain.EmpAndDep(e,d) from Emp e,Dep d " +
            "where e.depId=d.depId " +
            "and d.depName=?1 " +
            "and e.empName=?2")
    List<EmpAndDep> queryDepIdBydepName(String departmentName, String empname);

    @Query(value = "select new com.example.demo.domain.EmpAndDep(e,d) from Emp e,Dep d where e.dep_id=d.dep_id and d.dep_name = ?1")
    List<EmpAndDep> findEmpId(String depOption);
}
