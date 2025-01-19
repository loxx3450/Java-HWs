package com.loxx3450.hw_23_01_25.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntegersListAnalyzer {
	private final List<Integer> list;

	public IntegersListAnalyzer(List<Integer> list) {
		this.list = list;
	}

	public IntegersListAnalyzer(int count, int min, int max) {
		this.list = generateListOfIntegers(count, min, max);
	}

	public long getPositiveNumbersCount() {
		return list.stream()
			.filter(i -> i > 0)
			.count();
	}

	public long getNegativeNumbersCount() {
		return list.stream()
			.filter(i -> i < 0)
			.count();
	}

	public long getTwoDigitNumbersCount() {
		return list.stream()
			.filter(i -> Math.abs(i) >= 10 && Math.abs(i) <= 99)
			.count();
	}

	public long getMirrorNumbersCount() {
		return list.stream()
			.filter(IntegersListAnalyzer::isMirrorNumber)
			.count();
	}

	public void printSortedList() {
		list.stream().sorted().forEach(i -> System.out.print(i + " "));
		System.out.println();
	}

	private static boolean isMirrorNumber(Integer number) {
		String str = number.toString();

		for (int j = 0; j < str.length(); j++) {
			if (str.charAt(j) != str.charAt(str.length() - j - 1)) {
				return false;
			}
		}

		return true;
	}

	private static List<Integer> generateListOfIntegers(int count, int min, int max) {
		List<Integer> list = new ArrayList<Integer>();

		Random random = new Random();

		for (int i = 0; i < count; i++) {
			list.add(random.nextInt(min, max + 1));
		}

		return list;
	}
}
