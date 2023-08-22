package com.lama.loanmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lama.loanmanagementsystem.model.loanType;

import java.util.List;
import java.util.Optional;

@Repository
public interface loanTypeRepository extends JpaRepository<loanType,String> {
    Optional<loanType> findByLoanTypeIs(String loanType );
}
