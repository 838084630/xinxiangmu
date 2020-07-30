package com.example.demo.domain;

import com.example.demo.pojo.Dep;
import com.example.demo.pojo.Emp;
import lombok.Data;

import java.io.Serializable;
@Data
public class EmpAndDep implements Serializable {
    private Emp emp;
    private Dep dep;

    public EmpAndDep(Emp emp) {
        this.emp = emp;
    }

    public EmpAndDep(Dep dep) {
        this.dep = dep;
    }

    public EmpAndDep(Emp emp, Dep dep) {
        this.emp = emp;
        this.dep = dep;
    }
}
