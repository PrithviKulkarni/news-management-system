package com.fdmgroup.newsManagementServer.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.newsManagementServer.model.Author;
import com.fdmgroup.newsManagementServer.repository.AuthorDAO;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorDAO authorDAO;
	
	private Logger log = LogManager.getLogger(AuthorService.class);

	public List<Author> getAllAuthors() {
		log.traceEntry("getAllAuthors");
		return log.traceExit("getAllAuthors", authorDAO.findAll());
	}

	public Author getAuthor(int id) {
		log.traceEntry("getAuthor");
		Optional<Author> author = authorDAO.findById(id);
		if(author.isPresent()) {
			return log.traceExit("getAuthor", author.get());
		}
		return log.traceExit("getAuthor", null);
	}

	public Author createAuthor(Author author) {
		log.traceEntry("createAuthor");
		if(! authorDAO.existsById(author.getId())) {
			return log.traceExit("createAuthor", authorDAO.save(author));
		}
		return log.traceExit("createAuthor", null);
	}

	public boolean removeAuthor(int id) {
		log.traceEntry("removeAuthor");
		if(authorDAO.existsById(id)) {
			authorDAO.deleteById(id);
			return log.traceExit("removeAuthor", true);
		}
		return log.traceExit("removeAuthor", false);
	}

	public boolean updateAuthor(Author author) {
		log.traceEntry("updateAuthor");
		if(authorDAO.existsById(author.getId())) {
			authorDAO.save(author);
			return log.traceExit("updateAuthor", true);
		}
		return log.traceExit("updateAuthor", false);
	}
	
	public Optional<Author> listAuthorsByUsername(String username){
		log.traceEntry("listAuthorsByUsername");
		return log.traceExit("listAuthorsByUsername", authorDAO.findByEmail(username));
	}

}
