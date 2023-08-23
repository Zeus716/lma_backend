package com.lama.loanmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "loanType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanType {
    @Id
    @GeneratedValue(generator = "shortUUIDgenerator")
    @GenericGenerator(
            name = "shortUUIDgenerator",
            strategy = "com.lama.loanmanagementsystem.model.UUIDgenerator"
    )
    @Column(name = "loan_id")
    private String loanId ;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "duration_in_months")
    private Integer durationInMonths;

}
