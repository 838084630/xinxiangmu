package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "emp")
public class Emp {
    @Id
    @Column(name = "emp_id")
    private String empId;
    @Column(name = "emp_name")
    private String empName;
    @Column(name = "dep_id")
    private String depId;
    @Column(name = "role")
    private String role;

    public Emp(){

    }
    public Emp(String empId, String empName, String depId, String role) {
        this.empId = empId;
        this.empName = empName;
        this.depId = depId;
        this.role = role;

    }

    @Override
    public String toString() {
        return "Emp{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", depId='" + depId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
