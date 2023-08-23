package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.EmployeeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, String> {


}
