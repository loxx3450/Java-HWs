package com.loxx3450.hw_23_01_25.task4;

import java.util.Comparator;
import java.util.List;

public class ProjectorsCollection {
	private List<Projector> projectors;

	public ProjectorsCollection(List<Projector> projectors) {
		this.projectors = projectors;
	}

	public void printAllProjectors() {
		System.out.println("Printing all projectors:");

		projectors.forEach(System.out::println);

		System.out.println();
	}

	public void printProjectorsByManufacturer(Manufacturer manufacturer) {
		System.out.println("Printing all projectors from " + manufacturer.toString() + ":");

		projectors.stream()
			.filter(p -> p.getManufacturer().equals(manufacturer))
			.forEach(System.out::println);

		System.out.println();
	}

	public void printProjectorsByYear(int year) {
		System.out.println("Printing all projectors from " + year + ":");

		projectors.stream()
			.filter(p -> p.getYear() == year)
			.forEach(System.out::println);

		System.out.println();
	}

	public void printProjectorsByMinPrice(double minPrice) {
		System.out.println("Printing all projectors from " + minPrice + ":");

		projectors.stream()
			.filter(p -> p.getPrice() >= minPrice)
			.forEach(System.out::println);

		System.out.println();
	}

	public void printProjectorsSortedByPriceAscending() {
		System.out.println("Printing all projectors by price ascending:");

		projectors.stream()
			.sorted(Comparator.comparingDouble(Projector::getPrice))
			.forEach(System.out::println);

		System.out.println();
	}

	public void printProjectorsSortedByPriceDescending() {
		System.out.println("Printing all projectors by price descending:");

		projectors.stream()
			.sorted(Comparator.comparingDouble(Projector::getPrice).reversed())
			.forEach(System.out::println);

		System.out.println();
	}

	public void printProjectorsSortedByYearAscending() {
		System.out.println("Printing all projectors by release year ascending:");

		projectors.stream()
			.sorted(Comparator.comparingInt(Projector::getYear))
			.forEach(System.out::println);

		System.out.println();
	}

	public void printProjectorsSortedByYearDescending() {
		System.out.println("Printing all projectors by release year descending:");

		projectors.stream()
			.sorted(Comparator.comparingInt(Projector::getYear).reversed())
			.forEach(System.out::println);

		System.out.println();
	}
}
