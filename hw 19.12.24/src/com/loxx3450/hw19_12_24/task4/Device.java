package com.loxx3450.hw19_12_24.task4;

public abstract class Device {
  private String name;


  protected Device(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public abstract void sound();

  public void show() {
    System.out.println(this.name);
  }

  public abstract void desc();
}
