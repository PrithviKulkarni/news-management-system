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

import com.fdmgroup.newsManagementServer.model.Author;
import com.fdmgroup.newsManagementServer.repository.AuthorDAO;
import com.fdmgroup.newsManagementServer.service.AuthorService;

@SpringBootTest
public class AuthorServiceTests {
	
	@Autowired
	AuthorService authorService;
	
	@MockBean
	AuthorDAO mockAuthorDAO;
	
	@MockBean
	Author mockAuthor;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void test_getAllAuthors_callsFindAll() {
		authorService.getAllAuthors();
		verify(mockAuthorDAO).findAll();
	}
	
	@Test
	void test_getAuthor_callsFindById() {
		authorService.getAuthor(1);
		verify(mockAuthorDAO).findById(1);
	}
	
	@Test
	void test_getAuthor_returnsArticle_whenAuthorIdExists() {
		Author expectedAuthor = new Author();
		when(mockAuthorDAO.findById(1)).thenReturn(Optional.of(expectedAuthor));
		Author actualAuthor = authorService.getAuthor(1);
		assertSame(expectedAuthor, actualAuthor);
	}
	
	@Test
	void test_getAuthor_ReturnsNull_whenAuthorIdDoesNotExist() {
		when(mockAuthorDAO.findById(2)).thenReturn(Optional.empty());
		Author actualAuthor = authorService.getAuthor(2);
		verify(mockAuthorDAO).findById(2);
		assertNull(actualAuthor);
		}
	
	@Test
	void test_createAuthor_callsSavePassingInAuthor() {
		when(mockAuthorDAO.existsById(mockAuthor.getId())).thenReturn(false);
		authorService.createAuthor(mockAuthor);
		verify(mockAuthorDAO, times(1)).save(mockAuthor);
	}
	
	@Test
	void test_createAuthor_doesntCallSave_whenAuthorIdAlreadyExists() {
		when(mockAuthorDAO.existsById(mockAuthor.getId())).thenReturn(true);
		authorService.createAuthor(mockAuthor);
		verify(mockAuthorDAO, times(0)).save(mockAuthor);
	}
	
	@Test
	void test_removeAuthor_callsDeleteById_whenAuthorIdExists() {
		when(mockAuthorDAO.existsById(1)).thenReturn(true);
		authorService.removeAuthor(1);
		verify(mockAuthorDAO, times(1)).deleteById(1);
	}
	
	@Test
	void test_removeAuthor_doesntCallDeleteById_whenAuthorIdDoesntExist() {
		when(mockAuthorDAO.existsById(1)).thenReturn(false);
		authorService.removeAuthor(1);
		verify(mockAuthorDAO, times(0)).deleteById(1);
	}
	
	@Test
	void test_updateAuthor_callsSave_whenAuthorExists() {
		when(mockAuthor.getId()).thenReturn(1);
		when(mockAuthorDAO.existsById(1)).thenReturn(true);
		authorService.updateAuthor(mockAuthor);
		verify(mockAuthorDAO, times(1)).save(mockAuthor);
	}
	
	@Test
	void test_updateAuthor_doesntCallSave_whenAuthorDoesntExist() {
		when(mockAuthorDAO.existsById(1)).thenReturn(false);
		authorService.updateAuthor(mockAuthor);
		verify(mockAuthorDAO, times(0)).save(mockAuthor);
	}
	
	@Test
	void test_listAuthorsByUsername_callsFindByEmail() {
		authorService.listAuthorsByUsername("username");
		verify(mockAuthorDAO).findByEmail("username");
	}
	
}
