package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.LoanMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanMaster,String> {
    List<LoanMaster> findByLoanType(String loanType);
    List<LoanMaster> findByEmployee_EmployeeId(String employeeId);
}
