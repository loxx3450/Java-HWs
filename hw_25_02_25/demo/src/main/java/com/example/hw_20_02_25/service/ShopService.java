package com.example.hw_20_02_25.service;

import com.example.hw_20_02_25.entity.Shop;
import com.example.hw_20_02_25.entity.ShopFilter;
import java.util.List;


public interface ShopService {
	List<Shop> getAll();

	List<Shop> getAll(ShopFilter filter);

	Shop getById(int id);

	void add(Shop shop);

	void update(int id, Shop shop);

	void delete(int id);
}
