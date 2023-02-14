package com.fdmgroup.newsManagementServer.controller;

import java.net.URI;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.newsManagementServer.model.Article;
import com.fdmgroup.newsManagementServer.service.ArticleService;

@RestController
@RequestMapping("/api/v1/article")
@CrossOrigin(origins="http://localhost:3000")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	private Logger log = LogManager.getLogger(ArticleController.class);
	
	@GetMapping
	public List<Article> getArticles(){
		log.traceEntry("getArticles");
		return log.traceExit("getArticles", articleService.getAllArticles());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Article> getArticle(@PathVariable int id){
		log.traceEntry("getArticle");
		Article article = articleService.getArticle(id);
		
		if(article != null) {
			return log.traceExit("getArticle", ResponseEntity
					.status(HttpStatus.OK)
					.body(article));
		}
		return log.traceExit("getArticle", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable int id){
		log.traceEntry("deleteArticle");
		if(articleService.removeArticle(id)) {
			
			return log.traceExit("deleteArticle", ResponseEntity.status(HttpStatus.OK).build());
		}
		return log.traceExit("deleteArticle", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	public ResponseEntity<Article> createArticle(@RequestBody Article article){
		log.traceEntry("createArticle");
		Article createdArticle = articleService.createArticle(article);
		
		if(createdArticle != null) {
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{articleId}")
					.buildAndExpand(createdArticle.getId())
					.toUri();
			return log.traceExit("createArticle", ResponseEntity
					.created(location)
					.build());
		}
		return log.traceExit("createArticle", ResponseEntity.status(HttpStatus.CONFLICT).build());
	}
	
	@PutMapping
	public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
		log.traceEntry("updateArticle");
		if(articleService.updateArticle(article)) {
			return log.traceExit("updateArticle", ResponseEntity.ok(article));
		}
		return log.traceExit("updateArticle", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/cat/{category}")
	public ResponseEntity<List<Article>> listArticlesByCategory(@PathVariable String category){
		log.traceEntry("listArticlesByCategory");
		List<Article> articleList = articleService.getAllByCategory(category);
		
		if(!articleList.isEmpty()) {
			return log.traceExit("listArticlesByCategory", ResponseEntity
					.status(HttpStatus.OK)
					.body(articleList));
		}
		return log.traceExit("listArticlesByCategory", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	
	
	
	
	
	
	
	
	
	
}
