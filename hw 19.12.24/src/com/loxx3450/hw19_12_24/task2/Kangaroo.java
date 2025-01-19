package com.loxx3450.hw19_12_24.task2;

public class Kangaroo extends Animal {

  private int maxBounce;

  public Kangaroo(String species, double weight, double height, int age, int maxBounce) {
    super(species, weight, height, age);
    this.maxBounce = maxBounce;
  }

  public int getMaxBounce() {
    return maxBounce;
  }

  public void setMaxBounce(int maxBounce) {
    this.maxBounce = maxBounce;
  }
}
