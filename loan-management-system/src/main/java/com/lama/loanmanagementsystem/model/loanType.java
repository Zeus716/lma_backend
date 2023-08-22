package com.lama.loanmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loanType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class loanType {
    @Id
    private String loanCategory;

    private Integer durationInMonths;

}
