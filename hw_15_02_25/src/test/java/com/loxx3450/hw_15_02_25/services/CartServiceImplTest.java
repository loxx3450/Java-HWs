package com.loxx3450.hw_15_02_25.services;

import static org.junit.jupiter.api.Assertions.*;

import com.loxx3450.hw_15_02_25.config.ApplicationConfig;
import com.loxx3450.hw_15_02_25.entities.Cart;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ApplicationConfig.class)
class CartServiceImplTest {
	@Autowired
	private ApplicationContext context;

	@Autowired
	private ProductService productService;

	private CartService service;

	@BeforeEach
	void setUp() {
		service = context.getBean(CartService.class);
	}

	@Test
	void takenServiceFromContext_whenGetBean_thenReturnServiceImpl() {
		// Assert
		assertNotNull(service, () -> "CartServiceImpl should not be null");
		assertInstanceOf(CartServiceImpl.class, service, () -> "Bean should be an instance of CartServiceImpl");
	}

	@Test
	void takenServiceFromContext_whenGetBean_thenReturnServiceImplWithInjectedProductService()
		throws IllegalAccessException {
		// Assert
		Class<?> clazz = service.getClass();
		for (Field f : clazz.getDeclaredFields()) {
			f.setAccessible(true);

			if (f.getName().equals("productService")) {
				assertNotNull(f.get(service), () -> "ProductService should be injected into ProductService");
				return;
			}
		}
	}

	@Test
	void takenTwoServicesFromContext_whenGetBean_thenReturnSingleService() {
		// Act
		CartService service2 = context.getBean(CartService.class);

		// Assert
		assertNotNull(service, () -> "CartServiceImpl should not be null");
		assertSame(service, service2, () -> "CartServiceImpl has scope 'Singleton'");
	}

	@Test
	void givenCartService_whenGetNewCart_returnNewCart() {
		// Act
		Cart cart1 = service.getNewCart();
		Cart cart2 = service.getNewCart();

		// Assert
		assertNotSame(cart1, cart2,
			() -> "CartService should return new Carts");
	}

	@Test
	void givenCart_whenAddProduct_updateCartMap() {
		// Arrange
		Cart cart = service.getNewCart();
		int quantity = 10;

		// Act
		var product = productService.getProductList().stream().findFirst().orElse(null);
		service.addProduct(cart, product, quantity);

		// Assert
		assertTrue(cart.getCartMap().containsKey(product), () -> "Add Product does not add new row in Cart Map");
		assertEquals(quantity, (int) cart.getCartMap().get(product), () -> "Parameter quantity works");
	}

	@Test
	void givenCart_whenRemoveProduct_updateCartMap() {
		// Arrange
		Cart cart = service.getNewCart();

		int addQuantity = 10;
		int removeQuantity = 8;
		int expectedQuantity = addQuantity - removeQuantity;

		// Act
		var product = productService.getProductList().stream().findFirst().orElse(null);
		service.addProduct(cart, product, addQuantity);
		service.delProduct(cart, product, removeQuantity);

		// Assert
		assertTrue(cart.getCartMap().containsKey(product), () -> "delProduct() does not delete fully row");
		assertEquals(expectedQuantity, (int) cart.getCartMap().get(product), () -> "delProduct() correctly changes quantity in Cart Map");
	}

	@Test
	void givenCart_whenRemoveProductAndRemoveQuantityIsBigger_updateCartMap() {
		// Arrange
		Cart cart = service.getNewCart();

		int addQuantity = 10;
		int removeQuantity = addQuantity + 1;

		// Act
		var product = productService.getProductList().stream().findFirst().orElse(null);
		service.addProduct(cart, product, addQuantity);
		service.delProduct(cart, product, removeQuantity);

		// Assert
		assertFalse(cart.getCartMap().containsKey(product), () -> "delProduct() should delete key, when there are no quantity of it in cart");
	}

	@Test
	void givenCart_whenGetProductQuantity_returnCorrectQuantity() {
		// Arrange
		Cart cart = service.getNewCart();
		int quantity = 10;

		// Act
		var product = productService.getProductList().stream().findFirst().orElse(null);
		service.addProduct(cart, product, quantity);

		var actualQuantity = service.getProductQuantity(cart, product);

		// Assert
		assertEquals(quantity, actualQuantity, () -> "getProductQuantity() returns incorrect quantity");
	}
}