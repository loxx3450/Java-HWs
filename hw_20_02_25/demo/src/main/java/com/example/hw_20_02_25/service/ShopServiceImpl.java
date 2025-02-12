package com.example.hw_20_02_25.service;

import com.example.hw_20_02_25.entity.Shop;
import com.example.hw_20_02_25.entity.ShopFilter;
import com.example.hw_20_02_25.entity.ShopType;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@Service
public class ShopServiceImpl implements ShopService {

	private final List<Shop> shops = new ArrayList<>();
	private final AtomicInteger idCounter = new AtomicInteger(1);

	@PostConstruct
	public void init() {
		shops.add(new Shop(idCounter.getAndIncrement(), "Fresh Market", "123 Main Street", "+1234567890", "fresh@market.com",
			"https://freshmarket.com", ShopType.GROCERY, "A store for fresh fruits and vegetables.",
			"/uploads/logotypes/fresh_market.jpg"));

		shops.add(new Shop(idCounter.getAndIncrement(), "Tech Hub", "456 Tech Rd", "+9876543210", "info@techhub.com",
			"https://techhub.com", ShopType.ELECTRONICS, "Latest gadgets and electronics.",
			"/uploads/logotypes/tech_hub.jpg"));

		shops.add(new Shop(idCounter.getAndIncrement(), "Fit & Strong", "789 Gym Avenue", "+1122334455", "contact@fitstrong.com",
			"https://fitstrong.com", ShopType.SPORTS, "Everything you need for fitness and sports.",
			"/uploads/logotypes/fit_strong.jpg"));

		shops.add(new Shop(idCounter.getAndIncrement(), "Home Essentials", "101 Home St", "+5566778899", "support@homeessentials.com",
			"https://homeessentials.com", ShopType.HOUSEHOLD, "Household items and cleaning products.",
			"/uploads/logotypes/home_essentials.jpg"));
	}

	@Override
	public List<Shop> getAll() {
		return shops;
	}

	@Override
	public List<Shop> getAll(ShopFilter filter) {
		return shops.stream()
			.filter(s -> s.getName().toLowerCase().contains(filter.getNamePattern().toLowerCase())
			&& s.getAddress().toLowerCase().contains(filter.getAddressPattern().toLowerCase())
			&& s.getType().toString().toLowerCase().contains(filter.getCategoryPattern().toLowerCase())
		).toList();
	}

	@Override
	public Shop getById(int id) {
		return findById(id)
			.orElseThrow(() ->
				new NoSuchElementException("Shop with ID " + id + " not found"));
	}

	@Override
	public void add(Shop shop) {
		shop.setId(idCounter.getAndIncrement());
		shops.add(shop);
	}

	@Override
	public void update(int id, Shop shop) {
		delete(id);
		add(shop);
	}

	@Override
	public void delete(int id) {
		ensureShopExists(id);
		shops.removeIf(s -> s.getId() == id);
	}

	private Optional<Shop> findById(int id) {
		return shops.stream()
			.filter(s -> s.getId() == id)
			.findFirst();
	}

	private void ensureShopExists(int id) {
		if (findById(id).isEmpty()) {
			throw new NoSuchElementException("Shop with ID " + id + " not found");
		}
	}
}
