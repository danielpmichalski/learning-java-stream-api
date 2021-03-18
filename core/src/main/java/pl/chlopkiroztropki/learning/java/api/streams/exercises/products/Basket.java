package pl.chlopkiroztropki.learning.java.api.streams.exercises.products;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Basket {

  private final Collection<Product> products = new LinkedList<>();

  public Basket(Product... products) {
    this.products.addAll(Arrays.asList(products));
  }

  public void addProduct(Product product) {
    products.add(product);
  }

  public void removeProduct(Product product) {
    products.remove(product);
  }

  public void clearBasket() {
    products.clear();
  }

  /**
   * Returns an unmodifiable view of the basket.
   */
  public Collection<Product> getProducts() {
    return Collections.unmodifiableCollection(products);
  }

  public int getNumberOfProducts() {
    return products.size();
  }

}
