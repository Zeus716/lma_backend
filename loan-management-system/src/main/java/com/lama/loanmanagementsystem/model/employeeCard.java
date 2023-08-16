package com.lama.loanmanagementsystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class employeeCard {
    @Id
    private String employeeId;
    private String loanId;
    private Date issueDate;
    private char approvalStatus;

}
