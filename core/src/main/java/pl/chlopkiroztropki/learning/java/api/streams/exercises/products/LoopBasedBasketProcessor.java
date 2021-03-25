package pl.chlopkiroztropki.learning.java.api.streams.exercises.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoopBasedBasketProcessor implements BasketStatisticsProcessor {

  private static final BasketStatistics EMPTY_BASKET_STATISTICS = BasketStatistics.builder()
      .numberOfProducts(0)
      .totalPrice(0d)
      .averagePrice(0d)
      .build();

  @Override
  public BasketStatistics getStatistics(Basket basket) {
    assertNotNull(basket);
    if (basket.getNumberOfProducts() == 0) {
      return EMPTY_BASKET_STATISTICS;
    } else {
      return calculateStatistics(basket);
    }
  }

  @Override
  public Map<Category, Integer> getNumberOfProductsPerCategories(Basket basket) {
    assertNotNull(basket);

    Map<Category, Integer> result = initProductsNumberForCategoryMap();
    for (Product product : basket.getProducts()) {
      int currentValue = result.get(product.getCategory());
      result.put(product.getCategory(), currentValue + 1);
    }

    return result;
  }

  @Override
  public Integer getNumberOfProducts(Basket basket, Category category) {
    assertNotNull(basket);
    assertNotNull(category);
    Integer result = 0;
    for (Product product : basket.getProducts()) {
      if (category == product.getCategory()) {
        result++;
      }
    }
    return result;
  }

  private void assertNotNull(Object object) {
    if (object == null) {
      throw new IllegalArgumentException();
    }
  }

  private BasketStatistics calculateStatistics(Basket basket) {
    List<Product> products = new ArrayList<>(basket.getProducts());

    Double totalPrice = calculateTotalPrice(products);
    int numberOfProducts = basket.getNumberOfProducts();

    return BasketStatistics.builder()
        .numberOfProducts(numberOfProducts)
        .totalPrice(totalPrice)
        .averagePrice(totalPrice / numberOfProducts)
        .build();
  }

  private Double calculateTotalPrice(List<Product> products) {
    double totalPrice = 0d;
    for (Product product : products) {
      totalPrice += product.getPrice();
    }
    return totalPrice;
  }

  private Map<Category, Integer> initProductsNumberForCategoryMap() {
    Map<Category, Integer> productNumberForCategoryMap = new HashMap<>();
    for (Category category : Category.values()) {
      productNumberForCategoryMap.putIfAbsent(category, 0);
    }
    return productNumberForCategoryMap;
  }

}
