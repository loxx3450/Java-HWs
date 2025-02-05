package com.loxx3450.hw_09_02_25.task1.shapes;

import lombok.Data;

@Data
public class Rectangle implements Shape {
	private double width;
	private double height;

	public Rectangle(double width, double height) {
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException("width and height must be greater than 0");
		}
		this.width = width;
		this.height = height;
	}
}
