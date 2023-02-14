package com.fdmgroup.newsManagementServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.newsManagementServer.model.Author;

public interface AuthorDAO extends JpaRepository<Author, Integer> {
	
	Optional<Author> findByEmail(String email);

}
