package com.loxx3450.hw19_12_24.task3;

public class Money {
  private int wholePart;
  private int fractionalPart;
  private Currency currency;

  public Money(int wholePart, int fractionalPart, Currency currency) {
    this.wholePart = wholePart;
    this.fractionalPart = fractionalPart;
    this.currency = currency;
  }

  public int getWholePart() {
    return wholePart;
  }

  public void setWholePart(int wholePart) {
    this.wholePart = wholePart;
  }

  public int getFractionalPart() {
    return fractionalPart;
  }

  public void setFractionalPart(int fractionalPart) {
    this.fractionalPart = fractionalPart;

    if (this.fractionalPart > 99) {
      this.fractionalPart -= 100;
      this.wholePart++;
    }
    else if (this.fractionalPart < 0) {
      this.fractionalPart += 100;
      this.wholePart--;
    }
  }

  @Override
  public String toString() {
    return String.format("%.2f %s", wholePart + (fractionalPart / 100.0f), currency.toString());
  }
}
