package com.fdmgroup.newsManagementServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fdmgroup.newsManagementServer.model.Article;
import com.fdmgroup.newsManagementServer.model.Users;

class UserModelTest {

	@Test
	void testGettersAndSetters() {
		int id = 1;
		String name = "name";
		String email = "email";
		String password = "password";
		List<Article> likedArticles = null;
		
		Users users = new Users();
		users.setId(id);
		users.setName(name);
		users.setEmail(email);
		users.setPassword(password);
		users.setLikedArticles(likedArticles);
		
		assertEquals(id, users.getId());
		assertEquals(name, users.getName());
		assertEquals(email, users.getEmail());
		assertEquals(password, users.getPassword());
		assertEquals(likedArticles, users.getLikedArticles());
		
		Users users2 = new Users(name, email, password, likedArticles);
	}
}
