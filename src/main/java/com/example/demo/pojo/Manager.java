package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int managerId;
    @Column(name = "admin_name")
    private String managerUsername;
    @Column(name = "admin_password")
    private String managerPassword;


}
