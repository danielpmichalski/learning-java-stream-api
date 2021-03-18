package pl.chlopkiroztropki.learning.java.api.streams.exercises.products;

import lombok.Getter;

@Getter
public class Product {

  private final String name;
  private final Double price;
  private final Category category;

  public Product(String name, Double price, Category category) {
    this.name = name;
    this.price = price;
    this.category = category;
  }
}
