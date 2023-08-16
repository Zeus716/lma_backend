package com.lama.loanmanagementsystem.model;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Entity
@Table(name = "employeeIssue")
public class employeeIssue {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String issueId = UUID.randomUUID().toString();
    @Column(nullable = false)
    private String itemId;
    @Column(nullable = false)
    private String employeeId;
    @Column(nullable = false)
    private Date issueDate;
    @Column(nullable = false)
    private Date returnDate;

    public employeeIssue() {
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public employeeIssue(String itemId, String employeeId, Date issueDate, Date returnDate) {
        this.itemId = itemId;
        this.employeeId = employeeId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


}
