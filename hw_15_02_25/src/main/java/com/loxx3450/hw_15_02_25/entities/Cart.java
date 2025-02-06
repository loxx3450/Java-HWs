package com.loxx3450.hw_15_02_25.entities;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Cart {
	private final Map<Product, Integer> cartMap = new HashMap<Product, Integer>();
}
