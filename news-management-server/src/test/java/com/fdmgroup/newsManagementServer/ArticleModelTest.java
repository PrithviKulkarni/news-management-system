package com.fdmgroup.newsManagementServer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fdmgroup.newsManagementServer.model.Article;

class ArticleModelTest {

	 @Test
	 void testGettersAndSetters() {
		int id = 1;
	    String title = "title";
	    String shortDescription = "shortDescription";
	    String fullStory = "fullStory";
	    Date publishDate = new Date();
	    String category = "category";
	    String coverPhotoURL = "coverPhotoURL";
	    String authorName = "authorName";
	    
	    Article article = new Article();
	    article.setId(id);
	    article.setTitle(title);
	    article.setShortDescription(shortDescription);
	    article.setFullStory(fullStory);
	    article.setPublishDate(publishDate);
	    article.setCategory(category);
	    article.setCoverPhotoURL(coverPhotoURL);
	    article.setAuthorName(authorName);
	    
	    assertEquals(id, article.getId());
	    assertEquals(title, article.getTitle());
	    assertEquals(shortDescription, article.getShortDescription());
	    assertEquals(fullStory, article.getFullStory());
	    assertEquals(publishDate, article.getPublishDate());
	    assertEquals(category, article.getCategory());
	    assertEquals(coverPhotoURL, article.getCoverPhotoURL());
	    assertEquals(authorName, article.getAuthorName());
	  }
	 
	 @Test
	 void testConstructor() {
		 String title = "title";
		 String shortDescription = "shortDescription";
		 String fullStory = "fullStory";
		 Date publishDate = new Date();
		 String category = "category";
		 String coverPhotoURL = "coverPhotoURL";
		 String authorName = "authorName";
		 
		 Article article = new Article(title, shortDescription, fullStory, publishDate, category, coverPhotoURL, authorName);
		 
		 assertEquals(title, article.getTitle());
		 assertEquals(shortDescription, article.getShortDescription());
		 assertEquals(fullStory, article.getFullStory());
		 assertEquals(publishDate, article.getPublishDate());
		 assertEquals(category, article.getCategory());
		 assertEquals(coverPhotoURL, article.getCoverPhotoURL());
		 assertEquals(authorName, article.getAuthorName());
	 }

}
