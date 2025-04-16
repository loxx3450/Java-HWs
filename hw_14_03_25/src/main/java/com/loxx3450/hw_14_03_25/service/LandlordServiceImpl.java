package com.loxx3450.hw_14_03_25.service;

import com.loxx3450.hw_14_03_25.dto.LandlordDto;
import com.loxx3450.hw_14_03_25.model.Landlord;
import com.loxx3450.hw_14_03_25.util.HibernateUtil;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class LandlordServiceImpl implements LandlordService {
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public List<LandlordDto> getAll() {
		try (Session session = sessionFactory.openSession()) {
			List<Landlord> landlords = session.createQuery("from Landlord", Landlord.class).list();
			return landlords.stream().map(LandlordDto::new).toList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public LandlordDto getById(int id) {
		try (Session session = sessionFactory.openSession()) {
			Landlord landlord = session.get(Landlord.class, id);
			return new LandlordDto(landlord);
		} catch (Exception e) {
			return null;
		}
	}
}
