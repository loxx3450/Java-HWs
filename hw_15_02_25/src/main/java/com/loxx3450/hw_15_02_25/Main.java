package com.loxx3450.hw_15_02_25;

import com.loxx3450.hw_15_02_25.config.ApplicationConfig;
import com.loxx3450.hw_15_02_25.services.CartService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		CartService cartService = context.getBean(CartService.class);

		var cart = cartService.getNewCart();

		cartService.addProduct(cart, 1, 1);
		cartService.addProduct(cart, 3, 6);

		cartService.printCart(cart);

		context.close();
	}
}
