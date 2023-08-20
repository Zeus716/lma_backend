package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.employeeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeMasterRepository extends JpaRepository<employeeMaster, String> {

}
