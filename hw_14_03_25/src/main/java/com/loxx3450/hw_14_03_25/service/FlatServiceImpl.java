package com.loxx3450.hw_14_03_25.service;

import com.loxx3450.hw_14_03_25.dto.FlatDto;
import com.loxx3450.hw_14_03_25.dto.LandlordDto;
import com.loxx3450.hw_14_03_25.model.Flat;
import com.loxx3450.hw_14_03_25.model.Landlord;
import com.loxx3450.hw_14_03_25.util.HibernateUtil;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class FlatServiceImpl implements FlatService {
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public List<FlatDto> getAll() {
		try (Session session = sessionFactory.openSession()) {
			List<Flat> flats = session.createQuery("from Flat", Flat.class).list();
			return flats.stream().map(FlatDto::new).toList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public FlatDto getById(int id) {
		try (Session session = sessionFactory.openSession()) {
			Flat flat = session.get(Flat.class, id);
			return new FlatDto(flat);
		} catch (Exception e) {
			return null;
		}
	}
}
