package com.loxx3450.hw_09_02_25.task1;

import static org.junit.jupiter.api.Assertions.*;

import com.loxx3450.hw_09_02_25.task1.shapes.Rectangle;
import com.loxx3450.hw_09_02_25.task1.shapes.Rhombus;
import com.loxx3450.hw_09_02_25.task1.shapes.Shape;
import com.loxx3450.hw_09_02_25.task1.shapes.Square;
import com.loxx3450.hw_09_02_25.task1.shapes.Triangle;
import org.junit.jupiter.api.Test;

public class AreaCalculatorTest {
	private static final double DELTA = 0.000001;

	@Test
	void givenNullShape_whenCalculateArea_thenThrowNullPointerException() {
		assertThrows(NullPointerException.class, () -> AreaCalculator.calculateArea(null));
	}

	@Test
	void givenNewShape_whenCalculateArea_thenThrowIllegalStateException() {
		var newShape = new Shape() {};

		assertThrows(IllegalStateException.class, () -> AreaCalculator.calculateArea(newShape));
	}


	@Test
	void givenRectangle_whenCalculateArea_thenReturnCorrectArea() {
		// Arrange
		Rectangle rectangle = new Rectangle(3, 2);
		double expectedArea = 6.0;

		// Act
		double actualArea = AreaCalculator.calculateArea(rectangle);

		// Assert
		assertEquals(expectedArea, actualArea, DELTA, () -> "The area of the rectangle with width 3 and height 2 should be 6.");
	}

	@Test
	void givenRectangleWithZeroWidth_whenCalculateArea_thenReturnZeroArea() {
		// Arrange
		Rectangle rectangle = new Rectangle(0, 2);
		double expectedArea = 0.0;

		// Act
		double actualArea = AreaCalculator.calculateArea(rectangle);

		// Assert
		assertEquals(expectedArea, actualArea, DELTA, () -> "Area should be 0 when width is 0.");
	}

	@Test
	void givenRhombus_whenCalculateArea_thenReturnCorrectData() {
		// Arrange
		Rhombus rhombus = new Rhombus(14, 30);
		double expectedArea = 98.0;

		// Act
		double actualArea = AreaCalculator.calculateArea(rhombus);

		// Assert
		assertEquals(expectedArea, actualArea, DELTA, () -> "The area of the rhombus with width 14 and height 30 should be 98.");
	}

	@Test
	void givenRhombusWithZeroSide_whenCalculateArea_thenReturnZeroArea() {
		// Arrange
		Rhombus rhombus = new Rhombus(0, 30);
		double expectedArea = 0.0;

		// Act
		double actualArea = AreaCalculator.calculateArea(rhombus);

		// Assert
		assertEquals(expectedArea, actualArea, DELTA, () -> "The area of the rhombus with width 0 should be 0.");
	}

	@Test
	void givenRhombusAsSquare_whenCalculateArea_thenReturnCorrectData() {
		// Arrange
		Rhombus rhombus = new Rhombus(4, 90);
		double expectedArea = 16.0;

		// Act
		double actualArea = AreaCalculator.calculateArea(rhombus);

		// Assert
		assertEquals(expectedArea, actualArea, DELTA, () -> "The rhombus with delta 90 is square.");
	}

	@Test
	void givenSquare_whenCalculateArea_thenReturnCorrectArea() {
		// Arrange
		Square square = new Square(4);
		double expectedArea = 16.0;

		// Act
		double actualArea = AreaCalculator.calculateArea(square);

		// Assert
		assertEquals(expectedArea, actualArea, DELTA, () -> "The area of the square with width 4 should be 16.");
	}

	@Test
	void givenSquareWithZeroSide_whenCalculateArea_thenReturnZeroArea() {
		// Arrange
		Square square = new Square(0);
		double expectedArea = 0.0;

		// Act
		double actualArea = AreaCalculator.calculateArea(square);

		// Assert
		assertEquals(expectedArea, actualArea, DELTA, () -> "The area of the square with width 0 should be 0.");
	}

	@Test
	void givenSquareWithVeryLargeSide_whenCalculateArea_thenReturnInfinity() {
		// Arrange
		Square square = new Square(Double.MAX_VALUE);

		// Act
		double actualArea = AreaCalculator.calculateArea(square);

		// Assert
		assertTrue(Double.isInfinite(actualArea), () -> "The area of the square with max width should be infinite.");
	}

	@Test
	void givenTriangle_whenCalculateArea_thenReturnCorrectArea() {
		// Arrange
		Triangle triangle = new Triangle(4, 4, 6);
		double expectedArea = 7.9372539331938;

		// Act
		double actualArea = AreaCalculator.calculateArea(triangle);

		// Assert
		assertEquals(expectedArea, actualArea, DELTA, () -> "The area of the triangle should be calculated correctly");
	}
}