package pl.chlopkiroztropki.learning.java.api.streams.exercises.products;


import java.util.Collection;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class BasketTest {

  @Test
  void shouldAddProduct() {
    // given
    Basket basket = new Basket();
    Product product = new Product("Ciasteczka", 10d, Category.SLODYCZE);

    // when
    basket.addProduct(product);

    // then
    Assertions.assertThat(basket.getNumberOfProducts()).isEqualTo(1);
  }

  @Test
  void shouldRemoveProduct() {
    // given
    Basket basket = new Basket();
    Product product = new Product("Trenabolon", 10d, Category.KOKSY);
    basket.addProduct(product);

    // when
    basket.removeProduct(product);

    // then
    Assertions.assertThat(basket.getNumberOfProducts()).isEqualTo(0);
  }

  @Test
  void shouldRetrieveProducts() {
    // given
    Basket basket = new Basket();
    Product product = new Product("Trenabolon", 10d, Category.KOKSY);
    Product anotherProduct = new Product("Broadsword", 10d, Category.BRONIE);
    basket.addProduct(product);
    basket.addProduct(anotherProduct);

    // when
    Collection<Product> basketContent = basket.getProducts();

    // then
    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(basketContent).containsOnly(product, anotherProduct);
    softAssertions.assertAll();
  }

}
