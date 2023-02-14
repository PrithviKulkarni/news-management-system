package com.fdmgroup.newsManagementServer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="articles")
public class Article {
	@Id
	@Column(name="article_id")
	@SequenceGenerator(name = "ARTICLE_ID_GEN", sequenceName = "article_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_ID_GEN")
	private int id;
	@Column(unique = true)
	private String title;
	@Column(name = "short_description")
	private String shortDescription;
	@Column(name = "full_story", columnDefinition = "VARCHAR(MAX)")
	private String fullStory;
	@Temporal(TemporalType.DATE)
	@Column(name = "publish_date")
	private Date publishDate;
	private String category;
	@Column(name = "image")
	private String coverPhotoURL;
	@Column(name = "author_name")
	private String authorName;

	
	
	public Article() {
		super();
	}


	public Article(String title, String shortDescription, String fullStory, Date publishDate,
			String category, String coverPhotoURL, String authorName) {
		super();
		this.title = title;
		this.shortDescription = shortDescription;
		this.fullStory = fullStory;
		this.publishDate = publishDate;
		this.category = category;
		this.coverPhotoURL = coverPhotoURL;
		this.authorName = authorName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}


	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}


	public String getFullStory() {
		return fullStory;
	}


	public void setFullStory(String fullStory) {
		this.fullStory = fullStory;
	}


	public Date getPublishDate() {
		return publishDate;
	}


	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getCoverPhotoURL() {
		return coverPhotoURL;
	}


	public void setCoverPhotoURL(String coverPhotoURL) {
		this.coverPhotoURL = coverPhotoURL;
	}


	public String getAuthorName() {
		return authorName;
	}


	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
