package com.lama.loanmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Table(name = "employeeMaster")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class employeeMaster {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String employeeId ;
    private String employeeName;
    private String employeeDesignation;
    private String employeeDepartment;
    private Date employeeDOJ;
    private Date employeeDOB;
    private String gender;

////    @JsonBackReference
//    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "employeeId",fetch = FetchType.EAGER)
//    private List<itemMaster> items;
//    @JsonBackReference


}
