package com.example.hw_20_02_25.service;

import com.example.hw_20_02_25.entity.Shop;
import com.example.hw_20_02_25.entity.ShopFilter;
import com.example.hw_20_02_25.entity.ShopType;
import com.example.hw_20_02_25.repository.ShopRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

	private final ShopRepository shopRepository;

	public ShopServiceImpl(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	@PostConstruct
	public void init() {
		if (shopRepository.count() > 0) {
			return;
		}

		List<Shop> initialShops = List.of(
			new Shop(0, "Fresh Market", "123 Main Street", "+1234567890", "fresh@market.com",
				"https://freshmarket.com", ShopType.GROCERY, "A store for fresh fruits and vegetables.",
				"/uploads/logotypes/fresh_market.jpg"),

			new Shop(0, "Tech Hub", "456 Tech Rd", "+9876543210", "info@techhub.com",
				"https://techhub.com", ShopType.ELECTRONICS, "Latest gadgets and electronics.",
				"/uploads/logotypes/tech_hub.jpg"),

			new Shop(0, "Fit & Strong", "789 Gym Avenue", "+1122334455", "contact@fitstrong.com",
				"https://fitstrong.com", ShopType.SPORTS, "Everything you need for fitness and sports.",
				"/uploads/logotypes/fit_strong.jpg"),

			new Shop(0, "Home Essentials", "101 Home St", "+5566778899", "support@homeessentials.com",
				"https://homeessentials.com", ShopType.HOUSEHOLD, "Household items and cleaning products.",
				"/uploads/logotypes/home_essentials.jpg")
		);

		shopRepository.saveAll(initialShops);
	}


	@Override
	public List<Shop> getAll() {
		return (List<Shop>) shopRepository.findAll();
	}

	@Override
	public List<Shop> getAll(ShopFilter filter) {
		return shopRepository.findByFilter(
			filter.getNamePattern(),
			filter.getAddressPattern(),
			filter.getCategoryPattern()
		);
	}

	@Override
	public Shop getById(int id) {
		return shopRepository.findById(id)
			.orElseThrow(() ->
				new NoSuchElementException("Shop with ID " + id + " not found"));
	}

	@Override
	public void add(Shop shop) {
		shopRepository.save(shop);
	}

	@Override
	public void update(int id, Shop shop) {
		ensureShopExists(id);

		shop.setId(id);
		shopRepository.save(shop);
	}

	@Override
	public void delete(int id) {
		ensureShopExists(id);
		shopRepository.deleteById(id);
	}

	private void ensureShopExists(int id) {
		if (shopRepository.findById(id).isEmpty()) {
			throw new NoSuchElementException("Shop with ID " + id + " not found");
		}
	}
}
