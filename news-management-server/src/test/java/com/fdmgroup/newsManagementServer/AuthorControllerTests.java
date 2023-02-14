package com.fdmgroup.newsManagementServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fdmgroup.newsManagementServer.controller.AuthorController;
import com.fdmgroup.newsManagementServer.model.Author;
import com.fdmgroup.newsManagementServer.service.AuthorService;

@SpringBootTest
public class AuthorControllerTests {
	
	@Autowired
	AuthorController authorController;
	
	@MockBean
	AuthorService mockAuthorService;
	
	@MockBean 
	Author mockAuthor;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void test_getAuthors_callsGetAllAuthors() {
		authorController.getAuthors();
		verify(mockAuthorService).getAllAuthors();
	}
	
	@Test
	void test_getAuthor_returnsHttpStatusOKAndReturnsAuthor_whenAuthorExists() {
		when(mockAuthorService.getAuthor(mockAuthor.getId())).thenReturn(mockAuthor);
		
		ResponseEntity<Author> response = authorController.getAuthor(mockAuthor.getId());
		
		verify(mockAuthorService).getAuthor(mockAuthor.getId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockAuthor, response.getBody());
	}
	
	@Test
	void test_getAuthor_returnsHttpStatusNOT_FOUND_whenAuthorDoesntExist() {
		when(mockAuthorService.getAuthor(mockAuthor.getId())).thenReturn(null);
		
		ResponseEntity<Author> response = authorController.getAuthor(mockAuthor.getId());
		
		verify(mockAuthorService).getAuthor(mockAuthor.getId());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void test_createAuthor_returnsAuthor_whenAuthorDoesntExist() {
		when(mockAuthorService.createAuthor(mockAuthor)).thenReturn(mockAuthor);
		authorController.createAuthor(mockAuthor);
		verify(mockAuthorService).createAuthor(mockAuthor);
	}
	
	@Test
	void test_createAuthor_returnsHttpStatusCONFLICT_whenAuthorAlreadyExists() {
		when(mockAuthorService.createAuthor(mockAuthor)).thenReturn(null);
		
		ResponseEntity<Author> response = authorController.createAuthor(mockAuthor);
		
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}
	
	@Test
	void test_deleteAuthor_returnsHttpStatusOK_whenAuthorIdExists() {
		when(mockAuthorService.removeAuthor(mockAuthor.getId())).thenReturn(true);
		ResponseEntity<Void> response = authorController.deleteAuthor(mockAuthor.getId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void test_deleteAuthor_returnsHttpStatusNOT_FOUND_whenAuthorIdDoesntExist() {
		when(mockAuthorService.removeAuthor(mockAuthor.getId())).thenReturn(false);
		ResponseEntity<Void> response = authorController.deleteAuthor(mockAuthor.getId());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void test_updateAuthor_returnsHttpStatusOK_whenAuthorExists() {
		when(mockAuthorService.updateAuthor(mockAuthor)).thenReturn(true);
		ResponseEntity<Author> response = authorController.updateAuthor(mockAuthor);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void test_updateAuthor_returnsHttpStatusNOT_FOUND_whenAuthorDoesntExist() {
		when(mockAuthorService.updateAuthor(mockAuthor)).thenReturn(false);
		ResponseEntity<Author> response = authorController.updateAuthor(mockAuthor);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void test_getAuthorByEmail_returnsAuthor_whenEmailExists() {
		String email = "test@test.com";
		Author expectedAuthor = new Author();
		expectedAuthor.setEmail(email);
		when(mockAuthorService.listAuthorsByUsername(email)).thenReturn(Optional.of(expectedAuthor));
		ResponseEntity<Author> response = authorController.getAuthorByEmail(email);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedAuthor, response.getBody());
	}
	
	@Test
	void test_getAuthorByEmail_returnsHttpStatusNOT_FOUND_whenEmailDoesntExist() {
		when(mockAuthorService.listAuthorsByUsername("email")).thenReturn(Optional.empty());
		ResponseEntity<Author> response = authorController.getAuthorByEmail("email");
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
}
