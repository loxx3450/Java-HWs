package com.loxx3450.hw19_12_24.task4;

public class Teamaker extends Device {

  private int consum;

  public Teamaker(int consum) {
    super(Teamaker.class.getSimpleName());
    this.consum = consum;
  }

  public int getConsum() {
    return consum;
  }

  public void setConsum(int consum) {
    this.consum = consum;
  }

  @Override
  public void sound() {
    System.out.println("Whistle... Bubbling... Pouring...");
  }

  @Override
  public void desc() {
    System.out.println("Teamaker: " + this.getName() + " " + this.consum);
  }
}
