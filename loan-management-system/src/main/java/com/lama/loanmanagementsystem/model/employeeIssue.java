//package com.lama.loanmanagementsystem.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.Set;
//import java.util.UUID;
//
//@Getter
//@Entity
//@Table(name = "employeeIssue")
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class employeeIssue {
//
//    @Id
//    @GeneratedValue(generator  = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
//    @Column(name = "issue_id")
//    private String issueId ;
////    private String itemId;
////    @Column(nullable = false)
//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinColumn(name = "employee")
//    @JsonIgnore
//    private employeeMaster employeeId;
//    @Column(nullable = false)
//
////    @ForeignKey()
////    private String empId;
//
////    @JsonBackReference
////    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
////    @JoinColumn(name = "itemId")
////    @JsonIgnore
////    private itemMaster itemId;
//
//}
