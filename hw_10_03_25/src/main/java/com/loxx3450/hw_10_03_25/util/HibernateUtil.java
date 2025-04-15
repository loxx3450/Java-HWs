package com.loxx3450.hw_10_03_25.util;

import com.loxx3450.hw_10_03_25.model.Book;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		return new Configuration()
			.configure()
			.addAnnotatedClass(Book.class)
			.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		sessionFactory.close();
	}
}
