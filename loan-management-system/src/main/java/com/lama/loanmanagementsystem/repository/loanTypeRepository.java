package com.lama.loanmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lama.loanmanagementsystem.model.LoanType;

import java.util.Optional;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType,String> {
    Optional<LoanType> findByLoanTypeIs(String loanType );
}
