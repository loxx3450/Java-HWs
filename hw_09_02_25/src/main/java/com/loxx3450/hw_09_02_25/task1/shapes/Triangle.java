package com.loxx3450.hw_09_02_25.task1.shapes;

import lombok.Data;

@Data
public class Triangle implements Shape {
	private double a, b, c;

	public Triangle(double a, double b, double c) {
		if (a < 0 || b < 0 || c < 0) {
			throw new IllegalArgumentException("Sides of triangle must be non-negative");
		} else if (!isValidTriangle(a, b, c)) {
			throw new IllegalArgumentException("Invalid triangle sides.");
		}
		this.a = a;
		this.b = b;
		this.c = c;
	}

	private boolean isValidTriangle(double a, double b, double c) {
		// sum of any two sides > third side
		return (a + b > c) && (a + c > b) && (b + c > a);
	}

}
