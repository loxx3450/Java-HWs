package com.loxx3450.hw_25_01_25.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class TxtFileAnalyzer {
	public static CompletableFuture<String> findTheLongestLine(Path path, ExecutorService executor) {
		return CompletableFuture.supplyAsync(() -> {
			try (BufferedReader br = Files.newBufferedReader(path)) {
				int maxLength = 0;
				String maxLengthLine = null;

				String line;

				while ((line = br.readLine()) != null) {
					if (line.length() > maxLength) {
						maxLength = line.length();
						maxLengthLine = line;
					}
				}

				if (maxLengthLine == null) {
					return "File is empty";
				} else {
					return String.format("Max length: %d\nLine itself: %s", maxLength, maxLengthLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}, executor);
	}
}
