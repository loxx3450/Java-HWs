package com.loxx3450.hw_23_01_25.task2;

import java.util.List;

public class ProductsCollection {
	private List<Product> products;

	public ProductsCollection(List<Product> products) {
		this.products = products;
	}

	public void printAllProducts() {
		System.out.println("Printing all products: ");

		products.forEach(System.out::println);

		System.out.println();
	}

	public void printProductsByMaxLength(int maxLength) {
		System.out.println("Printing all products with max length " + maxLength + ": ");

		products.stream()
			.filter(p -> p.getName().length() <= maxLength)
			.forEach(System.out::println);

		System.out.println();
	}

	public void countProductByName(String name) {
		long count = products.stream()
			.filter(p -> p.getName().equalsIgnoreCase(name))
			.count();

		System.out.println("Count of " + name + " products: " + count);

		System.out.println();
	}

	public void printProductsByFirstLetter(char letter) {
		System.out.println("Printing all products with first letter '" + letter + "': ");

		products.stream()
			.filter(p -> p.getName().toLowerCase().charAt(0) == Character.toLowerCase(letter))
			.forEach(System.out::println);

		System.out.println();
	}

	public void printProductsByCategory(ProductCategory category) {
		System.out.println("Printing all products from category '" + category.toString() + "': ");

		products.stream()
			.filter(p -> p.getCategory().equals(category))
			.forEach(System.out::println);

		System.out.println();
	}
}
