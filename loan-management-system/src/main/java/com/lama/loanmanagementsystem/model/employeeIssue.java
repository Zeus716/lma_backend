package com.lama.loanmanagementsystem.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@Table(name = "employeeIssue")
//@Builder
public class employeeIssue {




    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "issue_id")
    private String issueId ;
//    @Column(nullable = false)
    @Getter
    @OneToOne(mappedBy = "employeeIssue",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private itemMaster itemId;
    @Column(nullable = false)

    private String employeeId;
    @Column(nullable = false)
    private Date issueDate;
    @Column(nullable = false)
    private Date returnDate;



//    @NonNull

//    private itemMaster item_id_from_other;

    public employeeIssue() {
    }

    public employeeIssue(String employeeId, Date issueDate, Date returnDate) {
        this.issueId = UUID.randomUUID().toString().split("-")[0];
        this.employeeId = employeeId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public employeeIssue(itemMaster itemId, String employeeId, Date issueDate, Date returnDate) {
        this.issueId = UUID.randomUUID().toString().split("-")[0];
        this.itemId = itemId;
        this.employeeId = employeeId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public void setItemId(itemMaster itemId) {
        this.itemId = itemId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }


    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


}
