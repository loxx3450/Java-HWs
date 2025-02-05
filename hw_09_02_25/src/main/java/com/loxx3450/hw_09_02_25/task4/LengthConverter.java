package com.loxx3450.hw_09_02_25.task4;

public class LengthConverter {
	public static double convert(LengthUnit from, LengthUnit to, double length) {
		double lengthInMeters = from.toMeters(length);
		return to.fromMeters(lengthInMeters);
	}
}
