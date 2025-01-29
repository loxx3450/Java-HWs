package com.loxx3450.hw_30_01_25_concurrency.task4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DirectoryParser {
	public static CompletableFuture<Void> findAllFilesWithWord(Path sourceDirectory, Path resultFile, String word) {
		return CompletableFuture.runAsync(() -> {
			if (!Files.exists(sourceDirectory) || !Files.isDirectory(sourceDirectory)) {
				throw new RuntimeException("Source directory does not exist");
			}

			try (var stream = Files.walk(sourceDirectory)) {
				ensureFileIsCreated(resultFile);

				var files = stream.filter(Files::isRegularFile);

				files.forEach(path -> {
					if (containsWord(path, word)) {
						appendFileContent(path, resultFile);
					}
				});
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private static boolean containsWord(Path filePath, String word) {
		try (var lines = Files.lines(filePath)) {
			return lines.anyMatch(line -> line.toLowerCase().contains(word.toLowerCase()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void appendFileContent(Path source, Path target) {
		try (BufferedReader br = Files.newBufferedReader(source);
			 BufferedWriter bw = Files.newBufferedWriter(target, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

			String line;
			while ((line = br.readLine()) != null) {
				bw.write(line);
				bw.newLine();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static CompletableFuture<Void> cutAllBannedWords(Path resultFile, Path bannedWordsFile) {
		return CompletableFuture.runAsync(() -> {
			try {
				ensureFileIsCreated(resultFile);

				List<String> banWords = parseWords(bannedWordsFile);

				List<String> lines = Files.readAllLines(resultFile);

				List<String> filteredLines = lines.stream()
					.map(line -> removeWords(line, banWords))
					.toList();

				Files.write(resultFile, filteredLines, StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private static List<String> parseWords(Path filePath) {
		try (BufferedReader br = Files.newBufferedReader(filePath)) {
			List<String> words = new ArrayList<>();

			String line;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					words.add(line);
				}
			}

			return words;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String removeWords(String line, List<String> bannedWords) {
		for (String word : bannedWords) {
			line = line.replaceAll("(?i)" + word, "");
		}
		return line.trim();
	}

	private static void ensureFileIsCreated(Path file) throws IOException {
		if (!Files.exists(file)) {
			Files.createFile(file);
		}
	}
}
