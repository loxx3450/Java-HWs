package com.loxx3450.hw19_12_24.task3;

public class Product {
  private String description;
  private Money price;

  public Product(String description, Money price) {
    this.description = description;
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Money getPrice() {
    return price;
  }

  public void IncreasePrice(float value)
  {
    var newWholePart = price.getWholePart() + (int)(value / 1);
    var newFractionalPart = price.getFractionalPart() + (int)(value % 1 * 100);

    price.setWholePart(newWholePart);
    price.setFractionalPart(newFractionalPart);
  }

  public void DecreasePrice(float value)
  {
    var newWholePart = price.getWholePart() - (int)(value / 1);
    var newFractionalPart = price.getFractionalPart() - (int)(value % 1 * 100);

    price.setWholePart(newWholePart);
    price.setFractionalPart(newFractionalPart);
  }
}
