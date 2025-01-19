package com.loxx3450.hw19_12_24.task67;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Array implements IMath, ISort {
  private int[] array;

  public Array(int[] array) {
    this.array = array;
  }

  public Array(int size) {
    this.array = new int[size];

    FillArrayWithRandomValues();
  }

  private void FillArrayWithRandomValues() {
    Random rand = new Random();

    for (int i = 0; i < array.length; i++) {
      array[i] = rand.nextInt(100);
    }
  }

  @Override
  public int max() {
    return Arrays.stream(array).max().getAsInt();
  }

  @Override
  public int min() {
    return Arrays.stream(array).min().getAsInt();
  }

  @Override
  public float avg() {
    return (float)Arrays.stream(array).average().getAsDouble();
  }

  @Override
  public void sortAsc() {
    Arrays.sort(array);
  }

  @Override
  public void sortDesc() {
    Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
    Arrays.sort(boxedArray, Collections.reverseOrder());

    array = Arrays.stream(boxedArray).mapToInt(Integer::intValue).toArray();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < array.length; i++) {
      builder.append(array[i] + " ");
    }

    return builder.toString();
  }
}
