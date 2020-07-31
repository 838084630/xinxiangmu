package com.example.demo.pojo;

import lombok.Data;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "train")
public class Train {
    @Id
    @Column(name = "train_type")
    private String trainType;
    @Column(name = "train_type_name")
    private String trainTypeName;
    @Column(name = "train_description")
    private String trainDescription;
    @Column(name = "lesson_hour")
    private int lessonHour;
    @Column(name = "student_type")
    private String studentType;


}
