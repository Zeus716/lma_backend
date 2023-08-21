package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.employeeIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface issueRepository extends JpaRepository<employeeIssue,String> {
    List<employeeIssue>  findByEmployeeId_EmployeeId(String employeeId);
}
