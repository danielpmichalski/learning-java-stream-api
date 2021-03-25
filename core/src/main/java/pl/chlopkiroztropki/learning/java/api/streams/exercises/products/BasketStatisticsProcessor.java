package pl.chlopkiroztropki.learning.java.api.streams.exercises.products;

import java.util.Map;

public interface BasketStatisticsProcessor {

  BasketStatistics getStatistics(Basket basket);

  Map<Category, Integer> getNumberOfProductsPerCategories(Basket basket);

  /**
   * Returns a number of products per category.
   */
  Integer getNumberOfProducts(Basket basket, Category category);

}
