package com.fdmgroup.newsManagementServer.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="authors")
public class Author {
	
	@Id
	@Column(name="author_id")
	@SequenceGenerator(name = "AUTHOR_ID_GEN", sequenceName = "author_id_seq", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_ID_GEN")
	private int id;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	@OneToMany
	@JoinColumn(name="FK_AUTHOR_ID")
	private List<Article> articlesList;
	@Column(name="twitter_link")
	private String twitterLink;
	
	public Author() {
		super();
	}

	public Author(String name, String password, String email, List<Article> articlesList, String twitterLink) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.articlesList = articlesList;
		this.twitterLink = twitterLink;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Article> getArticlesList() {
		return articlesList;
	}

	public void setArticlesList(List<Article> articlesList) {
		this.articlesList = articlesList;
	}

	public String getTwitterLink() {
		return twitterLink;
	}

	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}
	
}

