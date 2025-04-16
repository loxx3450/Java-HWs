package com.loxx3450.hw_14_03_25.util;

import com.loxx3450.hw_14_03_25.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		return new Configuration()
			.configure()
			.addAnnotatedClass(User.class)
			.addAnnotatedClass(Client.class)
			.addAnnotatedClass(Landlord.class)
			.addAnnotatedClass(Flat.class)
			.addAnnotatedClass(Rent.class)
			.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		sessionFactory.close();
	}
}
