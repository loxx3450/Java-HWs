package com.loxx3450.hw_07_02_25;

import com.loxx3450.hw_07_02_25.entities.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

	public static void main(String[] args) {
		// to check NotNull and NotEmpty
//		User userWithErrors = new User(
//			null,
//			null,
//			null,
//			LocalDate.now().plusDays(1),
//			null
//		);

		// to check Size
//		User userWithErrors = new User(
//			1,
//			"as",
//			"valid@gmail.com",
//			LocalDate.now().plusDays(1),
//			19
//		);

		// to check Min
//		User userWithErrors = new User(
//			-1,
//			"valid name",
//			"valid@gmail.com",
//			LocalDate.now().plusDays(1),
//			16
//		);

		// to check Max
//		User userWithErrors = new User(
//			1,
//			"valid name",
//			"valid@gmail.com",
//			LocalDate.now().plusDays(1),
//			101
//		);

		// to check Pattern
//		User userWithErrors = new User(
//			1,
//			"name_surname",
//			"valid@gmail.com",
//			LocalDate.now().plusDays(1),
//			20
//		);

		// to check Email
//		User userWithErrors = new User(
//			1,
//			"valid name",
//			"test@gma*il.com",
//			LocalDate.now().plusDays(1),
//			20
//		);

		// to check Future
//		User userWithErrors = new User(
//			1,
//			"valid name",
//			"valid@gmail.com",
//			LocalDate.now().minusDays(1),
//			20
//		);

		// finally is everything fine
		User userWithErrors = new User(
			1,
			"valid name",
			"valid@gmail.com",
			LocalDate.now().plusYears(1),
			20
		);

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		try {
			Map<String, List<String>> errors = Validator.validateObject(userWithErrors, executorService);

			printErrors(errors);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			executorService.shutdown();
		}
	}

	private static void printErrors(Map<String, List<String>> errors) {
		if (errors.isEmpty()) {
			System.out.println("No validation errors found.");
			return;
		}

		System.out.println("Validation Errors:");
		errors.forEach((field, messages) -> {
			System.out.printf("Field '%s':%n", field);
			messages.forEach(message -> System.out.printf("  - %s%n", message));
		});
	}

}