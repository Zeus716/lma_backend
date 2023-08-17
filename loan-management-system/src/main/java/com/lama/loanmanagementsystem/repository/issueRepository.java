package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.employeeIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface issueRepository extends JpaRepository<employeeIssue,String> {

}
