package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.loanMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface loanRepository extends JpaRepository<loanMaster,String> {

}
