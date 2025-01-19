package com.loxx3450.hw19_12_24.task2;

public class Crocodile extends Animal {

  private int teethCount;

  public Crocodile(String species, double weight, double height, int age, int teethCount) {
    super(species, weight, height, age);
    this.teethCount = teethCount;
  }

  public int getTeethCount() {
    return teethCount;
  }

  public void setTeethCount(int teethCount) {
    this.teethCount = teethCount;
  }
}
