package com.loxx3450.hw_30_01_25_concurrency;

import com.loxx3450.hw_30_01_25_concurrency.task1.ArrayAnalyzer;
import com.loxx3450.hw_30_01_25_concurrency.task2.FileAnalyzer;
import com.loxx3450.hw_30_01_25_concurrency.task3.DirectoryForwarder;
import com.loxx3450.hw_30_01_25_concurrency.task4.DirectoryParser;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

	public static void main(String[] args) {
		// Task 1
//		ArrayAnalyzer analyzer = new ArrayAnalyzer();
//
//		var future = analyzer.fillArrayWithRandomNumbers(10, 1, 100)
//			.thenAccept(Main::printList)
//			.thenCompose(_ -> {
//				var sumFuture = analyzer.findSumOfNumbers();
//				var avgFuture = analyzer.findAvgNumber();
//
//				// Waiting till all threads will be finished
//				return CompletableFuture.allOf(sumFuture, avgFuture)
//					.thenRun(() -> {
//						try {
//							System.out.printf("Sum: %d\n", sumFuture.get());
//							System.out.printf("Avg: %f\n", avgFuture.get());
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					});
//			});
//
//		// can use ExecutorService
//		// is used to not finish program before thread will be done
//		future.join();

		// Task 2
//		ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//		FileAnalyzer analyzer = new FileAnalyzer(Paths.get("resources", "task2", "origin_file.txt"));
//
//		CompletableFuture<Void> future = analyzer.fillFileWithRandomNumbers(10, 1, 20, executorService)
//			.thenRun(() -> {
//				System.out.println("File is filled with random numbers");
//
//				analyzer.saveAllPrimeNumbers(Paths.get("resources", "task2", "prime_numbers.txt"), executorService)
//					.thenRun(() -> System.out.println("Prime numbers are saved"));
//
//				analyzer.saveFactorialsOfNumbers(Paths.get("resources", "task2", "factorials.txt"), executorService)
//					.thenRun(() -> System.out.println("Factorials are saved"));
//			});
//
//		future.join();
//		executorService.shutdown();

		// Task 3
//		ExecutorService executor = Executors.newFixedThreadPool(5);
//
//		Path source = Paths.get("resources", "task3", "oldDir");
//		Path target = Paths.get("resources", "task3", "newDir");
//
//		var future = DirectoryForwarder.moveDirectory(source, target, executor)
//			.thenRun(() -> System.out.println("Directory is forwarded!"));
//
//		future.join();
//		executor.shutdown();

		// Task 4
//		Path sourceDirectory = Paths.get("resources", "task4", "dir");
//		Path resultFile = Paths.get("resources", "task4", "result_file.txt");
//		Path bannedWordsFile = Paths.get("resources", "task4", "banned_words.txt");
//
//		String searchedWord = "apple";
//
//		var future = DirectoryParser.findAllFilesWithWord(sourceDirectory, resultFile, searchedWord)
//			.thenRun(() -> System.out.printf("Content of files with word '%s' is saved in result file\n", searchedWord))
//			.thenCompose(_ -> DirectoryParser.cutAllBannedWords(resultFile, bannedWordsFile))
//			.thenRun(() -> System.out.println("Banned words are cut from result file"));
//
//		future.join();
	}

	private static <T> void printList(List<T> list) {
		for (T item : list) {
			System.out.print(item + " ");
		}
		System.out.println();
	}
}