package com.loxx3450.hw_10_03_25.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String author;
	@Column(name = "published_in")
	private int publishedIn;
	private String genre;
	@Column(name = "pages_count")
	private int pagesCount;
	private String description;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublishedIn() {
		return publishedIn;
	}

	public void setPublishedIn(int publishedIn) {
		this.publishedIn = publishedIn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book{" +
			"id=" + id +
			", title='" + title + '\'' +
			", author='" + author + '\'' +
			", publishedIn=" + publishedIn +
			", genre='" + genre + '\'' +
			", pagesCount=" + pagesCount +
			", description='" + description + '\'' +
			'}';
	}
}
