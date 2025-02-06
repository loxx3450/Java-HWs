package com.loxx3450.hw_15_02_25.services;

import com.loxx3450.hw_15_02_25.entities.Product;
import com.loxx3450.hw_15_02_25.repositories.ProductRepository;
import com.loxx3450.hw_15_02_25.repositories.ProductRepositoryImpl;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;

	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Product> getProductList() {
		return repository.getAll();
	}

	@Override
	public void saveOrUpdate(Product product) {
		repository.addOrUpdate(product);
	}

	@Override
	public Product getProductById(int id) {
		var product = repository.getById(id);

		if (product == null) {
			throw new IllegalArgumentException("Product with id " + id + " not found");
		}

		return product;
	}

	@Override
	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
