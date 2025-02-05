package com.loxx3450.hw_09_02_25.task1.shapes;

import lombok.Data;

@Data
public class Square implements Shape {
	private double side;

	public Square(double side) {
		if (side < 0) {
			throw new IllegalArgumentException("Side must be a positive number");
		}
		this.side = side;
	}
}

