package com.fdmgroup.newsManagementServer.service;

import java.util.List;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.newsManagementServer.model.Users;
import com.fdmgroup.newsManagementServer.repository.UserDAO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	Logger log = LogManager.getLogger(UserService.class);

	public List<Users> getAllUsers() {
		log.traceEntry("getAllUsers");
		return log.traceExit("getAllUsers", userDAO.findAll());
	}

	public Users createUser(Users user) {
		log.traceEntry("createUser");
		if (!userDAO.existsById(user.getId())) {
			return log.traceExit("createUser", userDAO.save(user));
		}
		return log.traceExit("createUser", null);
	}
	
	public Users getUser(int userId) {
		log.traceEntry("getUser");
		Optional<Users> userOptional = userDAO.findById(userId);
		
		if (userOptional.isPresent()) {
			return log.traceExit("getUser", userOptional.get());
		}
		return log.traceExit("getUser", null);
	}

	public Optional<Users> listByEmail(String email) {
		log.traceEntry("listByEmail");
		return log.traceExit("listByEmail", userDAO.findByEmail(email));
	}
	
	public boolean removeUser(int id) {
		log.traceEntry("removeUser");
		if (userDAO.existsById(id)) {
			userDAO.deleteById(id);
			return log.traceExit("removeUser", true);
		}
		return log.traceExit("removeUser", false);
	}
	
	public boolean updateUsers(Users users) {
		log.traceEntry("updateUsers");
		if (userDAO.existsById(users.getId())) {
			userDAO.save(users);
			return log.traceExit("updateUsers", true);
		}
		return log.traceExit("updateUsers", false);
	}

}
