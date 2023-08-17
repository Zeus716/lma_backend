package com.lama.loanmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
//@Builder
@Setter
@Entity
@Table(name = "itemMaster")

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
    private char issueStatus;
    private String itemMake;
    private String itemCategory;
    private Integer itemValuation;


    @Getter
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "issueId",referencedColumnName = "issue_id")
    private employeeIssue employeeIssue;


    public void setEmployeeIssue(com.lama.loanmanagementsystem.model.employeeIssue employeeIssue) {
        this.employeeIssue = employeeIssue;
    }

    public itemMaster(String itemDescription, char issueStatus, String itemMake, String itemCategory, Integer itemValuation, com.lama.loanmanagementsystem.model.employeeIssue employeeIssue) {
        this.itemDescription = itemDescription;
        this.issueStatus = issueStatus;
        this.itemMake = itemMake;
        this.itemCategory = itemCategory;
        this.itemValuation = itemValuation;
        this.employeeIssue = employeeIssue;
    }
    public itemMaster(String itemDescription, char issueStatus, String itemMake, String itemCategory, Integer itemValuation) {
        this.itemDescription = itemDescription;
        this.issueStatus = issueStatus;
        this.itemMake = itemMake;
        this.itemCategory = itemCategory;
        this.itemValuation = itemValuation;
    }



    public itemMaster() {
    }
}
