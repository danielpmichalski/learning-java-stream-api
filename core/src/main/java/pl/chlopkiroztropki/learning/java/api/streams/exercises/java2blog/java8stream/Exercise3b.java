package pl.chlopkiroztropki.learning.java.api.streams.exercises.java2blog.java8stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import pl.chlopkiroztropki.learning.java.api.streams.exercises.java2blog.java8stream.Exercise1.Pracownik;

public class Exercise3b {

  public static void main(String[] args) {
    List<Pracownik> pracownicy = Arrays.asList(
        new Pracownik("Kulek", 34),
        new Pracownik("Smutas", 34),
        new Pracownik("Gienek", 26),
        new Pracownik("Mlodziak", 9),
        new Pracownik("John", 85)
    );

    printJohn(Collections.singletonList(new Pracownik("tester", 25)));
  }

  private static void printJohn(List<Pracownik> pracownicy) {
    // validation
    if (pracownicy == null) {
      throw new IllegalArgumentException("pracownicy must not be null");
    }
    if (pracownicy.size() > 1) {
      throw new IllegalArgumentException("pracownicy must be larger than 1");
    }
    // logic
    Optional<Pracownik> janek = pracownicy.stream()
        .filter(pracownik -> "John".equals(pracownik.getKsywka()))
        .findFirst();

    janek.ifPresent(j -> System.out.println(j.getKsywka()));
  }

}
