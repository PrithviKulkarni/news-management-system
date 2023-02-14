package com.fdmgroup.newsManagementServer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fdmgroup.newsManagementServer.controller.UserController;
import com.fdmgroup.newsManagementServer.model.Users;
import com.fdmgroup.newsManagementServer.service.UserService;

@SpringBootTest
class UserControllerTests {

	@Autowired
	UserController userController;

	@MockBean
	UserService mockUserService;

	@MockBean
	Users mockUser;

	@Test
	void contextLoads() {
	}

	@Test
	void test_getAllUsersControllerMethod_callsgetAllUsersServiceMethod() {
		userController.getAllUsers();
		verify(mockUserService).getAllUsers();
	}

	@Test
	void test_getUser_returnsOKHttpStatus_whenUserExists() {
		when(mockUserService.getUser(mockUser.getId())).thenReturn(mockUser);
		ResponseEntity<Users> responseEntity = userController.getUsers(mockUser.getId());
		verify(mockUserService).getUser(mockUser.getId());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockUser, responseEntity.getBody());
	}

	@Test
	void test_getUser_returnsNotFoundHttpStatus_whenUserDoesntExist() {
		when(mockUserService.getUser(mockUser.getId())).thenReturn(null);
		ResponseEntity<Users> responseEntity = userController.getUsers(mockUser.getId());
		verify(mockUserService).getUser(mockUser.getId());
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	void test_createUser_returnsConflictHttpStatus_whenUserAlreadyExists() {
		when(mockUserService.createUser(mockUser)).thenReturn(null);
		ResponseEntity<Users> responseEntity = userController.createUser(mockUser);
		verify(mockUserService).createUser(mockUser);
		assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
	}

	@Test
	void test_createUser_returnsCreatedUser_whenUserDoesntExist() {
		when(mockUserService.createUser(mockUser)).thenReturn(mockUser);
		userController.createUser(mockUser);
		verify(mockUserService).createUser(mockUser);
	}

	@Test
	void test_getUserByEmail_returnsOKHttpStatus_whenUserExists() {
		String email = "test@test.com";
		Users expectedUser = new Users();
		expectedUser.setEmail(email);
		when(mockUserService.listByEmail(email)).thenReturn(Optional.of(expectedUser));
		ResponseEntity<Users> responseEntity = userController.getUsersByEmail(email);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(expectedUser, responseEntity.getBody());
	}

	@Test
	void test_getUserByEmail_returnsNotFoundStatus_whenUserDoesntExist() {
		when(mockUserService.listByEmail(mockUser.getEmail())).thenReturn(Optional.empty());
		ResponseEntity<Users> responseEntity = userController.getUsersByEmail(mockUser.getEmail());
		verify(mockUserService).listByEmail(mockUser.getEmail());
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	void test_deleteUser_returnsHttpStatusOk_whenUserExists() {
		when(mockUserService.removeUser(mockUser.getId())).thenReturn(true);
		ResponseEntity<Void> responseEntity = userController.deleteUser(mockUser.getId());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	void test_deleteUser_returnsHttpStatusNotFound_whenUserDoesntExist() {
		when(mockUserService.removeUser(mockUser.getId())).thenReturn(false);
		ResponseEntity<Void> responseEntity = userController.deleteUser(mockUser.getId());
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	void test_updateUser_returnsHttpStatusOk_whenUserExists() {
		when(mockUserService.updateUsers(mockUser)).thenReturn(true);
		ResponseEntity<Users> responseEntity = userController.updateUser(mockUser);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	void test_updateUser_returnHttpStatusNotFound_whenUserDoesntExist() {
		when(mockUserService.updateUsers(mockUser)).thenReturn(false);
		ResponseEntity<Users> responseEntity = userController.updateUser(mockUser);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

}
