package com.lama.loanmanagementsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "user_data")
public class userData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employee_id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String isAdmin;

    public Integer getEmployee_id() {
        return employee_id;
    }

    public userData() {
    }

    public userData(String password, String isAdmin) {
//        this.employee_id = employee_id;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
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
