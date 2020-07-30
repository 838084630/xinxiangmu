package com.example.demo.dao;

import com.example.demo.domain.EmpAndDep;
import com.example.demo.pojo.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpRepository extends JpaRepository<Emp,String> {
    @Query(value = "select new com.example.demo.domain.EmpAndDep(e,d) from Emp e,Dep d where e.depId=d.depId")
    public List<EmpAndDep> findEmpAndDep();
}
