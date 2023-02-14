package com.fdmgroup.newsManagementServer;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fdmgroup.newsManagementServer.controller.ArticleController;
import com.fdmgroup.newsManagementServer.model.Article;
import com.fdmgroup.newsManagementServer.service.ArticleService;

@SpringBootTest
class ArticleControllerTest {

	@Autowired
	ArticleController articleController;
	
	@MockBean
	ArticleService mockService;
	
	@MockBean
	Article mockArticle;
	
	@MockBean
	List<Article> mockList;

	@Test
	void test_getArticle_callsGetAllAtricles() {
		articleController.getArticles();
		verify(mockService).getAllArticles();
	}
	
	@Test
	void test_getArticle_returnsOkStatus_andArticle_whenArticleExists() {
		when(mockService.getArticle(mockArticle.getId())).thenReturn(mockArticle);
		
		ResponseEntity<Article> response = articleController.getArticle(mockArticle.getId());
		
		verify(mockService).getArticle(mockArticle.getId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockArticle, response.getBody());
	}
	
	@Test 
	void test_getArticle_returnsNotFoundStatus_whenArticleDoesNotExist() {
		when(mockService.getArticle(mockArticle.getId())).thenReturn(null);
		
		ResponseEntity<Article> response = articleController.getArticle(mockArticle.getId());
		
		verify(mockService).getArticle(mockArticle.getId());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void test_deleteArticle_deletesArticle_andReturnsOkStatus_whenArtilceExists() {
		when(mockService.removeArticle(mockArticle.getId())).thenReturn(true);
		
		ResponseEntity<Void> response = articleController.deleteArticle(mockArticle.getId());
		
		verify(mockService).removeArticle(mockArticle.getId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void test_deleteArticle_returnsNotFoundStaus_whenArtilceDoesNotExist() {
		when(mockService.removeArticle(mockArticle.getId())).thenReturn(false);
		
		ResponseEntity<Void> response = articleController.deleteArticle(mockArticle.getId());
		
		verify(mockService).removeArticle(mockArticle.getId());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void test_createArticle_returnsCompiledUriBasedOnArticleId() {
		when(mockService.createArticle(mockArticle)).thenReturn(mockArticle);
		articleController.createArticle(mockArticle);
		verify(mockService).createArticle(mockArticle);
	}
	
	@Test
	void test_createArticle_returnsConflictStatus_whenArticleWithSameTitleExistsAlready() {
		when(mockService.createArticle(mockArticle)).thenReturn(null);
		
		ResponseEntity<Article> response = articleController.createArticle(mockArticle);
		
		verify(mockService).createArticle(mockArticle);
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}

	@Test
	void test_updateArticle_returnsOkStatus_whenArticleExists() {
		when(mockService.updateArticle(mockArticle)).thenReturn(true);
		
		ResponseEntity<Article> response = articleController.updateArticle(mockArticle);
		
		verify(mockService).updateArticle(mockArticle);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void test_updateArticle_returnsNotFoundStatus_whenArticleDoesNotExists() {
		when(mockService.updateArticle(mockArticle)).thenReturn(false);
		
		ResponseEntity<Article> response = articleController.updateArticle(mockArticle);
		
		verify(mockService).updateArticle(mockArticle);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	
	@Test
	void test_listArticlesByCategory_returnsStatusOkandList_whenExistingCategoryIsPassedIn() {
		mockList.add(mockArticle);
		when(mockService.getAllByCategory("Sports")).thenReturn(mockList);
		
		ResponseEntity<List<Article>> response = articleController.listArticlesByCategory("Sports");
		
		verify(mockService).getAllByCategory("Sports");
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void test_listArticlesByCategory_returnsStatusNotFound_whenCategoryDoesNotExist() {
		when(mockService.getAllByCategory("Tech")).thenReturn(mockList);
		when(mockList.isEmpty()).thenReturn(true);
		
		ResponseEntity<List<Article>> response = articleController.listArticlesByCategory("Tech");
		
		verify(mockService).getAllByCategory("Tech");
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
