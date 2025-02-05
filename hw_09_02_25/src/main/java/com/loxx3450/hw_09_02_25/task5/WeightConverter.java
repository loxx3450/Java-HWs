package com.loxx3450.hw_09_02_25.task5;

public class WeightConverter {
	public static double convert(WeightUnit from, WeightUnit to, double weight) {
		double weightInKilograms = from.toKilograms(weight);
		return to.fromKilograms(weightInKilograms);
	}
}
