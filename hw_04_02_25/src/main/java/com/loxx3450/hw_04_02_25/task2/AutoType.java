package com.loxx3450.hw_04_02_25.task2;

public enum AutoType {
	SEDAN,
	HATCHBACK,
	STATION_WAGON;

	public static AutoType fromString(String type) {
		return switch (type.toLowerCase()) {
			case "sedan" -> SEDAN;
			case "hatchback" -> HATCHBACK;
			case "station wagon" -> STATION_WAGON;
			default -> throw new IllegalArgumentException("Unknown auto type: " + type);
		};
	}

	@Override
	public String toString() {
		return switch (this) {
			case SEDAN -> "sedan";
			case HATCHBACK -> "hatchback";
			case STATION_WAGON -> "station wagon";
		};
	}
}
