package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemMaster,String> {
    List<ItemMaster> findByEmployee_EmployeeId(String empId);
    List<ItemMaster> findByIssueStatusIs(char F);
}
