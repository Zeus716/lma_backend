package com.lama.loanmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private char issueStatus;
    private String itemMake;
    private String itemCategory;
    private Integer itemValuation;

    @ManyToOne(cascade = {CascadeType.ALL} ,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "employeeId",referencedColumnName = "employeeId")
    private employeeMaster employeeId;

}
