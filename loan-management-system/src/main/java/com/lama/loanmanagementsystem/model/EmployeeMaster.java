package com.lama.loanmanagementsystem.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Table(name = "employeeMaster")
@Setter
@Validated
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMaster {
    @Id
    @GeneratedValue(generator = "shortUUIDgenerator")
    @GenericGenerator(
            name = "shortUUIDgenerator",
            strategy = "com.lama.loanmanagementsystem.model.UUIDgenerator"
    )
    @Column(name = "employee_id")
    private String employeeId ;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_designation")
    private String employeeDesignation;

    @Column(name = "employee_department")
    private String employeeDepartment;

    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomDeserializer.class)
    @Column(name = "employee_DOJ")
    private Date employeeDOJ;

    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomDeserializer.class)
    @Column(name= "employee_DOB")
    private Date employeeDOB;

    @Column(name = "gender")
    private String gender;



}
