package com.loxx3450.hw_15_02_25;

import com.loxx3450.hw_15_02_25.config.ApplicationConfig;
import com.loxx3450.hw_15_02_25.entities.Product;
import com.loxx3450.hw_15_02_25.entities.ProductRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		var product = context.getBean(ProductRepository.class);

		product.print();
	}
}
