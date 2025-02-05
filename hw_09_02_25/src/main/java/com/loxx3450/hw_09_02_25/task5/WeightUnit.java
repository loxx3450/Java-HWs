package com.loxx3450.hw_09_02_25.task5;

public enum WeightUnit {
	Milligram(0.000001),
	Gram(0.001),
	Kilogram(1),
	Centner(100),
	Tonne(1_000);

	private final double weightInKilograms;

	WeightUnit(double weightInKilograms) {
		this.weightInKilograms = weightInKilograms;
	}

	public double getWeightInKilograms() {
		return weightInKilograms;
	}

	public double toKilograms(double value) {
		return value * weightInKilograms;
	}

	public double fromKilograms(double value) {
		return value / weightInKilograms;
	}
}
