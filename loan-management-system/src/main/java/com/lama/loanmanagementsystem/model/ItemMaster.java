package com.lama.loanmanagementsystem.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Date;

@Getter
//@Builder
@Setter
@Entity
@Table(name = "itemMaster")
@AllArgsConstructor
@Validated
@NoArgsConstructor
public class ItemMaster {
    @Id
    @GeneratedValue(generator = "shortUUIDgenerator")
    @GenericGenerator(
            name = "shortUUIDgenerator",
            strategy = "com.lama.loanmanagementsystem.model.UUIDgenerator"
    )
    @Column(name = "item_id")
    private String itemId ;
    private String itemUrl;
    private String itemDescription;
//    @Column(columnDefinition = "default F")
    private char issueStatus = 'N';
    private String itemMake;
    private String itemCategory;
    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomDeserializer.class)
    private Date issueDate;
    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomDeserializer.class)
    private Date returnDate;
    private Integer itemValuation;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee")

    private EmployeeMaster employee;




}
