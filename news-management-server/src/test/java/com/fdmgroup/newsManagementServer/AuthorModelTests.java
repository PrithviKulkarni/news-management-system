package com.fdmgroup.newsManagementServer;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.fdmgroup.newsManagementServer.model.Article;
import com.fdmgroup.newsManagementServer.model.Author;

public class AuthorModelTests {
	
	@Test
	void testGettersAndSetters() {
		int id = 1;
		String name = "name";
		String email = "email";
		String password = "password";
		List<Article> articlesList = new ArrayList<>();
		String twitterLink = "link";
		
		Author author = new Author();
		
		author.setId(id);
		author.setName(name);
		author.setEmail(email);
		author.setPassword(password);
		author.setArticlesList(articlesList);
		author.setTwitterLink(twitterLink);
		
		assertEquals(id, author.getId());
		assertEquals(name, author.getName());
		assertEquals(email, author.getEmail());
		assertEquals(password, author.getPassword());
		assertEquals(articlesList, author.getArticlesList());
		assertEquals(twitterLink, author.getTwitterLink());
	}
	
	@Test
	void testConstructor() {
		String name = "name";
		String email = "email";
		String password = "password";
		List<Article> articlesList = new ArrayList<>();
		String twitterLink = "link";
		
		Author author = new Author(name, password, email, articlesList, twitterLink);
		
		assertEquals(name, author.getName());
		assertEquals(email, author.getEmail());
		assertEquals(password, author.getPassword());
		assertEquals(articlesList, author.getArticlesList());
		assertEquals(twitterLink, author.getTwitterLink());
	}
	
}
