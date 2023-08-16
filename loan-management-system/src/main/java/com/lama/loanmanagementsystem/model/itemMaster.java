package com.lama.loanmanagementsystem.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
public class itemMaster {
    @Id
    private String itemId;
    private String itemDescription;
    private char issueStatus;
    private String itemMake;
    private String itemCategory;
    private Integer itemValuation;


//    public itemMaster(String itemDescription, char issueStatus, String itemMake, String itemCategory, Integer itemValuation, com.lama.loanmanagementsystem.model.employeeIssue employeeIssue) {
//        this.itemDescription = itemDescription;
//        this.issueStatus = issueStatus;
//        this.itemMake = itemMake;
//        this.itemCategory = itemCategory;
//        this.itemValuation = itemValuation;
//        this.employeeIssue = employeeIssue;
//    }

//    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
//    @JoinTable(name = "itemMaster_employeeIssue",
//            joinColumns = @JoinColumn(name = "itemMaster_itemId"),
//            inverseJoinColumns = @JoinColumn(name = "employeeIssue_issueId"))
//    private employeeIssue employeeIssue;


    public itemMaster() {
    }
}
