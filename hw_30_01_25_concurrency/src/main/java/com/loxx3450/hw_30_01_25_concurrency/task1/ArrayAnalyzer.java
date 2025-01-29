package com.loxx3450.hw_30_01_25_concurrency.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class ArrayAnalyzer {
	private final List<Integer> list;

	public ArrayAnalyzer() {
		list = new ArrayList<>();
	}

	public CompletableFuture<List<Integer>> fillArrayWithRandomNumbers(int length, int min, int max) {
		return CompletableFuture.supplyAsync(() -> {
			Random random = new Random();

			for (int i = 0; i < length; i++) {
				list.add(random.nextInt(max - min + 1) + min);
			}

			return list;
		});
	}

	public CompletableFuture<Integer> findSumOfNumbers() {
		return CompletableFuture.supplyAsync(() -> {
			if (list.isEmpty())
				return 0;

			return list.stream()
				.mapToInt(Integer::intValue)
				.sum();
		});
	}

	public CompletableFuture<Float> findAvgNumber() {
		return CompletableFuture.supplyAsync(() -> {
			if (list.isEmpty())
				return 0f;

			return (float)list.stream()
				.mapToInt(Integer::intValue)
				.average()
				.orElse(0f);
		});
	}
}
