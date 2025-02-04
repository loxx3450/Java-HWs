package com.loxx3450.hw_13_02_25.task5;

public enum OperationType {
	Addition,
	Subtraction,
	Multiplication,
	Division,
	Exponentiation,
	Percentage;

	public static OperationType fromString(String type) {
		return switch (type.toLowerCase()) {
			case "addition" -> Addition;
			case "subtraction" -> Subtraction;
			case "multiplication" -> Multiplication;
			case "division" -> Division;
			case "exponentiation" -> Exponentiation;
			case "percentage" -> Percentage;
			default -> throw new IllegalArgumentException("Unknown operation type: " + type);
		};
	}
}

