package com.loxx3450.hw_15_02_25.repositories;

import com.loxx3450.hw_15_02_25.entities.Product;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	private final List<Product> products;

	public ProductRepositoryImpl() {
		products = new ArrayList<>();
	}

	@PostConstruct
	public void init() {
		products.add(new Product(1, "I5 8600K", 1200.99));
		products.add(new Product(2, "I7 12700", 299.99));
		products.add(new Product(3, "I5 5300", 199.99));
		products.add(new Product(4, "I9 13700K", 699.49));
		products.add(new Product(5, "I3 10700", 89.99));
	}

	public List<Product> getAll() {
		return List.copyOf(products);
	}

	public Product getById(int id) {
		return products.stream()
			.filter(product -> product.getId() == id)
			.findFirst()
			.orElse(null);
	}

	public void addOrUpdate(Product product) {
		deleteById(product.getId());
		products.add(product);
	}

	public void deleteById(int id) {
		products.removeIf(product -> product.getId() == id);
	}
}
