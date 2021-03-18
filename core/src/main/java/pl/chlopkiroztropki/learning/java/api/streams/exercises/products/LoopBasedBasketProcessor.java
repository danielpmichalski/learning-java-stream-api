package pl.chlopkiroztropki.learning.java.api.streams.exercises.products;

import java.util.ArrayList;
import java.util.List;

public class LoopBasedBasketProcessor implements BasketProcessor {

  private static final BasketStatistics EMPTY_BASKET_STATISTICS = BasketStatistics.builder()
      .numberOfProducts(0)
      .totalPrice(0d)
      .averagePrice(0d)
      .build();

  @Override
  public BasketStatistics getStatistics(Basket basket) {
    if (basket == null) {
      throw new IllegalArgumentException();
    } else if (basket.getNumberOfProducts() == 0) {
      return EMPTY_BASKET_STATISTICS;
    } else {
      return calculateStatistics(basket);
    }
  }

  private BasketStatistics calculateStatistics(Basket basket) {
    List<Product> products = new ArrayList<>(basket.getProducts());

    Double totalPrice = calculateTotalPrice(products);
    int numberOfProducts = basket.getNumberOfProducts();

    BasketStatistics statistics = BasketStatistics.builder()
        .numberOfProducts(numberOfProducts)
        .totalPrice(totalPrice)
        .averagePrice(totalPrice / numberOfProducts)
        .build();

    Product firstProduct = products.get(0);
    statistics.setNumberOfProductsForCategory(firstProduct.getCategory(), 1);

    return statistics;
  }

  private Double calculateTotalPrice(List<Product> products) {
    double totalPrice = 0d;
    for (Product product : products) {
      totalPrice += product.getPrice();
    }
    return totalPrice;
  }

}
