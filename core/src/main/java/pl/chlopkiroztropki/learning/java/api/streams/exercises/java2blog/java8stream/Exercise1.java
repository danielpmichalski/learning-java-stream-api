package pl.chlopkiroztropki.learning.java.api.streams.exercises.java2blog.java8stream;

import java.util.Arrays;
import java.util.List;

public class Exercise1 {

  public static void main(String[] args) {
    List<Pracownik> pracownicy = Arrays.asList(
        new Pracownik("Kulek", 34),
        new Pracownik("Smutas", 34),
        new Pracownik("Gienek", 26)
    );

    pracownicy.stream()
        .filter(pracownik -> pracownik.getWiek() > 30)
        .map(Pracownik::getKsywka)
        .forEach(System.out::println);
  }

  static class Pracownik {

    String ksywka;
    int wiek;

    public Pracownik(String ksywka, int wiek) {
      this.ksywka = ksywka;
      this.wiek = wiek;
    }

    public String getKsywka() {
      return ksywka;
    }

    public int getWiek() {
      return wiek;
    }

    @Override
    public String toString() {
      return "Pracownik{" +
          "ksywka='" + ksywka + '\'' +
          ", wiek=" + wiek +
          '}';
    }
  }
}
