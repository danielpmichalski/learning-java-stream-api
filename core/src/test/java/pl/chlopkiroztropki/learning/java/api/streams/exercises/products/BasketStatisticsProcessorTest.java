package pl.chlopkiroztropki.learning.java.api.streams.exercises.products;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class BasketStatisticsProcessorTest {

  private static final Basket EMPTY_BASKET = new Basket();

  private final Product princepolo = new Product("Princepolo", 2.99, Category.SLODYCZE);
  private final Product grzeski = new Product("Grześki", 3.49, Category.SLODYCZE);
  private final Product broadSword = new Product("Broadsword", 10d, Category.BRONIE);
  private final Basket SINGLE_PRODUCT_BASKET = new Basket(princepolo);

  private final BasketStatisticsProcessor processor = new LoopBasedBasketProcessor();

  @Test
  void returnsZeroNumberOfProductsForAllCategoriesForEmptyBasket() {
    // when
    Map<Category, Integer> numberOfProductsPerCategories =
        processor.getNumberOfProductsPerCategories(EMPTY_BASKET);
    // then
    SoftAssertions assertions = new SoftAssertions();
    Stream.of(Category.values()).forEach(cat ->
        assertions.assertThat(numberOfProductsPerCategories.get(cat)).isEqualTo(0)
    );
    assertions.assertAll();
  }

  @Test
  void throwsExceptionWhenGettingNumberOfProductsForNullBasket() {
    assertThrows(
        IllegalArgumentException.class,
        () -> processor.getNumberOfProducts(null, Category.SLODYCZE)
    );
  }

  @Test
  void throwsExceptionWhenGettingNumberOfProductsForEmptyBasketAndNullCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> processor.getNumberOfProducts(EMPTY_BASKET, null)
    );
  }

  @Test
  void returnsZeroNumberOfProductsForEmptyBasketAndSomeCategory() {
    Assertions.assertThat(
        processor.getNumberOfProducts(EMPTY_BASKET, Category.SLODYCZE)
    ).isEqualTo(0);
  }

  @Test
  void returnsOneProductForSingleProductBasketAndSomeCategory() {
    Assertions.assertThat(
        processor.getNumberOfProducts(SINGLE_PRODUCT_BASKET, Category.SLODYCZE)
    ).isEqualTo(1);
  }

  @Test
  void returnsTwoProductsForTwoProductsOfSameCategoryBasketAndTheCategoryOfThoseProducts() {
    // given
    Basket basket = new Basket(princepolo, grzeski);
    // when
    Integer result = processor.getNumberOfProducts(basket, Category.SLODYCZE);
    // then
    Assertions.assertThat(result).isEqualTo(2);
  }

  @Test
  void returnsOneForNumberOfProductsForCategoryOfProductForSingleProductBasket() {
    // when
    Map<Category, Integer> numberOfProductsPerCategories =
        processor.getNumberOfProductsPerCategories(SINGLE_PRODUCT_BASKET);
    // then
    Assertions.assertThat(numberOfProductsPerCategories.get(Category.SLODYCZE)).isEqualTo(1);
  }

  @Test
  void returnsZeroAveragePriceForEmptyBasket() {
    // when
    BasketStatistics statistics = processor.getStatistics(EMPTY_BASKET);
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
    // when
    BasketStatistics statistics = processor.getStatistics(EMPTY_BASKET);
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
    // when
    BasketStatistics statistics = processor.getStatistics(EMPTY_BASKET);
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
    return processor.getStatistics(SINGLE_PRODUCT_BASKET);
  }

  private BasketStatistics getTwoProductsBasketStatistics() {
    return processor.getStatistics(new Basket(princepolo, broadSword));
  }

}
