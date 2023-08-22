package com.lama.loanmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
//@Builder
@Setter
@Entity
@Table(name = "itemMaster")
@AllArgsConstructor
@NoArgsConstructor
public class itemMaster {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "item_id")
    private String itemId ;
    private String itemDescription;
//    @Column(columnDefinition = "default F")
    private char issueStatus = 'F';
    private String itemMake;
    private String itemCategory;
    private Date issueDate;
    private Date returnDate;
    private Integer itemValuation;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee")
//    @JsonIgnore
    private employeeMaster employee;

//    @ManyToOne(cascade = {CascadeType.ALL} ,fetch = FetchType.EAGER)
//    @JoinColumn(name = "employeeId",referencedColumnName = "employeeId")
//    @JsonBackReference
//    private employeeMaster employeeId;


}
