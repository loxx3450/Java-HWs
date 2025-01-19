package com.loxx3450.hw19_12_24.task4;

public class Microware extends Device {

  private int maxTemp;

  public Microware(int maxTemp) {
    super(Microware.class.getSimpleName());
    this.maxTemp = maxTemp;
  }

  public int getMaxTemp() {
    return maxTemp;
  }

  public void setMaxTemp(int maxTemp) {
    this.maxTemp = maxTemp;
  }

  @Override
  public void sound() {
    System.out.println("Beep... Humming... Ding!");
  }

  @Override
  public void desc() {
    System.out.println("Microware: " + this.getName() + " " + this.maxTemp);
  }
}
