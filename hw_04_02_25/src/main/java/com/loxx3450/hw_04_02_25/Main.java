package com.loxx3450.hw_04_02_25;

import com.loxx3450.hw_04_02_25.task2.Auto;
import com.loxx3450.hw_04_02_25.task2.AutoType;
import com.loxx3450.hw_04_02_25.task2.AutosDbHelper;
import com.loxx3450.hw_04_02_25.task2.UpdateAutoDTO;
import java.sql.SQLException;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

	public static void main(String[] args) {
		// Task 1
//		try {
//			AutosDbCreator.createDb();
//		} catch (SQLException e) {
//			System.err.println("SQL Error: " + e.getMessage());
//			e.printStackTrace();
//		}

		// Task 2
//		try (AutosDbHelper helper = new AutosDbHelper()) {
//			helper.connect();
//
//			helper.printAllAutos();
//			System.out.printf("%n%n%n");
//
//			helper.printAllManufacturers();
//			System.out.printf("%n%n%n");
//
//			helper.printCountOfAutosByManufacturer();
//			System.out.printf("%n%n%n");
//		} catch (SQLException e) {
//			System.err.println("SQL Error: " + e.getMessage());
//		} catch (Exception e) {
//			System.err.println("Unexpected error: " + e.getMessage());
//		}

		// Task 3
//		try (AutosDbHelper helper = new AutosDbHelper()) {
//			helper.connect();
//
//			helper.printManufacturerWithTheMostAutos();
//			System.out.printf("%n%n");
//
//			helper.printManufacturerWithTheFewestAutos();
//			System.out.printf("%n%n");
//
//			helper.printAllAutosFromYear(2020);
//			System.out.printf("%n%n");
//
//			helper.printAllAutosBetweenYears(2020, 2021);
//			System.out.printf("%n%n");
//
//		} catch (SQLException e) {
//			System.err.println("SQL Error: " + e.getMessage());
//		} catch (Exception e) {
//			System.err.println("Unexpected error: " + e.getMessage());
//		}

		// Task 4
		try (AutosDbHelper helper = new AutosDbHelper()) {
			helper.connect();

			helper.printAllAutosByManufacturer("toyoTA");
			System.out.printf("%n%n");

			helper.printAllAutosByColor("bLACK");
			System.out.printf("%n%n");

			helper.printAllAutosByEngineCapacity(2.0);
			System.out.printf("%n%n");

			helper.printAllAutosByType(AutoType.STATION_WAGON);
			System.out.printf("%n%n");
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
		}

		// Task 5
//		try (AutosDbHelper helper = new AutosDbHelper()) {
//			helper.connect();
//
//			Auto autoToInsert = Auto.builder()
//				.manufacturer("Toyota")
//				.model("safasdfsdf")
//				.engineCapacity(9.9)
//				.year(2000)
//				.color("white")
//				.type(AutoType.STATION_WAGON)
//				.build();
//
//			helper.insertAuto(autoToInsert);
//			System.out.printf("%n%n");
//
//			helper.deleteAuto(8);
//
//			UpdateAutoDTO autoToUpdate = UpdateAutoDTO.builder()
//				.id(9)
//				.manufacturer(Optional.of("Honda"))
//				.model(Optional.of("Civic"))
//				.engineCapacity(Optional.of(1.8))
//				.year(Optional.of(2015))
//				.color(Optional.of("blue"))
//				.type(Optional.of(AutoType.SEDAN))
//				.build();
//
//			helper.updateAuto(autoToUpdate);
//
//		} catch (SQLException e) {
//			System.err.println("SQL Error: " + e.getMessage());
//		} catch (Exception e) {
//			System.err.println("Unexpected error: " + e.getMessage());
//		}
	}
}