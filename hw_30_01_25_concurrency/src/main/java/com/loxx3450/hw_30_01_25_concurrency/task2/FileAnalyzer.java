package com.loxx3450.hw_30_01_25_concurrency.task2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class FileAnalyzer {
	private final Path originFilePath;

	public FileAnalyzer(Path filePath) {
		this.originFilePath = filePath;
		ensureFileExists(originFilePath);
	}

	public CompletableFuture<Void> fillFileWithRandomNumbers(int times, int min, int max, ExecutorService executorService) {
		return CompletableFuture.runAsync(() -> {
			try (BufferedWriter bw = Files.newBufferedWriter(originFilePath)) {
				Random rand = new Random();

				for (int i = 0; i < times; i++) {
					bw.write(Integer.toString(rand.nextInt(max - min) + min));
					bw.newLine();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}, executorService);
	}

	public CompletableFuture<Void> saveAllPrimeNumbers(Path resultFile, ExecutorService executorService) {
		return CompletableFuture.runAsync(() -> {
			readLinesAndWriteProcessedData(originFilePath, resultFile, (number) -> isPrime(number) ? Integer.toString(number) : null);
		}, executorService);
	}

	public CompletableFuture<Void> saveFactorialsOfNumbers(Path resultFile, ExecutorService executorService) {
		return CompletableFuture.runAsync(() -> {
			readLinesAndWriteProcessedData(originFilePath, resultFile, (number) -> Long.toString(factorial(number)));
		}, executorService);
	}

	// writing processed data from every line in result file
	private static void readLinesAndWriteProcessedData(Path sourceFile, Path resultFile, Function<Integer, String> processor) {
		ensureFileExists(resultFile);

		try (BufferedWriter bw = Files.newBufferedWriter(resultFile)) {
			readAndProcessLines(sourceFile, (number) -> {
				String result = processor.apply(number);
				if (result != null) {
					writeToFileNewLine(bw, result);
				}
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void writeToFileNewLine(BufferedWriter bw, String value) {
		try {
			bw.write(value);
			bw.newLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void ensureFileExists(Path filePath) {
		try {
			if (Files.notExists(filePath)) {
				Files.createFile(filePath);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// callback will be called on every not empty line
	private static void readAndProcessLines(Path sourceFile, Consumer<Integer> callback) throws IOException {
		try (BufferedReader br = Files.newBufferedReader(sourceFile)) {
			String line;

			while ((line = br.readLine()) != null) {
				if (! line.isEmpty()) {
					int number = Integer.parseInt(line);

					callback.accept(number);
				}
			}
		}
	}

	private static boolean isPrime(int number) {
		if (number == 0 || number == 1) {
			return false;
		}

		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}

		return true;
	}

	private static long factorial(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("Factorial does not support negative numbers");
		}

		long result = 1;

		for (int i = 2; i <= number; i++) {
			result *= i;
		}

		return result;
	}
}
