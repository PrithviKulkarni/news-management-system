package com.fdmgroup.newsManagementServer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fdmgroup.newsManagementServer.model.Article;
import com.fdmgroup.newsManagementServer.repository.ArticleDAO;
import com.fdmgroup.newsManagementServer.service.ArticleService;

@SpringBootTest
class ArticleServiceTest {

	@Autowired 
	ArticleService articleService;
	
	@MockBean 
	ArticleDAO mockArticleDAO;
	
	@MockBean
	Article mockArticle;

	@Test
	void test_getAllArticles_callsDAOfindAll() {
		articleService.getAllArticles();
		verify(mockArticleDAO).findAll();
	}
	
	@Test
	void test_getArticle_ReturnsArticle_whenArticleIdExists() {
		Article expectedArticle = new Article();
		when(mockArticleDAO.findById(1)).thenReturn(Optional.of(expectedArticle));
		
		Article actualArticle = articleService.getArticle(1);
		
		verify(mockArticleDAO).findById(1);
		assertSame(expectedArticle, actualArticle);
	}
	
	@Test
	void test_getArticle_ReturnsNull_whenArticleIdDoesNotExist() {
		when(mockArticleDAO.findById(2)).thenReturn(Optional.empty());
		
		Article actualArticle = articleService.getArticle(2);
		
		verify(mockArticleDAO).findById(2);
		assertNull(actualArticle);
	}
	
	@Test
	void test_createArticle_callsSave_whenArticleWithTitleDoesNotExists() {
		when(mockArticleDAO.findByTitle(mockArticle.getTitle())).thenReturn(null);
		articleService.createArticle(mockArticle);
		verify(mockArticleDAO).save(mockArticle);
	}
	
	@Test
	void test_createArticle_returnsNull_whenArticleWithTitleExists() {
		when(mockArticleDAO.findByTitle(mockArticle.getTitle())).thenReturn(mockArticle);
		articleService.createArticle(mockArticle);
		verify(mockArticleDAO,times(0)).save(mockArticle);
	}
	
	@Test
	void test_updateArticle_callsSaveAndReturnsTrue_whenArticleExists() {
		when(mockArticleDAO.existsById(mockArticle.getId())).thenReturn(true);
		Boolean result = articleService.updateArticle(mockArticle);
		verify(mockArticleDAO).existsById(mockArticle.getId());
		verify(mockArticleDAO).save(mockArticle);
		assertTrue(result);
	}
	
	@Test
	void test_updateArticle_returnsFalse_whenArticleDoesNotExist() {
		when(mockArticleDAO.existsById(mockArticle.getId())).thenReturn(false);
		Boolean result = articleService.updateArticle(mockArticle);
		verify(mockArticleDAO).existsById(mockArticle.getId());
		verify(mockArticleDAO,times(0)).save(mockArticle);
		assertFalse(result);
	}
	
	@Test
	void test_removeArticle_callsDeeleteById_andReturnsTrue_whenArticleExists() {
		when(mockArticleDAO.existsById(mockArticle.getId())).thenReturn(true);
		Boolean result = articleService.removeArticle(mockArticle.getId());
		verify(mockArticleDAO).existsById(mockArticle.getId());
		verify(mockArticleDAO).deleteById(mockArticle.getId());
		assertTrue(result);
	}
	
	@Test
	void test_removeArticle_returnsFalse_whenArticleDoesNotExist() {
		when(mockArticleDAO.existsById(mockArticle.getId())).thenReturn(false);
		Boolean result = articleService.removeArticle(mockArticle.getId());
		verify(mockArticleDAO).existsById(mockArticle.getId());
		verify(mockArticleDAO,times(0)).deleteById(mockArticle.getId());
		assertFalse(result);
	}
	
	@Test
	void test_getAllByCategory_callsDAOfindByCategoryIgnoreCase() {
		mockArticle.setCategory("Sports");
		articleService.getAllByCategory("Sports");
		verify(mockArticleDAO).findByCategoryIgnoreCase("Sports");
	}
	

}
