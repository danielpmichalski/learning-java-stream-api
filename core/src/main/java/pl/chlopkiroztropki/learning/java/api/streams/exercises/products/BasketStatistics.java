package pl.chlopkiroztropki.learning.java.api.streams.exercises.products;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BasketStatistics {

  // liczba produktów
  private final int numberOfProducts;
  // liczba produktów per kategoria
  // cena całkowita
  private final Double totalPrice;
  // średnia cena
  private final Double averagePrice;

  private Map<Category, Integer> initMap() {
    Map<Category, Integer> map = new HashMap<>();
    Stream.of(Category.values()).forEach(c -> map.put(c, 0));
    return map;
  }

  // później
  // cena całkowita per kategoria
  // średnia cena per kategoria
}
