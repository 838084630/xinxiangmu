package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "dep")
public class Dep {
    @Id
    @Column(name = "dep_id")
    private String depId;
    @Column(name = "dep_name")
    private String depName;
    public Dep(){

    }
    public Dep(String depId, String depName) {
        this.depId = depId;
        this.depName = depName;
    }

    @Override
    public String toString() {
        return "Dep{" +
                "depId='" + depId + '\'' +
                ", depName='" + depName + '\'' +
                '}';
    }
}
