package com.loxx3450.hw_23_01_25.task2;

public class Product {
	private String name;
	private ProductCategory category;

	public Product(String name, ProductCategory category) {
		this.name = name;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product{" +
			"name='" + name + '\'' +
			", category=" + category +
			'}';
	}
}
