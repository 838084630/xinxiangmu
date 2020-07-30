package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "train_info")
public class TrainInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id")
    private int trainId;
    @Column(name = "train_type")
    private String trainType;
    @Column(name = "train_type_name")
    private String trainTypeName;
    @Column(name = "subjects")
    private String subjects;
    @Column(name = "director")
    private String director;
    @Column(name = "goal")
    private String goal;
    @Column(name = "description")
    private String description;
    @Column(name = "period")
    private String period;
    @Column(name = "status")
    private String status;
    @Column(name = "create_id")
    private int createId;
    @Column(name = "update_id")
    private int updateId;
    @Column(name = "update_date")
    private String updateDate;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "join_num")
    private int joinNum;
    @Column(name = "join_numtrue")
    private int joinNumtrue;



}
