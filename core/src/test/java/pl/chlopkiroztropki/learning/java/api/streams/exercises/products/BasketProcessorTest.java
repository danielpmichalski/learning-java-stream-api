package pl.chlopkiroztropki.learning.java.api.streams.exercises.products;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class BasketProcessorTest {

  private final Product princepolo = new Product("Princepolo", 2.99, Category.SLODYCZE);
  private final Product broadSword = new Product("Broadsword", 10d, Category.BRONIE);

  private final BasketProcessor processor = new LoopBasedBasketProcessor();

  @Test
  void returnsZeroNumberOfProductsForAllCategoriesForEmptyBasket() {
    // given
    Basket emptyBasket = new Basket();
    // when
    BasketStatistics statistics = processor.getStatistics(emptyBasket);
    // then
    SoftAssertions assertions = new SoftAssertions();
    Stream.of(Category.values()).forEach(cat ->
        assertions.assertThat(
            statistics.getNumberOfProductsPerCategory(cat)
        ).isEqualTo(0)
    );
    assertions.assertAll();
  }

  @Test
  void returnsOneForNumberOfProductsForCategoryOfProductForSingleProductBasket() {
    BasketStatistics statistics = getSingleProductBasketStatistics();
    Assertions.assertThat(statistics.getNumberOfProductsPerCategories().get(Category.SLODYCZE))
        .isEqualTo(1);
  }

  @Test
  void returnsZeroAveragePriceForEmptyBasket() {
    // given
    Basket emptyBasket = new Basket();
    // when
    BasketStatistics statistics = processor.getStatistics(emptyBasket);
    // then
    Assertions.assertThat(statistics.getAveragePrice()).isEqualTo(0.0);
  }

  @Test
  void returnsAveragePriceForSingleProductBasket() {
    BasketStatistics statistics = getSingleProductBasketStatistics();
    Assertions.assertThat(statistics.getAveragePrice()).isEqualTo(2.99);
  }

  @Test
  void returnsAveragePriceForTwoProductsBasket() {
    BasketStatistics statistics = getTwoProductsBasketStatistics();
    Assertions.assertThat(statistics.getAveragePrice()).isEqualTo(6.495);
  }

  @Test
  void returnsZeroTotalPriceForEmptyBasket() {
    // given
    Basket emptyBasket = new Basket();
    // when
    BasketStatistics statistics = processor.getStatistics(emptyBasket);
    // then
    Assertions.assertThat(statistics.getTotalPrice()).isEqualTo(0.0);
  }

  @Test
  void returnsTotalPriceForSingleProductBasket() {
    BasketStatistics statistics = getSingleProductBasketStatistics();
    Assertions.assertThat(statistics.getTotalPrice()).isEqualTo(2.99);
  }

  @Test
  void returnsTotalPriceForTwoProductsBasket() {
    BasketStatistics statistics = getTwoProductsBasketStatistics();
    Assertions.assertThat(statistics.getTotalPrice()).isEqualTo(12.99);
  }

  @Test
  void returnsZeroProductsForEmptyBasket() {
    // given
    Basket emptyBasket = new Basket();
    // when
    BasketStatistics statistics = processor.getStatistics(emptyBasket);
    // then
    Assertions.assertThat(statistics.getNumberOfProducts()).isEqualTo(0);
  }

  @Test
  void returnsOneProductForSingleProductBasket() {
    BasketStatistics statistics = getSingleProductBasketStatistics();
    Assertions.assertThat(statistics.getNumberOfProducts()).isEqualTo(1);
  }

  @Test
  void returnsTwoProductForTwoProductBasket() {
    BasketStatistics statistics = getTwoProductsBasketStatistics();
    Assertions.assertThat(statistics.getNumberOfProducts()).isEqualTo(2);
  }

  @Test
  void throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> processor.getStatistics(null));
  }

  private BasketStatistics getSingleProductBasketStatistics() {
    return processor.getStatistics(new Basket(princepolo));
  }

  private BasketStatistics getTwoProductsBasketStatistics() {
    return processor.getStatistics(new Basket(princepolo, broadSword));
  }

}
