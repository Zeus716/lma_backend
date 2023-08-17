package com.lama.loanmanagementsystem.model;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Table(name = "employeeMaster")
public class employeeMaster {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String employeeId ; ;


    private String employeeName;
    private String employeeDesignation;
    private Date employeeDOJ;
    private Date employeeDOB;
    private String gender;
    private String employeeDepartment;

    public employeeMaster(String employeeName, String employeeDesignation, Date employeeDOJ, Date employeeDOB, String gender, String employeeDepartment) {
        this.employeeName = employeeName;
        this.employeeDesignation = employeeDesignation;
        this.employeeDOJ = employeeDOJ;
        this.employeeDOB = employeeDOB;
        this.gender = gender;
        this.employeeDepartment = employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public employeeMaster() {
    }



    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public void setEmployeeDOJ(Date employeeDOJ) {
        this.employeeDOJ = employeeDOJ;
    }

    public void setEmployeeDOB(Date employeeDOB) {
        this.employeeDOB = employeeDOB;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }






}
