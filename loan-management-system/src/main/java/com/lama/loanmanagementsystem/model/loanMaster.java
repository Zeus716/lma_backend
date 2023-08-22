package com.lama.loanmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "loanMaster")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class loanMaster {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String loanId ;
//    private String employeeId;
    @ManyToOne
    @JoinColumn(name ="loanType")
    private loanType loanType;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee")
     private employeeMaster employee;
}
