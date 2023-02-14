package com.fdmgroup.newsManagementServer.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.newsManagementServer.model.Article;

public interface ArticleDAO extends JpaRepository<Article, Integer> {
	
	Article findByTitle(String title);
	
	List<Article> findByCategoryIgnoreCase(String category);

}
