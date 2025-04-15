package com.loxx3450.hw_10_03_25;

import com.loxx3450.hw_10_03_25.filter.BookFilter;
import com.loxx3450.hw_10_03_25.model.Book;
import com.loxx3450.hw_10_03_25.service.BookService;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw100325Application {

	public static void main(String[] args) {
		var service = new BookService();

		// Task 1
//		for (Book book : service.getAllBooks()) {
//			System.out.println(book);
//			System.out.println("\n");
//		}
//		System.out.println("\n\n\n");
//
//		var book = service.getBookById(10);
//
//		System.out.println(book);
//		System.out.println("\n\n\n");

		// Task 2
//		Book newBook = new Book();
//
//		newBook.setTitle("New Title");
//		newBook.setAuthor("New Author");
//		newBook.setPublishedIn(2025);
//		newBook.setGenre("New Genre");
//		newBook.setPagesCount(1000);
//		newBook.setDescription("New Description");
//
//		service.addBook(newBook);
//
//		System.out.println(service.getAllBooks().getLast());

		// Task 3
//		service.deleteBookById(10);
//
//		Book book = service.getBookById(10);
//		System.out.println(book == null);

		// Task 4
//		var oldBook = service.getBookById(50);
//
//		Book newBook = new Book();
//
//		newBook.setTitle("Updated Title");
//		newBook.setAuthor("Updated Author");
//		newBook.setPublishedIn(2025);
//		newBook.setGenre("Updated Genre");
//		newBook.setPagesCount(1000);
//		newBook.setDescription("Updated Description");
//
//		service.updateBook(50, newBook);
//
//		var updatedBook = service.getBookById(50);
//
//		System.out.println(oldBook);
//		System.out.println(updatedBook);

		// Task 5
//		BookFilter filter = new BookFilter();
//
//		filter.setPartOfDescription(Optional.of("new"));
//
//		for (Book book : service.findBooks(filter)) {
//			System.out.println(book);
//			System.out.println("\n");
//		}
//		System.out.println("\n\n\n");



//		BookFilter bookFilter = new BookFilter();
//		bookFilter.setGenre(Optional.of("fantasy"));
//
//		for (var book : service.findBooks(bookFilter)) {
//			System.out.println(book);
//			System.out.println("\n");
//		}
//		System.out.println("\n\n\n");
	}

}
