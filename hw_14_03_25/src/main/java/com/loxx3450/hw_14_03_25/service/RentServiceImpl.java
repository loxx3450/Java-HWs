package com.loxx3450.hw_14_03_25.service;

import com.loxx3450.hw_14_03_25.dto.RentDto;
import com.loxx3450.hw_14_03_25.model.Rent;
import com.loxx3450.hw_14_03_25.util.HibernateUtil;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class RentServiceImpl implements RentService {
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public List<RentDto> getAll() {
		try (Session session = sessionFactory.openSession()) {
			List<Rent> rents = session.createQuery("from Rent", Rent.class).list();
			return rents.stream().map(RentDto::new).toList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public RentDto getById(int id) {
		try (Session session = sessionFactory.openSession()) {
			Rent rent = session.get(Rent.class, id);
			return new RentDto(rent);
		} catch (Exception e) {
			return null;
		}
	}
}
