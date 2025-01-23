package com.loxx3450.hw_25_01_25;

import com.loxx3450.hw_25_01_25.task1.TxtFilesComparer;
import com.loxx3450.hw_25_01_25.task2.TxtFileAnalyzer;
import com.loxx3450.hw_25_01_25.task3.TxtFileParser;
import com.loxx3450.hw_25_01_25.task4.IntegerListWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		final ExecutorService executor = Executors.newFixedThreadPool(1);

		// Task 1
//		Path path1 = Paths.get("resources", "task1", "task1_file1.txt");
//		Path path2 = Paths.get("resources", "task1", "task1_file2.txt");
//		TxtFilesComparer.compare(path1, path2, executor)
//			.thenAccept(_ -> System.out.println("Comparison completed"));


		// Task 2
//		Path path = Paths.get("resources", "task2", "task2_file1.txt");
//		TxtFileAnalyzer.findTheLongestLine(path, executor)
//			.thenAccept(result -> System.out.println("Result: " + result));

		// Task 3
//		Path path = Paths.get("resources", "task3", "task3_file1.txt");
//		TxtFileParser.parseFile(path, executor)
//			.thenAccept(_ -> System.out.println("Operation is completed!"));

		// Task 4
		Path path = Paths.get("resources", "task4", "task4_file1.txt");
		IntegerListWriter.writeIntoFile(path, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)), executor)
			.thenAccept(_ -> System.out.println("Operation is completed!"));

		executor.shutdown();
	}
}
