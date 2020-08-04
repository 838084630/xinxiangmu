package com.example.demo.dao;

import com.example.demo.pojo.Dep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepRepository extends JpaRepository<Dep,String> {

    List<Dep> findAll();

    @Query(value = "select  * from dep where dep_name = ?1",nativeQuery = true)
    Dep findDepIdByDepName(String depOption);
}
