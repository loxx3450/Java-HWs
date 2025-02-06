package com.loxx3450.hw_15_02_25.services;

import com.loxx3450.hw_15_02_25.entities.Product;
import java.util.List;

public interface ProductService {

	List<Product> getProductList();
	void saveOrUpdate(Product product);
	Product getProductById(int id);
	void deleteById(int id);
}
