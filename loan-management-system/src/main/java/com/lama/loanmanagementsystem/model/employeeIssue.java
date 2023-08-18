package com.lama.loanmanagementsystem.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@Table(name = "employeeIssue")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class employeeIssue {

    @Id
    @GeneratedValue(generator  = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "issue_id")
    private String issueId ;
    private String itemId;
    @Column(nullable = false)
    private String employeeId;
    @Column(nullable = false)
    private Date issueDate;
    @Column(nullable = false)
    private Date returnDate;

}
