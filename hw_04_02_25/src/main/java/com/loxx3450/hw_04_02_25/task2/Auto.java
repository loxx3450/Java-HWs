package com.loxx3450.hw_04_02_25.task2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auto {
	private int id;
	private String manufacturer;
	private String model;
	private double engineCapacity;
	private int year;
	private String color;
	private AutoType type;

	@Override
	public String toString() {
		return String.format(
			"ID: %d | Manufacturer: %s | Model: %s | Engine Capacity: %.2fL | Year: %d | Color: %s | Type: %s",
			id, manufacturer, model, engineCapacity, year, color, type
		);
	}
}
