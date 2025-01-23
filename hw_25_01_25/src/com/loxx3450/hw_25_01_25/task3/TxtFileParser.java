package com.loxx3450.hw_25_01_25.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class TxtFileParser {
	public static CompletableFuture<Void> parseFile(Path path, ExecutorService executor) {
		return CompletableFuture.runAsync(() -> {
			try (BufferedReader br = Files.newBufferedReader(path)) {
				int totalSum = 0;

				String line;

				while ((line = br.readLine()) != null) {
					List<Integer> numbers = parseLineToList(line);

					if (numbers.isEmpty()) {
						continue;
					}

					// Print array
					System.out.println("List: ");
					numbers.forEach(n -> System.out.print(n + " "));
					System.out.println();

					// Print max and min number
					int maxNumber = numbers.stream()
						.max(Integer::compareTo)
						.get();

					int minNumber = numbers.stream()
						.min(Integer::compareTo)
						.get();

					System.out.printf("Max value: %d\nMin Value: %d\n", maxNumber, minNumber);

					// Print sum
					int sum = numbers.stream().mapToInt(Integer::intValue).sum();

					System.out.printf("Sum: %d\n", sum);

					totalSum += sum;

					System.out.println();
					System.out.println();
				}

				System.out.printf("Total sum: %d\n", totalSum);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, executor);
	}

	private static List<Integer> parseLineToList(String line) {
		String[] parts = line.split("\s+");

		// in case if line is empty
		if (parts.length == 0 || (parts.length == 1 && parts[0].isEmpty())) {
			return new ArrayList<>();
		}

		List<Integer> numbers = new ArrayList<>();

		for (String part : parts) {
			numbers.add(Integer.parseInt(part));
		}

		return numbers;
	}
}
