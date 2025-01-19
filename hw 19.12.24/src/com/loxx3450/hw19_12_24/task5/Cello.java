package com.loxx3450.hw19_12_24.task5;

public class Cello extends MusicalInstrument {
  private int whatever;

  public Cello(int whatever) {
    super(Cello.class.getSimpleName());
    this.whatever = whatever;
  }

  public int getWhatever() {
    return whatever;
  }

  public void setWhatever(int whatever) {
    this.whatever = whatever;
  }

  @Override
  public void sound() {
    System.out.println("[sound of cello...]");
  }

  @Override
  public void desc() {
    System.out.println("Cello: " + this.getName() + " " + this.whatever);
  }

  @Override
  public void history() {
    System.out.println("bla bla bla a long time ago bla bla bla");
  }
}