package com.loxx3450.hw_23_01_25.task4;

public class Projector {
	private String name;
	private int year;
	private double price;
	private Manufacturer manufacturer;

	public Projector(String name, int year, double price, Manufacturer manufacturer) {
		this.name = name;
		this.year = year;
		this.price = price;
		this.manufacturer = manufacturer;
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

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "Projector{" +
			"name='" + name + '\'' +
			", year=" + year +
			", price=" + price +
			", manufacturer=" + manufacturer +
			'}';
	}
}
