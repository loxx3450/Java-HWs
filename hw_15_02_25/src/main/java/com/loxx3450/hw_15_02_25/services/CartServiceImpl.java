package com.loxx3450.hw_15_02_25.services;

import com.loxx3450.hw_15_02_25.entities.Cart;
import com.loxx3450.hw_15_02_25.entities.Product;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Cart getNewCart() {
		return new Cart();
	}

	@Override
	public void addProduct(Cart cart, Product product, Integer quantity) {
		Map<Product, Integer> cartMap = cart.getCartMap();
		cartMap.merge(product, quantity, Integer::sum);
	}

	@Override
	public void addProduct(Cart cart, int prodId, Integer quantity) {
		var product = productService.getProductById(prodId);

		addProduct(cart, product, quantity);
	}

	@Override
	public void delProduct(Cart cart, Product product, Integer quantity) {
		Map<Product, Integer> cartMap = cart.getCartMap();

		if (cartMap.containsKey(product)) {
			if (cartMap.get(product) > quantity) {
				cartMap.put(product, cartMap.get(product) - quantity);
			} else {
				cartMap.remove(product);
			}
		}
	}

	@Override
	public void delProduct(Cart cart, int prodId, Integer quantity) {
		var product = productService.getProductById(prodId);

		delProduct(cart, product, quantity);
	}

	@Override
	public void printCart(Cart cart) {
		Map<Product, Integer> cartMap = cart.getCartMap();

		if (cartMap.isEmpty()) {
			System.out.println("🛒 Cart is empty.");
			return;
		}

		System.out.println("🛒 Cart Contents:");
		cartMap.forEach((product, quantity) ->
			System.out.printf("• %s (ID: %d) - Quantity: %d%n",
				product.getName(), product.getId(), quantity)
		);
	}


	@Override
	public int getProductQuantity(Cart cart, Product product) {
		return cart.getCartMap().getOrDefault(product, 0);
	}

	@Override
	public int getProductQuantity(Cart cart, int prodId) {
		var product = productService.getProductById(prodId);

		return getProductQuantity(cart, product);
	}
}
