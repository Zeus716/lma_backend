package com.lama.loanmanagementsystem.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user_data")
public class userData {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String employeeId;
    @Column(nullable = false)
    private String password = "";
    @Column(nullable = false)
    private String isAdmin="";

    public String getEmployeeId() {
        return employeeId;
    }

    public userData() {
    }

    public userData(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public userData(String password , String isAdmin ) {
//        this.employee_id = employee_id;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
}
