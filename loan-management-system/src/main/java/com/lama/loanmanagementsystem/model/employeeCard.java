package com.lama.loanmanagementsystem.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employeeCard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class employeeCard {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String employeeId;
    private Date issueDate;

//    @Column(columnDefinition = "character default F")
    private char approvalStatus = 'F';

//    @ManyToOne(cascade = CascadeType.ALL)


}
