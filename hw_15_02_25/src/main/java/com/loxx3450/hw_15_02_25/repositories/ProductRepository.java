package com.loxx3450.hw_15_02_25.repositories;

import com.loxx3450.hw_15_02_25.entities.Product;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public interface ProductRepository {
	List<Product> getAll();

	Product getById(int id);

	void addOrUpdate(Product product);

	void deleteById(int id);
}
