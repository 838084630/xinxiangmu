package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "emp_id")
    private String empId;
    @Column(name = "train_id")
    private String trainId;
    @Column(name = "flg")
    private Integer flg;
    @Column(name = "update_id")
    private String updateId;
    @Column(name = "update_date")
    private String updateDate;


}
