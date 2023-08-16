package com.lama.loanmanagementsystem.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "loanMaster")
public class loanMaster {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String loanId = UUID.randomUUID().toString();

    public loanMaster() {

    }

    @Column(nullable = false)
    private String loanType ="def";

    public loanMaster(Integer durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Integer getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(Integer durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    @Column(nullable = false)
    private Integer durationInMonths = 0;

    public loanMaster(String loanType) {
        this.loanType = loanType;
    }

    public loanMaster(String loanType, Integer durationInMonths) {
//        this.loanId = UUID.randomUUID();
        this.loanType = loanType;
        this.durationInMonths = durationInMonths;
    }
}
