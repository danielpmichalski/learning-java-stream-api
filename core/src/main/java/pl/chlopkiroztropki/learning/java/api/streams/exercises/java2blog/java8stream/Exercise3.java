package pl.chlopkiroztropki.learning.java.api.streams.exercises.java2blog.java8stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import pl.chlopkiroztropki.learning.java.api.streams.exercises.java2blog.java8stream.Exercise1.Pracownik;

public class Exercise3 {

  public static void main(String[] args) {
    List<Pracownik> pracownicy = Arrays.asList(
        new Pracownik("Kulek", 34),
        new Pracownik("Smutas", 34),
        new Pracownik("Gienek", 26),
        new Pracownik("Mlodziak", 9),
        new Pracownik("John", 85)
    );

    printJohn(pracownicy);
  }

  private static void printJohn(List<Pracownik> pracownicy) {
    Optional<Pracownik> janek = pracownicy.stream()
        .filter(pracownik -> "John".equals(pracownik.getKsywka()))
        .findFirst();

    janek.ifPresent(j -> System.out.println(j.getKsywka()));
  }

}
