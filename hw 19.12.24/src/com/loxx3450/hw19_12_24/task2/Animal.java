package com.loxx3450.hw19_12_24.task2;

public class Animal {
  private String species;
  private double weight;
  private double height;
  private int age;

  public Animal(String species, double weight, double height, int age) {
    this.species = species;
    this.weight = weight;
    this.height = height;
    this.age = age;
  }

  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String toString() {
    return "Animal{species='" + species + "', weight=" + weight + ", height=" + height + ", age=" + age + "}";
  }
}
