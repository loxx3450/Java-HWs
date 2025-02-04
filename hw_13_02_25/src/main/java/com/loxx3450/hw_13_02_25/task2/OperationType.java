package com.loxx3450.hw_13_02_25.task2;

public enum OperationType {
	Max,
	Min,
	Avg;

	public static OperationType fromString(String type) {
		return switch (type.toLowerCase()) {
			case "max" -> Max;
			case "min" -> Min;
			case "avg" -> Avg;
			default -> throw new IllegalArgumentException("Unknown operation type: " + type);
		};
	}
}
