package com.lama.loanmanagementsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employeeCard")
public class employeeCard {
    @Id

    private String employeeId;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "loanId",referencedColumnName = "loanId")
    private loanMaster loanId;
    private Date issueDate;
    @Column(columnDefinition = "default F")
    private char approvalStatus;

}
