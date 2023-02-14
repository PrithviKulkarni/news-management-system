package com.fdmgroup.newsManagementServer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fdmgroup.newsManagementServer.model.Users;
import com.fdmgroup.newsManagementServer.repository.UserDAO;
import com.fdmgroup.newsManagementServer.service.UserService;

@SpringBootTest
class UserServiceTests {

	@Autowired
	UserService userService;

	@MockBean
	UserDAO mockUserDAO;

	@MockBean
	Users mockUser;

	@MockBean
	List<Users> mockUsers;

	@Test
	void contextLoads() {
	}
	
	@Test
	void test_getAllUsers_callsFindAllMethod() {
		userService.getAllUsers();
		verify(mockUserDAO).findAll();
	}
	
	@Test
	void test_listByEmail_callsFindByEmailMethod() {
		userService.listByEmail(mockUser.getEmail());
		verify(mockUserDAO).findByEmail(mockUser.getEmail());
	}
	
	@Test
	void test_createUser_callSave_whenUserDoesntExist() {
		when(mockUser.getId()).thenReturn(100);
		when(mockUserDAO.existsById(100)).thenReturn(false);
		userService.createUser(mockUser);
		verify(mockUserDAO, times(1)).save(mockUser);
	}
	
	@Test
	void test_createUser_callsSave_whenUserDoesExist() {
		when(mockUser.getId()).thenReturn(100);
		when(mockUserDAO.existsById(100)).thenReturn(true);
		userService.createUser(mockUser);
		verify(mockUserDAO, times(0)).save(mockUser);
	}
	
	@Test
	void test_getUser_callsGet_whenUserDoesExist() {
		Users expectedUser = new Users();
		when(mockUserDAO.findById(1)).thenReturn(Optional.of(expectedUser));
		Users actualUser = userService.getUser(1);
		verify(mockUserDAO).findById(1);
		assertSame(expectedUser, actualUser);
	}
	
	@Test
	void test_getUser_returnsNull_whenUserDoesNotExist() {
		when(mockUserDAO.findById(2)).thenReturn(Optional.empty());
		Users actualUser = userService.getUser(2);
		verify(mockUserDAO).findById(2);
		assertNull(actualUser);
	}
	
	@Test
	void test_removeUser_callsDeleteById_whenUserExists() {
		when(mockUserDAO.existsById(1)).thenReturn(true);
		userService.removeUser(1);
		verify(mockUserDAO, times(1)).deleteById(1);
	}
	
	@Test
	void test_removeUser_doesntCallDeleteById_whenUserDoesntExists() {
		when(mockUserDAO.existsById(1)).thenReturn(false);
		userService.removeUser(1);
		verify(mockUserDAO, times(0)).deleteById(1);
	}
	
	@Test
	void test_updateUser_callsSave_WhenUserExists() {
		when(mockUser.getId()).thenReturn(1);
		when(mockUserDAO.existsById(1)).thenReturn(true);
		userService.updateUsers(mockUser);
		verify(mockUserDAO, times(1)).save(mockUser);
	}
	
	@Test
	void test_updateUser_doesntCallSave_whenUserDoesntExist() {
		when(mockUserDAO.existsById(1)).thenReturn(false);
		userService.updateUsers(mockUser);
		verify(mockUserDAO, times(0)).save(mockUser);
	}


}
