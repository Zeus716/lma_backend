package com.lama.loanmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "loanMaster")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class loanMaster {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String loanId ;
    private Integer durationInMonths = 0;
    private String loanType ="def";


}
