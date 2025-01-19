package com.loxx3450.hw19_12_24.task1;

public class Sailor extends Human {
  private String rank;
  private String shipName;

  public Sailor(String name, int age, double height, double weight, String rank, String shipName) {
    super(name, age, height, weight);
    this.rank = rank;
    this.shipName = shipName;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getShipName() {
    return shipName;
  }

  public void setShipName(String shipName) {
    this.shipName = shipName;
  }
}
