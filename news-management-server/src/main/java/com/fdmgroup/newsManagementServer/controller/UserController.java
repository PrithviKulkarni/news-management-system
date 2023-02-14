package com.fdmgroup.newsManagementServer.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.fdmgroup.newsManagementServer.model.Users;
import com.fdmgroup.newsManagementServer.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;
	
	Logger log = LogManager.getLogger(UserController.class);

	@GetMapping
	public List<Users> getAllUsers() {
		log.traceEntry("getAllUsers");
		return log.traceExit("getAllUsers", userService.getAllUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Users> getUsers(@PathVariable int id) {
		log.traceEntry("getUsers");
		Users user = userService.getUser(id);

		if (user != null) {
			return log.traceExit("getUsers", ResponseEntity.status(HttpStatus.OK).body(user));
		}
		return log.traceExit("getUsers", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	public ResponseEntity<Users> createUser(@RequestBody Users users) {
		log.traceEntry("createUser");
		Users createdUser = userService.createUser(users);

		if (createdUser != null) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
					.buildAndExpand(createdUser.getId()).toUri();
			return log.traceExit("createUser", ResponseEntity.created(location).build());
		}
		return log.traceExit("createUser", ResponseEntity.status(HttpStatus.CONFLICT).build());
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Users> getUsersByEmail(@PathVariable("email") String email) {
		log.traceEntry("getUsersByEmail");
		Optional<Users> user = userService.listByEmail(email);

		if (user.isPresent()) {
			return log.traceExit("getUsersByEmail", ResponseEntity.status(HttpStatus.OK).body(user.get()));
		}
		return log.traceExit("getUsersByEmail", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		log.traceEntry("deleteUser");
		if (userService.removeUser(id)) {
			return log.traceExit("deleteUser", ResponseEntity.status(HttpStatus.OK).build());
		}
		return log.traceExit("deleteUser", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PutMapping
	public ResponseEntity<Users> updateUser(@RequestBody Users users) {
		log.traceEntry("updateUser");
		if (userService.updateUsers(users)) {
			return log.traceExit("updateUser", ResponseEntity.ok(users));
		}
		return log.traceExit("updateUser", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
