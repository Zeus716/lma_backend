package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.itemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface itemRepository extends JpaRepository<itemMaster,String> {
    List<itemMaster> findByEmployeeId_EmployeeId(String empId);
}
