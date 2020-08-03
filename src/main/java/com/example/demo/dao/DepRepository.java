package com.example.demo.dao;

import com.example.demo.pojo.Dep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepRepository extends JpaRepository<Dep,String> {

    List<Dep> findAll();


}
