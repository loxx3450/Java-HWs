package com.loxx3450.hw_23_01_25.task3;

import java.awt.Color;

public class Device {
	private String name;
	private int year;
	private double price;
	private Color color;
	private DeviceType type;

	public Device(String name, int year, double price, Color color, DeviceType type) {
		this.name = name;
		this.year = year;
		this.price = price;
		this.color = color;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public DeviceType getType() {
		return type;
	}

	public void setType(DeviceType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Device{" +
			"name='" + name + '\'' +
			", year=" + year +
			", price=" + price +
			", color=" + color.toString() +
			", type='" + type + '\'' +
			'}';
	}
}
