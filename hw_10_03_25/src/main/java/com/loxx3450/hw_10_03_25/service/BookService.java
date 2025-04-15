package com.loxx3450.hw_10_03_25.service;

import com.loxx3450.hw_10_03_25.filter.BookFilter;
import com.loxx3450.hw_10_03_25.model.Book;
import com.loxx3450.hw_10_03_25.util.HibernateUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BookService {
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public List<Book> getAllBooks() {
		try (Session session = sessionFactory.openSession()) {
			List<Book> books = session.createQuery("FROM Book", Book.class).list();
			return books;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public Book getBookById(int id) {
		try (Session session = sessionFactory.openSession()) {
			Book book = session.get(Book.class, id);
			return book;
		} catch (Exception e) {
			return null;
		}
	}

	public void addBook(Book book) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.persist(book);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBookById(int id) {
		try (Session session = sessionFactory.openSession()) {
			Book book = session.get(Book.class, id);

			session.beginTransaction();
			session.delete(book);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateBook(int id, Book book) {
		try (Session session = sessionFactory.openSession()) {
			Book bookToUpdate = session.get(Book.class, id);

			bookToUpdate.setTitle(book.getTitle());
			bookToUpdate.setAuthor(book.getAuthor());
			bookToUpdate.setDescription(book.getDescription());
			bookToUpdate.setPublishedIn(book.getPublishedIn());
			bookToUpdate.setGenre(book.getGenre());
			bookToUpdate.setPagesCount(book.getPagesCount());

			session.beginTransaction();
			session.merge(bookToUpdate);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Book> findBooks(BookFilter filter) {
		StringBuilder query = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		query.append("FROM Book WHERE TRUE");

		if (filter.getTitle().isPresent()) {
			query.append(" AND LOWER(title) = LOWER(:title)");
			params.put("title", filter.getTitle().get());
		}

		if (filter.getAuthor().isPresent()) {
			query.append(" AND LOWER(author) = LOWER(:author)");
			params.put("author", filter.getAuthor().get());
		}

		if (filter.getPublishedIn().isPresent()) {
			query.append(" AND published_in = :publishedIn");
			params.put("publishedIn", filter.getPublishedIn().get());
		}

		if (filter.getGenre().isPresent()) {
			query.append(" AND LOWER(genre) = LOWER(:genre)");
			params.put("genre", filter.getGenre().get());
		}

		if (filter.getPagesCount().isPresent()) {
			query.append(" AND pages_count = :pagesCount");
			params.put("pagesCount", filter.getPagesCount().get());
		}

		if (filter.getPartOfDescription().isPresent()) {
			query.append(" AND LOWER(description) LIKE LOWER(:descPart)");
			params.put("descPart", "%" + filter.getPartOfDescription().get() + "%");
		}

		try (Session session = sessionFactory.openSession()) {
			var hibernateQuery = session.createQuery(query.toString(), Book.class);
			params.forEach(hibernateQuery::setParameter);

			return hibernateQuery.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}
}
