package com.loxx3450.hw_25_01_25.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class TxtFilesComparer {

	private static final int FILE_1 = 1;
	private static final int FILE_2 = 2;

	public static CompletableFuture<Void> compare(Path path1, Path path2, ExecutorService executor) {
		return CompletableFuture.runAsync(() -> {
			try (BufferedReader br1 = new BufferedReader(new FileReader(path1.toString()));
				BufferedReader br2 = new BufferedReader(new FileReader(path2.toString()))) {
				String line1;
				String line2;

				System.out.println("Below would be printed all differences between files:");

				int lineNumber = 0;

				// using bitwise OR (|) to ensure that readLine() will be called on both readers
				while ((line1 = br1.readLine()) != null | (line2 = br2.readLine()) != null) {
					++lineNumber;

					if (line1 != null && line2 != null) {
						compareLines(line1, line2, lineNumber);
					} else {
						// The not read yet file will be printed till the end
						if (line1 == null) {
							printRemainingLines(FILE_2, lineNumber, line2, br2);
						}
						else {
							printRemainingLines(FILE_1, lineNumber, line1, br1);
						}

						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, executor);
	}

	private static void compareLines(String line1, String line2, int lineNumber) {
		if (line1.equals(line2)) {
			return;
		}

		printDifferenceInLine(FILE_1, lineNumber, line1);
		printDifferenceInLine(FILE_2, lineNumber, line2);

		System.out.println();
	}

	private static void printDifferenceInLine(int fileNumber, int lineNumber, String line) {
		System.out.printf("File %d on line %d: %s%n", fileNumber, lineNumber, line);
	}

	private static void printRemainingLines(int fileNumber, int lineNumber, String line, BufferedReader br) throws IOException {
		while (line != null) {
			printDifferenceInLine(fileNumber, lineNumber, line);

			line = br.readLine();
			++lineNumber;
		}
	}
}
