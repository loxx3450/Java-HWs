package com.loxx3450.hw_15_02_25.services;

import com.loxx3450.hw_15_02_25.entities.Cart;
import com.loxx3450.hw_15_02_25.entities.Product;

public interface CartService {
	Cart getNewCart();

	void addProduct(Cart cart, Product product, Integer quantity);
	void addProduct(Cart cart, int prodId, Integer quantity);

	void delProduct(Cart cart, Product product, Integer quantity);
	void delProduct(Cart cart, int prodId, Integer quantity);

	void printCart(Cart cart);

	int getProductQuantity(Cart cart, Product product);
	int getProductQuantity(Cart cart, int prodId);
}
