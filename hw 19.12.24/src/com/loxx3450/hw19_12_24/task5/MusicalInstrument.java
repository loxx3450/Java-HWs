package com.loxx3450.hw19_12_24.task5;

abstract class MusicalInstrument {
  private String name;

  public MusicalInstrument(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract void sound();

  public void show() {
    System.out.println(name);
  }

  public abstract void desc();

  public abstract void history();
}