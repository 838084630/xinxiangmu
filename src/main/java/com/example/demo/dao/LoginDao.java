package com.example.demo.dao;

import com.example.demo.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends JpaRepository<Admin,Integer> {

//    @Query("select count(a) from admin a where a.admin_name = ?1 and a.admin_password = ?2")
    Admin findByAdminNameAndAdminPass(String adminName, String adminPass);

}
