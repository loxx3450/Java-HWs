package com.loxx3450.hw_10_03_25.filter;

import jakarta.persistence.Column;
import java.util.Optional;

public class BookFilter {
	private Optional<String> title = Optional.empty();
	private Optional<String> author = Optional.empty();
	private Optional<Integer> publishedIn = Optional.empty();
	private Optional<String> genre = Optional.empty();
	private Optional<Integer> pagesCount = Optional.empty();
	private Optional<String> partOfDescription = Optional.empty();

	public Optional<String> getTitle() {
		return title;
	}

	public void setTitle(Optional<String> title) {
		this.title = title;
	}

	public Optional<String> getAuthor() {
		return author;
	}

	public void setAuthor(Optional<String> author) {
		this.author = author;
	}

	public Optional<Integer> getPublishedIn() {
		return publishedIn;
	}

	public void setPublishedIn(Optional<Integer> publishedIn) {
		this.publishedIn = publishedIn;
	}

	public Optional<String> getGenre() {
		return genre;
	}

	public void setGenre(Optional<String> genre) {
		this.genre = genre;
	}

	public Optional<Integer> getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(Optional<Integer> pagesCount) {
		this.pagesCount = pagesCount;
	}

	public Optional<String> getPartOfDescription() {
		return partOfDescription;
	}

	public void setPartOfDescription(Optional<String> partOfDescription) {
		this.partOfDescription = partOfDescription;
	}
}
