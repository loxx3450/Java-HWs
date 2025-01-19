package com.loxx3450.hw_23_01_25;

import com.loxx3450.hw_23_01_25.task1.IntegersListAnalyzer;
import com.loxx3450.hw_23_01_25.task2.Product;
import com.loxx3450.hw_23_01_25.task2.ProductCategory;
import com.loxx3450.hw_23_01_25.task2.ProductsCollection;
import com.loxx3450.hw_23_01_25.task3.Device;
import com.loxx3450.hw_23_01_25.task3.DeviceType;
import com.loxx3450.hw_23_01_25.task3.DevicesCollection;
import com.loxx3450.hw_23_01_25.task4.Manufacturer;
import com.loxx3450.hw_23_01_25.task4.Projector;
import com.loxx3450.hw_23_01_25.task4.ProjectorsCollection;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		//task1();
		//task2();
		//task3();
		task4();
	}

	private static void task1() {
		IntegersListAnalyzer analyzer = new IntegersListAnalyzer(10, -99, 99);

		System.out.println("Sorted list:");
		analyzer.printSortedList();

		long positiveNumbersCount = analyzer.getPositiveNumbersCount();
		long negativeNumbersCount = analyzer.getNegativeNumbersCount();
		long twoDigitNumbersCount = analyzer.getTwoDigitNumbersCount();
		long mirrorNumbersCount = analyzer.getMirrorNumbersCount();

		System.out.println("Count of positive numbers: " + positiveNumbersCount);
		System.out.println("Count of negative numbers: " + negativeNumbersCount);
		System.out.println("Count of two-digit numbers: " + twoDigitNumbersCount);
		System.out.println("Count of mirror numbers(palindromes): " + mirrorNumbersCount);
	}

	private static void task2() {
		List<Product> products = Arrays.asList(
			new Product("Milk", ProductCategory.DAIRY),
			new Product("Cheese", ProductCategory.DAIRY),
			new Product("Yogurt", ProductCategory.DAIRY),
			new Product("Apple", ProductCategory.FRUITS),
			new Product("Apple", ProductCategory.FRUITS),
			new Product("Banana", ProductCategory.FRUITS),
			new Product("Fish", ProductCategory.MEAT)
		);

		ProductsCollection productsCollection = new ProductsCollection(products);

		productsCollection.printAllProducts();
		productsCollection.printProductsByMaxLength(4);
		productsCollection.countProductByName("Milk");
		productsCollection.countProductByName("Apple");
		productsCollection.printProductsByFirstLetter('a');
		productsCollection.printProductsByFirstLetter('B');
		productsCollection.printProductsByCategory(ProductCategory.DAIRY);
	}

	private static void task3() {
		List<Device> devices = Arrays.asList(
			new Device("Smartphone", 2021, 799.99, Color.BLACK, DeviceType.MOBILE),
			new Device("Laptop", 2020, 1200.50, Color.GRAY, DeviceType.COMPUTER),
			new Device("Tablet", 2022, 399.99, Color.WHITE, DeviceType.MOBILE),
			new Device("Smartwatch", 2021, 199.99, Color.BLACK, DeviceType.WEARABLE),
			new Device("Headphones", 2020, 150.00, Color.BLUE, DeviceType.AUDIO),
			new Device("Smart TV", 2021, 600.00, Color.BLACK, DeviceType.ELECTRONICS),
			new Device("Gaming Console", 2022, 499.99, Color.BLACK, DeviceType.GAMING)
		);

		DevicesCollection devicesCollection = new DevicesCollection(devices);

		devicesCollection.printAllDevices();
		devicesCollection.printDevicesByColor(Color.BLACK);
		devicesCollection.printDevicesByYear(2021);
		devicesCollection.printDevicesByMinPrice(600.00);
		devicesCollection.printDevicesByType(DeviceType.MOBILE);
		devicesCollection.printDevicesByYear(2020, 2021);
	}

	private static void task4() {
		List<Projector> projectors = Arrays.asList(
			new Projector("VPL-VW295ES", 2021, 4999.99, Manufacturer.SONY),
			new Projector("HU810PW", 2021, 2299.99, Manufacturer.LG),
			new Projector("PT-FRZ60", 2021, 3500.00, Manufacturer.PANASONIC),
			new Projector("VPL-HW45ES", 2019, 1500.00, Manufacturer.SONY),
			new Projector("EH-LS500B", 2021, 2999.99, Manufacturer.EPSON),
			new Projector("HU915QE", 2022, 3999.99, Manufacturer.LG),
			new Projector("PT-RZ120", 2020, 5000.00, Manufacturer.PANASONIC)
		);

		ProjectorsCollection projectorsCollection = new ProjectorsCollection(projectors);

		projectorsCollection.printAllProjectors();
		projectorsCollection.printProjectorsByManufacturer(Manufacturer.SONY);
		projectorsCollection.printProjectorsByYear(2020);
		projectorsCollection.printProjectorsByMinPrice(3600.00);
		projectorsCollection.printProjectorsSortedByPriceAscending();
		projectorsCollection.printProjectorsSortedByPriceDescending();
		projectorsCollection.printProjectorsSortedByYearAscending();
		projectorsCollection.printProjectorsSortedByYearDescending();
	}
}
