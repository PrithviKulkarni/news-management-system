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

import com.fdmgroup.newsManagementServer.model.Author;
import com.fdmgroup.newsManagementServer.service.AuthorService;

@RestController
@RequestMapping("/api/v1/author")
@CrossOrigin(origins="http://localhost:3000")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	private Logger log = LogManager.getLogger(AuthorController.class);
	
	@GetMapping
	public List<Author> getAuthors(){
		log.traceEntry("getAuthors");
		return log.traceExit("getAuthors", authorService.getAllAuthors());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthor(@PathVariable int id){
		log.traceEntry("getAuthor");
		Author author = authorService.getAuthor(id);
		
		if(author != null) {
			return log.traceExit("getAuthor", ResponseEntity
					.status(HttpStatus.OK)
					.body(author));
		}
		return log.traceExit("getAuthor - not found", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping
	public ResponseEntity<Author> createAuthor(@RequestBody Author author){
		log.traceEntry("createAuthor");
		Author createdAuthor = authorService.createAuthor(author);
		
		if(createdAuthor != null) {
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{AuthorId}")
					.buildAndExpand(createdAuthor.getId())
					.toUri();
			return log.traceExit("createAuthor", ResponseEntity
					.created(location)
					.build());
		}
		return log.traceExit("createAuthor - not found", ResponseEntity.status(HttpStatus.CONFLICT).build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable int id){
		log.traceEntry("deleteAuthor");
		if (authorService.removeAuthor(id)) {
			return log.traceExit("deleteAuthor", ResponseEntity.status(HttpStatus.OK).build());
		}
		return log.traceExit("deleteAuthor - not found", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PutMapping
	public ResponseEntity<Author> updateAuthor(@RequestBody Author author){
		log.traceEntry("updateAuthor");
		if(authorService.updateAuthor(author)) {
			return log.traceExit("updateAuthor", ResponseEntity.ok(author));
		}
		return log.traceExit("updateAuthor - not found", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Author> getAuthorByEmail(@PathVariable("email") String email){
		log.traceEntry("getAuthorByEmail");
		Optional<Author> author = authorService.listAuthorsByUsername(email);
		
		if(author.isPresent()) {
			return log.traceExit("getAuthorByEmail", ResponseEntity
					.status(HttpStatus.OK)
					.body(author.get()));
		}
		return log.traceExit("getAuthorByEmail - not found", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	

}
