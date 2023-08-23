package com.lama.loanmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "loanMaster")
@Getter
@Setter
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

    public LoanMaster(LoanType loanType, EmployeeMaster employee) {
        this.loanType = loanType;
        this.employee = employee;
    }

    public LoanMaster(LoanType loanType) {
        this.loanType = loanType;
    }
}
