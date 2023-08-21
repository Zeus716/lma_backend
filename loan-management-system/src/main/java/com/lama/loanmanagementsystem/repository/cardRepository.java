package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.employeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cardRepository extends JpaRepository<employeeCard,String> {
}
