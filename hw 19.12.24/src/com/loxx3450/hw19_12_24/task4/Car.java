package com.loxx3450.hw19_12_24.task4;

public class Car extends Device{

  private int yearOfCreation;

  public Car(int yearOfCreation) {
    super(Car.class.getSimpleName());
    this.yearOfCreation = yearOfCreation;
  }

  public int getYearOfCreation() {
    return yearOfCreation;
  }

  public void setYearOfCreation(int yearOfCreation) {
    this.yearOfCreation = yearOfCreation;
  }

  @Override
  public void sound() {
    System.out.println("Vroom... Honk... Screech...");
  }

  @Override
  public void desc() {
    System.out.println("Car: " + this.getName() + " " + this.yearOfCreation);
  }
}
