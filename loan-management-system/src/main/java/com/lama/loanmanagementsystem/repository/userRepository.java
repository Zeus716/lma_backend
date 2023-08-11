package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.userData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<userData, Integer> {

}
