package com.loxx3450.hw_09_02_25.task4;

public enum LengthUnit {
	Millimeter(0.001),
	Centimeter(0.01),
	Decimeter(0.1),
	Meter(1),
	Kilometer(1_000);

	private final double weightInMeters;

	LengthUnit(double weightInMeters) {
		this.weightInMeters = weightInMeters;
	}

	public double getWeightInMeters() {
		return weightInMeters;
	}

	public double toMeters(double value) {
		return value * weightInMeters;
	}

	public double fromMeters(double value) {
		return value / weightInMeters;
	}
}
