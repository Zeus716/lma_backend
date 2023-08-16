package com.lama.loanmanagementsystem.model;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employeeMaster")
public class employeeMaster {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String employeeId;

    @Getter
    private String employeeName;
    @Getter
    private String employeeDesignation;
    @Getter
    private Date employeeDOJ;
    @Getter
    private Date employeeDOB;
    @Getter
    private String gender;

    public employeeMaster() {
    }

    public employeeMaster(String employeeName, String employeeDesignation, Date employeeDOJ, Date employeeDOB, String gender) {
        this.employeeName = employeeName;
        this.employeeDesignation = employeeDesignation;
        this.employeeDOJ = employeeDOJ;
        this.employeeDOB = employeeDOB;
        this.gender = gender;
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



//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    Set<loanMaster> loanMasterSet;


}
