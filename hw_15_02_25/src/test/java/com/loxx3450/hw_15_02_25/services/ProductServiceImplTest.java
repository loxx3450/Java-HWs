package com.loxx3450.hw_15_02_25.services;

import static org.junit.jupiter.api.Assertions.*;

import com.loxx3450.hw_15_02_25.config.ApplicationConfig;
import com.loxx3450.hw_15_02_25.repositories.ProductRepository;
import com.loxx3450.hw_15_02_25.repositories.ProductRepositoryImpl;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ApplicationConfig.class)
class ProductServiceImplTest {
	@Autowired
	private ApplicationContext context;

	@Test
	void takenServiceFromContext_whenGetBean_thenReturnServiceImpl() {
		// Act
		ProductService service = context.getBean(ProductService.class);

		// Assert
		assertNotNull(service, () -> "ProductServiceImpl should not be null");
		assertInstanceOf(ProductServiceImpl.class, service, () -> "Bean should be an instance of ProductServiceImpl");
	}

	@Test
	void takenServiceFromContext_whenGetBean_thenReturnServiceImplWithInjectedRepository()
		throws IllegalAccessException {
		// Act
		ProductService service = context.getBean(ProductService.class);

		// Assert
		Class<?> clazz = service.getClass();
		for (Field f : clazz.getDeclaredFields()) {
			f.setAccessible(true);

			if (f.getName().equals("repository")) {
				assertNotNull(f.get(service), () -> "ProductRepository should be injected into ProductService");
				return;
			}
		}
	}

	@Test
	void takenTwoServicesFromContext_whenGetBean_thenReturnSingleService() {
		// Act
		ProductService service1 = context.getBean(ProductService.class);
		ProductService service2 = context.getBean(ProductService.class);

		// Assert
		assertNotNull(service1, () -> "ProductServiceImpl should not be null");
		assertSame(service1, service2, () -> "ProductServiceImpl has scope 'Singletone'");
	}

	@Test
	void givenWrongProductId_whenGetProductById_thenThrowException() {
		// Arrange
		var id = -1;

		// Act
		ProductService service = context.getBean(ProductService.class);

		// Assert
		assertThrows(IllegalArgumentException.class, () -> service.getProductById(id),
			() -> "ProductServiceImpl should throw IllegalArgumentException when user asks for product with wrong id");
	}
}