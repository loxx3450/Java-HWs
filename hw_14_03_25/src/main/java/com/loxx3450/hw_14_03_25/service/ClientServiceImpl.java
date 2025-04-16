package com.loxx3450.hw_14_03_25.service;

import com.loxx3450.hw_14_03_25.dto.ClientDto;
import com.loxx3450.hw_14_03_25.filter.ClientFilter;
import com.loxx3450.hw_14_03_25.model.Client;
import com.loxx3450.hw_14_03_25.util.HibernateUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public ClientDto getById(int id) {
		try (Session session = sessionFactory.openSession()) {
			Client client = session.get(Client.class, id);
			return new ClientDto(client);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ClientDto> getAllByFilter(ClientFilter filter) {
		StringBuilder query = new StringBuilder();
		Map<String, Object> params = new HashMap<>();

		query.append("FROM Client WHERE TRUE");

		if (filter.getName().isPresent()) {
			query.append(" AND fullName ILIKE CONCAT('%', :name, '%')");
			params.put("name", filter.getName().get());
		}
		if (filter.getPhone().isPresent()) {
			query.append(" AND phone ILIKE CONCAT('%', :phone, '%')");
			params.put("phone", filter.getPhone().get());
		}
		if (filter.getWantsRooms().isPresent()) {
			query.append(" AND wantsRooms = :wantsRooms");
			params.put("wantsRooms", filter.getWantsRooms().get());
		}

		try (Session session = sessionFactory.openSession()) {
			var hibernateQuery = session.createQuery(query.toString(), Client.class);
			params.forEach(hibernateQuery::setParameter);

			var clients = hibernateQuery.list();

			return clients.stream().map(ClientDto::new).toList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
}
