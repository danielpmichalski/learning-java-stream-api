package pl.chlopkiroztropki.learning.java.api.streams;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import sun.misc.IOUtils;

public class RegexPatternStreamOperations {

  public void calculate() throws IOException {

    Pattern pattern = Pattern.compile("abc0+");

    InputStream textFile = Main.class.getClassLoader().getResourceAsStream("text.txt");
    byte[] content = IOUtils.readFully(textFile, textFile.available(), true);
    String string = new String(content);

    int limit = 10000;
    LocalDateTime startTime = LocalDateTime.now();
    List<String> result = Stream.of(pattern.split(string))
        .limit(limit)
        .collect(Collectors.toList());
    LocalDateTime endTime = LocalDateTime.now();
    System.out.println(startTime.until(endTime, ChronoUnit.NANOS));

    startTime = LocalDateTime.now();
    List<String> result2 = pattern.splitAsStream(string)
        .limit(1000000)
        .collect(Collectors.toList());
    endTime = LocalDateTime.now();
    System.out.println(startTime.until(endTime, ChronoUnit.NANOS));
  }
}
