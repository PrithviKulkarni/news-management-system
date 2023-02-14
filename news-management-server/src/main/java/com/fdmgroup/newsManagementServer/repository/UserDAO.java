package com.fdmgroup.newsManagementServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.newsManagementServer.model.Users;

public interface UserDAO extends JpaRepository<Users, Integer> {
	
	Optional<Users> findByEmail(String email);

}
