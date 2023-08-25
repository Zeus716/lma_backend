package com.lama.loanmanagementsystem.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loanMaster")
@Getter
@Setter
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class LoanMaster {
    @Id
    @GeneratedValue(generator = "shortUUIDgenerator")
    @GenericGenerator(
            name = "shortUUIDgenerator",
            strategy = "com.lama.loanmanagementsystem.model.UUIDgenerator"
    )
    private String loanId ;
//    private String employeeId;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name ="loanType")
    private LoanType loanType;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee")
     private EmployeeMaster employee;
    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomDeserializer.class)
    private Date loanIssueDate ;

    public LoanMaster(LoanType loanType, EmployeeMaster employee, Date loanIssueDate) {
        this.loanType = loanType;
        this.employee = employee;
        this.loanIssueDate = loanIssueDate;
    }

    public LoanMaster(LoanType loanType, EmployeeMaster employee) {
        this.loanType = loanType;
        this.employee = employee;
    }

    public LoanMaster(LoanType loanType) {
        this.loanType = loanType;
    }
}
