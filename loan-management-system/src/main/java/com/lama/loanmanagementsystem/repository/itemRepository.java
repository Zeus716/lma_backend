package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.itemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface itemRepository extends JpaRepository<itemMaster,String> {

}
