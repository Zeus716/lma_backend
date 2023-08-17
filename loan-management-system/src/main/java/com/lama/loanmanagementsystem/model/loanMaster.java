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
    private String loanId ;

    @Column(nullable = false)
    private Integer durationInMonths = 0;

    @Column(nullable = false)
    private String loanType ="def";

    @OneToOne(mappedBy = "loanMaster")
    private employeeCard empCard;

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



    public loanMaster() {

    }
    public loanMaster(String loanType, Integer durationInMonths) {
        this.loanType = loanType;
        this.durationInMonths = durationInMonths;
    }
}
