package com.lama.loanmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lama.loanmanagementsystem.model.loanType;
@Repository
public interface loanTypeRepository extends JpaRepository<loanType,String> {

}
