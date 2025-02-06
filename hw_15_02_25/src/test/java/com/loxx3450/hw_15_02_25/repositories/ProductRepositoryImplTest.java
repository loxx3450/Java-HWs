package com.loxx3450.hw_15_02_25.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.loxx3450.hw_15_02_25.config.ApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ApplicationConfig.class)
class ProductRepositoryImplTest {
	@Autowired
	private ApplicationContext context;

	@Test
	void takenRepositoryFromContext_whenGetBean_thenReturnRepositoryImpl() {
		// Act
		ProductRepository repository = context.getBean(ProductRepository.class);

		// Assert
		assertNotNull(repository, () -> "ProductRepositoryImpl should not be null");
		assertInstanceOf(ProductRepositoryImpl.class, repository, () -> "Bean should be an instance of ProductRepositoryImpl");
	}

	@Test
	void takenTwoRepositoriesFromContext_whenGetBean_thenReturnSingleRepository() {
		// Act
		ProductRepository repository1 = context.getBean(ProductRepository.class);
		ProductRepository repository2 = context.getBean(ProductRepository.class);

		// Assert
		assertNotNull(repository1, () -> "ProductRepositoryImpl should not be null");
		assertSame(repository1, repository2, () -> "ProductRepositoryImpl has scope 'Singletone'");
	}

	@Test
	void takenRepositoryFromContext_whenGetBean_thenReturnRepositoryWithCollectionOfProducts() {
		// Act
		ProductRepository repository = context.getBean(ProductRepository.class);

		// Assert
		assertNotNull(repository.getAll(), () -> "ProductRepositoryImpl should have collection of products");
		assertFalse(repository.getAll().isEmpty(), () -> "ProductRepositoryImpl's collection of products should not be empty");
	}

	@Test
	void givenWrongProductId_whenGetById_thenReturnNull() {
		// Arrange
		var id = -1;

		// Act
		ProductRepository repository = context.getBean(ProductRepository.class);
		var product = repository.getById(id);

		// Assert
		assertNull(product, () -> "ProductRepositoryImpl.getById should return null in case of wrong id");
	}
}