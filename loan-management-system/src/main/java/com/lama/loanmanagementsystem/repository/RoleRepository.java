package com.lama.loanmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lama.loanmanagementsystem.model.ERole;
import com.lama.loanmanagementsystem.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(ERole name);
}
