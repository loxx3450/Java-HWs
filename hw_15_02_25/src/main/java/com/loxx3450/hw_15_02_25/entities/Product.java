package com.loxx3450.hw_15_02_25.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
	private int id;
	private String name;
	private double price;
}
