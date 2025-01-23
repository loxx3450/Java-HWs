package com.loxx3450.hw_25_01_25.task4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class IntegerListWriter {
	public static CompletableFuture<Void> writeIntoFile(Path path, List<Integer> list, ExecutorService executor) {
		return CompletableFuture.runAsync(() -> {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
				fillOutFirstLine(writer, list);
				fillOutSecondLine(writer, list);
				fillOutThirdLine(writer, list);
				fillOutFourthLine(writer, list);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, executor);
	}

	private static void fillOutFirstLine(BufferedWriter writer, List<Integer> list) throws IOException {
		String line = list.stream()
			.map(String::valueOf)
			.collect(Collectors.joining(", "));

		writer.write(line);
		writer.newLine();
	}

	private static void fillOutSecondLine(BufferedWriter writer, List<Integer> list) throws IOException {
		String line = list.stream()
			.filter(item -> item % 2 == 0)		// even numbers
			.map(String::valueOf)
			.collect(Collectors.joining(", "));

		writer.write(line);
		writer.newLine();
	}

	private static void fillOutThirdLine(BufferedWriter writer, List<Integer> list) throws IOException {
		String line = list.stream()
			.filter(item -> item % 2 != 0)		// odd numbers
			.map(String::valueOf)
			.collect(Collectors.joining(", "));

		writer.write(line);
		writer.newLine();
	}

	private static void fillOutFourthLine(BufferedWriter writer, List<Integer> list) throws IOException {
		List<Integer> reversedList = new ArrayList<>(list);
		Collections.reverse(reversedList); 		// original list will be not modified

		String line = list.stream()
			.map(String::valueOf)
			.collect(Collectors.joining(", "));

		writer.write(line);
		writer.newLine();
	}
}
