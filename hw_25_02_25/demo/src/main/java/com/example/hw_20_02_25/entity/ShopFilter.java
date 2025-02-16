package com.example.hw_20_02_25.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ShopFilter {
	private String namePattern;
	private String addressPattern;
	private String categoryPattern;
}
