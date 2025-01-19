package com.loxx3450.hw19_12_24.task1;

public class Pilot extends Human {
  private String licenseNumber;
  private int flightHours;

  public Pilot(String name, int age, double height, double weight, String licenseNumber, int flightHours) {
    super(name, age, height, weight);
    this.licenseNumber = licenseNumber;
    this.flightHours = flightHours;
  }

  public String getLicenseNumber() {
    return licenseNumber;
  }

  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  public int getFlightHours() {
    return flightHours;
  }

  public void setFlightHours(int flightHours) {
    this.flightHours = flightHours;
  }
}