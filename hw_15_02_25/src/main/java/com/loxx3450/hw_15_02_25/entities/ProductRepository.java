package com.loxx3450.hw_15_02_25.entities;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository {
	private List<Product> products = new ArrayList<Product>();

	public ProductRepository() {
		System.out.println("ProductRepository Bean Created");
	}

	@PostConstruct
	public void init() {
		products.add(new Product(1, "I5 8600K", 1200.99));
		products.add(new Product(2, "I7 12700", 299.99));
		products.add(new Product(3, "I5 5300", 199.99));
		products.add(new Product(4, "I9 13700K", 699.49));
		products.add(new Product(5, "I3 10700", 89.99));
	}

	public void print() {
		for (Product product : products) {
			System.out.println(product);
		}
	}
}
