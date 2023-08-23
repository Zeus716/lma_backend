package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, String> {

}
