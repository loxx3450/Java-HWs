package com.loxx3450.hw_30_01_25_concurrency.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class DirectoryForwarder {
	public static CompletableFuture<Void> moveDirectory(Path source, Path target, ExecutorService executor) {
		return CompletableFuture.runAsync(() -> {
			ensureDirectoriesExist(source, target);

			try (var stream = Files.walk(source)) {
				List<Path> paths = stream.toList();

				List<CompletableFuture<Void>> fileCopyFutures = new ArrayList<>();

				moveAllDirectories(paths, source, target);
				moveAllFiles(fileCopyFutures, paths, source, target, executor);

				// waiting for all files to be moved
				CompletableFuture.allOf(fileCopyFutures.toArray(new CompletableFuture[0])).join();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}, executor);
	}

	private static void ensureDirectoriesExist(Path source, Path target) {
		if (!Files.exists(source) || !Files.isDirectory(source)) {
			throw new RuntimeException("Source path does not exist or is not a directory");
		}
		if (!Files.exists(target)) {
			try {
				Files.createDirectories(target);
			} catch (IOException e) {
				throw new RuntimeException("Failed to create target directory");
			}
		}
	}

	private static void moveAllDirectories(List<Path> paths, Path source, Path target) {
		paths.stream()
			.filter(Files::isDirectory)
			.forEach(sourcePath -> {
				try {
					Path targetPath = target.resolve(source.relativize(sourcePath));
					Files.createDirectories(targetPath);

					System.out.printf("Directory %s is moved\n", sourcePath);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
	}

	private static void moveAllFiles(
		List<CompletableFuture<Void>> fileCopyFutures,
		List<Path> paths,
		Path source,
		Path target,
		ExecutorService executor)
	{
		paths.stream()
			.filter(Files::isRegularFile)
			.forEach(sourcePath -> {
				fileCopyFutures.add(CompletableFuture.runAsync(() -> {
					try {
						Path targetPath = target.resolve(source.relativize(sourcePath));
						Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

						System.out.printf("File %s is moved\n", sourcePath);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}, executor));
			});
	}
}
