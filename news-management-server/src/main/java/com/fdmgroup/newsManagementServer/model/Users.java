package com.fdmgroup.newsManagementServer.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "Users")
public class Users {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int id;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	@ManyToMany
	@JoinTable(name = "users_articles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "article_id"))
	private List<Article> likedArticles;

	public Users(String name, String email, String password, List<Article> likedArticles) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.likedArticles = likedArticles;
	}

	public Users() {
		super();
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

	public List<Article> getLikedArticles() {
		return likedArticles;
	}

	public void setLikedArticles(List<Article> likedArticles) {
		this.likedArticles = likedArticles;
	}

}
