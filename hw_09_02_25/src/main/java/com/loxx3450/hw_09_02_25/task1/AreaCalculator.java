package com.loxx3450.hw_09_02_25.task1;

import com.loxx3450.hw_09_02_25.task1.shapes.Rectangle;
import com.loxx3450.hw_09_02_25.task1.shapes.Rhombus;
import com.loxx3450.hw_09_02_25.task1.shapes.Shape;
import com.loxx3450.hw_09_02_25.task1.shapes.Square;
import com.loxx3450.hw_09_02_25.task1.shapes.Triangle;
import javax.sound.sampled.Line;

public class AreaCalculator {
	public static double calculateArea(Shape shape) {
		return switch (shape) {
			case Rectangle rectangle -> calculateRectangleArea(rectangle);
			case Square square -> calculateSquareArea(square);
			case Triangle triangle -> calculateTriangleArea(triangle);
			case Rhombus rhombus -> calculateRhombusArea(rhombus);

			default -> throw new IllegalStateException("Unexpected value: " + shape);
		};
	}
	
	private static double calculateRectangleArea(Rectangle rectangle) {
		return rectangle.getWidth() * rectangle.getHeight();
	}

	private static double calculateSquareArea(Square square) {
		return Math.pow(square.getSide(), 2);
	}

	private static double calculateTriangleArea(Triangle triangle) {
		double a = triangle.getA();
		double b = triangle.getB();
		double c = triangle.getC();
		double p = (a + b + c) / 2;

		return Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}

	private static double calculateRhombusArea(Rhombus rhombus) {
		double angleInRadians = Math.toRadians(rhombus.getAngleInDegrees());
		return Math.pow(rhombus.getSide(), 2) * Math.sin(angleInRadians);
	}
}
