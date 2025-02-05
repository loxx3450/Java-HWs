package com.loxx3450.hw_09_02_25.task1.shapes;

import lombok.Data;

@Data
public class Rhombus implements Shape {
	private double side;
	private double angleInDegrees;

	public Rhombus(double side, double angleInDegrees) {
		if (angleInDegrees <= 0 || angleInDegrees >= 180) {
			throw new IllegalArgumentException("Angle must be between 0 and 180 degrees (exclusive).");
		} else if (side < 0) {
			throw new IllegalArgumentException("Side must be greater than or equal to zero.");
		}
		this.side = side;
		this.angleInDegrees = angleInDegrees;
	}
}

