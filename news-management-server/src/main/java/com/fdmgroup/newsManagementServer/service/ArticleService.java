package com.fdmgroup.newsManagementServer.service;

import java.util.List;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.newsManagementServer.model.Article;
import com.fdmgroup.newsManagementServer.repository.ArticleDAO;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDAO articleDAO;
	
	private Logger log = LogManager.getLogger(ArticleService.class);
	
	public List<Article> getAllArticles(){
		log.traceEntry("getAllArticles");
		return log.traceExit("getAllArticles", articleDAO.findAll());
	}
	
	public Article createArticle(Article article) {
		log.traceEntry("createArticles");
		Article existingArticle = articleDAO.findByTitle(article.getTitle());
		if (existingArticle == null) {
			return log.traceExit("createArticles", articleDAO.save(article));
		}
		return log.traceExit("createArticles", null);
	}
	
	public Article getArticle(int articleId) {
		log.traceEntry("getArticle");
		Optional<Article> article = articleDAO.findById(articleId);
		
		if (article.isPresent()) {
			return log.traceExit("getArticle", article.get());
		}
		return log.traceExit("getArticle", null);
	}
	
	public boolean updateArticle(Article article) {
		log.traceEntry("updateArticle");
		if(articleDAO.existsById(article.getId())) {
			articleDAO.save(article);
			return log.traceExit("updateArticle - saved", true);
		}
		return log.traceExit("updateArticle - not found", false);
	}
	
	public boolean removeArticle(int articleId) {
		log.traceEntry("removeArticle");
		if (articleDAO.existsById(articleId)) {
			articleDAO.deleteById(articleId);
			return log.traceExit("removeArticle", true);
		}
		return log.traceExit("removeArticle", false);
	}
	
	public List<Article> getAllByCategory(String category){
		log.traceEntry("getAllByCategory");
		return log.traceExit("getAllByCategory", articleDAO.findByCategoryIgnoreCase(category));
	}

}
