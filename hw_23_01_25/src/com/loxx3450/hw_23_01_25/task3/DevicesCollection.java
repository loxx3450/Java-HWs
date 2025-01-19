package com.loxx3450.hw_23_01_25.task3;

import java.awt.Color;
import java.util.List;

public class DevicesCollection {
	private final List<Device> devices;

	public DevicesCollection(List<Device> devices) {
		this.devices = devices;
	}

	public void printAllDevices() {
		System.out.println("Printing all devices:");

		devices.forEach(System.out::println);

		System.out.println();
	}

	public void printDevicesByColor(Color color) {
		System.out.println("Printing all devices with color '" + color + "':");

		devices.stream()
			.filter(d -> d.getColor().equals(color))
			.forEach(System.out::println);

		System.out.println();
	}

	public void printDevicesByYear(int year) {
		System.out.println("Printing all devices from " + year + ":");

		devices.stream()
			.filter(d -> d.getYear() == year)
			.forEach(System.out::println);

		System.out.println();
	}

	public void printDevicesByYear(int fromYear, int toYear) {
		System.out.println("Printing all devices from " + fromYear + " to " + toYear + ":");

		devices.stream()
			.filter(d -> d.getYear() >= fromYear && d.getYear() <= toYear)
			.forEach(System.out::println);

		System.out.println();
	}

	public void printDevicesByMinPrice(double minPrice) {
		System.out.println("Printing all devices with price from " + minPrice + ":");

		devices.stream()
			.filter(d -> d.getPrice() >= minPrice)
			.forEach(System.out::println);

		System.out.println();
	}

	public void printDevicesByType(DeviceType type) {
		System.out.println("Printing all devices with type '" + type + "':");

		devices.stream()
			.filter(d -> d.getType().equals(type))
			.forEach(System.out::println);

		System.out.println();
	}
}
