package pl.chlopkiroztropki.learning.java.api.streams.exercises.java2blog.java8stream;

import java.util.Arrays;
import java.util.List;
import pl.chlopkiroztropki.learning.java.api.streams.exercises.java2blog.java8stream.Exercise1.Pracownik;

public class Exercise2 {

  public static void main(String[] args) {
    List<Pracownik> pracownicy = Arrays.asList(
        new Pracownik("Kulek", 34),
        new Pracownik("Smutas", 34),
        new Pracownik("Gienek", 26),
        new Pracownik("Mlodziak", 9)
    );

    long mlodsi25 = pracownicy.stream()
        .filter(pracownik -> pracownik.getWiek() > 25)
        .count();
    System.out.println(mlodsi25);
  }
}
