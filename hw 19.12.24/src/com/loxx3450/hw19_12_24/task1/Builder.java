package com.loxx3450.hw19_12_24.task1;

public class Builder extends Human {
  private String specialty;
  private int yearsOfExperience;

  public Builder(String name, int age, double height, double weight, String specialty, int yearsOfExperience) {
    super(name, age, height, weight);
    this.specialty = specialty;
    this.yearsOfExperience = yearsOfExperience;
  }

  public String getSpecialty() {
    return specialty;
  }

  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }

  public int getYearsOfExperience() {
    return yearsOfExperience;
  }

  public void setYearsOfExperience(int yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience;
  }
}